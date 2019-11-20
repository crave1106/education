package com.crave.edu.commons;

import com.crave.edu.bean.JsApiTicket;

import javax.servlet.ServletContext;

public class JsApiTicketThread implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                ServletContext servletContext = ServletContextUtil.getServletContext();
                String access_token = (String) servletContext.getAttribute("access_token");

                JsApiTicket jsApiTicket = null;

                if(null != access_token && !"".equals(access_token)){
                    // 获取jsapi_ticket
                    jsApiTicket = CommonUtil.getJsApiTicket(access_token);

                    if (null != jsApiTicket) {
                        System.out.println("jsapi_ticket获取成功:" + jsApiTicket.getTicket());
                        // 全局缓存jsapi_ticket
                        servletContext.setAttribute("jsapi_ticket", jsApiTicket.getTicket());

                        Thread.sleep((jsApiTicket.getExpiresIn() - 200) * 1000);
                    }
                }
                Thread.sleep(60 * 1000);
            } catch (Exception e) {
                try {
                    Thread.sleep(60 * 1000);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                e.printStackTrace();
            }
        }
    }

}