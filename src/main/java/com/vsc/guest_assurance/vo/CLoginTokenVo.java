package com.vsc.guest_assurance.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class CLoginTokenVo {
    @ApiModelProperty(value="用户id", required = true)
    private int userId;
    @ApiModelProperty(value="token", required = true)
    private String token;
    @ApiModelProperty(value="权限列表", required = true)
    private String privileges;
    @ApiModelProperty(value = "用户名称")
    private String name;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "角色名称")
    private String roleName;
    @ApiModelProperty(value = "角色类型")
    private Integer roleId;
    @ApiModelProperty(value = "客户角色标识:0aad账号;1客户账号")
    private Integer customerFlag;
    @ApiModelProperty(value = "权限集合")
    private List<CPrivilegeGenreListVo> privilegeVoList;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPrivileges() {
        return privileges;
    }

    public void setPrivileges(String privileges) {
        this.privileges = privileges;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getCustomerFlag() {
        return customerFlag;
    }

    public void setCustomerFlag(Integer customerFlag) {
        this.customerFlag = customerFlag;
    }

    public List<CPrivilegeGenreListVo> getPrivilegeVoList() {
        return privilegeVoList;
    }

    public void setPrivilegeVoList(List<CPrivilegeGenreListVo> privilegeVoList) {
        this.privilegeVoList = privilegeVoList;
    }
}
