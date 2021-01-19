package com.vsc.guest_assurance.entity;

import java.util.Date;

public class Privileges {
    private Integer id;

    private String type;

    private String genre;

    private String name;

    private String urlPattern;

    private Date createAt;

    private Integer createUser;

    private Date updateAt;

    private Integer updateUser;

    private Integer genreId;

    private Integer nameId;

    private Integer buttonType;

    private String buttonName;

    private Integer customerFlag;

    private String buttonNameEn;

    private String buttonNameTc;

    private String genreEn;

    private String genreTc;

    private String nameEn;

    private String nameTc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlPattern() {
        return urlPattern;
    }

    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
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

    public Integer getGenreId() {
        return genreId;
    }

    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
    }

    public Integer getNameId() {
        return nameId;
    }

    public void setNameId(Integer nameId) {
        this.nameId = nameId;
    }

    public Integer getButtonType() {
        return buttonType;
    }

    public void setButtonType(Integer buttonType) {
        this.buttonType = buttonType;
    }

    public String getButtonName() {
        return buttonName;
    }

    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }

    public Integer getCustomerFlag() {
        return customerFlag;
    }

    public void setCustomerFlag(Integer customerFlag) {
        this.customerFlag = customerFlag;
    }

    public String getButtonNameEn() {
        return buttonNameEn;
    }

    public void setButtonNameEn(String buttonNameEn) {
        this.buttonNameEn = buttonNameEn;
    }

    public String getButtonNameTc() {
        return buttonNameTc;
    }

    public void setButtonNameTc(String buttonNameTc) {
        this.buttonNameTc = buttonNameTc;
    }

    public String getGenreEn() {
        return genreEn;
    }

    public void setGenreEn(String genreEn) {
        this.genreEn = genreEn;
    }

    public String getGenreTc() {
        return genreTc;
    }

    public void setGenreTc(String genreTc) {
        this.genreTc = genreTc;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameTc() {
        return nameTc;
    }

    public void setNameTc(String nameTc) {
        this.nameTc = nameTc;
    }
}