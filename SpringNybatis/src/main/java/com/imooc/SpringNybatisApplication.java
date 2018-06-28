package com.imooc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.I0Itec.zkclient.ZkClient;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;
//import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.imooc.thrift.ThriftService;
import com.imooc.thrift.ThriftServiceImpl;

@SpringBootApplication
//@MapperScan("com.imooc")
public class SpringNybatisApplication {

	public static void main(String[] args) {
	    ExecutorService executorService = Executors.newSingleThreadExecutor();  
        executorService.submit(SpringNybatisApplication::startThrift);
	    
		SpringApplication.run(SpringNybatisApplication.class, args);
	}
	
	public static void startThrift(){
	    String servicePath = "/ThriftServer" ;// 根节点路径

	       ZkClient zkClient = new ZkClient("192.168.0.85:2181");

	       boolean rootExists = zkClient.exists(servicePath);

	       if (!rootExists) {

	           zkClient.createPersistent(servicePath);

	       }

	       String ip = "192.168.0.42";
	       int port = 8082;
	       rootExists = zkClient.exists(servicePath + "/" + ip + ":" + port);

           if (rootExists) {

               zkClient.delete(servicePath + "/" + ip + ":" + port);

           }
	       // 注册当前服务
	       zkClient.createEphemeral(servicePath + "/" + ip + ":" + port);

	       System.out.println("提供的服务为：" + servicePath + "/" + ip + ":" + port);
	    try {
            TProcessor tprocessor = new ThriftService.Processor<ThriftService.Iface>(
                    new ThriftServiceImpl());

            TServerSocket serverTransport = new TServerSocket(port);
            TThreadPoolServer.Args ttpsArgs = new TThreadPoolServer.Args(
                    serverTransport);
            ttpsArgs.processor(tprocessor);
            ttpsArgs.protocolFactory(new TBinaryProtocol.Factory());

            // 线程池服务模型，使用标准的阻塞式IO，预先创建一组线程处理请求。
            TServer server = new TThreadPoolServer(ttpsArgs);
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
       
	}
}
