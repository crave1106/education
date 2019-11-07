package com.crave.edu.controller;

import com.crave.edu.bean.ResponseBean;
import com.crave.edu.bean.Students;
import com.crave.edu.bean.StudentsExample;
import com.crave.edu.mapper.MyBatisBaseDao;
import com.crave.edu.mapper.StudentsDAO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/enlist")
public class EnController extends BaseController<Students, Integer, StudentsExample>{

    @Autowired
    private StudentsDAO studentsDAO;

    @RequestMapping("/page")
    public String page(){
        return "enlist";
    }

    @RequestMapping("/queryList")
    @ResponseBody
    public ResponseBean queryList(@RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam Map<String,Object> map){
        Map<String,Object> result = new HashMap<String,Object>();
        try {
            Page<Object> page = PageHelper.startPage(pageNo, pageSize);
            List<Map<String, String>> staticParams = studentsDAO.queryList(map);
            result.put("total", page.getTotal());
            result.put("list", staticParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseBean.getSuccess(result);
    }

    @RequestMapping("/edit")
    @ResponseBody
    public ResponseBean edit(Students students){
        if (studentsDAO.updateByPrimaryKeySelective(students) <= 0)
            return ResponseBean.getFail("修改失败");
        return ResponseBean.getSuccess();
    }

    @Override
    public MyBatisBaseDao getMapper() {
        return studentsDAO;
    }
}
