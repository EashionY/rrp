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
     * 列出某公司发布的职位
     * @param companyId
     * @param status
     * @param offset
     * @param pageSize
     * @return
     */
	List<Post> listPostJob(@Param("companyId")int companyId, @Param("status")String status, @Param("offset")int offset, @Param("pageSize")int pageSize);
    
	/**
	 * 搜索职位/公司
	 * @param keyword
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	List<Map<String,Object>> searchPostCompany(@Param("keyword")String keyword,@Param("offset")int offset,@Param("pageSize")int pageSize);
	
	/**
	 * 热门职位(10个)
	 * @return
	 */
	List<Map<String,Object>> popularJob();
	
}