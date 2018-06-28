package com.imooc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.imooc.model.InterestPoint;
@Mapper
public interface InterestPointMapper {
    int deleteByPrimaryKey(Short interestpointid);

    int insert(InterestPoint record);

    int insertSelective(InterestPoint record);

    InterestPoint selectByPrimaryKey(Short interestpointid);

    int updateByPrimaryKeySelective(InterestPoint record);

    int updateByPrimaryKey(InterestPoint record);
    
    List<InterestPoint> getAll();
}