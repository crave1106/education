package com.crave.edu.util;

import java.net.URLEncoder;
import java.util.*;

public class SignUtils {

    /** <一句话功能简述>
     * <功能详细描述>验证返回参数
     * @param params
     * @param key
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static boolean checkParam(Map<String,String> params,String key){
        boolean result = false;
        if(params.containsKey("sign")){
            String sign = params.get("sign");
            params.remove("sign");
            StringBuilder buf = new StringBuilder((params.size() +1) * 10);
            SignUtils.buildPayParams(buf,params,false);
            String preStr = buf.toString();
            String signRecieve = MD5.sign(preStr, "&key=" + key, "utf-8");
            result = sign.equalsIgnoreCase(signRecieve);
        }
        return result;
    }

    /**
     * @author
     * @param payParams
     * @return
     */
    public static void buildPayParams(StringBuilder sb, Map<String, String> payParams, boolean encoding){
        List<String> keys = new ArrayList<String>(payParams.keySet());
        Collections.sort(keys);
        for(String key : keys){
            sb.append(key).append("=");
            if(encoding){
                sb.append(urlEncode(payParams.get(key)));
            }else{
                sb.append(payParams.get(key));
            }
            sb.append("&");
        }
        sb.setLength(sb.length() - 1);
    }

    public static String urlEncode(String str){
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (Throwable e) {
            return str;
        }
    }

    /**
     * 解析Map为参数
     *
     * @param param
     * @return
     */
    public static String parseToUrlParam(Map<String, String> param) {
        StringBuffer buffer = new StringBuffer();
        try {
            if (param != null && !param.isEmpty()) {
                Iterator iterator = param.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry entry = (Map.Entry) iterator.next();
                    buffer.append(entry.getKey()).append("=")
                            .append(URLEncoder.encode(entry.getValue().toString(), "utf-8"))
                            .append("&");
                }
                buffer.deleteCharAt(buffer.length() - 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }

    /**
     * 用map的key排序输出key=value字符串
     * @author
     * @param params
     * @return String
     */
    public static String sortMapKeyStr(Map<String, String> params){
        StringBuilder buf = new StringBuilder();
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        int i = 0;
        for(String key : keys){
            if(i != keys.size()-1) {
                buf.append(key).append("=").append(params.get(key)).append("&");
            }else {
                buf.append(key).append("=").append(params.get(key));
            }
            i++;
        }
        return buf.toString();
    }

    /**
     * 过滤参数
     * @author
     * @param sArray
     * @return
     */
    public static Map<String, String> paraFilter(Map<String, String> sArray) {
        Map<String, String> result = new HashMap<String, String>(sArray.size());
        if (sArray == null || sArray.size() <= 0) {
            return result;
        }
        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign")) {
                continue;
            }
            result.put(key, value);
        }
        return result;
    }
}
