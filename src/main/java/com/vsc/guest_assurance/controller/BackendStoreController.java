package com.vsc.guest_assurance.controller;

import com.google.common.io.ByteStreams;
import com.vsc.guest_assurance.common.ExcelUtil;
import com.vsc.guest_assurance.common.MessageCode;
import com.vsc.guest_assurance.common.ResultObject;
import com.vsc.guest_assurance.config.Config;
import com.vsc.guest_assurance.entity.Stores;
import com.vsc.guest_assurance.service.AttachmentService;
import com.vsc.guest_assurance.service.StoresService;
import com.vsc.guest_assurance.vo.BackendContactInformationListVo;
import com.vsc.guest_assurance.service.ContactInformationService;
import com.vsc.guest_assurance.vo.BackendStoreListVo;
import com.vsc.guest_assurance.vo.BackendStoresThumbsUpVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author Roger
 * @Date 2020/9/27
 */
@RestController
@Api(tags = {"后台-门店-接口"})
@RequestMapping(value = "/backend/store")
public class BackendStoreController {

    @Autowired
    private StoresService storesService;
    @Autowired
    private AttachmentService attachmentService;

    @ApiOperation(value = "列表")
    @GetMapping(value = "/list")
    public ResultObject<BackendStoreListVo> list(
            HttpServletRequest request,
            @ApiParam(value = "关键字") @RequestParam(required = false) String keyWord,
            @ApiParam(value = "页数", required = true) @RequestParam Integer page,
            @ApiParam(value = "数量", required = true) @RequestParam Integer size) {
        return new ResultObject(MessageCode.CODE_SUCCESS, storesService.list(keyWord, page, size));
    }

    @ApiOperation(value = "数据导出")
    @RequestMapping(value = "/dataExport", method = RequestMethod.GET)
    @ResponseBody
    public void export(HttpServletRequest request, HttpServletResponse response,
            @ApiParam(value = "关键字") @RequestParam(required = false) String keyWord) throws IOException {
        List<Stores> storesList = storesService.list(keyWord);

        String[] titles = new String[] {"客户号","客户全称","客户负责人","客户类型","上级单位","SoldTo全称","SoldTo号","国家",
                "地址 1","城市","客户中文名","客户地区","客户简称","所属部门","经度","纬度","送货联系人","送货联系人电话","门店地址",
                "集团H4全称","集团H4号","集团H5号","集团H5名称"};

        List<String[]> contents = new ArrayList<>();
        List<String> data;
        for(Stores item : storesList) {
            data = new ArrayList<>();
            //客户号
            data.add(item.getAccountnumber());
            //客户全称
            data.add(item.getName());
            //客户全称：没有该字段
            data.add("");
            //客户类型
            data.add(item.getEcolabcn_account_type() == null ? "" : String.valueOf(item.getEcolabcn_account_type()));
            //上级单位
            data.add(item.get_parentaccountid_value());
            //SoldTo全称
            data.add(item.getEcolabcn_soldto_name());
            //SoldTo号
            data.add(item.getEcolabcn_soldto_number());
            //国家
            data.add(item.getEcolabcn_country());
            //地址 1
            data.add(item.getAddress1_composite());
            //城市
            data.add(item.getAddress1_City());
            //客户中文名
            data.add(item.getEcolabcn_chinese_name());
            //客户地区
            data.add(item.getAddress1_country());
            //客户简称
            data.add(item.getEcolabcn_short_name());
            //所属部门
            data.add(item.getEcolabcn_department() == null ? "" : String.valueOf(item.getEcolabcn_department()));
            //经度
            data.add(item.getAddress1_longitude() == null ? "" : String.valueOf(item.getAddress1_longitude()));
            //纬度
            data.add(item.getAddress1_latitude() == null ? "" : String.valueOf(item.getAddress1_latitude()));
            //送货联系人
            data.add(item.getEcolabcn_main_contact());
            //送货联系人电话：给的是telephone1字段，返回json中没有
            data.add(item.getAddress1_telephone1());
            //门店地址
            data.add(item.getAddress1_Line1());
            //集团H4全称
            data.add(item.getEcolabcn_h4_name());
            //集团H4号
            data.add(item.getEcolabcn_h4_number());
            //集团H5号
            data.add(item.getEcolabcn_h5number());
            //集团H5名称
            data.add(item.getEcolabcn_h5_name());

            contents.add(data.toArray(new String[] {}));
        }
        String filename = attachmentService.genFileNameByExt(".xlsx");
        File file = attachmentService.getFile(filename);
        ExcelUtil.buildExcel(new FileOutputStream(file), null, titles, contents, "门店数据列表");
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
        ByteStreams.copy(new FileInputStream(Config.attachFolder + filename), response.getOutputStream());
    }

    @ApiOperation("点赞门店列表")
    @RequestMapping(value = "/thumbsUpStores",  method = RequestMethod.GET)
    public ResultObject<List<BackendStoresThumbsUpVo>> storeList(HttpServletRequest request
            , @ApiParam(value = "经度", required = true) @RequestParam Float longitude
            , @ApiParam(value = "纬度", required = true) @RequestParam Float latitude) {
        return new ResultObject(MessageCode.CODE_SUCCESS, storesService.thumbsUpStores(longitude, latitude));
    }

    @ApiOperation("点赞门店")
    @RequestMapping(value = "/thumbsUp",  method = RequestMethod.GET)
    public ResultObject thumbsUp(HttpServletRequest request
            , @ApiParam(value = "门店id", required = true) @RequestParam Integer id
            , @ApiParam(value = "分数", required = true) @RequestParam Integer points) {
        storesService.thumbsUp(id, points);
        return new ResultObject(MessageCode.CODE_SUCCESS, null);
    }

}
