package com.rrenpin.dao;

import java.util.List;
import java.util.Map;

import com.rrenpin.entity.WorkExp;

public interface WorkExpMapper {
    int deleteByPrimaryKey(Integer workexpId);

    int insert(WorkExp record);

    int insertSelective(WorkExp record);

    WorkExp selectByPrimaryKey(Integer workexpId);

    int updateByPrimaryKeySelective(WorkExp record);

    int updateByPrimaryKey(WorkExp record);
    
    List<Map<String,Object>> findByUserId(Integer userId);
}