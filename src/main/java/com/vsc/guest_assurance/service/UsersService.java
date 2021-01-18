package com.vsc.guest_assurance.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.microsoft.graph.models.extensions.DirectoryObject;
import com.microsoft.graph.models.extensions.User;
import com.vsc.guest_assurance.common.*;
import com.vsc.guest_assurance.dao.UsersMapper;
import com.vsc.guest_assurance.vo.BackendUserDetailVo;
import com.vsc.guest_assurance.vo.BackendUserListVo;
import com.vsc.guest_assurance.vo.BackendUserUpdateVo;
import com.vsc.guest_assurance.vo.CommonLoginTokenVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author Roger
 * @Date 2021/1/13
 */
@Service
public class UsersService {
    @Autowired
    private UsersMapper usersMapper;

    public PageBean<BackendUserListVo> list(String email, String userName, Integer privilege, Integer page, Integer size) {
        email = StringUtils.isEmpty(email) ? null : "%" + email + "%";
        userName = StringUtils.isEmpty(userName) ? null : "%" + userName + "%";
        PageHelper.startPage(page, size);
        List<BackendUserListVo> vos = usersMapper.selectList(email, userName, privilege);
        PageInfo<BackendUserListVo> pageInfo = new PageInfo(vos);
        return new PageBean<>(page, size, pageInfo.getTotal(), vos);
    }

    public CommonLoginTokenVo login(String token, String type, String ip, Integer language) {
        User user = ClientCredentialProviderForLogin.getUser(token);
        //UserAdmins userAdmins = userAdminsService.selectByEmail(user.userPrincipalName);
        //Users users = assertUser(userAdmins.getUserId(), language);
        //Roles roles = rolesService.sureRole(users.getRoleId(), language);
        //if (roles.getId().equals(Constant.ROLE_TYPE_NONE)) {
        //    throw new ApiException(MessageCode.CODE_NO_PRIVILEGE, language);
        //}
        //
        ////检查角色是否等登录
        //if (type.equals(Constant.TOKEN_TYPE_APP)
        //        && (!roles.getAppFlag().equals(Constant.TRUE))) {
        //    throw new ApiException(MessageCode.CODE_NO_PRIVILEGE, language);
        //}
        //if (type.equals(Constant.TOKEN_TYPE_BACKEND)
        //        && (!roles.getBackendFlag().equals(Constant.TRUE))) {
        //    throw new ApiException(MessageCode.CODE_NO_PRIVILEGE, language);
        //}
        //
        //LoginTokens loginToken = loginTokensService.addLoginToken(type, users.getId(), ip, token, language);
        //CommonLoginTokenVo cLoginTokenVo = new CommonLoginTokenVo();
        //cLoginTokenVo.setUserId(users.getId());
        //cLoginTokenVo.setEmail(userAdmins.getEmail());
        //cLoginTokenVo.setName(userAdmins.getUserName());
        //cLoginTokenVo.setToken(loginToken.getToken());
        //cLoginTokenVo.setRoleId(roles.getId());
        //cLoginTokenVo.setRoleName(roles.getRoleNameByLanguage(language));
        //cLoginTokenVo.setCustomerFlag(roles.getCustomerFlag());
        //
        //List<Integer> idList = new ArrayList<>();
        //cLoginTokenVo.setPrivileges(roles.getPrivilegeIds());
        //if (!StringUtils.equals(Constant.PRIVILEGE_ALL, roles.getPrivilegeIds())) {
        //    for (String id : roles.getPrivilegeIds().split(",")) {
        //        idList.add(Integer.parseInt(id));
        //    }
        //}
        //List<CPrivilegeGenreListVo> cPrivilegeGenreListVos =
        //        MyUtils.buildPrivileges(privilegesService.selectPrivileges(roles.getPrivilegeIds(), type, idList, language));
        //cLoginTokenVo.setPrivilegeVoList(cPrivilegeGenreListVos);
        //
        //return cLoginTokenVo;
        return null;
    }

    /**
     * 自动更新用户列表
     */
    public void updateUsers() {
        List<DirectoryObject> userList = ClientCredentialProvider.getUsers();
        for (DirectoryObject user : userList) {
            //createUserAdmin(user.getRawObject().get("userPrincipalName").getAsString(), user.getRawObject().get("displayName").getAsString());
        }
    }

    public void edit(BackendUserUpdateVo backendUserUpdateVo) {
    }

    public BackendUserDetailVo detail(Integer id) {
        return new BackendUserDetailVo();
    }

    /**
     * 添加Admin用户
     * aad用户添加，默认角色为无权限角色
     *
     * @param email
     * @param userName
     */
    //public void createUserAdmin(String email, String userName) {
    //    UserAdmins userAdmins = userAdminsService.selectByEmail(email);
    //    if (userAdmins == null) {
    //        Users users = new Users();
    //        users.setUserName(userName);
    //        users.setType(Constant.USER_TYPE_ADMIN);
    //        users.setRoleId(Constant.ROLE_TYPE_NONE);
    //        users.setCreateAt(new Date());
    //        users.setCreateUser(Constant.USER_DEFAULT_ADMIN);
    //        users.setUpdateAt(new Date());
    //        users.setUpdateUser(Constant.USER_DEFAULT_ADMIN);
    //        usersMapper.insert(users);
    //        userAdminsService.addUserAdmin(users.getId(), email, userName);
    //    } else {
    //        userAdminsService.updateUserAdminUserName(userAdmins, userName);
    //    }
    //
    //}
}
