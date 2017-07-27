package com.rrenpin.dao;

import java.util.List;
import java.util.Map;

import com.rrenpin.entity.ProjectExp;

public interface ProjectExpMapper {
    int deleteByPrimaryKey(Integer projectexpId);

    int insert(ProjectExp record);

    int insertSelective(ProjectExp record);

    ProjectExp selectByPrimaryKey(Integer projectexpId);

    int updateByPrimaryKeySelective(ProjectExp record);

    int updateByPrimaryKey(ProjectExp record);
    
    List<Map<String,Object>> findByUserId(Integer userId);
}