package com.vsc.guest_assurance.qo.common;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.*;

public class CLoginQo {
    @ApiModelProperty(value="aad返回token", required = true)
    @NotBlank
    @NotNull
    private String token;
    //@ApiModelProperty(value="登录类型：后台用户:backend;app用户:app", required = true)
    //@Pattern(regexp = "(backend)|(app)")
    //private String type;
    //@ApiModelProperty(value="语言:0简体中文;1英语;2繁体中文", required = true)
    //@Min(0)
    //@Max(2)
    //private Integer language;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    //public String getType() {
    //    return type;
    //}
    //
    //public void setType(String type) {
    //    this.type = type;
    //}
    //
    //public Integer getLanguage() {
    //    return language;
    //}
    //
    //public void setLanguage(Integer language) {
    //    this.language = language;
    //}
}
