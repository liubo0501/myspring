package com.imooc.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Component
public class RedisConfig{
	private final Logger logger = LoggerFactory.getLogger(RedisConfig.class);  
	
	 @Value("${spring.redis.host}")
    private String host;
	 @Value("${spring.redis.port}")
    private Integer port;
    
    @Value("${spring.redis.pool.max-wait}")
    private Integer maxTotal;
    @Value("${spring.redis.pool.max-idle}")
    private Integer maxIdle;
    @Value("${spring.redis.pool.max-wait}")
    private Integer maxWaitMillis;
    
    @Bean(name= "jedis.pool")
    @Autowired  
    public JedisPool jedisPool() {  
    	logger.info("maxTotal:" + maxTotal); 
    	logger.info("maxIdle:" + maxIdle); 
    	logger.info("maxWaitMillis:" + maxWaitMillis); 
    	 JedisPoolConfig config = new JedisPoolConfig();  
         config.setMaxTotal(maxTotal);  
         config.setMaxIdle(maxIdle);
         config.setMaxWaitMillis(maxWaitMillis);  
        return new JedisPool(config, host, port);  
    }  
}