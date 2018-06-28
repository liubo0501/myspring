package com.imooc.thrift;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.imooc.thrift.ThriftService.Client;

/******************************************************************************** 
** Copyright(c) 2014-2017 湖南万为智能机器人技术有限公司 All Rights Reserved. 
** @author liubo@anbot.cn
** 日期：2017/11/10
** 描述：Thrift连接池
** 版本：v1.0
*********************************************************************************/
public class ThriftPoolFactoryUtil {
    
    /**
     *  日志
     */
    private static Logger logger = LoggerFactory.getLogger(ThriftPoolFactoryUtil.class);
    
    public static GenericObjectPool<ThriftClient> pool;
    
    static List<String> servers = new ArrayList<>();
    
    static {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);
        pool = new GenericObjectPool<ThriftClient>(new ConnectionFactory(), config);
        String path = "/ThriftServer";
        ZkClient zkClient = new ZkClient("192.168.0.85:2181", 60000, 1000);
        List<String> childs = zkClient.getChildren(path);
        servers.clear();
//        for(String p : childs) {  
//            servers.add(zkClient.readData(path + "/" + p));  
//        }  
        childs.stream().forEach(p -> servers.add(p));
        // 订阅节点变化事件
        zkClient.subscribeChildChanges(path, (p, c) -> {
            System.out.println(String.format("[ZookeeperRegistry] service list change: path=%s, currentChilds=%s", p, c.toString()));
            servers.clear();
//            c.stream().forEach(d -> servers.add(zkClient.readData(path + "/" + d)));
            c.stream().forEach(d -> servers.add(d));
            System.out.println("Servers: " + servers.toString());
            pool.clear();
        });
    }
    
    /**
     * 从池里获取一个Transport对象
     * @return
     */
    public static ThriftClient getConnection(){  
        try {
            return pool.borrowObject();
        } catch (Exception e) {
            logger.error("获取TTransport对象报错：{}",e.getMessage());
            e.printStackTrace();
        }
        return null;  
    }  
    
    /**
     * 把一个Transport对象归还到池里
     */
    public static void releaseConnection(ThriftClient transport) {  
        pool.returnObject(transport);   
    }
    
  
    public static void invalidateObject(ThriftClient transport) {  
         try {
            pool.invalidateObject(transport);
        } catch (Exception e) {
            logger.error("出错：{}",e.getMessage());
            e.printStackTrace();
        }
    }  
    
    /**
     * 连接池管理的对象Transport的工厂类，
     * GenericObjectPool会使用此类的create方法来
     * 创建Transport对象并放进pool里进行管理等操作。
     */
    static class ConnectionFactory extends BasePooledObjectFactory<ThriftClient> {   
//        private String ip = Constants.getProperties().getProperty("THRIFT_SERVER_IP"); 
//        private int port =Integer.parseInt(Constants.getProperties().getProperty("THRIFT_SERVER_PORT"));
//        private int socketTimeout =Integer.parseInt(Constants.getProperties().getProperty("THRIFT_SERVER_TIMEOUT"));
        private int socketTimeout =30000;
        
        //创建TTransport类型对象方法
        @Override
        public ThriftClient create() throws Exception {
           String ip_port = servers.get(new Random().nextInt(servers.size())); 
            TSocket transport = new TSocket(ip_port.split(":")[0], Integer.parseInt(ip_port.split(":")[1]),socketTimeout);
            System.out.println(ip_port);
            if (!transport.isOpen() ) {
                transport.open();
            }
            //使用二进制协议   
            TProtocol protocol = new TBinaryProtocol(transport);  
            //创建Client  
            Client client = new ThriftService.Client(protocol);  
            ThriftClient thriftClient = new ThriftClient();
            thriftClient.setClient(client);
            thriftClient.setTransport(transport);
            return thriftClient;
        }

        //把TTransport对象打包成池管理的对象PooledObject<ThriftClient>
        @Override
        public PooledObject<ThriftClient> wrap(ThriftClient transport) {
            return new DefaultPooledObject<ThriftClient>(transport);
        }

        @Override
        public boolean validateObject(PooledObject<ThriftClient> p) {
            ThriftClient  thriftClient = (ThriftClient) p.getObject();
            TSocket t = thriftClient.getTransport();
            Socket s = t.getSocket();
            boolean closed = s.isClosed();
            boolean connected = s.isConnected();
            boolean outputShutdown = s.isOutputShutdown();
            boolean inputShutdown = s.isInputShutdown();
            boolean connectFlg = true;
            Client client = thriftClient.getClient();  
            try {
                // 尝试连接
                client.httpRequest("conn");
            } catch (TException e1) {
                connectFlg = false;
                e1.printStackTrace();
            }
            if(!(connectFlg && connected && !closed && !inputShutdown && !outputShutdown && t.isOpen())){
                try {
                    pool.invalidateObject(thriftClient);
                } catch (Exception e) {
                    logger.error("出错：{}",e.getMessage());
                    e.printStackTrace();
                }
            }
            return connectFlg && connected && !closed && !inputShutdown && !outputShutdown && t.isOpen();
        }
  
    }   
}
