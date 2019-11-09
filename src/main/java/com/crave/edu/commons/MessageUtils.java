package com.crave.edu.commons;

import com.crave.edu.util.HttpUtil;
import com.crave.edu.util.SignUtils;

import java.util.HashMap;
import java.util.Map;

public class MessageUtils {

    public static String saiYouSend(String mobile){
        int code = (int) Math.round(Math.random() * (9999-1000) + 1000);
        String content = "【仨致】您的报名短信验证码："+code+"，请在10分钟内输入";
        Map<String,String> param = new HashMap<String,String>();
        param.put("appid", "43068");
        param.put("to", mobile);
        param.put("content", content);
        param.put("signature", "3948f51e61337af7bfdd9fc75e1227e5");

        Map<String,String> requestProperty = new HashMap<String,String>();
//        requestProperty.put("Content-type", "multipart/form-data");

        String paramStr = SignUtils.sortMapKeyStr(param);
        System.out.println(paramStr);
        String s = HttpUtil.doPost("https://api.mysubmail.com/message/send", paramStr, requestProperty);
        System.out.println(s);
        return code+"";
    }
}