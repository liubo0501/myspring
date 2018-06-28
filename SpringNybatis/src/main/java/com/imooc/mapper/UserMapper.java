package com.imooc.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.imooc.model.User;
@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Short userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Short userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    List<User> getAll(Map<String,Object> map);
}