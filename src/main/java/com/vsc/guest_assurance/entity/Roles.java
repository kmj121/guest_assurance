package com.vsc.guest_assurance.entity;

import com.vsc.guest_assurance.common.Constant;

import java.util.Date;

public class Roles {
    private Integer id;

    private String roleName;

    private String roleInfo;

    private String privilegeIds;

    private Date createAt;

    private Integer createUser;

    private Date updateAt;

    private Integer updateUser;

    private String roleNameTc;

    private String roleNameEn;

    private Integer customerFlag;

    private Integer backendFlag;

    private Integer appFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleInfo() {
        return roleInfo;
    }

    public void setRoleInfo(String roleInfo) {
        this.roleInfo = roleInfo;
    }

    public String getPrivilegeIds() {
        return privilegeIds;
    }

    public void setPrivilegeIds(String privilegeIds) {
        this.privilegeIds = privilegeIds;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    public String getRoleNameTc() {
        return roleNameTc;
    }

    public void setRoleNameTc(String roleNameTc) {
        this.roleNameTc = roleNameTc;
    }

    public String getRoleNameEn() {
        return roleNameEn;
    }

    public void setRoleNameEn(String roleNameEn) {
        this.roleNameEn = roleNameEn;
    }

    public Integer getCustomerFlag() {
        return customerFlag;
    }

    public void setCustomerFlag(Integer customerFlag) {
        this.customerFlag = customerFlag;
    }

    public Integer getBackendFlag() {
        return backendFlag;
    }

    public void setBackendFlag(Integer backendFlag) {
        this.backendFlag = backendFlag;
    }

    public Integer getAppFlag() {
        return appFlag;
    }

    public void setAppFlag(Integer appFlag) {
        this.appFlag = appFlag;
    }

    public String getRoleNameByLanguage(Integer language) {
        if (language != null && language.equals(Constant.LANGUAGE_SIMPLIFIED_CHINESE)) {
            return roleName;
        } else if (language != null && language.equals(Constant.LANGUAGE_TRADITIONAL_CHINESE)) {
            return roleNameTc;
        }
        return roleNameEn;
    }
}