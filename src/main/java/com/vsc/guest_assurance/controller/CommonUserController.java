package com.vsc.guest_assurance.controller;

import com.vsc.guest_assurance.common.MessageCode;
import com.vsc.guest_assurance.common.ResultObject;
import com.vsc.guest_assurance.qo.CLoginQo;
import com.vsc.guest_assurance.service.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author curry
 */
@RestController
@Api(tags = {"共通-用户"})
@RequestMapping("/common/user")
public class CommonUserController {
    @Autowired
    private UsersService usersService;

    @PostMapping(value = "/login")
    @ApiOperation(value = "登录")
    public ResultObject login(
            HttpServletRequest request,
            @Valid @RequestBody CLoginQo qo,
            BindingResult errors
    ) throws Exception {
        if (errors.hasErrors()) {
            return new ResultObject(MessageCode.CODE_PARAMETER_ERROR, errors.getAllErrors());
        }
        usersService.login(qo.getToken(), qo.getType(), request.getRemoteAddr(), qo.getLanguage());
        return new ResultObject(MessageCode.CODE_SUCCESS, null);
    }
}
