package com.imooc.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.imooc.model.MediaFile;
@Mapper
public interface MediaFileMapper {
    int deleteByPrimaryKey(Integer mediaid);

    int insert(MediaFile record);

    int insertSelective(MediaFile record);

    MediaFile selectByPrimaryKey(Integer mediaid);

    int updateByPrimaryKeySelective(MediaFile record);

    int updateByPrimaryKey(MediaFile record);
}