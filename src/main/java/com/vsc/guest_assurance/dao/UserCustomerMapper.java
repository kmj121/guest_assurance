package com.vsc.guest_assurance.dao;

import com.vsc.guest_assurance.entity.UserCustomer;

public interface UserCustomerMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(UserCustomer record);

    int insertSelective(UserCustomer record);

    UserCustomer selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(UserCustomer record);

    int updateByPrimaryKey(UserCustomer record);
}