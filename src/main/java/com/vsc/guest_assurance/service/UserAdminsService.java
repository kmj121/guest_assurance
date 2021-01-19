package com.vsc.guest_assurance.service;

import com.vsc.guest_assurance.common.ApiException;
import com.vsc.guest_assurance.common.Constant;
import com.vsc.guest_assurance.common.MessageCode;
import com.vsc.guest_assurance.dao.UserAdminsMapper;
import com.vsc.guest_assurance.entity.UserAdmins;
import com.vsc.guest_assurance.entity.Users;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class UserAdminsService {
    @Autowired
    private UserAdminsMapper userAdminsMapper;
    //@Autowired
    //private DepartmentService departmentService;
    @Autowired
    private UsersService usersService;
    //@Autowired
    //private CountriesService countriesService;

    /**
     * 添加管理员账号
     * @param userId
     * @param email
     * @param userName
     */
    public void addUserAdmin(Integer userId, String email, String userName) {
        UserAdmins userAdmins = new UserAdmins();
        userAdmins.setUserId(userId);
        userAdmins.setEmail(email);
        userAdmins.setUserName(userName);
        userAdmins.setCreateAt(new Date());
        userAdmins.setCreateUser(Constant.USER_DEFAULT_ADMIN);
        userAdmins.setUpdateAt(new Date());
        userAdmins.setUpdateUser(Constant.USER_DEFAULT_ADMIN);
        userAdminsMapper.insert(userAdmins);
    }

    ///**
    // * 编辑管理员账号
    // * @param qo
    // * @param loginTokens
    // */
    //public void updateUserAdmin(BUserUpdQo qo, LoginTokens loginTokens) {
    //    UserAdmins userAdmins = sureUserAdmin(qo.getUserId(), loginTokens);
    //    userAdmins.setEnglishName(qo.getEnglishName());
    //    //确认部门存在
    //    if (qo.getDepartmentCode() != null) {
    //        Departments departments = departmentService.sureDepartment(qo.getDepartmentCode(), loginTokens);
    //        userAdmins.setDepartmentCode(departments.getCode());
    //        userAdmins.setDepartmentVersion(departments.getVersion());
    //    }else {
    //        userAdmins.setDepartmentCode(null);
    //        userAdmins.setDepartmentVersion(null);
    //    }
    //
    //    if (qo.getCountryId() != null) {
    //        countriesService.sureCountry(qo.getCountryId(), loginTokens);
    //    }
    //    userAdmins.setCountryId(qo.getCountryId());
    //    userAdmins.setMobile(qo.getMobile());
    //
    //    if (qo.getLeaderId() != null) {
    //        //上级用户不能是自己
    //        if(qo.getLeaderId().equals(userAdmins.getUserId())){
    //            throw new ApiException(MessageCode.CODE_PARAMETER_ERROR, loginTokens.getLanguage());
    //        }
    //        Users users = usersService.assertUser(qo.getLeaderId(), loginTokens.getLanguage());
    //        if (!users.getRoleId().equals(Constant.ROLE_TYPE_SALES_MANAGER)) {
    //            throw new ApiException(MessageCode.CODE_USER_ADMIN_LEADER_ERROR, loginTokens.getLanguage());
    //        }
    //    }
    //    userAdmins.setLeaderId(qo.getLeaderId());
    //
    //    userAdmins.setUpdateAt(new Date());
    //    userAdmins.setUpdateUser(loginTokens.getUserId());
    //    userAdminsMapper.updateByPrimaryKey(userAdmins);
    //}

    /**
     * 修改管理员名称
     * @param userAdmins
     * @param userName
     */
    public void updateUserAdminUserName(UserAdmins userAdmins, String userName) {
        userAdmins.setUserName(userName);
        userAdmins.setUpdateAt(new Date());
        userAdmins.setUpdateUser(Constant.USER_DEFAULT_ADMIN);
        userAdminsMapper.updateByPrimaryKey(userAdmins);
    }

    /**
     * 根据Email查询管理员
     * @param email
     * @return
     */
    public UserAdmins selectByEmail(String email) {
        UserAdmins userAdmins = userAdminsMapper.selectByEmail(email);
        return userAdmins;
    }

    ///**
    // * 确认管理员存在性
    // * @param userId
    // * @param loginTokens
    // * @return
    // */
    //public UserAdmins sureUserAdmin(Integer userId, LoginTokens loginTokens) {
    //    UserAdmins userAdmins = userAdminsMapper.selectByPrimaryKey(userId);
    //    if (userAdmins == null) {
    //        throw new ApiException(MessageCode.CODE_USER_NOT_EXIST, loginTokens.getLanguage());
    //    }
    //    return userAdmins;
    //}

    ///**
    // * 检查部门使用情况
    // * @param code
    // * @param loginTokens
    // */
    //public void checkDepartmentCode(String code, LoginTokens loginTokens) {
    //    List<UserAdmins> list = userAdminsMapper.selectByDepartmentCode(code);
    //    if(CollectionUtils.isNotEmpty(list)) {
    //        throw new ApiException(MessageCode.CODE_PROHIBIT_TO_DELETE, loginTokens.getLanguage());
    //    }
    //}

    ///**
    // * 检查地区使用情况
    // * @param countryId
    // * @param loginTokens
    // */
    //public void checkCountryId(Integer countryId, LoginTokens loginTokens) {
    //    List<UserAdmins> list = userAdminsMapper.selectByCountryId(countryId);
    //    if(CollectionUtils.isNotEmpty(list)) {
    //        throw new ApiException(MessageCode.CODE_PROHIBIT_TO_DELETE, loginTokens.getLanguage());
    //    }
    //}
}
