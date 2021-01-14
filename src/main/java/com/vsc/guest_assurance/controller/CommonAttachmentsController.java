package com.vsc.guest_assurance.controller;

import com.vsc.guest_assurance.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @author curry
 */
@RestController
@Api(tags = {"共通-附件"})
@RequestMapping("/common/attachments")
public class CommonAttachmentsController {
    @ApiOperation(value = "下载附件")
    @GetMapping(value = "download")
    public void download(
            HttpServletRequest request,
            @ApiParam(value = "url") @RequestParam String url,
            HttpServletResponse response) throws IOException {

        if (url.startsWith("classpath:static/image/")) {
            File file = new ClassPathResource(StringUtil.substring(url, "classpath:")).getFile();
            String fileName = file.getName();
            if ("png".equals(StringUtil.substring(fileName, "."))) {
                response.setContentType("image/png");
            }
            if ("jpg".equals(StringUtil.substring(fileName, ".")) || "jpeg".equals(StringUtil.substring(fileName, "."))) {
                response.setContentType("image/jpeg");
            }

            response.setHeader("content-disposition", "attachment; filename="
                    + URLEncoder.encode(fileName, "UTF-8"));

            IOUtils.copy(new FileInputStream(file), response.getOutputStream());
            response.getOutputStream().flush();
        }
    }
}
