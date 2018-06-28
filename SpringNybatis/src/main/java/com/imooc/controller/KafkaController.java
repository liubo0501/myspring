package com.imooc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {
	@Autowired
    private KafkaTemplate<String, String> template;

    @RequestMapping("/send")
    String send() {
    	String message = "hello world";
    	ListenableFuture<SendResult<String, String>> future = template.send("t1","a1" ,message );
        future.addCallback(o -> System.out.println("send-消息发送成功：" + message), throwable -> System.out.println("消息发送失败：" + message));
        return "success";
    }

    @KafkaListener(topics = "t1")
    public void listenT1(String message) throws Exception {
    	System.out.println(message);
    }

    @KafkaListener(topics = "t2")
    public void listenT2(String message) throws Exception {
    	System.out.println(message);
    }

}
