package com.rrenpin.dao;

import java.util.Map;

import com.rrenpin.entity.Resume;

public interface ResumeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Resume record);

    int insertSelective(Resume record);

    Resume selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Resume record);

    int updateByPrimaryKey(Resume record);
    
    Map<String, Object> findByUserId(Integer userId);
    
    
}