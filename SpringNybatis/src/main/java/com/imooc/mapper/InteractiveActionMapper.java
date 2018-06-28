package com.imooc.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.imooc.model.InteractiveAction;
@Mapper
public interface InteractiveActionMapper {
    int deleteByPrimaryKey(Short actionid);

    int insert(InteractiveAction record);

    int insertSelective(InteractiveAction record);

    InteractiveAction selectByPrimaryKey(Short actionid);

    int updateByPrimaryKeySelective(InteractiveAction record);

    int updateByPrimaryKey(InteractiveAction record);
}