package com.rrenpin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

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
	
	List<Map<String,Object>> listAllCompany(@Param("status")String status,@Param("page")int page,@Param("pageSize")int pageSize);
}