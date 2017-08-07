package com.rrenpin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.rrenpin.entity.Post;

public interface PostMapper {
    int deleteByPrimaryKey(Integer postId);

    int insert(Post record);

    int insertSelective(Post record);

    Post selectByPrimaryKey(Integer postId);

    int updateByPrimaryKeySelective(Post record);

    int updateByPrimaryKey(Post record);

    /**
     * �г�ĳ��˾������ְλ
     * @param companyId
     * @param status
     * @param offset
     * @param pageSize
     * @return
     */
	List<Map<String,Object>> listPostJob(@Param("companyId")int companyId, @Param("status")String status, @Param("offset")int offset, @Param("pageSize")int pageSize);
    
	/**
	 * ����ְλ/��˾
	 * @param keyword
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	List<Map<String,Object>> searchPostCompany(@Param("keyword")String keyword,@Param("region")String region,
			@Param("workExp")String workExp,@Param("degree")String degree,@Param("scale")String scale,
			@Param("salary")String salary,@Param("industry")String industry,@Param("financing")String financing,
			@Param("offset")int offset,@Param("pageSize")int pageSize);
	
	/**
	 * ����ְλ(10��)
	 * @return
	 */
	List<Map<String,Object>> popularJob();
	
	/**
	 * ���������ְλ
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	List<Map<String, Object>> latestPostJob(@Param("offset")int offset,@Param("pageSize")int pageSize);
	
	/**
	 * ְλ����
	 * @param postId
	 * @return
	 */
	List<Map<String,Object>> jobDetail(int postId);
	
	
}