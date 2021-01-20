package com.vsc.guest_assurance.qo.backend;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class BUserUpdQo {

    @ApiModelProperty(value="用户id")
    @NotNull
    private Integer userId;
    @ApiModelProperty(value="角色Id")
    @NotNull
    private Integer roleId;
    //@ApiModelProperty(value="电话")
    //@Pattern(regexp = "^[\\s\\S]{0,20}$")
    //private String mobile;
    //@ApiModelProperty(value="姓名")
    //@Pattern(regexp = "^[\\s\\S]{0,50}$")
    //private String name;
    //@ApiModelProperty(value="邮箱")
    //@Pattern(regexp = "^[\\s\\S]{0,200}$")
    //private String email;
    //@ApiModelProperty(value="密码")
    //@Pattern(regexp = "^[\\s\\S]{0,18}$")
    //private String password;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    //public String getMobile() {
    //    return mobile;
    //}
    //
    //public void setMobile(String mobile) {
    //    this.mobile = mobile;
    //}
    //
    //public String getName() {
    //    return name;
    //}
    //
    //public void setName(String name) {
    //    this.name = name;
    //}
    //
    //public String getEmail() {
    //    return email;
    //}
    //
    //public void setEmail(String email) {
    //    this.email = email;
    //}
    //
    //public String getPassword() {
    //    return password;
    //}
    //
    //public void setPassword(String password) {
    //    this.password = password;
    //}
}
