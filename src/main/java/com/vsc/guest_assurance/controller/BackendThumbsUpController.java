package com.vsc.guest_assurance.controller;

import com.vsc.guest_assurance.common.MessageCode;
import com.vsc.guest_assurance.common.ResultObject;
import com.vsc.guest_assurance.entity.Stores;
import com.vsc.guest_assurance.service.StoresService;
import com.vsc.guest_assurance.vo.BackendStoreListVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Description
 * @Author Roger
 * @Date 2020/9/27
 */
@RestController
@Api(tags = {"后台-点赞-接口"})
@RequestMapping(value = "/backend/thumbsUp")
public class BackendThumbsUpController {

    @Autowired
    private StoresService storesService;

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
        //List<UserRegionDetailDtoOut> userRegionDetailDtoOuts = userService.getMyRegion(Util.getLoginToken(request));
        //boolean adminFlag;
        //if(userRegionDetailDtoOuts==null){
        //    adminFlag = true;
        //}else {
        //    adminFlag = false;
        //}
        //
        //List<SchoolListDtoOut> listDtoOuts = schoolMapper.search(provinceCode, cityCode, areaCode, schoolId, managerId, status,
        //        adminFlag, userRegionDetailDtoOuts);
        //
        //String[] titles = new String[] {"编号","省","市","区县","学校名称","地址","联系人","联系方式","小学年级数","中学年级数","基准点","状态"};
        //
        //List<String[]> contents = new ArrayList<>();
        //List<String> data;
        //for(SchoolListDtoOut item : listDtoOuts) {
        //    data = new ArrayList<>();
        //    //编号
        //    data.add(String.valueOf(item.getId()));
        //    //省
        //    data.add(item.getProvinceDesc() == null ? "" : item.getProvinceDesc());
        //    //市
        //    data.add(item.getCityDesc() == null ? "" : item.getCityDesc());
        //    //区县
        //    data.add(item.getAreaDesc() == null ? "" : item.getAreaDesc());
        //    //学校名称
        //    data.add(item.getSchoolName() == null ? "" : item.getSchoolName());
        //    //地址
        //    data.add(item.getAddress() == null ? "" : item.getAddress());
        //    //联系人
        //    data.add(item.getLinkman() == null ? "" : item.getLinkman());
        //    //联系方式
        //    data.add(item.getPhone() == null ? "" : item.getPhone());
        //    //学校管理
        //    //List<String> list = schoolMapper.selectSchoolManageBySchoolId(item.getId());
        //    //StringBuilder sb = new StringBuilder();
        //    //if (list != null && list.size() >= Constant.COUNT1) {
        //    //    for (int i = 0; i < list.size(); i++) {
        //    //        sb.append(list.get(i)).append(Constant.separator);
        //    //    }
        //    //    data.add(sb.toString().substring(0, sb.toString().length() - 1));
        //    //} else {
        //    //    data.add("");
        //    //}
        //    //小学年级数
        //    data.add(item.getPrimaryGrades() == null ? "" : String.valueOf(item.getPrimaryGrades()));
        //    //初中年级数
        //    data.add(item.getJuniorGrades() == null ? "" : String.valueOf(item.getJuniorGrades()));
        //    //基准点
        //    data.add(item.getEquipmentNumber() == null ? "" : item.getEquipmentNumber());
        //    //状态
        //    if (Constant.VALID.equals(item.getStatus())) {
        //        data.add(Constant.VALID_DESC);
        //    } else {
        //        data.add(Constant.INVALID_DESC);
        //    }
        //
        //    contents.add(data.toArray(new String[] {}));
        //}
        //String filename = attachmentService.genFileNameByExt(".xlsx");
        //File file = attachmentService.getFile(filename);
        //ExcelUtil.buildExcel(new FileOutputStream(file), null, titles, contents, "学校数据列表");
        //response.setContentType("application/vnd.ms-excel");
        //response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
        //ByteStreams.copy(new FileInputStream(Config.attachFolder + filename), response.getOutputStream());
    }

    //@ApiOperation("详情")
    //@GetMapping(value = "/{id}/detail")
    //public ResultObject<BackendContactInformationDetailQo> detail(HttpServletRequest request, @ApiParam(value = "id",required = true) @PathVariable Integer id) {
    //    return new ResultObject<BackendContactInformationDetailQo>(MessageCode.CODE_SUCCESS, contactInformationService.detail(id));
    //}
}
