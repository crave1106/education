package com.crave.edu.controller.wechat;

import com.alibaba.fastjson.JSONObject;
import com.crave.edu.bean.ResponseBean;
import com.crave.edu.bean.WechatConfigWithBLOBs;
import com.crave.edu.commons.Constant;
import com.crave.edu.commons.WechatUtil;
import com.crave.edu.util.HttpUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/wechat")
public class WechatBaseController {

    /**
     *
     * @param code 获取微信服务器授权返回的code值
     * @return
     */
    @RequestMapping("/getOpenid")
    @ResponseBody
    public ResponseBean getOpenid(String code){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (StringUtils.isBlank(code)){
                return ResponseBean.getFail("code不能为空");
            }
            /**
             * 构造请求链接
             * https://api.weixin.qq.com/sns/oauth2/access_token?
             * appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
             */
            WechatConfigWithBLOBs wechatConfig = WechatUtil.getWechatConfig();
            String url = Constant.ACCESS_TOKEN_URL+"?appid="+wechatConfig.getAppId()+"&secret="+wechatConfig.getAppSecret()+"&code="+code+"&grant_type=authorization_code";
            System.out.println(url);
            String jsonStr = HttpUtil.doGet(url);
            System.out.println(jsonStr);
            String openid = JSONObject.parseObject(jsonStr).getString("openid");
            String accessToken = JSONObject.parseObject(jsonStr).getString("access_token");
            System.out.println(openid+"==========================");
            map.put("accessToken", accessToken);
            map.put("openId", openid);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.getFail("微信OpenId获取异常, 请联系管理员");
        }
        return ResponseBean.getSuccess(map);
    }

    @RequestMapping("/getBaseInfo")
    @ResponseBody
    public ResponseBean getWeChatBaseInfo(){
        return ResponseBean.getSuccess(WechatUtil.getWechatConfig());
    }
}