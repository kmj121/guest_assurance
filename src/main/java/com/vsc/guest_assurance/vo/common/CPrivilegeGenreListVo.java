package com.vsc.guest_assurance.vo.common;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class CPrivilegeGenreListVo {
    @ApiModelProperty(value = "分类id")
    private Integer genreId;
    @ApiModelProperty(value = "分类名称")
    private String genre;
    @ApiModelProperty(value = "子类集合")
    private List<CPrivilegeNameListVo> cPrivilegeNameListVos;

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

    public List<CPrivilegeNameListVo> getcPrivilegeNameListVos() {
        return cPrivilegeNameListVos;
    }

    public void setcPrivilegeNameListVos(List<CPrivilegeNameListVo> cPrivilegeNameListVos) {
        this.cPrivilegeNameListVos = cPrivilegeNameListVos;
    }
}
