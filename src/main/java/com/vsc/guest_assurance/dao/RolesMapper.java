package com.vsc.guest_assurance.dao;

import com.vsc.guest_assurance.entity.Roles;
import com.vsc.guest_assurance.vo.backend.BRoleListVo;

import java.util.List;

public interface RolesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Roles record);

    int insertSelective(Roles record);

    Roles selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Roles record);

    int updateByPrimaryKey(Roles record);

    List<BRoleListVo> selectRole();
}