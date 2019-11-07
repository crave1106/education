package com.crave.edu.controller;

import com.crave.edu.bean.ResponseBean;
import com.crave.edu.bean.Users;
import com.crave.edu.commons.Constant;
import com.crave.edu.server.UserService;
import com.crave.edu.util.MD5;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("/index")
    public String index(){
        return "login";
    }

    @RequestMapping(value = "/validate",method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean loginValidate(String username, String password, HttpServletRequest request){
        try {
            if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
                ResponseBean.getFail("参数缺失");
            }
            // 密码md5
            password = MD5.MD5Encode(password);
            // 从SecurityUtils里边创建一个 subject
            Subject subject = SecurityUtils.getSubject();
            // 在认证提交前准备 token（令牌）
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            // 执行认证登陆
            subject.login(token);
            Users user = userService.backLogin(username, password, request);
            request.getSession().setAttribute(Constant.BACK_USER, user);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.getFail(e.getMessage());
        }
        return ResponseBean.getSuccess();
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        String sessionId = request.getSession().getId();
        redisTemplate.delete(sessionId);
        return "login";
    }
}
