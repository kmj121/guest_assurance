package com.vsc.guest_assurance.vo.backend;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class BPrivilegeTypeVo {

    @ApiModelProperty(value = "权限类型集合(backend)")
    private List<BPrivilegeListVo> bPrivilegeListVoListBackend;
    @ApiModelProperty(value = "权限类型集合(app)")
    private List<BPrivilegeListVo> bPrivilegeListVoListApp;

    public List<BPrivilegeListVo> getbPrivilegeListVoListBackend() {
        return bPrivilegeListVoListBackend;
    }

    public void setbPrivilegeListVoListBackend(List<BPrivilegeListVo> bPrivilegeListVoListBackend) {
        this.bPrivilegeListVoListBackend = bPrivilegeListVoListBackend;
    }

    public List<BPrivilegeListVo> getbPrivilegeListVoListApp() {
        return bPrivilegeListVoListApp;
    }

    public void setbPrivilegeListVoListApp(List<BPrivilegeListVo> bPrivilegeListVoListApp) {
        this.bPrivilegeListVoListApp = bPrivilegeListVoListApp;
    }
}
