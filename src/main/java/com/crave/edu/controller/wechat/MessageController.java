package com.crave.edu.controller.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.crave.edu.bean.ResponseBean;
import com.crave.edu.commons.MessageUtils;
import com.crave.edu.util.MobileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/wechat/message")
public class MessageController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("/send")
    @ResponseBody
    public ResponseBean send(String mobile, HttpSession session){
        try {
            if (!MobileUtil.checkPhone(mobile)){
                return ResponseBean.getFail("手机号码不能为空");
            }

            if (redisTemplate.hasKey(session.getId())){
                long nd = 1000 * 24 * 60 * 60;
                long nh = 1000 * 60 * 60;
                long nm = 1000 * 60;
                long ns = 1000;

                String cache = redisTemplate.opsForValue().get(session.getId());
                Map cacheMap = JSONObject.parseObject(cache, Map.class);
                long sendTime = Long.parseLong(cacheMap.get("sendTime").toString());
                long thisTime = System.currentTimeMillis();
                long sec = (thisTime - sendTime) % nd % nh % nm / ns;

                //30秒内请勿重复发送
                if (sec < 30){
                    return ResponseBean.getFail("操作过于频繁, 请稍后重试");
                }
            }

            String code = MessageUtils.saiYouSend(mobile);

            Map<String,Object> map = new HashMap<String, Object>();
            map.put("code", code);
            map.put("sendTime", System.currentTimeMillis());
            redisTemplate.opsForValue().set(session.getId(), JSON.toJSONString(map), 10, TimeUnit.MINUTES);
            return ResponseBean.getSuccess();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseBean.getFail("发送异常, 请联系管理员");
    }
}
