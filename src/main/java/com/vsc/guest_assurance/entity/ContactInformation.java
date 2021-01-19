package com.vsc.guest_assurance.entity;

import java.util.Date;

public class ContactInformation {
    private Integer id;

    private String name;

    private String email;

    private String industryType;

    private String message;

    private Date create_time;

    private Date update_time;

    private String phone;

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

    public Date getupdate_time() {
        return update_time;
    }

    public void setupdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}