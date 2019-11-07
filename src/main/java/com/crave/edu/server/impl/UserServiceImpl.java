package com.crave.edu.server.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.crave.edu.bean.Users;
import com.crave.edu.bean.UsersExample;
import com.crave.edu.mapper.UsersDAO;
import com.crave.edu.server.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersDAO userDao;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public Users backLogin(String account, String password, HttpServletRequest request) {
        try {
            Users user = null;
            UsersExample example = new UsersExample();
            UsersExample.Criteria criteria = example.createCriteria();
            criteria.andAccountEqualTo(account);
            criteria.andPasswordEqualTo(password);
            List<Users> users = userDao.selectByExample(example);
            if (null != users && users.size() > 0){
                user = users.get(0);
                String userStr = JSONObject.toJSONString(user);
                String sessionId = request.getSession().getId();
                redisTemplate.opsForValue().set(sessionId, userStr, 30, TimeUnit.DAYS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Users getBackLoginParkInfo(HttpServletRequest request) {
        String parkStr = null;
        try {
            String sessionId = request.getSession().getId();
            parkStr = redisTemplate.opsForValue().get(sessionId);
            if (StringUtils.isBlank(parkStr)) {
                throw new Exception("登录失效");
            }
            // 更新token有效期
            redisTemplate.opsForValue().set(sessionId, parkStr, 30, TimeUnit.DAYS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.parseObject(parkStr, new TypeReference<Users>(){});
    }
}
