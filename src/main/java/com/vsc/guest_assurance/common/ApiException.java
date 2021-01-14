package com.vsc.guest_assurance.common;


import com.vsc.guest_assurance.util.Util;

/**
 * @Description
 * @Author Roger
 * @Date 2020/9/27
 */
public class ApiException extends RuntimeException {

    private static final long serialVersionUID = 267105423560454711L;
    private MessageCode code;
    private String messageKey;
    private String[] argv = null;

    public ApiException(MessageCode code) {
        this.code = code;
        this.messageKey = code.name();
    }

    public ApiException(MessageCode code, String... argv) {
        this.code = code;
        this.messageKey = code.name();
        this.argv = argv;
    }

    public MessageCode getCode() {
        return code;
    }

    public void setCode(MessageCode code) {
        this.code = code;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }

    public String[] getArgv() {
        return argv;
    }

    public void setArgv(String[] argv) {
        this.argv = argv;
    }

    @Override
    public String getMessage() {
        return Util.getMessage(messageKey, argv);
    }
}
