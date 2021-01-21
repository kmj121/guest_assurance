package com.vsc.guest_assurance.controller.backend;

import com.vsc.guest_assurance.common.MessageCode;
import com.vsc.guest_assurance.common.MyUtils;
import com.vsc.guest_assurance.common.ResultObject;
import com.vsc.guest_assurance.qo.backend.BUserAddQo;
import com.vsc.guest_assurance.qo.backend.BUserUpdQo;
import com.vsc.guest_assurance.service.UsersService;
import com.vsc.guest_assurance.util.Util;
import com.vsc.guest_assurance.vo.backend.BRoleListVo;
import com.vsc.guest_assurance.vo.backend.BUserDetailVo;
import com.vsc.guest_assurance.vo.backend.BUserListVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @Description
 * @Author Roger
 * @Date 2020/9/27
 */
@RestController
@Api(tags = {"后台-用戶-接口"})
@RequestMapping(value = "/backend/user")
public class BUserController {

    @Autowired
    private UsersService usersService;

    @GetMapping(value = "/list")
    @ApiOperation(value = "用户列表")
    public ResultObject<BUserListVo> list(
            HttpServletRequest request,
            @ApiParam(value = "邮箱") @RequestParam(required = false) String email,
            @ApiParam(value = "用户名") @RequestParam(required = false) String userName,
            @ApiParam(value = "页数", required = true) @RequestParam int page,
            @ApiParam(value = "数量", required = true) @RequestParam int size
    ) throws Exception {
        email = MyUtils.replaceStrParam(email);
        userName = MyUtils.replaceStrParam(userName);
        return new ResultObject(MessageCode.CODE_SUCCESS, usersService.list(email, userName, page, size));
    }

    //@GetMapping(value = "/roleList")
    //@ApiOperation(value = "角色列表")
    //public ResultObject<List<BRoleListVo>> roleList(HttpServletRequest request) throws Exception {
    //    return new ResultObject(MessageCode.CODE_SUCCESS, usersService.roleList());
    //}
    //
    //@GetMapping(value = "/{userId}/detail")
    //@ApiOperation(value = "用户详情")
    //public ResultObject<BUserDetailVo> detail(
    //        HttpServletRequest request,
    //        @ApiParam(value = "用户Id", required = true) @PathVariable Integer userId
    //) throws Exception {
    //    return new ResultObject(MessageCode.CODE_SUCCESS, usersService.detail(userId, Util.getLoginToken(request)));
    //}
    //
    //@PutMapping(value = "/update")
    //@ApiOperation(value = "修改用户")
    //public ResultObject update(
    //        HttpServletRequest request,
    //        @RequestBody @Valid BUserUpdQo qo,
    //        BindingResult errors
    //) throws Exception {
    //    if (errors.hasErrors()) {
    //        return new ResultObject(MessageCode.CODE_PARAMETER_ERROR, errors.getAllErrors());
    //    }
    //    usersService.updateUser(qo, Util.getLoginToken(request));
    //    return new ResultObject(MessageCode.CODE_SUCCESS, Util.getLoginToken(request).getLanguage());
    //}

    @PutMapping(value = "/add")
    @ApiOperation(value = "新增用户")
    public ResultObject add(
            HttpServletRequest request,
            @RequestBody @Valid BUserAddQo qo,
            BindingResult errors
    ) throws Exception {
        if (errors.hasErrors()) {
            return new ResultObject(MessageCode.CODE_PARAMETER_ERROR, errors.getAllErrors());
        }
        usersService.add(qo, Util.getLoginToken(request));
        return new ResultObject(MessageCode.CODE_SUCCESS, null);
    }

    @GetMapping(value = "/{userId}/delete")
    @ApiOperation(value = "删除用户")
    public ResultObject delete(
            HttpServletRequest request,
            @ApiParam(value = "用户Id", required = true) @PathVariable Integer userId
    ) throws Exception {
        usersService.delete(userId, Util.getLoginToken(request));
        return new ResultObject(MessageCode.CODE_SUCCESS, null);
    }
}
