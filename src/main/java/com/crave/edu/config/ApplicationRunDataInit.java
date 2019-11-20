package com.crave.edu.config;

import com.alibaba.fastjson.JSON;
import com.crave.edu.bean.WechatConfigWithBLOBs;
import com.crave.edu.commons.AccessTokenThread;
import com.crave.edu.commons.Constant;
import com.crave.edu.commons.JsApiTicketThread;
import com.crave.edu.mapper.WechatConfigDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApplicationRunDataInit implements ApplicationRunner {

    @Autowired
    private WechatConfigDAO wechatConfigDAO;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initWechatConfig();
    }

    public void initWechatConfig(){
        WechatConfigWithBLOBs wechatConfig = new WechatConfigWithBLOBs();
        List<WechatConfigWithBLOBs> wechatConfigs = wechatConfigDAO.selectByExampleWithBLOBs(null);
        if (null != wechatConfigs && wechatConfigs.size() > 0 ){
            wechatConfig = wechatConfigs.get(0);
        }
        redisTemplate.opsForValue().set(Constant.WECHAT_CONFIG_KEY, JSON.toJSONString(wechatConfig));


        // 获取web.xml中配置的参数
        AccessTokenThread.appid = wechatConfig.getAppId();
        AccessTokenThread.appsecret = wechatConfig.getAppSecret();

        if ("".equals(wechatConfig.getAppId()) || "".equals(wechatConfig.getAppSecret())) {
            System.out.println("appid和appsecret未给出");
        } else {
            new Thread(new AccessTokenThread()).start();
            new Thread(new JsApiTicketThread()).start();
        }
    }
}
