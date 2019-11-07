package com.crave.edu.bean;

import com.alibaba.fastjson.JSONObject;

/**
 * 返回结构体
 */
public class ResponseBean {
    private int code;
    private String msg;
    private Object data = new JSONObject(); // 需要对方调用方法时，传入的参数

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static ResponseBean getSuccess() {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setCode(1);
        responseBean.setMsg("success");
        return responseBean;
    }

    public static ResponseBean getSuccess(Object object) {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setCode(1);
        responseBean.setMsg("success");
        responseBean.setData(object);
        return responseBean;
    }

    public static ResponseBean getFail() {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setCode(0);
        responseBean.setMsg("fail");
        return responseBean;
    }

    public static ResponseBean getFail(String msg) {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setCode(0);
        responseBean.setMsg(msg);
        return responseBean;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
