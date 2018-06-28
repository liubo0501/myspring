package com.imooc.controller;

import org.apache.thrift.TException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imooc.thrift.ThriftClient;
import com.imooc.thrift.ThriftPoolFactoryUtil;
import com.imooc.thrift.ThriftService.Client;

@RestController
public class ThriftController {
@RequestMapping("/getThrift")
    public String getThrift(){
        ThriftClient thriftClient = ThriftPoolFactoryUtil.getConnection();
        Client client = thriftClient.getClient();  
        try {
            return client.httpRequest("conn");
        } catch (TException e) {
            e.printStackTrace();
        }finally{
            ThriftPoolFactoryUtil.releaseConnection(thriftClient);
        }
        return null;
    }
}
