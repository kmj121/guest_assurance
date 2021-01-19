package com.vsc.guest_assurance.common;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.File;

/**
 * @Description
 * @Author Roger
 * @Date 2021/1/7
 */
public class ParseStores {
    private static String url = "https://ins-fsm-functionapp-001-d.azurewebsites.net/api/account/getScienceProgrameList";

    public static String testResumeParser() throws Exception {

        HttpGet httpGet = new HttpGet(url);

        // 设置头字段
        httpGet.addHeader("content-type", "application/json");

        // 发送请求
        HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = httpclient.execute(httpGet);

        // 处理返回结果
        String resCont = EntityUtils.toString(response.getEntity(), Consts.UTF_8);

        JSONObject res = new JSONObject(resCont);
        System.out.println("========" + res.toString(4) + "==========");
        return res.toString(4);
    }
    public static void main(String[] args) throws Exception {
        ParseStores.testResumeParser();
    }
}
