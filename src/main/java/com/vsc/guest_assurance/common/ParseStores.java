package com.vsc.guest_assurance.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.vsc.guest_assurance.entity.Stores;
import com.vsc.guest_assurance.vo.common.ParseStoresVo;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author Roger
 * @Date 2021/1/7
 */
public class ParseStores {
    private static String url = "https://ins-fsm-functionapp-001-d.azurewebsites.net/api/account/getScienceProgrameList";

    public static List<ParseStoresVo> storeParser() throws Exception {
        HttpGet httpGet = new HttpGet(url);
        // 设置头字段
        httpGet.addHeader("content-type", "application/json");

        // 发送请求
        HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = httpclient.execute(httpGet);

        // 处理返回结果
        String resCont = EntityUtils.toString(response.getEntity(), Consts.UTF_8);

        JSONObject res = JSON.parseObject(resCont);
        System.out.println("========" + res.toString() + "==========");
        JSONArray jsonArray = res.getJSONArray("account");
        List<ParseStoresVo> list = new ArrayList<>();
        if (jsonArray != null && jsonArray.size() > 0) {
            for (int i = 0; i < jsonArray.size(); i++) {
                //Json串反序列化成对象
                ParseStoresVo parseStoresVo = JSON.parseObject(jsonArray.get(i).toString(), ParseStoresVo.class);
                parseStoresVo.set_parentaccountid_value(((JSONObject) jsonArray.get(i)).getString("_parentaccountid_value"));
                parseStoresVo.set_ownerid_value(((JSONObject) jsonArray.get(i)).getString("_ownerid_value"));
                list.add(parseStoresVo);
            }
        }
        return list;
    }

    public static void main(String[] args) throws Exception {
        ParseStores.storeParser();
    }
}
