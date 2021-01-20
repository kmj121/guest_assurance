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


    /** 没有用户*/
    CODE_NO_USER(9003),
    /** 有多条{1}数据*/
    CODE_ONE_MORE_DATA(9004),
    /** {1}数据不存在*/
    CODE_NOT_EXIST(9005),
    /** 不能修改自己权限*/
    CODE_USER_PRIVILEGE_ERROR(9006),


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
