package com.imooc.controller;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imooc.mapper.InterestPointMapper;
import com.imooc.model.InterestPoint;

@RestController
public class PointController {
	
	@Autowired
	private InterestPointMapper pointMapper;
	@RequestMapping("getAllPoint")
	public List<InterestPoint> getAllPoint(){
		List<InterestPoint> list = pointMapper.getAll();
		list.stream().limit(4).sorted(Comparator.comparing(InterestPoint::getName)).forEach(u->{
			System.out.println(u.getInterestpointid());
		u.getActions().stream().forEach(m->{
			System.out.println(m.getMedia().getName());
		});});
		return list;
	}

}
