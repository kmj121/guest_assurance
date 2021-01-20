package com.vsc.guest_assurance.qo.backend;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @Description
 * @Author Roger
 * @Date 2021/1/14
 */
public class BContactInformationDetailQo {
    private Integer id;
    private String name;

    private String email;

    private String phone;

    private String industryType;

    private String message;

    @ApiModelProperty(value = "提交时间", example = "2019-02-20 10:10:10")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date create_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getcreate_time() {
        return create_time;
    }

    public void setcreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
