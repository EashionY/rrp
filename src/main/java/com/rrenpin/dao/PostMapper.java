package com.rrenpin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rrenpin.entity.Post;

public interface PostMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Post record);

    int insertSelective(Post record);

    Post selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Post record);

    int updateByPrimaryKey(Post record);
    /**
     * 获取表中的最大id(即为最后插入数据的id)
     * @return
     */
    int getMaxId();
    
    /**
     * 公司发布的职位集合
     * @param companyId
     * @param status 状态码
     * @return
     */
    List<Post> listPostJob(@Param("companyId")int companyId,@Param("status")String status);
}