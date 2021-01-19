package com.vsc.guest_assurance.dao;

import com.vsc.guest_assurance.entity.Users;
import com.vsc.guest_assurance.vo.BackendUserListVo;

import java.util.List;

public interface UsersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    //todo
    List<BackendUserListVo> selectList(String email, String userName, Integer privilege);
}