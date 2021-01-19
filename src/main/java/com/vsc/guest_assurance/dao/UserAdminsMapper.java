package com.vsc.guest_assurance.dao;

import com.vsc.guest_assurance.entity.UserAdmins;

public interface UserAdminsMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(UserAdmins record);

    int insertSelective(UserAdmins record);

    UserAdmins selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(UserAdmins record);

    int updateByPrimaryKey(UserAdmins record);

    UserAdmins selectByEmail(String email);
}