package com.vsc.guest_assurance.vo.backend;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class BPrivilegeListVo {

    @ApiModelProperty(value = "类型")
    private String type;
    @ApiModelProperty(value = "分类id")
    private Integer genreId;
    @ApiModelProperty(value = "分类名称")
    private String genre;
    @ApiModelProperty(value = "权限子类集合")
    private List<BPrivilegeItemVo> privilegeItemVoList;

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

    public List<BPrivilegeItemVo> getPrivilegeItemVoList() {
        return privilegeItemVoList;
    }

    public void setPrivilegeItemVoList(List<BPrivilegeItemVo> privilegeItemVoList) {
        this.privilegeItemVoList = privilegeItemVoList;
    }
}
