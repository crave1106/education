package com.crave.edu.controller.wechat;

import com.crave.edu.bean.Assist;
import com.crave.edu.bean.ResponseBean;
import com.crave.edu.commons.Constant;
import com.crave.edu.mapper.AssistDAO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/wechat/assist")
public class AssistController {

    @Autowired
    private AssistDAO assistDAO;

    @RequestMapping("/stroke")
    @ResponseBody
    public ResponseBean stroke(Assist assist){
        Map<String, Integer> map = new HashMap<String, Integer>();
        try {
            if (StringUtils.isBlank(assist.getOpenId())){
                return ResponseBean.getFail("发起人OpenId不能为空");
            }
            if (StringUtils.isBlank(assist.getHelpOpenId())){
                return ResponseBean.getFail("助力人OpenId不能为空");
            }

            Integer strokesCount = assistDAO.getStrokesCount(assist.getOpenId());
            if (strokesCount == Constant.MAX_STROKES_COUNT){
                return ResponseBean.getFail("该用户已集满笔画");
            }

            if (assistDAO.insertSelective(assist) <= 0){
                return ResponseBean.getFail("助力失败, 请联系管理员");
            }
            strokesCount = assistDAO.getStrokesCount(assist.getOpenId());
            map.put("strokesCount", strokesCount);
        }catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().indexOf("MySQLIntegrityConstraintViolationException") > 0){
                return ResponseBean.getFail("您已写过一笔，请勿重复写入");
            }
        }

        return ResponseBean.getSuccess(map);
    }
}
