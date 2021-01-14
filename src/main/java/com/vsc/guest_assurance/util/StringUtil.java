package com.vsc.guest_assurance.util;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author Roger
 * @Date 2020/9/27
 */
public class StringUtil {
    /**
     * 模糊查询特殊字符处理
     *
     * @param str
     * @return
     */
    public static String replaceStrParam(String str) {
        str = StringUtils.trimToNull(str);
        if (StringUtils.isBlank(str)) {
            return str;
        }
        str = str.replace("[", "[[]");
        str = str.replace("%", "[%]");
        str = str.replace("_", "[_]");
        str = str.replace("/", "[/]");
        return str;
    }

    /**
     * 判断字符串是否为空
     *
     * @param	str
     * @return	true: 为空,	false: 不为空
     */
    public static boolean empty(String str) {
        if (str == null || "".equals(str.trim()) || "(null)".equals(str)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 去除首尾空字符
     */
    public static String trim(String str) {
        if (str == null) {
            return str;
        }

        return str.trim();
    }

    /**
     * String to List<Long>
     */
    public static List<Long> toLongList(String templateMeasureIds) {
        List<Long> list = new ArrayList<>();

        if (StringUtils.isEmpty(templateMeasureIds)) {
            return list;
        }

        String[] arr = templateMeasureIds.split(",");
        for (String item : arr) {
            list.add(Long.parseLong(item));
        }

        return list;
    }

    /**
     * 从指定字符串后开始截取
     * @param string
     * @param separator
     * @return
     */
    public static String substring(String string, String separator) {
        int begin=string.indexOf(separator) + separator.length();
        int last=string.length();
        return string.substring(begin,last);
    }
}
