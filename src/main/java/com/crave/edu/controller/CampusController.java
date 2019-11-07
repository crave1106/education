package com.crave.edu.controller;

import com.crave.edu.bean.Campus;
import com.crave.edu.bean.CampusExample;
import com.crave.edu.bean.ResponseBean;
import com.crave.edu.bean.StaticParameter;
import com.crave.edu.mapper.CampusDAO;
import com.crave.edu.mapper.MyBatisBaseDao;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/campus")
public class CampusController extends BaseController<Campus, Integer, CampusExample>{

    @Autowired
    private CampusDAO campusDAO;

    @RequestMapping("/page")
    public String page(){
        return "campus";
    }

    @RequestMapping("/queryList")
    @ResponseBody
    public ResponseBean queryList(@RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam Map<String,Object> map){
        Map<String,Object> result = new HashMap<String,Object>();
        try {
            Page<Object> page = PageHelper.startPage(pageNo, pageSize);
            List<Map<String, String>> staticParams = campusDAO.queryList(map);
            result.put("total", page.getTotal());
            result.put("list", staticParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseBean.getSuccess(result);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean add(Campus campus){
        try {
            if (campusDAO.insertSelective(campus) <= 0){
                throw new RuntimeException("保存失败");
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseBean.getFail(e.getMessage());
        }
        return ResponseBean.getSuccess();
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean edit(Campus sp){
        try {
            if(campusDAO.updateByPrimaryKeySelective(sp) <= 0){
                throw new RuntimeException("修改失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.getFail(e.getMessage());
        }
        return ResponseBean.getSuccess();
    }

    @Override
    public MyBatisBaseDao getMapper() {
        return campusDAO;
    }
}
