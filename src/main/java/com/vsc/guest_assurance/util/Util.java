package com.vsc.guest_assurance.util;

import com.vsc.guest_assurance.common.Constant;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
    //public static LoginToken getLoginToken(HttpServletRequest request) {
    //    LoginToken loginToken = (LoginToken) request.getAttribute("loginToken");
    //    return loginToken;
    //}

    /**
     * URI参数编码
     */
    public static String encodeUriParam(String param) {
        try {
            if (param == null) {
                return null;
            }
//			return new String(param.getBytes("ISO-8859-1"), "UTF-8");
            return new String(param.getBytes("UTF-8"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static <T> T getFromMap(Map map, Object key, Class<T> classT) {
        T result = (T) map.get(key);

        if (result == null) {
            try {
                result = classT.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            map.put(key, result);
        }

        return result;
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

    /**
     * 四舍五入
     *
     * @param value
     * @return
     */
    public static BigDecimal round(Number value) {
        if (value == null) {
            return null;
        }
        return new BigDecimal(value.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 验证集合
     */
    public static void validCollection(Validator validator, Collection collection, Errors errors) {
        for (Object object : collection) {
            ValidationUtils.invokeValidator(validator, object, errors);
        }
    }

    /**
     * 生成随机数
     */
    public static Integer getRandom(int size) {
        Double b = Math.random();
        b = b * size;
        return b.intValue();
    }

    /**
     * 生成随机数
     */
    public static List<Integer> getRandomList(int size, int num) {
        Map<Integer, Integer> map = new HashMap<>();
        if (size <= num) {
            while (map.size() < size) {
                int i = ((Double) (Math.random() * size)).intValue();
                map.put(i, i);
            }
        } else {
            while (map.size() < num) {
                int i = ((Double) (Math.random() * size)).intValue();
                map.put(i, i);
            }
        }
        return new ArrayList<>(map.keySet());
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().toLowerCase().trim().replaceAll("-", "");
    }

    public static Map<String, String> convertRequestParamsToMap(HttpServletRequest request) {
        Map<String, String> retMap = new HashMap();

        Set<Map.Entry<String, String[]>> entrySet = request.getParameterMap().entrySet();

        for (Map.Entry<String, String[]> entry : entrySet) {
            String name = entry.getKey();
            String[] values = entry.getValue();
            int valLen = values.length;

            if (valLen == 1) {
                retMap.put(name, values[0]);
            } else if (valLen > 1) {
                StringBuilder sb = new StringBuilder();
                for (String val : values) {
                    sb.append(",").append(val);
                }
                retMap.put(name, sb.toString().substring(1));
            } else {
                retMap.put(name, "");
            }
        }

        return retMap;
    }

    private static char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
            'f'};

    public static String getMD5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {

            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }

        byte[] md5Bytes = md5.digest(byteArray);

        StringBuffer hexValue = new StringBuffer();

        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }

        return hexValue.toString();
    }

    public static String getMD5(InputStream fileStream) {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] buffer = new byte[2048];
            int length = -1;
            while ((length = fileStream.read(buffer)) != -1) {
                md.update(buffer, 0, length);
            }
            byte[] b = md.digest();
            return byteToHexString(b);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            try {
                fileStream.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private static String byteToHexString(byte[] tmp) {
        String s;
        // 用字节表示就是 16 个字节
        // 每个字节用 16 进制表示的话，使用两个字符， 所以表示成 16 进制需要 32 个字符
        char[] str = new char[16 * 2];
        // 表示转换结果中对应的字符位置
        int k = 0;
        // 从第一个字节开始，对 MD5 的每一个字节
        for (int i = 0; i < 16; i++) {
            // 转换成 16 进制字符的转换
            // 取第 i 个字节
            byte byte0 = tmp[i];
            // 取字节中高 4 位的数字转换, >>> 为逻辑右移，将符号位一起右移
            str[k++] = hexDigits[byte0 >>> 4 & 0xf];
            // 取字节中低 4 位的数字转换
            str[k++] = hexDigits[byte0 & 0xf];
        }
        // 换后的结果转换为字符串
        s = new String(str);
        return s;
    }

    /**
     * 根据当前浏览器构建请求头
     * @param userAgent
     * @param saveName
     * @return
     * @throws UnsupportedEncodingException
     */
    //public static String buildSaveName(String userAgent, String saveName) throws UnsupportedEncodingException {
    //    if ((userAgent.contains("safari") && !userAgent.contains("chrome"))
    //            || userAgent.contains("micromessenger") || userAgent.contains("iphone")) {
    //        //safari浏览器和微信浏览器和iphone手机
    //        saveName = "filename*=utf-8''" + URLEncoder.encode(saveName, "UTF-8").replace("+", "%20");
    //    } else if (userAgent.contains("firefox")) {
    //        saveName = "filename=" + "=?UTF-8?B?" + (new String(Base64.encodeBase64(saveName.getBytes("UTF-8")))) + "?=";
    //    } else if (userAgent.contains("android")) {
    //        saveName = "filename=" + URLEncoder.encode(saveName, "UTF-8").replace("+", "%20");
    //    } else {
    //        saveName = "filename=" + URLEncoder.encode(saveName, "UTF-8").replace("+", "%20");
    //    }
    //    return saveName;
    //}

    /**
     * 重命名文件
     *
     * @param fileName
     * @return
     */
    public static void renameFile(String filePath, String fileName) {
        SimpleDateFormat fmdate = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String oldFileName = filePath + fileName;
        File oldFile = new File(oldFileName);
        String newFileName = filePath + fileName + "." + fmdate.format(new Date());
        File newFile = new File(newFileName);
        if (oldFile.exists() && oldFile.isFile()) {
            oldFile.renameTo(newFile);
        }
    }

    public static final char[] PASSWORD_CHAR = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
            'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    /**
     * 生成
     * @param length
     * @return
     */
    public static String genPassword(int length) {
        String password = "";

        for (int i = 0; i < length; i++) {
            password += PASSWORD_CHAR[RandomUtils.nextInt(36)];
        }

        return password;
    }

    /**
     * 生成验证码
     */
    public static String genCaptcha(int length) {
        String captcha = "";

        for (int i = 0; i < length; i++) {
            captcha += RandomUtils.nextInt(10);
        }

        return captcha;
    }


    /**
     * 验证是否为数字
     * StringUtils无法校验负号以及小数点
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        //^-?\d+$  整数
        //浮点数
        Pattern pattern = Pattern.compile("^(-?\\d+)(\\.\\d+)?$");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

    public static String separateToString(List<Integer> list, String delimited) {
        List<String> stringList = new ArrayList<>();
        for (Integer item : list) {
            stringList.add(item.toString());
        }
        if(CollectionUtils.isNotEmpty(stringList)) {
            return stringList.stream().collect(Collectors.joining(delimited));
        }
        return "";
    }

    public static boolean mapValueIsEmpty(Map<Integer, String> map) {
        for(String value :map.values()){
            if(StringUtils.isNotBlank(value)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] argv) {
        renameFile("C:\\Users\\gread\\Desktop", "q1.pdf");
    }
}
