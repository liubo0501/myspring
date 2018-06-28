package com.netposa.springsocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

@RestController
public class GreetingController {
    @Autowired
    private WebSocketService webSocketService;
//    @MessageMapping("hello/{message}")
//    @SendTo("/topic/greetings")
//    public Greeting greeting(@DestinationVariable("message") HelloMessage message) throws Exception {
//        Thread.sleep(1000); // simulated delay
//        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
//    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(String message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message) + "!");
    }
    
    @RequestMapping("/say/{word}")
    public String say(@PathVariable("word") String word){
        webSocketService.sendMsg(word);
        return word;
    }
    
    @RequestMapping("/sayOne/{word}/{userId}")
    public String sayOne(@PathVariable("word") String word,@PathVariable("userId") String userId){
        webSocketService.send2Users(userId, word);
        return word;
    }
}
