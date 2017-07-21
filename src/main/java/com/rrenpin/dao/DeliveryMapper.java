package com.rrenpin.dao;

import com.rrenpin.entity.Delivery;

public interface DeliveryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Delivery record);

    int insertSelective(Delivery record);

    Delivery selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Delivery record);

    int updateByPrimaryKey(Delivery record);
}