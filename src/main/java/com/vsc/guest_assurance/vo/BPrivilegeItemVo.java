package com.vsc.guest_assurance.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class BPrivilegeItemVo {


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
    @ApiModelProperty(value = "权限集合")
    private List<BPrivilegeVo> bPrivilegeVoList;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public List<BPrivilegeVo> getbPrivilegeVoList() {
        return bPrivilegeVoList;
    }

    public void setbPrivilegeVoList(List<BPrivilegeVo> bPrivilegeVoList) {
        this.bPrivilegeVoList = bPrivilegeVoList;
    }
}
