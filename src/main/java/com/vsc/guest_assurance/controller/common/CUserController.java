package com.vsc.guest_assurance.controller.common;

import com.vsc.guest_assurance.common.MessageCode;
import com.vsc.guest_assurance.common.ResultObject;
import com.vsc.guest_assurance.qo.common.CLoginQo;
import com.vsc.guest_assurance.service.UsersService;
import com.vsc.guest_assurance.vo.common.CLoginTokenVo;
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
@Api(tags = {"共通-用户-接口"})
@RequestMapping("/common/users")
public class CUserController {
    @Autowired
    private UsersService usersService;

    @PostMapping(value = "/login")
    @ApiOperation(value = "aad登录")
    public ResultObject<CLoginTokenVo> login(
            HttpServletRequest request,
            @Valid @RequestBody CLoginQo qo,
            BindingResult errors
    ) throws Exception {
        if (errors.hasErrors()) {
            return new ResultObject(MessageCode.CODE_PARAMETER_ERROR, qo.getLanguage(), errors.getAllErrors());
        }
        return new ResultObject(MessageCode.CODE_SUCCESS, usersService.login(qo.getToken(), qo.getType(), request.getRemoteAddr(), qo.getLanguage()));
    }
}
