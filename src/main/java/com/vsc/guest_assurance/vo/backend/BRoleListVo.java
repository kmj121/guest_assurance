package com.vsc.guest_assurance.vo.backend;

import io.swagger.annotations.ApiModelProperty;

public class BRoleListVo {

    @ApiModelProperty(value="角色Id")
    private Integer Id;

    @ApiModelProperty(value="角色名")
    private String roleName;

    @ApiModelProperty(value = "角色说明")
    private String roleInfo;

    @ApiModelProperty(value = "角色权限")
    private String privilegeIds;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleInfo() {
        return roleInfo;
    }

    public void setRoleInfo(String roleInfo) {
        this.roleInfo = roleInfo;
    }

    public String getPrivilegeIds() {
        return privilegeIds;
    }

    public void setPrivilegeIds(String privilegeIds) {
        this.privilegeIds = privilegeIds;
    }
}
