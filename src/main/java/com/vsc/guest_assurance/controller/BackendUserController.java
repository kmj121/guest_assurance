package com.vsc.guest_assurance.controller;

import com.vsc.guest_assurance.common.MessageCode;
import com.vsc.guest_assurance.common.ResultObject;
import com.vsc.guest_assurance.service.UserService;
import com.vsc.guest_assurance.util.StringUtil;
import com.vsc.guest_assurance.util.Util;
import com.vsc.guest_assurance.vo.BackendUserListVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description
 * @Author Roger
 * @Date 2020/9/27
 */
@RestController
@Api(tags = {"后台-用戶-接口"})
@RequestMapping(value = "/backend/user")
public class BackendUserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户列表")
    @GetMapping(value = "/list")
    public ResultObject<BackendUserListVo> list(
            HttpServletRequest request,
            @ApiParam(value = "邮箱") @RequestParam(required = false) String email,
            @ApiParam(value = "用户名") @RequestParam(required = false) String userName,
            @ApiParam(value = "人员权限:0无权限,1管理员权限", allowableValues = "0,1") @RequestParam(required = false) Integer privilege,
            @ApiParam(value = "页数", required = true) @RequestParam Integer page,
            @ApiParam(value = "数量", required = true) @RequestParam Integer size) {
        email = StringUtil.replaceStrParam(email);
        userName = StringUtil.replaceStrParam(userName);
        return new ResultObject(MessageCode.CODE_SUCCESS, userService.list(email, userName, privilege, page, size));
    }

    //@ApiOperation(value = "获取我的用户信息")
    //@GetMapping(value = "/userInfo")
    //public ResultObject<BackendUserListVo> detail(
    //        HttpServletRequest request) {
    //    return new ResultObject(MessageCode.CODE_SUCCESS, Util.getLoginToken(request).getLanguage(),
    //            usersService.userInfo(Util.getLoginToken(request)));
    //}
}
