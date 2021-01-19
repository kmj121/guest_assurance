package com.vsc.guest_assurance.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class CPrivilegeNameListVo {
    @ApiModelProperty(value = "子类id")
    private Integer nameId;
    @ApiModelProperty(value = "子类名称")
    private String name;
    @ApiModelProperty(value = "权限集合")
    private List<BPrivilegeVo> bPrivilegeVos;

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

    public List<BPrivilegeVo> getbPrivilegeVos() {
        return bPrivilegeVos;
    }

    public void setbPrivilegeVos(List<BPrivilegeVo> bPrivilegeVos) {
        this.bPrivilegeVos = bPrivilegeVos;
    }

}
