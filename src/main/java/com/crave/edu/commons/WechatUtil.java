package com.crave.edu.commons;

import com.alibaba.fastjson.JSON;
import com.crave.edu.bean.WechatConfigWithBLOBs;
import com.crave.edu.mapper.WechatConfigDAO;
import com.crave.edu.util.SpringBeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

public class WechatUtil {

    private static StringRedisTemplate redisTemplate;
    private static WechatConfigDAO wechatConfigDAO;

    public static WechatConfigWithBLOBs getWechatConfig(){
        if (null == redisTemplate){
            redisTemplate = SpringBeanUtil.getBean(StringRedisTemplate.class);
        }
        if (null == wechatConfigDAO){
            wechatConfigDAO = SpringBeanUtil.getBean(WechatConfigDAO.class);
        }

        String wechatConfigCatch = redisTemplate.opsForValue().get(Constant.WECHAT_CONFIG_KEY);
        WechatConfigWithBLOBs wechatConfig = null;
        if (StringUtils.isNotBlank(wechatConfigCatch)){
            wechatConfig = JSON.parseObject(wechatConfigCatch, WechatConfigWithBLOBs.class);
        }else{
            wechatConfig = new WechatConfigWithBLOBs();
            List<WechatConfigWithBLOBs> wechatConfigs = wechatConfigDAO.selectByExampleWithBLOBs(null);
            if (null != wechatConfigs && wechatConfigs.size() > 0 ){
                wechatConfig = wechatConfigs.get(0);
                redisTemplate.opsForValue().set(Constant.WECHAT_CONFIG_KEY, JSON.toJSONString(wechatConfig));
            }
        }
        return wechatConfig;
    }
}
