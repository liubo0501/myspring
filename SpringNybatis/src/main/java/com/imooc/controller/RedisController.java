package com.imooc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@RestController
public class RedisController {
    
    @Autowired  
    private JedisPool jedisPool;  
    @RequestMapping("/setRedis")
    public void setRedis(){
        Jedis jedis = null;  
        try {  
            jedis = jedisPool.getResource();  
            jedis.set("12", "hello world");  
        } finally {  
            //返还到连接池  
            jedis.close();  
        }  
    }
    @RequestMapping("/getRedis")
    public String getRedis(){
        Jedis jedis = null;  
        try {  
            jedis = jedisPool.getResource();  
            return jedis.get("12");  
        } finally {  
            //返还到连接池  
            jedis.close();  
        }  
    }
    public String get(String key){  
  
        Jedis jedis = null;  
        try {  
            jedis = jedisPool.getResource();  
            return jedis.get(key);  
        } finally {  
            //返还到连接池  
            jedis.close();  
        }  
    }  

}
