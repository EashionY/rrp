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
     * ��ȡ���е����id(��Ϊ���������ݵ�id)
     * @return
     */
    int getMaxId();
    
    /**
     * ��˾������ְλ����
     * @param companyId
     * @param status ״̬��
     * @return
     */
    List<Post> listPostJob(@Param("companyId")int companyId,@Param("status")String status);
}