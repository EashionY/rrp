package com.rrenpin.dao;

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
     * ͨ��resumeId,companyId,postId����Ͷ�ݱ�
     * @param resumeId
     * @param companyId
     * @param postId
     * @return
     */
	Delivery findDelivery(@Param("resumeId")int resumeId, @Param("companyId")int companyId, @Param("postId")int postId);
	
}