package com.vsc.guest_assurance.vo.common;

import com.vsc.guest_assurance.vo.backend.BPrivilegeVo;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class CLoginTokenVo {
    @ApiModelProperty(value="用户id", required = true)
    private int userId;
    @ApiModelProperty(value="token", required = true)
    private String token;
    @ApiModelProperty(value="权限列表", required = true)
    private String privileges;
    @ApiModelProperty(value = "用户名称")
    private String name;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "角色名称")
    private String roleName;
    @ApiModelProperty(value = "角色类型")
    private Integer roleId;
    @ApiModelProperty(value = "权限集合")
    private List<BPrivilegeVo> privilegeVoList;
    @ApiModelProperty(value = "用户类型：0aad用户；1普通用户")
    private Integer type;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPrivileges() {
        return privileges;
    }

    public void setPrivileges(String privileges) {
        this.privileges = privileges;
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public List<BPrivilegeVo> getPrivilegeVoList() {
        return privilegeVoList;
    }

    public void setPrivilegeVoList(List<BPrivilegeVo> privilegeVoList) {
        this.privilegeVoList = privilegeVoList;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
