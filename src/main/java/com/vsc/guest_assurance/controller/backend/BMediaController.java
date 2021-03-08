package com.vsc.guest_assurance.controller.backend;

import com.vsc.guest_assurance.common.MessageCode;
import com.vsc.guest_assurance.common.ResultObject;
import com.vsc.guest_assurance.service.MediaService;
import com.vsc.guest_assurance.vo.backend.BMediaVo;
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

/**
 * @Description
 * @Author Roger
 * @Date 2020/9/27
 */
@RestController
@Api(tags = {"后台-媒体-接口"})
@RequestMapping(value = "/backend/media")
public class BMediaController {

    @Autowired
    private MediaService mediaService;

    @ApiOperation(value = "媒体列表")
    @GetMapping(value = "/list")
    public ResultObject<BMediaVo> list(
            HttpServletRequest request,
            @ApiParam(value = "页数", required = true) @RequestParam Integer page,
            @ApiParam(value = "数量", required = true) @RequestParam Integer size) throws IOException, DocumentException {
        return new ResultObject(MessageCode.CODE_SUCCESS, mediaService.list(page, size));
    }
}
