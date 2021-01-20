package com.vsc.guest_assurance.util;

import com.vsc.guest_assurance.entity.LoginTokens;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

/**
 * @Description
 * @Author Roger
 * @Date 2020/10/9
 */
public class Util {

    static Properties properties = null;
    static Properties propertiesEN = null;
    static Properties propertiesTC = null;

    /**
     * 取得登录token
     */
    public static LoginTokens getLoginToken(HttpServletRequest request) {
        LoginTokens loginToken = (LoginTokens) request.getAttribute("loginToken");
        return loginToken;
    }

    public static void loadMessage() throws UnsupportedEncodingException, IOException {
        properties = new Properties();
        properties.load(new InputStreamReader(Util.class.getResourceAsStream("/message.properties"), "UTF-8"));
    }

    public static void loadMessageEN() throws UnsupportedEncodingException, IOException {
        propertiesEN = new Properties();
        propertiesEN.load(new InputStreamReader(Util.class.getResourceAsStream("/message_EN.properties"), "UTF-8"));
    }

    public static void loadMessageTC() throws UnsupportedEncodingException, IOException {
        propertiesTC = new Properties();
        propertiesTC.load(new InputStreamReader(Util.class.getResourceAsStream("/message_TC.properties"), "UTF-8"));
    }

    public static String getMessage(String key, String... argv) {
        if (properties == null) {
            try {
                loadMessage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String message = properties.getProperty(key);

        if (argv != null) {
            for (String arg : argv) {
                message = message.replaceFirst("\\{[0-9]*\\}", arg);
            }
        }

        return message;
    }

    public static String getConfig(String key) throws IOException {
        Properties configProperties = new Properties();
        configProperties.load(new InputStreamReader(Util.class.getResourceAsStream("/application.properties"), "UTF-8"));
        String message = configProperties.getProperty(key);
        return message;
    }

    public static boolean mapValueIsEmpty(Map<Integer, String> map) {
        for(String value :map.values()){
            if(StringUtils.isNotBlank(value)) {
                return false;
            }
        }
        return true;
    }
}
