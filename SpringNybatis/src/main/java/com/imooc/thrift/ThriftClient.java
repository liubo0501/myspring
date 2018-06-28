package com.imooc.thrift;

import org.apache.thrift.transport.TSocket;

import com.imooc.thrift.ThriftService.Client;

/******************************************************************************** 
** Copyright(c) 2014-2017 湖南万为智能机器人技术有限公司 All Rights Reserved. 
** @author liubo@anbot.cn
** 日期： 2017/11/10
** 描述：过滤器
** 版本：v1.0
*********************************************************************************/
public class ThriftClient {
    
	/**
	 * socket通道
	 */
    TSocket transport;
    
    /**
     * 客户端
     */
    Client client;

    public TSocket getTransport() {
        return transport;
    }

    public void setTransport(TSocket transport) {
        this.transport = transport;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
