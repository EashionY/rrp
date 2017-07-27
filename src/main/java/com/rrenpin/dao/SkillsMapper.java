package com.rrenpin.dao;

import java.util.List;
import java.util.Map;

import com.rrenpin.entity.Skills;

public interface SkillsMapper {
    int deleteByPrimaryKey(Integer skillId);

    int insert(Skills record);

    int insertSelective(Skills record);

    Skills selectByPrimaryKey(Integer skillId);

    int updateByPrimaryKeySelective(Skills record);

    int updateByPrimaryKey(Skills record);
    
    List<Map<String,Object>> findByUserId(Integer userId);
}