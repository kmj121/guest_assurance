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

    /** 权限：所有权限 */
    public static String PRIVILEGE_ALL = "all";
    /** 用户：默认管理员用户 */
    public static final int USER_DEFAULT_ADMIN = 1;

    /** 后台用户 */
    public static final String TOKEN_TYPE_BACKEND = "backend";

    public static final int LANGUAGE_CN = 0;

    /** 角色类型：普通管理员 */
    public static final int ROLE_TYPE_ADMIN = 1;
    /** 角色类型：普通用户 */
    public static final int ROLE_TYPE_NORMAL = 2;

    /** 用户类型：管理员用户 */
    public static final int USER_TYPE_ADMIN = 0;
    /** 用户类型：客户账号 */
    public static final int USER_TYPE_CUSTOMER = 1;


}
