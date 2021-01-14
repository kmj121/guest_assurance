package com.vsc.guest_assurance.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author Roger
 * @Date 2021/1/13
 */
public class PageUtil {
    public static List getData(Integer page, Integer pageSize, List list) {
        List resultList = new ArrayList();
        if (page * pageSize - pageSize + 1 > list.size()) {
            resultList = null;
        } else if(page * pageSize - pageSize + 1 <= list.size() && page * pageSize > list.size()) {
            resultList = list.subList(page * pageSize - pageSize, list.size());
        } else {
            resultList = list.subList(page * pageSize - pageSize, page * pageSize);
        }
        return resultList;
    }
}
