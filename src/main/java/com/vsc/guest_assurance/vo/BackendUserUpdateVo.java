package com.vsc.guest_assurance.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Description
 * @Author Roger
 * @Date 2021/1/13
 */
public class BackendUserUpdateVo {
    private String userName;
    private String email;
    @ApiModelProperty("人员权限:0无权限1管理员权限")
    private Integer privilege;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Integer privilege) {
        this.privilege = privilege;
    }
}
