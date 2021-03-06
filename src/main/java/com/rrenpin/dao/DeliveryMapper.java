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
	List<Map<String,Object>> findByResumeId(@Param("resumeId")int resumeId,@Param("deliveryStatus")String deliveryStatus,@Param("offset")int offset,
			@Param("pageSize")int pageSize);
	
	/**
	 * (企业)查看收到的简历状态
	 * @param companyId
	 * @param deliveryStatus
	 * @return
	 */
	List<Map<String,Object>> findByCompanyId(@Param("companyId")int companyId,@Param("deliveryStatus")String deliveryStatus,
			@Param("offset")int offset,@Param("pageSize")int pageSize);
	
	/**
	 * (企业)处理简历投递
	 * @param resumeId
	 * @param companyId
	 * @param deliveryStatus
	 * @return
	 */
	int dealResume(@Param("deliveryId")int deliveryId,@Param("deliveryStatus")String deliveryStatus);
	
	/**
	 * 简历管理中筛选简历
	 * @param companyId
	 * @param deliveryStatus
	 * @param keyword
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	List<Map<String,Object>> searchDelivery(@Param("companyId")int companyId,@Param("deliveryStatus")String deliveryStatus,
			@Param("keyword")String keyword,@Param("offset")int offset,@Param("pageSize")int pageSize);
	
	/**
	 * 通过公司id查找简历id
	 * @param companyId
	 * @return
	 */
	List<Integer> findResumeIdByCompanyId(int companyId);

	/**
	 * 删除对应职位id的投递关系
	 * @param postId
	 * @return
	 */
	int deleteByPostId(int postId);
}