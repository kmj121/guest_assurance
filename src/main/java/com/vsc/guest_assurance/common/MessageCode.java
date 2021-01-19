package com.vsc.guest_assurance.common;

/**
 * @Description
 * @Author Roger
 * @Date 2020/10/9
 */
public enum MessageCode {
    CODE_SUCCESS(0),
    /** 参数错误*/
    CODE_PARAMETER_ERROR(9000),
    /** 请先登录*/
    CODE_NEED_LOGIN(9001),
    /** 没有权限*/
    CODE_NO_PRIVILEGE(9002),

    /**客户不存在**/
    CODE_NO_CUSTOMER(8006),
    /** 微信code有误 */
    CODE_WXCODE_ERROR(9911),
    /** 用户不存在,请先注册 */
    CODE_CUSTOMER_ERROR(9912),
    /** 手机号已存在是否换绑 */
    CODE_PHONE_ERROR(9913),
    /** 手机验证码请求过于频繁 */
    CODE_MOBILE_CAPTCHA_ERROR(9038),
    /** 图片验证码有误*/
    CODE_IMAGE_CAPTCHA_ERROR(9043),
    /** 密码错误*/
    CODE_PASSWORD_ERROR(9027),
    /** 该用户不可删除*/
    CODE_USER_CAN_NOT_DELETE(9026),
    /** 没有用户*/
    CODE_NO_USER(9003),
    /** 角色已被使用，不可删除*/
    CODE_ROLE_DELETE_ERROR(9045),
    /** 客户账号不存在*/
    CODE_USER_CUSTOMER_NOT_EXIST(9024),
    /** 角色有误*/
    CODE_NO_ROLE(9042),
    /** 无权限账号无法修改权限*/
    CODE_NONE_CAN_NOT_UPDATE(9041),
    /** 超級管理员无法修改权限*/
    CODE_ADMIN_CAN_NOT_UPDATE(9040),
    /** 用户不存在*/
    CODE_USER_NOT_EXIST(9023),
    /** 文件不存在*/
    CODE_NO_FILE(9005),
    /** 文件格式错误 */
    CODE_FILE_ERROR(8058),
    /** 状态有误*/
    CODE_STATE_ERROR(9035),
    /** 区域不存在*/
    CODE_COUNTRY_NOT_EXIST(9019),
    /** 短信发送失败*/
    CODE_CUSTOMER_CAPTCHA_ERROR(9065),
    /** 有多条{1}数据*/
    CODE_ONE_MORE_DATA(9066),
    /** {1}数据不存在*/
    CODE_NOT_EXIST(9067),


    /** 无权限操作当前数据*/
    CODE_DATA_PRIVILEGE_ERROR(9996),
    /** 系统异常*/
    CODE_EXCEPTION(9999);

	private int code;
	MessageCode(int code) {
		this.code = code;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
}
