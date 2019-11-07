package com.crave.edu.controller;

import com.crave.edu.bean.ResponseBean;
import com.crave.edu.mapper.MyBatisBaseDao;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;

public abstract class BaseController<Model, PK extends Serializable, E> {
    public abstract MyBatisBaseDao getMapper();

    @RequestMapping(value = "/del",method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean del(PK id){
        try {
            if (getMapper().deleteByPrimaryKey(id) <= 0){
                throw new RuntimeException("删除失败");
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseBean.getFail(e.getMessage());
        }
        return ResponseBean.getSuccess();
    }

    @RequestMapping(value = "/queryId",method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean queryId(long id){
        try {
            return ResponseBean.getSuccess(getMapper().selectByPrimaryKey(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.getFail(e.getMessage());
        }
    }
}
