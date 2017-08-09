package com.rrenpin.dao;

import com.rrenpin.entity.Company;

public interface CompanyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Company record);

    int insertSelective(Company record);

    Company selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Company record);

    int updateByPrimaryKey(Company record);

	Company findByEmail(String email);

	Company findByUserId(int userId);
	
}