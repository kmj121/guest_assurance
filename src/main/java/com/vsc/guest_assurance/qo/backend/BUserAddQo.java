package com.vsc.guest_assurance.qo.backend;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BUserAddQo {

    @ApiModelProperty(value="邮箱")
    @NotBlank
    private String email;
    @ApiModelProperty(value="姓名")
    @NotBlank
    private String userName;

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
}
