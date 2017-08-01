package com.rrenpin.dao;

import com.rrenpin.entity.Authcode;

public interface AuthcodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Authcode record);

    int insertSelective(Authcode record);

    Authcode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Authcode record);

    int updateByPrimaryKey(Authcode record);

    Authcode findByEmail(String email);
}