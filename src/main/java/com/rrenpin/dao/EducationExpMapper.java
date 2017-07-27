package com.rrenpin.dao;

import java.util.List;
import java.util.Map;

import com.rrenpin.entity.EducationExp;

public interface EducationExpMapper {
    int deleteByPrimaryKey(Integer educationexpId);

    int insert(EducationExp record);

    int insertSelective(EducationExp record);

    EducationExp selectByPrimaryKey(Integer educationexpId);

    int updateByPrimaryKeySelective(EducationExp record);

    int updateByPrimaryKey(EducationExp record);
    
    List<Map<String,Object>> findByUserId(Integer userId);
}