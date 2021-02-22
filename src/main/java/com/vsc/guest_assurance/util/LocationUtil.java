package com.vsc.guest_assurance.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.vsc.guest_assurance.vo.LocationVo;

public class LocationUtil {

    /**
     * <p>
     * Title: GetLocationMsg
     * </p>
     * <p>
     * Description:高德地图API
     * </p>
     *
     * @param longitude
     * @param latitude
     * @return
     */
    public static LocationVo getLocationMsg(double longitude, double latitude) {
        LocationVo locationVo = new LocationVo();
        String message = "";
        String address = "";
        String key = "47cafad30007d87ff51f09c00bd3f422";
        // 高德地图逆地理编码API
        String url = String.format(
                "https://restapi.amap.com/v3/geocode/regeo?output=JSON&key=%s&radius=1000&location=%s,%s",
                key, longitude, latitude);
        URL myURL = null;
        URLConnection httpsConn = null;

        try {
            myURL = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            httpsConn = (URLConnection) myURL.openConnection();
            httpsConn.setConnectTimeout(100000);
            if (httpsConn != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(
                        httpsConn.getInputStream(), "UTF-8");
                BufferedReader br = new BufferedReader(inputStreamReader);
                String data = null;
                while ((data = br.readLine()) != null) {
                    message = message + data;
                }
                JSONObject jsonObject = JSON.parseObject(message);
                JSONObject addressObject = jsonObject.getJSONObject("regeocode").getJSONObject("addressComponent");
                locationVo.setProvinceName(addressObject.getString("province"));
                locationVo.setCityName(addressObject.getString("city"));
                locationVo.setDistrictName(addressObject.getString("district"));
                locationVo.setAdcode(addressObject.getInteger("adcode"));

                //JsonParser jp = new JsonParser();
                //将json字符串转化成json对象
                //JsonObject jo = jp.parse(message).getAsJsonObject();
                //String status = jo.get("status").getAsString();
                //String addressJsonEle = jo.get("regeocode").getAsJsonObject().get("formatted_address").toString();
                //if (addressJsonEle.equals("[]")) {
                //    address = null;
                //} else {
                //    if (jo.get("regeocode").getAsJsonObject().get("pois").getAsJsonArray().size() <= 0) {
                //        String detail = jo.get("regeocode").getAsJsonObject().get("addressComponent").getAsJsonObject().get("streetNumber").getAsJsonObject().get("street").getAsString() + jo.get("regeocode").getAsJsonObject().get("addressComponent").getAsJsonObject().get("streetNumber").getAsJsonObject().get("number").getAsString();
                //        if (status.equals("1") && !addressJsonEle.equals("[]")) {
                //            address = addressJsonEle + " " + detail;
                //        }
                //    } else {
                //        String detail = jo.get("regeocode").getAsJsonObject().get("pois").getAsJsonArray().get(0).getAsJsonObject().get("name").getAsString();
                //        String detailDistance = jo.get("regeocode").getAsJsonObject().get("pois").getAsJsonArray().get(0).getAsJsonObject().get("distance").getAsString();
                //        if (status.equals("1") && !addressJsonEle.equals("[]")) {
                //            address = addressJsonEle + " " + detail + " " + detailDistance.substring(0, detailDistance.lastIndexOf(".")) + "米";
                //        }
                //    }
                //}
                inputStreamReader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return locationVo;
    }

    ///**
    // * <p>
    // * Title: GetLocationMsgs
    // * </p>
    // * <p>
    // * Description:百度地图aPI
    // * </p>
    // *
    // * @param longitude
    // * @param latitude
    // * @return
    // */
    //public static String GetLocationMsgs(double latitude, double longitude) {
    //
    //    String message = "";
    //
    //    String address = "";
    //
    //    // 百度地图逆地理编码API
    //    String url = String.format(
    //            "http://api.map.baidu.com/reverse_geocoding/v3/?ak=你的ak&extensions_poi=1&radius=1000&output=json&coordtype=bd09ll&location=%s,%s",
    //            latitude, longitude);
    //
    //    URL myURL = null;
    //
    //    URLConnection httpsConn = null;
    //
    //    try {
    //
    //        myURL = new URL(url);
    //
    //    } catch (MalformedURLException e) {
    //
    //        e.printStackTrace();
    //
    //    }
    //
    //    try {
    //
    //        httpsConn = (URLConnection) myURL.openConnection();
    //
    //        if (httpsConn != null) {
    //
    //            InputStreamReader insr = new InputStreamReader(
    //
    //                    httpsConn.getInputStream(), "UTF-8");
    //
    //            BufferedReader br = new BufferedReader(insr);
    //
    //            String data = null;
    //
    //            while ((data = br.readLine()) != null) {
    //
    //                message = message + data;
    //
    //            }
    //
    //            JsonParser jp = new JsonParser();
    //            //将json字符串转化成json对象
    //            JsonObject jo = jp.parse(message).getAsJsonObject();
    //
    //            String status = jo.get("status").getAsString();
    //
    //            if (jo.get("result").getAsJsonObject().get("pois").getAsJsonArray().size() <= 0) {
    //                String adds = jo.get("result").getAsJsonObject().get("formatted_address").getAsString();
    //                address = adds;
    //
    //            } else {
    //                JsonElement addressJsonEle = jo.get("result").getAsJsonObject().get("addressComponent");
    //
    //                String adds = jo.get("result").getAsJsonObject().get("formatted_address").getAsString();
    //
    //                String details = jo.get("result").getAsJsonObject().get("pois").getAsJsonArray().get(0).getAsJsonObject().get("name").getAsString() + " " +
    //                        jo.get("result").getAsJsonObject().get("pois").getAsJsonArray().get(0).getAsJsonObject().get("distance").getAsString() + "米";
    //
    //                if (status.equals("0")) {
    //                    address = adds + " " + details;
    //                }
    //            }
    //
    //            insr.close();
    //
    //        }
    //
    //    } catch (IOException e) {
    //
    //        e.printStackTrace();
    //
    //    }
    //
    //    return address;
    //
    //}

    ///**
    // * <p>
    // * Title: GetLocationMsgForForeign
    // * </p>
    // * <p>
    // * Description: ArcGIS地图
    // * </p>
    // *
    // * @param longitude
    // * @param latitude
    // * @return
    // */
    //public static String GetLocationMsgForForeign(double longitude, double latitude) {
    //
    //    String message = "";
    //
    //    String address = "";
    //
    //    // 高德地图逆地理编码API
    //
    //    String url = String.format(
    //            "http://geocode.arcgis.com/arcgis/rest/services/World/GeocodeServer/reverseGeocode?f=pjson&langCode=EN&featureTypes=&location=%s,%s",
    //            longitude, latitude);
    //
    //    URL myURL = null;
    //
    //    URLConnection httpsConn = null;
    //
    //    try {
    //
    //        myURL = new URL(url);
    //
    //    } catch (MalformedURLException e) {
    //
    //        e.printStackTrace();
    //
    //    }
    //
    //    try {
    //
    //        httpsConn = (URLConnection) myURL.openConnection();
    //        httpsConn.setConnectTimeout(100000);
    //        if (httpsConn != null) {
    //
    //            InputStreamReader insr = new InputStreamReader(
    //
    //                    httpsConn.getInputStream(), "UTF-8");
    //
    //            BufferedReader br = new BufferedReader(insr);
    //
    //            String data = null;
    //
    //            while ((data = br.readLine()) != null) {
    //
    //                message = message + data;
    //
    //            }
    //
    //            JsonParser jp = new JsonParser();
    //            //将json字符串转化成json对象
    //            JsonObject jo = jp.parse(message).getAsJsonObject();
    //
    //            if (!jo.has("error")) {
    //                address = jo.get("address").getAsJsonObject().get("LongLabel").getAsString();
    //
    //            }
    //
    //
    //            insr.close();
    //
    //        }
    //
    //    } catch (IOException e) {
    //
    //        e.printStackTrace();
    //
    //    }
    //
    //    return address;
    //
    //}

    public final static void main(String[] args) {
        // 百度经纬度和高德地图经纬度，位置相反
        //114.196517, 22.380436
        getLocationMsg(104.0112, 30.62652);
        //String baiduResult = GetLocationMsgs(25.189741, 114.065240);
        //String result = GetLocationMsgForForeign(114.065240, 25.189741);
        //System.out.println("高德地址===" + gaodeResult);
        //System.out.println("百度地址===" + baiduResult);
        //System.out.println("国际地址===" + result);

    }

}