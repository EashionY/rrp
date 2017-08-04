package com.rrenpin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rrenpin.entity.Delivery;

public interface DeliveryMapper {
    int deleteByPrimaryKey(Integer deliveryId);

    int insert(Delivery record);

    int insertSelective(Delivery record);

    Delivery selectByPrimaryKey(Integer deliveryId);

    int updateByPrimaryKeySelective(Delivery record);

    int updateByPrimaryKey(Delivery record);

    /**
     * 通过三个id查找投递关系
     * @param resumeId
     * @param companyId
     * @param postId
     * @return
     */
	Delivery findDelivery(@Param("resumeId")int resumeId, @Param("companyId")int companyId, @Param("postId")int postId);
	
	/**
	 * (用户)查看已投递简历状态
	 * @param resumeId
	 * @param deliveryStatus
	 * @return
	 */
	List<Delivery> findByResumeId(@Param("resumeId")int resumeId,@Param("deliveryStatus")String deliveryStatus);
	
	/**
	 * (企业)查看收到的简历状态
	 * @param companyId
	 * @param deliveryStatus
	 * @return
	 */
	List<Delivery> findByCompanyId(@Param("companyId")int companyId,@Param("deliveryStatus")String deliveryStatus);
}