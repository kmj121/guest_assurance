package com.vsc.guest_assurance.controller.backend;

import com.vsc.guest_assurance.common.MessageCode;
import com.vsc.guest_assurance.common.ResultObject;
import com.vsc.guest_assurance.qo.backend.BContactInformationAddQo;
import com.vsc.guest_assurance.qo.backend.BContactInformationDetailQo;
import com.vsc.guest_assurance.vo.backend.BContactInformationListVo;
import com.vsc.guest_assurance.service.ContactInformationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
public class BContactInformationController {

    @Autowired
    private ContactInformationService contactInformationService;

    @PostMapping(value = "/add")
    @ApiOperation(value = "新增")
    public ResultObject<Integer> add(
            HttpServletRequest request,
            @Valid @RequestBody BContactInformationAddQo qo,
            BindingResult errors
    ) throws Exception {
        if (errors.hasErrors()) {
            return new ResultObject(MessageCode.CODE_PARAMETER_ERROR,
                    errors.getAllErrors());
        }
        return new ResultObject(MessageCode.CODE_SUCCESS, contactInformationService.add(qo));
    }

    @ApiOperation(value = "列表")
    @GetMapping(value = "/list")
    public ResultObject<BContactInformationListVo> list(
            HttpServletRequest request,
            @ApiParam(value = "关键字") @RequestParam(required = false) String keyWord,
            @ApiParam(value = "页数", required = true) @RequestParam Integer page,
            @ApiParam(value = "数量", required = true) @RequestParam Integer size) {
        return new ResultObject(MessageCode.CODE_SUCCESS, contactInformationService.list(keyWord, page, size));
    }

    @ApiOperation("详情")
    @GetMapping(value = "/{id}/detail")
    public ResultObject<BContactInformationDetailQo> detail(HttpServletRequest request, @ApiParam(value = "id",required = true) @PathVariable Integer id) {
        return new ResultObject<BContactInformationDetailQo>(MessageCode.CODE_SUCCESS, contactInformationService.detail(id));
    }
}
