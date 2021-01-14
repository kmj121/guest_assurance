package com.vsc.guest_assurance.controller;

import com.vsc.guest_assurance.common.MessageCode;
import com.vsc.guest_assurance.common.ResultObject;
import com.vsc.guest_assurance.qo.BackendContactInformationAddQo;
import com.vsc.guest_assurance.service.ContactInformationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @Description
 * @Author Roger
 * @Date 2020/9/27
 */
@RestController
@Api(tags = {"后台-联系方式-接口"})
@RequestMapping(value = "/backend/contactInformation")
public class BackendContactInformationController {

    @Autowired
    private ContactInformationService contactInformationService;

    @PostMapping(value = "/add")
    @ApiOperation(value = "新增")
    public ResultObject<Integer> add(
            HttpServletRequest request,
            @Valid @RequestBody BackendContactInformationAddQo qo,
            BindingResult errors
    ) throws Exception {
        if (errors.hasErrors()) {
            return new ResultObject(MessageCode.CODE_PARAMETER_ERROR,
                    errors.getAllErrors());
        }
        return new ResultObject(MessageCode.CODE_SUCCESS, contactInformationService.add(qo));
    }
}
