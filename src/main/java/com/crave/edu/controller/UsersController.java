package com.crave.edu.controller;

import com.crave.edu.bean.ResponseBean;
import com.crave.edu.bean.Users;
import com.crave.edu.bean.UsersExample;
import com.crave.edu.mapper.MyBatisBaseDao;
import com.crave.edu.mapper.UsersDAO;
import com.crave.edu.server.UserService;
import com.crave.edu.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController  extends BaseController<Users, Integer, UsersDAO>{

    @Autowired
    private UsersDAO usersDAO;

    @RequestMapping("/page")
    public String page(){
        return "error/research";
    }

    @Override
    public MyBatisBaseDao getMapper() {
        return usersDAO;
    }


    @Autowired
    private UserService userService;

    @RequestMapping(value = "/editPwd",method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean editPwd(@RequestParam  String oldPassword, @RequestParam String newPassword, HttpServletRequest request){
        oldPassword = MD5.MD5Encode(oldPassword);
        newPassword = MD5.MD5Encode(newPassword);
        try {
            Users users = userService.getBackLoginParkInfo(request);
            UsersExample example = new UsersExample();
            UsersExample.Criteria criteria = example.createCriteria();
            criteria.andPasswordEqualTo(oldPassword);
            criteria.andIdEqualTo(users.getId());
            List<Users> parks = usersDAO.selectByExample(example);
            if (null == parks || parks.size() <= 0){
                return ResponseBean.getFail("旧密码不正确");
            }

            Users park = new Users();
            park.setPassword(newPassword);
            park.setId(users.getId());
            if(usersDAO.updateByPrimaryKeySelective(park) <= 0){
                return ResponseBean.getFail("修改失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.getFail(e.getMessage());
        }
        return ResponseBean.getSuccess("修改成功");
    }
}
