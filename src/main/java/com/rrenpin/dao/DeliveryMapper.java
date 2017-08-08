package com.rrenpin.dao;

import java.util.List;
import java.util.Map;

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
     * ͨ������id����Ͷ�ݹ�ϵ
     * @param resumeId
     * @param companyId
     * @param postId
     * @return
     */
	Delivery findDelivery(@Param("resumeId")int resumeId, @Param("companyId")int companyId, @Param("postId")int postId);
	
	/**
	 * (�û�)�鿴��Ͷ�ݼ���״̬
	 * @param resumeId
	 * @param deliveryStatus
	 * @return
	 */
	List<Delivery> findByResumeId(@Param("resumeId")int resumeId,@Param("deliveryStatus")String deliveryStatus);
	
	/**
	 * (��ҵ)�鿴�յ��ļ���״̬
	 * @param companyId
	 * @param deliveryStatus
	 * @return
	 */
	List<Map<String,Object>> findByCompanyId(@Param("companyId")int companyId,@Param("deliveryStatus")String deliveryStatus,
			@Param("offset")int offset,@Param("pageSize")int pageSize);
	
	/**
	 * (��ҵ)�������Ͷ��
	 * @param resumeId
	 * @param companyId
	 * @param deliveryStatus
	 * @return
	 */
	int dealResume(@Param("deliveryId")int deliveryId,@Param("deliveryStatus")String deliveryStatus);
}