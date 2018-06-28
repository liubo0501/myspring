package com.imooc.controller;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imooc.mapper.UserMapper;
import com.imooc.model.User;

@RestController
public class UserController {
	
	@Autowired
	private UserMapper userMapper;
	
	@RequestMapping("/getUser")
	public User getUser(@RequestParam("userId") short userId,HttpServletRequest request){
		HttpSession session = request.getSession();
		session.setAttribute("aaa", 123);
		return userMapper.selectByPrimaryKey(userId);
	}

	@RequestMapping("/getUser1/{userId}")
	public User getUser1(@PathVariable short userId,HttpServletRequest request){
		HttpSession session = request.getSession();
		System.out.println(session.getAttribute("aaa1"));
		return userMapper.selectByPrimaryKey(userId);
	}
	
	@RequestMapping("/getAllName")
	public List<String> getAllName(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userName", "l");
		map.put("company", "万为");
		String[] ids = {"1","2","4","5"};
		map.put("userId", Stream.of(ids).map(x->"'" + x + "'").collect(Collectors.joining(",","(",")")));
		List<User> list = userMapper.getAll(map);
		return list.stream().filter(u->u.getCount()>1000).map(u->u.getUsername()).sorted(((u1,u2)->u2.compareTo(u1))).collect(Collectors.toList());
	}
	
	@RequestMapping("/getAll")
	public List<User> getAll(){
		List<User> list = userMapper.getAll(null);
		return list.stream().filter(u->u.getCount()>1000).sorted(Comparator.comparing(User::getManagerid).reversed().thenComparing(User::getName).reversed()).collect(Collectors.toList());
	}
}
