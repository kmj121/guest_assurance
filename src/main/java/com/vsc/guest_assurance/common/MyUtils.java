package com.vsc.guest_assurance.common;


import com.vsc.guest_assurance.entity.LoginTokens;
import com.vsc.guest_assurance.util.Util;
import com.vsc.guest_assurance.vo.BPrivilegeVo;
import com.vsc.guest_assurance.vo.CPrivilegeGenreListVo;
import com.vsc.guest_assurance.vo.CPrivilegeNameListVo;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

public class MyUtils {

    /**
     * 构建权限树
     *
     * @param bPrivilegeVos
     * @return
     */
    public static List<CPrivilegeGenreListVo> buildPrivileges(List<BPrivilegeVo> bPrivilegeVos) {
        if (bPrivilegeVos == null || bPrivilegeVos.isEmpty()) {
            return new ArrayList<>();
        }
        List<CPrivilegeGenreListVo> cPrivilegeGenreListVos = new ArrayList<>();
        //按分类id分组
        Map<Integer, List<BPrivilegeVo>> cPrivilegeGenreMap = bPrivilegeVos.stream()
                .collect(Collectors.groupingBy(BPrivilegeVo::getGenreId));

        List<Integer> genreIdList = cPrivilegeGenreMap.keySet().stream()
                .sorted(Comparator.comparing(Integer::intValue))
                .collect(Collectors.toList());
        for (Integer genreId : genreIdList) {
            CPrivilegeGenreListVo cPrivilegeGenreListVo = new CPrivilegeGenreListVo();

            cPrivilegeGenreListVo.setGenreId(genreId);
            cPrivilegeGenreListVo.setGenre(cPrivilegeGenreMap.get(genreId).get(0).getGenre());

            List<CPrivilegeNameListVo> cPrivilegeNameListVos = new ArrayList<>();
            //按子类id分组
            Map<Integer, List<BPrivilegeVo>> cPrivilegeNameMap = cPrivilegeGenreMap.get(genreId).stream()
                    .collect(Collectors.groupingBy(BPrivilegeVo::getNameId));
            List<Integer> nameIdList = cPrivilegeNameMap.keySet().stream()
                    .sorted(Comparator.comparing(Integer::intValue))
                    .collect(Collectors.toList());
            for (Integer nameId : nameIdList) {
                CPrivilegeNameListVo cPrivilegeNameListVo = new CPrivilegeNameListVo();

                cPrivilegeNameListVo.setNameId(nameId);
                cPrivilegeNameListVo.setName(cPrivilegeNameMap.get(nameId).get(0).getName());
                cPrivilegeNameListVo.setbPrivilegeVos(cPrivilegeNameMap.get(nameId));

                cPrivilegeNameListVos.add(cPrivilegeNameListVo);
            }

            cPrivilegeGenreListVo.setcPrivilegeNameListVos(cPrivilegeNameListVos);
            cPrivilegeGenreListVos.add(cPrivilegeGenreListVo);
        }

        return cPrivilegeGenreListVos;
    }

    ///**
    // * 构建城市树
    // *
    // * @param cCityTreeVoList
    // * @return
    // */
    //public static List<CProvinceTreeVo> buildAreaTree(List<CCityTreeVo> cCityTreeVoList) {
    //    List<CProvinceTreeVo> cProvinceTreeVoList = new ArrayList<>();
    //    if (cCityTreeVoList == null || cCityTreeVoList.isEmpty()) {
    //        return cProvinceTreeVoList;
    //    }
    //
    //    //按省份id分组
    //    Map<Integer, List<CCityTreeVo>> cProvinceTreeVoMap = cCityTreeVoList.stream()
    //            .collect(Collectors.groupingBy(CCityTreeVo::getProvinceId));
    //    for (Integer provinceId : cProvinceTreeVoMap.keySet()) {
    //        CProvinceTreeVo cProvinceTreeVo = new CProvinceTreeVo();
    //        cProvinceTreeVo.setProvinceId(provinceId);
    //        cProvinceTreeVo.setProvinceName(cProvinceTreeVoMap.get(provinceId).get(0).getProvinceName());
    //        cProvinceTreeVo.setcCityTreeVoList(cProvinceTreeVoMap.get(provinceId));
    //        cProvinceTreeVoList.add(cProvinceTreeVo);
    //    }
    //    return cProvinceTreeVoList;
    //}
    //
    ///**
    // * 构建输入参数树
    // *
    // * @param inputParametersListVos
    // * @return
    // */
    //public static List<CLabelClassListVo> buildInputParam(List<CInputParametersListVo> inputParametersListVos) {
    //    List<CLabelClassListVo> cLabelClassListVos = new ArrayList<>();
    //    if (inputParametersListVos == null || inputParametersListVos.isEmpty()) {
    //        return cLabelClassListVos;
    //    }
    //
    //    //按省份id分组
    //    Map<Integer, List<CInputParametersListVo>> collect = inputParametersListVos.stream()
    //            .collect(Collectors.groupingBy(CInputParametersListVo::getLabelClassId));
    //    for (Integer labelClassId : collect.keySet()) {
    //        CLabelClassListVo cLabelClassListVo = new CLabelClassListVo();
    //        cLabelClassListVo.setLabelClassId(labelClassId);
    //        cLabelClassListVo.setLabelClassName(collect.get(labelClassId).get(0).getLabelClassName());
    //        cLabelClassListVo.setcInputParametersListVos(collect.get(labelClassId));
    //        cLabelClassListVos.add(cLabelClassListVo);
    //    }
    //    return cLabelClassListVos;
    //}
    //
    //public static List<BGeneralServiceWaterAnalysisResultListVo> buildWaterAnalysisResult(
    //        List<BGeneralServiceWaterAnalysisResultListVo> resultListVos) {
    //    Map<Date, String> dateMap = new HashMap<>();
    //    //构建时间全集
    //    for (BGeneralServiceWaterAnalysisResultListVo vos : resultListVos) {
    //        for (BGeneralServiceWaterAnalysisResultVo vo : vos.getbGeneralServiceWaterAnalysisResultVoList()) {
    //            dateMap.put(vo.getSamplingTime(), "");
    //        }
    //    }
    //    //排序后的时间集合
    //    List<Date> dateList = dateMap.keySet().stream().sorted(Comparator.comparing(Date::getTime))
    //            .collect(Collectors.toList());
    //    //填充时间缺省值
    //    for (BGeneralServiceWaterAnalysisResultListVo vos : resultListVos) {
    //        List<BGeneralServiceWaterAnalysisResultVo> voList = new ArrayList<>();
    //        //获取原数据
    //        Map<Date, BGeneralServiceWaterAnalysisResultVo> bGeneralServiceWaterAnalysisResultVoMap =
    //                vos.getbGeneralServiceWaterAnalysisResultVoList().stream()
    //                        .collect(Collectors.toMap(BGeneralServiceWaterAnalysisResultVo::getSamplingTime, a -> a));
    //        for (Date date : dateList) {
    //            BGeneralServiceWaterAnalysisResultVo vo = new BGeneralServiceWaterAnalysisResultVo();
    //            vo.setSamplingTime(date);
    //            if (bGeneralServiceWaterAnalysisResultVoMap.containsKey(date)) {
    //                vo.setParameterValue(bGeneralServiceWaterAnalysisResultVoMap.get(date).getParameterValue());
    //            } else {
    //                vo.setParameterValue(null);
    //            }
    //            voList.add(vo);
    //        }
    //        vos.setbGeneralServiceWaterAnalysisResultVoList(voList);
    //    }
    //    return resultListVos;
    //}
    //
    ///**
    // * 校验膜组数量
    // *
    // * @param groupsNumber
    // * @param useNumber
    // * @param reserveNumber
    // * @param loginTokens
    // */
    //public static void checkGroupsNumber(BigDecimal groupsNumber, BigDecimal useNumber, BigDecimal reserveNumber,
    //                                     LoginTokens loginTokens) {
    //    if (!useNumber.add(reserveNumber).equals(groupsNumber)) {
    //        throw new ApiException(MessageCode.CODE_GROUP_NUMBER_ERROR, loginTokens.getLanguage());
    //    }
    //}

    /**
     * 模糊查询特殊字符处理
     *
     * @param str
     * @return
     */
    public static String replaceStrParam(String str) {
        str = StringUtils.trimToNull(str);
        if (!StringUtils.isNotBlank(str)) {
            return str;
        }
        str = str.replace("[", "[[]");
        str = str.replace("%", "[%]");
        str = str.replace("_", "[_]");
        str = str.replace("/", "[/]");
        return str;
    }


    ///**
    // * 获取登录用短信验证码
    // *
    // * @param captcha
    // * @param mobile
    // * @throws IOException
    // */
    //public static void sendMobileCaptcha(String captcha, String mobile, Integer language) throws IOException {
    //    String content = URLEncoder.encode("您的手机验证码：" + captcha + "，请在10分钟内使用。", "gb2312");
    //    sendMessages(content, mobile, null, language);
    //}

    ///**
    // * 创建客户账号发送短信
    // *
    // * @param password
    // * @param mobile
    // * @throws IOException
    // */
    //public static void sendCreateCustomerUser(String password, String mobile, Integer language) throws IOException {
    //    String content = URLEncoder.encode("恭喜您成为纳尔科Recycle Xpert系统的用户，用户名：" + mobile + "，密码："
    //            + password + ",请勿将用户名和密码泄露给其他人。", "gb2312");
    //    sendMessages(content, mobile, "2051012022690", language);
    //}


    ///**
    // * 重置客户账号密码发送短信
    // *
    // * @param password
    // * @param mobile
    // * @throws IOException
    // */
    //public static void sendResetCustomerUser(String password, String mobile, Integer language) throws IOException {
    //    String content = URLEncoder.encode("尊敬的纳尔科Recycle Xpert用户，您的密码已重置为："
    //            + password + ",请勿将用户名和密码泄露给其他人。", "gb2312");
    //    sendMessages(content, mobile, "2051012022693", language);
    //}

    ///**
    // * 设置短信参数
    // *
    // * @param content
    // * @param mobile
    // * @throws IOException
    // */
    //private static void sendMessages(String content, String mobile, String extendAccessNum, Integer language) throws IOException {
    //    String url = "https://api.ums86.com:9600/sms/Api/Send.do";
    //    String serialNumber = Util.genCaptcha(7) + System.currentTimeMillis();//20位数
    //    Map<String, String> params = new HashMap<>();
    //    params.put("SpCode", Util.getConfig("config.message.spCode"));//企业编号
    //    params.put("LoginName", Util.getConfig("config.message.loginName"));//用户名称
    //    params.put("Password", Util.getConfig("config.message.password"));//用户密码
    //    params.put("MessageContent", content);//短信内容
    //    params.put("UserNumber", mobile);//手机号码(多个号码用”,”分隔)
    //    params.put("SerialNumber", serialNumber);//流水号，20位数字
    //    if (extendAccessNum != null)
    //        params.put("ExtendAccessNum", extendAccessNum);//流水号，20位数字
    //    params.put("ScheduleTime", "");//立即发送填空
    //    params.put("f", "1");//提交时检测方式
    //    String msg = HttpRequestUtil.sendPost(url, params);
    //    System.out.println(msg);
    //    String[] strings = msg.split("&");
    //    Map<String, String> stringMap = new HashMap<>();
    //    for (String s : strings) {
    //        String[] value = s.split("=");
    //        if (value.length > 1) {
    //            stringMap.put(value[0], value[1]);
    //        } else {
    //            stringMap.put(value[0], "");
    //        }
    //    }
    //    if (!stringMap.get("result").equals("0")) {
    //        throw new ApiException(MessageCode.CODE_CUSTOMER_CAPTCHA_ERROR, language);
    //    }
    //}

    ///**
    // * @param industryType
    // * @param bactericideFlag
    // * @param scaleInhibitorFlag
    // * @param qo
    // * @param loginTokens
    // * @return
    // * @throws IllegalAccessException
    // * @throws NoSuchMethodException
    // * @throws InvocationTargetException
    // */
    //public static BProgramsBaseAddQo buildProgramsBaseAddQo(Integer industryType, Integer bactericideFlag
    //        , Integer scaleInhibitorFlag, BProgramsAddQo qo, Integer state, LoginTokens loginTokens)
    //        throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
    //    if (industryType.equals(Constant.INDUSTRY_TYPE_LIGHT)) {
    //        BProgramsLightIndustryAddQo addQo = new BProgramsLightIndustryAddQo();
    //        PropertyUtils.copyProperties(addQo, qo);
    //        if (state.equals(Constant.STATE_COMPLETED)) {
    //            addQo.checkParameters(loginTokens);
    //        }
    //        addQo.setIndustryType(industryType);
    //        return addQo;
    //    } else {
    //        if (bactericideFlag.equals(Constant.TRUE) && scaleInhibitorFlag.equals(Constant.FALSE)) {
    //            BProgramsHeavyIndustryBAddQo addQo = new BProgramsHeavyIndustryBAddQo();
    //            PropertyUtils.copyProperties(addQo, qo);
    //            if (state.equals(Constant.STATE_COMPLETED)) {
    //                addQo.checkParameters(loginTokens);
    //            }
    //            addQo.setIndustryType(industryType);
    //            return addQo;
    //        } else if (bactericideFlag.equals(Constant.FALSE) && scaleInhibitorFlag.equals(Constant.TRUE)) {
    //            BProgramsHeavyIndustrySAddQo addQo = new BProgramsHeavyIndustrySAddQo();
    //            PropertyUtils.copyProperties(addQo, qo);
    //            addQo.checkParameters(loginTokens);
    //            addQo.setIndustryType(industryType);
    //            return addQo;
    //        } else {
    //            BProgramsHeavyIndustryBSAddQo addQo = new BProgramsHeavyIndustryBSAddQo();
    //            PropertyUtils.copyProperties(addQo, qo);
    //            if (state.equals(Constant.STATE_COMPLETED)) {
    //                addQo.checkParameters(loginTokens);
    //            }
    //            addQo.setIndustryType(industryType);
    //            return addQo;
    //        }
    //    }
    //}
    //
    //public static void buildProgramDetail(BProgramsDetailVo bProgramsDetailVo, LoginTokens loginTokens) {
    //    if (bProgramsDetailVo.getbProgramParameterVoList() == null
    //            || bProgramsDetailVo.getbProgramParameterVoList().isEmpty()) {
    //        throw new ApiException(MessageCode.CODE_EXCEPTION, loginTokens.getLanguage());
    //    } else if (bProgramsDetailVo.getbProgramParameterVoList().size() == 1) {
    //        BProgramParameterVo bProgramParameterVo = bProgramsDetailVo.getbProgramParameterVoList().get(0);
    //        if (!bProgramParameterVo.getFormType().equals(Constant.PROGRAM_FORM_TYPE_DEFAULT)) {
    //            throw new ApiException(MessageCode.CODE_EXCEPTION, loginTokens.getLanguage());
    //        }
    //        //参数部分
    //        bProgramsDetailVo.setFormParamFlag(Constant.PROGRAM_FORM_TYPE_DEFAULT);
    //        //结果部分
    //        if (bProgramParameterVo.getbProgramParameterResultVo() == null) {
    //            //新建暂存无结果内容
    //            bProgramsDetailVo.setResultFlag(null);
    //            bProgramsDetailVo.setFormType(Constant.PROGRAM_FORM_TYPE_DEFAULT);
    //        } else {
    //            bProgramsDetailVo.setResultFlag(bProgramParameterVo.getbProgramParameterResultVo().getResultType());
    //            bProgramsDetailVo.setResultParamFlag(Constant.PROGRAM_FORM_TYPE_DEFAULT);
    //            if (bProgramsDetailVo.getResultFlag().equals(Constant.PROGRAM_RESULT_TYPE_LIGHT)
    //                    || bProgramsDetailVo.getResultFlag().equals(Constant.PROGRAM_RESULT_TYPE_HEAVY_B)
    //                    || bProgramsDetailVo.getResultFlag().equals(Constant.PROGRAM_RESULT_TYPE_HEAVY_S)
    //                    || bProgramsDetailVo.getResultFlag().equals(Constant.PROGRAM_RESULT_TYPE_HEAVY_BS)) {
    //                //确定结果时无需展示表单内容
    //                bProgramsDetailVo.setFormType(null);
    //            } else if (bProgramsDetailVo.getResultFlag().equals(Constant.PROGRAM_RESULT_TYPE_DESILICATION)) {
    //                bProgramsDetailVo.setFormType(Constant.PROGRAM_FORM_TYPE_DESILICATION);
    //            } else if (bProgramsDetailVo.getResultFlag().equals(Constant.PROGRAM_RESULT_TYPE_N3108)) {
    //                bProgramsDetailVo.setFormType(Constant.PROGRAM_FORM_TYPE_N3108);
    //            }
    //        }
    //    } else if (bProgramsDetailVo.getbProgramParameterVoList().size() == 2) {
    //        BProgramParameterVo bProgramParameterVoBase = null;
    //        BProgramParameterVo bProgramParameterVoAfter = null;
    //        for (BProgramParameterVo bProgramParameterVo : bProgramsDetailVo.getbProgramParameterVoList()) {
    //            if (bProgramParameterVo.getFormType().equals(Constant.PROGRAM_FORM_TYPE_DEFAULT)) {
    //                bProgramParameterVoBase = bProgramParameterVo;
    //            } else {
    //                bProgramParameterVoAfter = bProgramParameterVo;
    //            }
    //        }
    //        if (bProgramParameterVoBase == null || bProgramParameterVoAfter == null) {
    //            throw new ApiException(MessageCode.CODE_EXCEPTION, loginTokens.getLanguage());
    //        }
    //        //参数部分
    //        bProgramsDetailVo.setFormParamFlag(bProgramParameterVoAfter.getFormType());
    //        //结果部分
    //        if (bProgramParameterVoAfter.getbProgramParameterResultVo() == null) {
    //            //使用原表单结果
    //            bProgramsDetailVo.setResultFlag(bProgramParameterVoBase.getbProgramParameterResultVo().getResultType());
    //            bProgramsDetailVo.setResultParamFlag(bProgramParameterVoBase.getFormType());
    //            bProgramsDetailVo.setFormType(bProgramParameterVoAfter.getFormType());
    //        } else {
    //            bProgramsDetailVo.setResultFlag(bProgramParameterVoAfter.getbProgramParameterResultVo().getResultType());
    //            bProgramsDetailVo.setResultParamFlag(bProgramParameterVoAfter.getFormType());
    //            if (bProgramsDetailVo.getResultFlag().equals(Constant.PROGRAM_RESULT_TYPE_DESILICATION)
    //                    || bProgramsDetailVo.getResultFlag().equals(Constant.PROGRAM_RESULT_TYPE_HEAVY_B)
    //                    || bProgramsDetailVo.getResultFlag().equals(Constant.PROGRAM_RESULT_TYPE_HEAVY_S)
    //                    || bProgramsDetailVo.getResultFlag().equals(Constant.PROGRAM_RESULT_TYPE_HEAVY_BS)) {
    //                //确定结果时无需展示表单内容
    //                bProgramsDetailVo.setFormType(null);
    //            } else if (bProgramsDetailVo.getResultFlag().equals(Constant.PROGRAM_RESULT_TYPE_N3108)) {
    //                bProgramsDetailVo.setFormType(Constant.PROGRAM_FORM_TYPE_N3108);
    //            }
    //        }
    //
    //    } else if (bProgramsDetailVo.getbProgramParameterVoList().size() == 3) {
    //        BProgramParameterVo bProgramParameterVoD = null;
    //        BProgramParameterVo bProgramParameterVoN = null;
    //        for (BProgramParameterVo bProgramParameterVo : bProgramsDetailVo.getbProgramParameterVoList()) {
    //            if (bProgramParameterVo.getFormType().equals(Constant.PROGRAM_FORM_TYPE_DESILICATION)) {
    //                bProgramParameterVoD = bProgramParameterVo;
    //            }
    //            if (bProgramParameterVo.getFormType().equals(Constant.PROGRAM_FORM_TYPE_N3108)) {
    //                bProgramParameterVoN = bProgramParameterVo;
    //            }
    //        }
    //        if (bProgramParameterVoD == null || bProgramParameterVoN == null) {
    //            throw new ApiException(MessageCode.CODE_EXCEPTION, loginTokens.getLanguage());
    //        }
    //        //参数部分
    //        bProgramsDetailVo.setFormType(bProgramParameterVoN.getFormType());
    //        bProgramsDetailVo.setFormParamFlag(bProgramParameterVoN.getFormType());
    //        //结果部分
    //        if (bProgramParameterVoN.getbProgramParameterResultVo() == null) {
    //            //使用原表单结果
    //            bProgramsDetailVo.setResultFlag(bProgramParameterVoD.getbProgramParameterResultVo().getResultType());
    //            bProgramsDetailVo.setResultParamFlag(bProgramParameterVoD.getFormType());
    //        } else {
    //            bProgramsDetailVo.setResultFlag(bProgramParameterVoN.getbProgramParameterResultVo().getResultType());
    //            bProgramsDetailVo.setResultParamFlag(bProgramParameterVoN.getFormType());
    //            if (bProgramsDetailVo.getResultFlag().equals(Constant.PROGRAM_RESULT_TYPE_DESILICATION)
    //                    || bProgramsDetailVo.getResultFlag().equals(Constant.PROGRAM_RESULT_TYPE_HEAVY_B)
    //                    || bProgramsDetailVo.getResultFlag().equals(Constant.PROGRAM_RESULT_TYPE_HEAVY_S)
    //                    || bProgramsDetailVo.getResultFlag().equals(Constant.PROGRAM_RESULT_TYPE_HEAVY_BS)) {
    //                //确定结果时无需展示表单内容
    //                bProgramsDetailVo.setFormType(null);
    //            }
    //        }
    //    } else {
    //        throw new ApiException(MessageCode.CODE_EXCEPTION, loginTokens.getLanguage());
    //    }
    //
    //}
}
