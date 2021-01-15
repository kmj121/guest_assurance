package com.vsc.guest_assurance.controller;

import com.vsc.guest_assurance.common.ApiException;
import com.vsc.guest_assurance.common.MessageCode;
import com.vsc.guest_assurance.common.ResultObject;
import com.vsc.guest_assurance.qo.BackendContactInformationDetailQo;
import com.vsc.guest_assurance.service.UsersService;
import com.vsc.guest_assurance.util.StringUtil;
import com.vsc.guest_assurance.vo.BackendUserDetailVo;
import com.vsc.guest_assurance.vo.BackendUserListVo;
import com.vsc.guest_assurance.vo.BackendUserUpdateVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

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
    private UsersService usersService;

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
        return new ResultObject(MessageCode.CODE_SUCCESS, usersService.list(email, userName, privilege, page, size));
    }

    @ApiOperation("更新")
    @PostMapping(value = "/update")
    public ResultObject edit(HttpServletRequest request, @RequestBody @Valid BackendUserUpdateVo backendUserUpdateVo
            , BindingResult errors) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, IOException {
        if(errors.hasErrors()) {
            return new ResultObject(MessageCode.CODE_PARAMETER_ERROR, null, errors.getAllErrors());
        }
        usersService.edit(backendUserUpdateVo);
        return new ResultObject(MessageCode.CODE_SUCCESS, null);
    }

    @ApiOperation("详情")
    @GetMapping(value = "/{id}/detail")
    public ResultObject<BackendUserDetailVo> detail(HttpServletRequest request, @ApiParam(value = "id",required = true) @PathVariable Integer id) {
        return new ResultObject<BackendUserDetailVo>(MessageCode.CODE_SUCCESS, usersService.detail(id));
    }
}
