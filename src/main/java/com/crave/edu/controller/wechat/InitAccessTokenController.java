package com.crave.edu.controller.wechat;

import com.crave.edu.bean.ResponseBean;
import com.crave.edu.commons.ServletContextUtil;
import com.crave.edu.commons.WechatUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@Scope("prototype")
@RequestMapping("/wechat")
public class InitAccessTokenController {

    @RequestMapping("/initWXJSInterface")
    @ResponseBody
    public ResponseBean init(String url){
        Map<String, String> ret = null;
        try {
            // 从全局缓存中取出jsapi_ticket
            ServletContext servletContext = ServletContextUtil.getServletContext();
            String jsapi_ticket = (String) servletContext.getAttribute("jsapi_ticket");

            ret = sign(jsapi_ticket, url);

            System.out.println("currurl = "+ url);

            // 注意 URL 一定要动态获取，不能 hardcode
    //		for (Map.Entry entry : ret.entrySet()) {
    //			System.out.println(entry.getKey() + ", " + entry.getValue());
    //		}
            System.out.println("signature =" + ret.get("signature"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.getFail("获取jsapiTicket异常, 请联系管理员");
        }
        return ResponseBean.getSuccess(ret);
    }

    public Map<String, String> sign(String jsapi_ticket, String url) {
        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";

        // 注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket +
                "&noncestr=" + nonce_str +
                "&timestamp=" + timestamp +
                "&url=" + url;
        System.out.println(string1);

        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        ret.put("url", url);
        ret.put("appId", WechatUtil.getWechatConfig().getAppId());
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);

        return ret;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

}