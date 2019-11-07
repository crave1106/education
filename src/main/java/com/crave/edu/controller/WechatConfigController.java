package com.crave.edu.controller;

import com.alibaba.fastjson.JSON;
import com.crave.edu.bean.ResponseBean;
import com.crave.edu.bean.WechatConfigWithBLOBs;
import com.crave.edu.commons.Constant;
import com.crave.edu.mapper.WechatConfigDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/wxConfig")
public class WechatConfigController {

    @Autowired
    private WechatConfigDAO wechatConfigDAO;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/page")
    public String page(Model model, HttpServletRequest request){
        WechatConfigWithBLOBs wechatConfig = new WechatConfigWithBLOBs();
        List<WechatConfigWithBLOBs> wechatConfigs = wechatConfigDAO.selectByExampleWithBLOBs(null);
        if (null != wechatConfigs && wechatConfigs.size() > 0 ){
            wechatConfig = wechatConfigs.get(0);
        }
        model.addAttribute("config", wechatConfig);
        System.out.println(request.getAttribute("config"));
        return "wechatConfig";
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseBean add(WechatConfigWithBLOBs wechatConfigWithBLOBs){
        try {
            if (wechatConfigDAO.insertSelective(wechatConfigWithBLOBs) > 0){
                redisTemplate.opsForValue().set(Constant.WECHAT_CONFIG_KEY, JSON.toJSONString(wechatConfigWithBLOBs));
                return ResponseBean.getSuccess();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseBean.getFail("保存失败");
    }

    @RequestMapping("/edit")
    @ResponseBody
    public ResponseBean edit(WechatConfigWithBLOBs wechatConfigWithBLOBs){
        try {
            if (wechatConfigDAO.updateByPrimaryKeySelective(wechatConfigWithBLOBs) > 0){
                redisTemplate.opsForValue().set(Constant.WECHAT_CONFIG_KEY, JSON.toJSONString(wechatConfigWithBLOBs));
                return ResponseBean.getSuccess();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseBean.getFail("保存失败");
    }
}
