package com.vsc.guest_assurance.common;

/**
 * @Description
 * @Author Roger
 * @Date 2020/10/9
 */
public class Constant {

    /**登录过期时间(30分钟)：测试为10倍*/
    public static final long LOGIN_TOKEN_LIMIT = 10 * 30 * 60 * 1000L;
    /**验证码过期时间(10分钟)*/
    public static final long CAPTCHA_LIMIT = 10 * 60 * 1000L;

    /** 一天毫秒数 */
    public static final long ONE_DAY = 24 * 60 * 60 * 1000L;
    /** 一小时毫秒数 */
    public static final long ONE_HOUR = 60 * 60 * 1000L;
    /** 一分钟秒数 */
    public static final long ONE_MINUTE = 60 * 1000L;
    /** 一天秒数 */
    public static final long SECOND_IN_ONE_DAY = 24 * 60 * 60L;

    /** 标志-FALSE */
    public static final int FALSE = 0;
    /** 标志-TRUE */
    public static final int TRUE = 1;

    /** 图片验证码：session字符 */
    public static final String SK_CAPTCHA = "captcha";

    /** 权限：所有权限 */
    public static String PRIVILEGE_ALL = "all";
    /** 用户：默认管理员用户 */
    public static final int USER_DEFAULT_ADMIN = 1;

    /** 后台用户 */
    public static final String TOKEN_TYPE_BACKEND = "backend";
    /** app用户 */
    public static final String TOKEN_TYPE_APP = "app";
    /** 微信用户*/
    public static final String TOKEN_TYPE_WECHAT = "wechat";

    /** 小程序参数*/
    public static final String APPID = "aa";
    public static final String APPSECRET = "bb";
    /** 获取openid接口*/
    public static final String WEIXIN_OPENID_URL = "https://api.weixin.qq.com/sns/jscode2session?appid={APPID}&secret={APPSECRET}&js_code={jscode}&grant_type=authorization_code";
    /** 获取unionid接口*/
    public static final String WEIXIN_UNIONID_URL = "https://api.weixin.qq.com/sns/userinfo?access_token={ACCESS_TOKEN}&openid={OPENID}&lang=zh_CN";

    /** 手机短信类型：注册*/
    public static final String MOBILE_CAPTCHA_TYPE_REGISTER   = "register_mobile";
    /** 手机短信类型：注册*/
    public static final String MOBILE_CAPTCHA_TYPE_CHANGE_MOBILE   = "change_mobile";

    /** 角色类型：超级管理员 */
    public static final int ROLE_TYPE_ADMIN = 1;
    /** 角色类型：市场部管理员 */
    public static final int ROLE_TYPE_MARKET_MANAGER = 2;
    /** 角色类型：研发管理员 */
    public static final int ROLE_TYPE_DEVELOPMENT_MANAGER = 3;
    /** 角色类型：行业顾问 */
    public static final int ROLE_TYPE_TS_MANAGER = 4;
    /** 角色类型：销售经理 */
    public static final int ROLE_TYPE_SALES_MANAGER = 5;
    /** 角色类型：销售 */
    public static final int ROLE_TYPE_SALESMAN = 6;
    /** 角色类型：服务工程师 */
    public static final int ROLE_TYPE_SERVICE_ENGINEER = 7;
    /** 角色类型：默认客户账号 */
    public static final int ROLE_TYPE_DEFAULT_CUSTOMER = 8;
    /** 角色类型：无权限账号 */
    public static final int ROLE_TYPE_NONE = 9;
    /** 角色类型：集团销售 */
    public static final int ROLE_TYPE_GROUP_SALES = 10;
    /** 角色类型：第三方劳务 */
    public static final int ROLE_TYPE_THIRD_CUSTOMER = 11;

    /** 用户类型：管理员用户 */
    public static final int USER_TYPE_ADMIN = 0;
    /** 用户类型：客户账号 */
    public static final int USER_TYPE_CUSTOMER = 1;
    /** 操作类型：查询 */
    public static final int OPERATE_TYPE_SELECT = 0;
    /** 操作类型：新增|修改 */
    public static final int OPERATE_TYPE_ADD_OR_UPDATE = 1;
    /** 操作类型：删除 */
    public static final int OPERATE_TYPE_DELETE = 2;

    /** 语言：简体中文 */
    public static final int LANGUAGE_SIMPLIFIED_CHINESE = 0;
    /** 语言：英文 */
    public static final int LANGUAGE_ENGLISH = 1;
    /** 语言：繁体中文 */
    public static final int LANGUAGE_TRADITIONAL_CHINESE = 2;

    /**状态标识：有效（已完成）**/
    public static final int STATE_VALID = 1;
    /**状态标识：无效（进行中）**/
    public static final int STATE_INVALID = 0;

    /** 文件服务器-容器：附件  */
    public static final String FILE_SERVICE_BLOB_ATTACHMENT = "commonfile";

    /** 手机验证码类型：登录 */
    public static final String MOBILE_CAPTCHA_TYPE_LOGIN = "login";

    /**方案表单类型-原表单**/
    public static final int PROGRAM_FORM_TYPE_DEFAULT = 0;
    /**方案表单类型-除硅后表单**/
    public static final int PROGRAM_FORM_TYPE_DESILICATION = 1;
    /**方案表单类型-N3108后表单**/
    public static final int PROGRAM_FORM_TYPE_N3108 = 2;

    /**行业类型-轻工业**/
    public static final int INDUSTRY_TYPE_LIGHT = 0;
    /**行业类型-重工业**/
    public static final int INDUSTRY_TYPE_HEAVY = 1;

    /**方案结果类型-轻工业**/
    public static final int PROGRAM_RESULT_TYPE_LIGHT = 0;
    /**方案结果类型-重工业-除硅预处理**/
    public static final int PROGRAM_RESULT_TYPE_DESILICATION = 1;
    /**方案结果类型-重工业-N3108预处理**/
    public static final int PROGRAM_RESULT_TYPE_N3108 = 2;
    /**方案结果类型-重工业-杀菌剂**/
    public static final int PROGRAM_RESULT_TYPE_HEAVY_B = 3;
    /**方案结果类型-重工业-阻垢剂**/
    public static final int PROGRAM_RESULT_TYPE_HEAVY_S = 4;
    /**方案结果类型-重工业-杀菌剂+阻垢剂**/
    public static final int PROGRAM_RESULT_TYPE_HEAVY_BS = 5;
    /**方案结果类型-重工业-消息内容**/
    public static final int PROGRAM_RESULT_TYPE_HEAVY_MSG = 6;

    /**方案结果详情类型-重工业-N3108预处理**/
    public static final int PROGRAM_RESULT_DETAIL_RECOMMENDATION_N3108 = 15;
    /**方案结果详情类型-重工业-除硅预处理-HERO工艺的意见**/
    public static final int PROGRAM_RESULT_DETAIL_RECOMMENDATION_HERO_SUGGESTIONS = 19;
    /**方案结果详情类型-重工业-除硅预处理-N1998SI剂量的建议**/
    public static final int PROGRAM_RESULT_DETAIL_RECOMMENDATION_N1998SI_RECOMMENDED = 16;
    /**方案结果详情类型-重工业-除硅预处理-N1998SI的意见**/
    public static final int PROGRAM_RESULT_DETAIL_RECOMMENDATION_N1998SI_SUGGESTION = 20;
    /**方案结果详情类型-重工业-除硅预处理-每立方米进水因投加N1998SI所需要额外补加液碱的重量**/
    public static final int PROGRAM_RESULT_DETAIL_RECOMMENDATION_EXTRA_CAUSTIC_NEEDED = 17;
    /**方案结果详情类型-重工业-除硅预处理-每立方米进水产生的脱水后污泥重量 (kg)**/
    public static final int PROGRAM_RESULT_DETAIL_RECOMMENDATION_SLUDGE_GENERATION = 18;
    /**方案结果详情类型-重工业-无需阻垢剂**/
    public static final int PROGRAM_RESULT_DETAIL_NO_NEED = 21;
    /**方案结果详情类型-重工业-非正常情况**/
    public static final int PROGRAM_RESULT_DETAIL_ERROR= 22;

    /** 性能预测逻辑-结果-保安过滤器滤芯更换周期延长**/
    public static final String PERFORMANCE_PREDICTIONS_CHEMICALS_PROGRAMME_CFRF = "保安过滤器滤芯更换周期延长";
    /** 性能预测逻辑-结果-反渗透膜在线清洗周期延长**/
    public static final String PERFORMANCE_PREDICTIONS_CHEMICALS_PROGRAMME_CIP = "反渗透膜在线清洗周期延长";
    /** 性能预测逻辑-结果-反渗透膜离线清洗周期延长**/
    public static final String PERFORMANCE_PREDICTIONS_CHEMICALS_PROGRAMME_OCF = "反渗透膜离线清洗周期延长";


}
