package com.vsc.guest_assurance.controller.common;

import com.vsc.guest_assurance.common.MessageCode;
import com.vsc.guest_assurance.common.ResultObject;
import com.vsc.guest_assurance.service.MediaService;
import com.vsc.guest_assurance.service.RegionService;
import com.vsc.guest_assurance.vo.backend.BMediaVo;
import com.vsc.guest_assurance.vo.backend.BRegionPullDownListVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @Description
 * @Author Roger
 * @Date 2020/9/27
 */
@RestController
@Api(tags = {"共通-区域-接口"})
@RequestMapping(value = "/common/region")
public class CRegionController {

    @Autowired
    private RegionService regionService;

    @ApiOperation(value = "区域下拉列表")
    @GetMapping(value = "/pullDownList")
    public ResultObject<List<BRegionPullDownListVo>> pullDownList(
            HttpServletRequest request,
            @ApiParam(value = "省") @RequestParam(required = false) Integer province,
            @ApiParam(value = "市") @RequestParam(required = false) Integer city) {
        return new ResultObject(MessageCode.CODE_SUCCESS, regionService.pullDownList(province, city));
    }
}
