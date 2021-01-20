package com.vsc.guest_assurance.vo.backend;

import io.swagger.annotations.ApiModelProperty;

public class BPrivilegeVo {

    @ApiModelProperty(value = "权限id")
    private Integer id;
    @ApiModelProperty(value = "类型")
    private String type;
    @ApiModelProperty(value = "分类id")
    private Integer genreId;
    @ApiModelProperty(value = "分类名称")
    private String genre;
    @ApiModelProperty(value = "子类id")
    private Integer nameId;
    @ApiModelProperty(value = "子类名称")
    private String name;
    @ApiModelProperty(value = "按钮类型")
    private Integer buttonType;
    @ApiModelProperty(value = "按钮名称")
    private String buttonName;

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

    public Integer getGenreId() {
        return genreId;
    }

    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getNameId() {
        return nameId;
    }

    public void setNameId(Integer nameId) {
        this.nameId = nameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
