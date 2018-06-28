package com.netposa;

import javax.annotation.PreDestroy;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
@Component
public class RunnerListener implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("我被被执行了、、、、、、");  
        
    }

    @PreDestroy  
    public void destory() {  
        System.out.println("我被销毁了、、、、、我是用的@PreDestory的方式、、、、、、");  
        System.out.println("我被销毁了、、、、、我是用的@PreDestory的方式、、、、、、");  
    }  
}
