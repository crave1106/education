package com.crave.edu.controller;

import com.crave.edu.bean.ResponseBean;
import com.crave.edu.mapper.DtAreaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/area")
public class DtAreaController {

    @Autowired
    private DtAreaDAO dtAreaDAO;

    @RequestMapping("/getArea")
    @ResponseBody
    public ResponseBean getArea(Integer code, Integer type, Integer cityCode){
        Map<String, Object> map = new HashMap<>();
        try {
            List<Map<String,String>> dtAreas = dtAreaDAO.getArea(code);
            if (type == 0){ //初始化
                map.put("provinceList", dtAreas);
            }else if (type == 1){   //选择省份
                List<Map<String, String>> areaList = new ArrayList<>();
                if (null != dtAreas && dtAreas.size() > 0){
                    Integer fastCityCode = null;
                    if (null != cityCode)
                        fastCityCode = cityCode;
                    else
                        fastCityCode = Integer.valueOf(String.valueOf(dtAreas.get(0).get("code")));
                    areaList = dtAreaDAO.getArea(fastCityCode);
                }

                map.put("cityList", dtAreas);
                map.put("areaList", areaList);
            }else if (type == 2){
                map.put("areaList", dtAreas);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.getFail("地区获取异常");
        }
        return ResponseBean.getSuccess(map);
    }

    @RequestMapping("/getSearchArea")
    @ResponseBody
    public ResponseBean getSearchArea(Integer code){
        List<Map<String,String>> dtAreas = null;
        try {
            dtAreas = dtAreaDAO.getArea(code);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.getFail("地区获取异常");
        }
        return ResponseBean.getSuccess(dtAreas);
    }
}
