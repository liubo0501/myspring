package com.imooc.conrtoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imooc.bean.User;

@RestController
public class ServerController {
	
	@GetMapping("/get")
	public User get(){
		return new User("hello");
	}

	@GetMapping("/get1")
	public String get1(){
		return "hello";
	}
}
