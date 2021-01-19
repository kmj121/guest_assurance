package com.vsc.guest_assurance.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vsc.guest_assurance.common.ApiException;
import com.vsc.guest_assurance.common.Constant;
import com.vsc.guest_assurance.common.MessageCode;
import com.vsc.guest_assurance.common.PageBean;
import com.vsc.guest_assurance.dao.RolesMapper;
import com.vsc.guest_assurance.entity.Roles;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class RolesService {

    @Autowired
    private RolesMapper rolesMapper;
    @Autowired
    private UsersService usersService;
    //@Autowired
    //private PrivilegesService privilegesService;

    ///**
    // * 添加角色
    // * @param qo
    // * @param loginTokens
    // * @return
    // * @throws IllegalAccessException
    // * @throws NoSuchMethodException
    // * @throws InvocationTargetException
    // */
    //public Integer addRole(BRolesAddQo qo, LoginTokens loginTokens) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
    //
    //    privilegesService.checkPrivilegeIds(qo.getPrivilegeIds(),
    //            qo.getCustomerFlag().equals(Constant.TRUE) ? true : false, loginTokens);
    //
    //    Roles roles = new Roles();
    //    PropertyUtils.copyProperties(roles, qo);
    //    roles.setCreateAt(new Date());
    //    roles.setCreateUser(loginTokens.getUserId());
    //    roles.setUpdateAt(new Date());
    //    roles.setUpdateUser(loginTokens.getUserId());
    //    rolesMapper.insert(roles);
    //    return roles.getId();
    //}
    //
    ///**
    // * 修改角色
    // * @param roleId
    // * @param qo
    // * @param loginTokens
    // * @throws IllegalAccessException
    // * @throws NoSuchMethodException
    // * @throws InvocationTargetException
    // */
    //public void updateRole(Integer roleId, BRolesAddQo qo, LoginTokens loginTokens) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
    //    //超级管理员和无权限账号为系统保留角色不可操作
    //    if (Constant.ROLE_TYPE_ADMIN == roleId) {
    //        throw new ApiException(MessageCode.CODE_ADMIN_CAN_NOT_UPDATE, loginTokens.getLanguage());
    //    }
    //    if (Constant.ROLE_TYPE_NONE == roleId) {
    //        throw new ApiException(MessageCode.CODE_NONE_CAN_NOT_UPDATE, loginTokens.getLanguage());
    //    }
    //    //客户类型角色为系统限制角色仅能选部分权限
    //    privilegesService.checkPrivilegeIds(qo.getPrivilegeIds(),
    //            qo.getCustomerFlag().equals(Constant.TRUE) ? true : false, loginTokens);
    //    Roles roles = sureRole(roleId, loginTokens.getLanguage());
    //    PropertyUtils.copyProperties(roles, qo);
    //    roles.setUpdateAt(new Date());
    //    roles.setUpdateUser(loginTokens.getUserId());
    //    rolesMapper.updateByPrimaryKey(roles);
    //}
    //
    ///**
    // * 删除角色
    // * @param roleId
    // * @param loginTokens
    // */
    //public void deleteRole(Integer roleId, LoginTokens loginTokens){
    //    //超级管理员和无权限账号为系统保留角色不可操作
    //    if (Constant.ROLE_TYPE_ADMIN == roleId) {
    //        throw new ApiException(MessageCode.CODE_ADMIN_CAN_NOT_UPDATE, loginTokens.getLanguage());
    //    }
    //    if (Constant.ROLE_TYPE_NONE == roleId) {
    //        throw new ApiException(MessageCode.CODE_NONE_CAN_NOT_UPDATE, loginTokens.getLanguage());
    //    }
    //    //默认客户账号为系统保留角色不可删除
    //    if (Constant.ROLE_TYPE_NONE == roleId) {
    //        throw new ApiException(MessageCode.CODE_NONE_CAN_NOT_UPDATE, loginTokens.getLanguage());
    //    }
    //    usersService.checkRoleUsed(roleId, loginTokens);
    //    sureRole(roleId, loginTokens.getLanguage());
    //    rolesMapper.deleteByPrimaryKey(roleId);
    //}
    //
    ///**
    // * 角色列表
    // *
    // * @param roleName
    // * @param page
    // * @param size
    // * @param loginTokens
    // * @return
    // */
    //public PageBean<BRoleListVo> list(String roleName, int page, int size, LoginTokens loginTokens) {
    //    PageHelper.startPage(page, size);
    //    List<BRoleListVo> vos = rolesMapper.list(roleName);
    //    PageInfo<BRoleListVo> pageInfo = new PageInfo(vos);
    //    return new PageBean<>(page, size, pageInfo.getTotal(), vos);
    //}
    //
    ///**
    // * 角色详情
    // *
    // * @param roleId
    // * @param loginTokens
    // * @return
    // * @throws IllegalAccessException
    // * @throws NoSuchMethodException
    // * @throws InvocationTargetException
    // */
    //public BRoleDetailVo detail(Integer roleId, LoginTokens loginTokens) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
    //    Roles roles = sureRole(roleId, loginTokens.getLanguage());
    //    BRoleDetailVo bRoleDetailVo = new BRoleDetailVo();
    //    PropertyUtils.copyProperties(bRoleDetailVo, roles);
    //    if (roles.getUpdateUser() != null) {
    //        bRoleDetailVo.setUpdateUserName(usersService.getUserName(roles.getUpdateUser()));
    //    }
    //    return bRoleDetailVo;
    //}

    //
    ///**
    // * 获取角色下拉列表
    // */
    //public List<BRoleVo> roleList(Integer customerFlag, LoginTokens loginTokens) {
    //    List<BRoleVo> bRoleListVos = rolesMapper.selectRole(customerFlag, loginTokens.getLanguage());
    //    return bRoleListVos;
    //}

    /**
     * 获取角色信息
     *
     * @param roleId
     * @return
     */
    public Roles sureRole(Integer roleId, Integer language) {
        Roles roles = rolesMapper.selectByPrimaryKey(roleId);
        if (roles == null) {
            throw new ApiException(MessageCode.CODE_NO_ROLE);
        }
        return roles;
    }

    /**
     * 获取角色信息
     *
     * @param roleId
     * @return
     */
    public Roles sureRoleAdmin(Integer roleId, Integer language) {
        Roles roles = rolesMapper.selectByPrimaryKey(roleId);
        if (roles == null || roles.getCustomerFlag().equals(Constant.TRUE)) {
            throw new ApiException(MessageCode.CODE_NO_ROLE);
        }
        return roles;
    }

    /**
     * 获取角色信息
     *
     * @param roleId
     * @return
     */
    public Roles sureRoleCustomer(Integer roleId, Integer language) {
        Roles roles = rolesMapper.selectByPrimaryKey(roleId);
        if (roles == null || roles.getCustomerFlag().equals(Constant.FALSE)) {
            throw new ApiException(MessageCode.CODE_NO_ROLE);
        }
        return roles;
    }


}
