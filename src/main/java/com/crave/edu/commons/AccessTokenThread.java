package com.crave.edu.commons;

import com.crave.edu.bean.AccessToken;
import javax.servlet.ServletContext;

public class AccessTokenThread implements Runnable {
    public static String appid = "";
    public static String appsecret = "";
    public static AccessToken accessToken = null;


    @Override
    public void run() {
        while (true) {
            try {
                accessToken = CommonUtil.getAccessToken(appid, appsecret);
                if (null != accessToken) {
                    System.out.println("accessToken初始化成功:" + accessToken.getAccessToken());
                    // 全局缓存access_token
                    ServletContext servletContext = ServletContextUtil.getServletContext();
                    servletContext.setAttribute("access_token", accessToken.getAccessToken());

                    // 有效期（秒）减去200秒，乘以1000(毫秒)——也就是在有效期的200秒前去请求新的accessToken
                    Thread.sleep((accessToken.getExpiresIn() - 200) * 1000);
                } else {
                    // 等待一分钟，再次请求
                    Thread.sleep(60 * 1000);
                }
            } catch (Exception e) {
                try {
                    // 等待一分钟，再次请求
                    Thread.sleep(60 * 1000);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                e.printStackTrace();
            }
        }
    }
}
