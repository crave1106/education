package com.crave.edu.controller;

import com.crave.edu.bean.ResponseBean;
import com.crave.edu.bean.StaticParameter;
import com.crave.edu.bean.StaticParameterExample;
import com.crave.edu.mapper.MyBatisBaseDao;
import com.crave.edu.mapper.StaticParameterDAO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping(value = "/staticParam")
public class StaticParamController extends BaseController<StaticParameter, Integer, StaticParameterExample>{

    @Autowired
    private StaticParameterDAO staticParameterDAO;

    @RequestMapping(value = "/page")
    public String staticParam(){
        return "staticParameter";
    }

    @RequestMapping(value = "/queryList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean queryParkList(@RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(required = false) String key){
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            Page<Object> page = PageHelper.startPage(pageNo, pageSize);
            StaticParameterExample example = new StaticParameterExample();
            if (StringUtils.isNotBlank(key)){
                example.createCriteria().andKeyEqualTo(key);
            }
            List<StaticParameter> staticParams = staticParameterDAO.selectByExample(example);
            map.put("total", page.getTotal());
            map.put("list", staticParams);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return ResponseBean.getFail(e.getMessage());
        }
        return ResponseBean.getSuccess(map);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean edit(StaticParameter sp){
        try {
            StaticParameterExample example = new StaticParameterExample();
            StaticParameterExample.Criteria criteria = example.createCriteria();
            criteria.andUniqueKeyEqualTo(sp.getUniqueKey());
            criteria.andIdNotEqualTo(sp.getId());
            List<StaticParameter> staticParameter = staticParameterDAO.selectByExample(example);
            if (null != staticParameter && staticParameter.size() > 0){
                throw new RuntimeException("当前静态参数已存在");
            }

            if(staticParameterDAO.updateByPrimaryKeySelective(sp) <= 0){
                throw new RuntimeException("修改失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.getFail(e.getMessage());
        }
        return ResponseBean.getSuccess();
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean add(StaticParameter sp){
        try {
            StaticParameterExample example = new StaticParameterExample();
            StaticParameterExample.Criteria criteria = example.createCriteria();
            criteria.andUniqueKeyEqualTo(sp.getUniqueKey());
            List<StaticParameter> staticParameter = staticParameterDAO.selectByExample(example);
            if (null != staticParameter && staticParameter.size() > 0){
                throw new RuntimeException("当前静态参数已存在");
            }

            if(staticParameterDAO.insertSelective(sp) <= 0){
                throw new RuntimeException("保存失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.getFail(e.getMessage());
        }
        return ResponseBean.getSuccess();
    }

    @Override
    public MyBatisBaseDao getMapper() {
        return staticParameterDAO;
    }
}
