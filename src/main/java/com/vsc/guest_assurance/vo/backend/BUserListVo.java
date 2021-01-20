package com.vsc.guest_assurance.vo.backend;

import io.swagger.annotations.ApiModelProperty;


public class BUserListVo {
    @ApiModelProperty(value = "用户id")
    private Integer id;
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "角色Id")
    private Integer roleId;
    @ApiModelProperty(value = "角色名称")
    private String roleName;
    @ApiModelProperty(value = "电话")
    private String mobile;
    @ApiModelProperty(value = "用户类型0aad用户；1普通用户")
    private Integer type;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
