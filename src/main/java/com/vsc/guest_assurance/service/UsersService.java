package com.vsc.guest_assurance.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.microsoft.graph.models.extensions.DirectoryObject;
import com.microsoft.graph.models.extensions.User;
import com.vsc.guest_assurance.common.*;
import com.vsc.guest_assurance.dao.PrivilegesMapper;
import com.vsc.guest_assurance.dao.RolesMapper;
import com.vsc.guest_assurance.dao.UsersMapper;
import com.vsc.guest_assurance.entity.LoginTokens;
import com.vsc.guest_assurance.entity.Roles;
import com.vsc.guest_assurance.entity.Users;
import com.vsc.guest_assurance.qo.backend.BUserAddQo;
import com.vsc.guest_assurance.qo.backend.BUserUpdQo;
import com.vsc.guest_assurance.vo.backend.BRoleListVo;
import com.vsc.guest_assurance.vo.backend.BUserDetailVo;
import com.vsc.guest_assurance.vo.backend.BUserListVo;
import com.vsc.guest_assurance.vo.backend.BUserUpdateVo;
import com.vsc.guest_assurance.vo.common.CLoginTokenVo;
import com.vsc.guest_assurance.vo.common.CPrivilegeGenreListVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author Roger
 * @Date 2021/1/13
 */
@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class UsersService {
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private RolesMapper rolesMapper;
    @Autowired
    private LoginTokensService loginTokensService;
    @Autowired
    private PrivilegesMapper privilegesMapper;

    public PageBean<BUserListVo> list(String email, String userName, int page, int size) {
        String orderBy = "u.id desc";
        PageHelper.startPage(page, size, orderBy);
        List<BUserListVo> vos = usersMapper.selectList(email, userName);
        PageInfo<BUserListVo> pageInfo = new PageInfo(vos);
        return new PageBean<>(page, size, pageInfo.getTotal(), vos);
    }

    public CLoginTokenVo login(String token, String type, String ip, Integer language) {
        //校验token
        User user = ClientCredentialProvider.getUser(token);
        //数据库对比
        Users users = usersMapper.selectByEmail(user.userPrincipalName);
        if (users == null) {
            throw new ApiException(MessageCode.CODE_NO_USER);
        }
        //Roles roles = assertRole(users.getRoleId(), language);
        //创建token
        LoginTokens loginToken = loginTokensService.addLoginToken(type, users.getId(), ip, token, language);

        CLoginTokenVo bLoginTokenVo = new CLoginTokenVo();
        bLoginTokenVo.setUserId(users.getId());
        bLoginTokenVo.setEmail(users.getEmail());
        bLoginTokenVo.setName(users.getUserName());
        bLoginTokenVo.setToken(loginToken.getToken());

        //List<Integer> idList = new ArrayList<>();

        //后台只有超级管理员可以登录
        //if (type.equals(Constant.TOKEN_TYPE_BACKEND) && !roles.getId().equals(Constant.ROLE_TYPE_ADMIN)) {
        //    throw new ApiException(MessageCode.CODE_NO_PRIVILEGE);
        //}
        //用户权限信息
        //bLoginTokenVo.setRoleId(roles.getId());
        //bLoginTokenVo.setRoleName(roles.getRoleName());
        //bLoginTokenVo.setPrivileges(roles.getPrivilegeIds());
        //if (!StringUtils.equals(Constant.PRIVILEGE_ALL, roles.getPrivilegeIds())) {
        //    for (String id : roles.getPrivilegeIds().split(",")) {
        //        idList.add(Integer.parseInt(id));
        //    }
        //}
        //bLoginTokenVo.setPrivilegeVoList(privilegesMapper.selectPrivileges(roles.getPrivilegeIds(), type, idList));

        return bLoginTokenVo;
    }

    ///**
    // * 自动更新用户列表
    // */
    //public void updateUsers() {
    //    List<DirectoryObject> userList = ClientCredentialProvider.getUsers();
    //    for (DirectoryObject user : userList) {
    //        Users users = usersMapper.selectByUserNameAndType(user.getRawObject().get("userPrincipalName").getAsString()
    //                , Constant.USER_TYPE_ADMIN);
    //        if (users == null) {
    //            users = new Users();
    //            users.setUserName(user.getRawObject().get("userPrincipalName").getAsString());
    //            users.setEmail(user.getRawObject().get("userPrincipalName").getAsString());
    //            users.setName(user.getRawObject().get("displayName").getAsString());
    //            users.setRoleId(Constant.ROLE_TYPE_NORMAL);
    //            users.setType(Constant.USER_TYPE_ADMIN);
    //            users.setCreateAt(new Date());
    //            users.setCreateUser(Constant.USER_DEFAULT_ADMIN);
    //            users.setUpdateAt(new Date());
    //            users.setUpdateUser(Constant.USER_DEFAULT_ADMIN);
    //            users.setDelFlag(Constant.FALSE);
    //            usersMapper.insert(users);
    //        } else {
    //            users.setName(user.getRawObject().get("displayName").getAsString());
    //            users.setUpdateAt(new Date());
    //            users.setUpdateUser(Constant.USER_DEFAULT_ADMIN);
    //            usersMapper.updateByPrimaryKey(users);
    //        }
    //    }
    //}

    /**
     * 确认用户信息
     */
    public Users assertUser(Integer userId) {
        Users users = usersMapper.selectByPrimaryKey(userId);
        if (users == null) {
            throw new ApiException(MessageCode.CODE_NO_USER);
        }
        return users;
    }

    public void assertEmail(String email) {
        Users users = usersMapper.selectByEmail(email);
        if (users != null) {
            throw new ApiException(MessageCode.CODE_EXIST, "用户");
        }
    }

    ///**
    // * 获取角色信息
    // *
    // * @param roleId
    // * @return
    // */
    //public Roles assertRole(Integer roleId, Integer language) {
    //    Roles roles = rolesMapper.selectByPrimaryKey(roleId);
    //    if (roles == null) {
    //        throw new ApiException(MessageCode.CODE_NO_USER);
    //    }
    //    return roles;
    //}

    ///**
    // * 获取角色列表
    // */
    //public List<BRoleListVo> roleList() {
    //    List<BRoleListVo> bRoleListVos = rolesMapper.selectRole();
    //    return bRoleListVos;
    //}

    //public BUserDetailVo detail(Integer userId, LoginTokens loginTokens) {
    //    BUserDetailVo bUserDetailVo = usersMapper.selectByUserId(userId);
    //    if (bUserDetailVo == null) {
    //        throw new ApiException(MessageCode.CODE_NO_USER);
    //    }
    //    return bUserDetailVo;
    //}

    //public void updateUser(BUserUpdQo qo, LoginTokens loginTokens) throws Exception {
    //    //不能修改自己的权限
    //    if (loginTokens.getUserId().equals(qo.getUserId())) {
    //        throw new ApiException(MessageCode.CODE_USER_PRIVILEGE_ERROR);
    //    }
    //    Users users = assertUser(qo.getUserId(), loginTokens.getLanguage());
    //
    //    //if (users.getType().equals(Constant.USER_TYPE_CUSTOMER) &&
    //    //        (qo.getRoleId() != Constant.ROLE_TYPE_NORMAL_ADMIN && qo.getRoleId() != Constant.ROLE_TYPE_NORMAL)) {
    //    //    throw new ApiException(MessageCode.CODE_PARAMETER_ERROR);
    //    //}
    //    assertRole(qo.getRoleId(), loginTokens.getLanguage());
    //    users.setRoleId(qo.getRoleId());
    //    //users.setName(qo.getName());
    //    //users.setEmail(qo.getEmail());
    //    //if (users.getType().equals(Constant.USER_TYPE_ADMIN)) {
    //    //    users.setPassword(null);
    //    //} else {
    //    //    String password = qo.getPassword();
    //    //    if (StringUtils.isBlank(password) || password.length() > 18) {
    //    //        throw new ApiException(MessageCode.CODE_PARAMETER_ERROR);
    //    //    }
    //    //    users.setPassword(AESCoder.encrypt(password));
    //    //}
    //    //users.setMobile(qo.getMobile());
    //    users.setUpdateAt(new Date());
    //    users.setUpdateUser(loginTokens.getUserId());
    //    usersMapper.updateByPrimaryKey(users);
    //}

    public void add(BUserAddQo qo, LoginTokens loginToken) {
        assertEmail(qo.getEmail());
        Users users = new Users();
        users.setEmail(qo.getEmail());
        users.setUserName(qo.getUserName());
        users.setCreateAt(new Date());
        usersMapper.insert(users);
    }


    public void delete(Integer userId, LoginTokens loginTokens) {
        assertUser(userId);
        usersMapper.deleteByPrimaryKey(userId);
    }
}
