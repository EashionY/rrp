package com.rrenpin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.rrenpin.entity.Resume;

public interface ResumeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Resume record);

    int insertSelective(Resume record);

    Resume selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Resume record);

    int updateByPrimaryKey(Resume record);

	Map<String, Object> findByUserId(int userId);
	
	/**
	 * ͨ���ؼ��֣�����ְλ/���������Ҽ���
	 * @param keyword
	 * @return
	 */
	List<Map<String,Object>> searchResume(@Param("keyword")String keyword,@Param("offset")int offset,@Param("pageSize")int pageSize);
	
}