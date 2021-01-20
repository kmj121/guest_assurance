package com.vsc.guest_assurance.qo.backend;

import javax.validation.constraints.NotBlank;

/**
 * @Description
 * @Author Roger
 * @Date 2021/1/14
 */
public class BContactInformationAddQo {
    private String name;

    @NotBlank
    private String email;

    private String phone;

    private String industryType;

    private String message;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIndustryType() {
        return industryType;
    }

    public void setIndustryType(String industryType) {
        this.industryType = industryType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
