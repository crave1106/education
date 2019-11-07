package com.crave.edu.controller.wechat;

import com.crave.edu.bean.ResponseBean;
import com.crave.edu.bean.Students;
import com.crave.edu.bean.StudentsExample;
import com.crave.edu.commons.Constant;
import com.crave.edu.mapper.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/wechat/enroll")
public class EnrollController {

    @Autowired
    private StudentsDAO studentsDAO;

    @Autowired
    private AssistDAO assistDAO;

    @Autowired
    private StaticParameterDAO staticParameterDAO;

    @Autowired
    private DtAreaDAO dtAreaDAO;

    @Autowired
    private CampusDAO campusDAO;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 获取年级
     * @return
     */
    @RequestMapping("/getGrade")
    @ResponseBody
    public ResponseBean getGrade(){
        return ResponseBean.getSuccess(staticParameterDAO.queryKey("grade"));
    }

    /**
     * 三级联动
     * @param code
     * @return
     */
    @RequestMapping("/getArea")
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

    /**
     * 获取校区
     * @return
     */
    @RequestMapping("/getCampus")
    @ResponseBody
    public ResponseBean getCampus(Integer area){
        List<Map<String,Object>> campus = null;
        try {
            campus = campusDAO.wechatQueryList(area);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.getFail("校区读取异常");
        }
        return ResponseBean.getSuccess(campus);
    }

    /**
     * 获取最新20个学生报名信息
     * @return
     */
    @RequestMapping("/getEnrollList")
    @ResponseBody
    public ResponseBean getEnrollList(@RequestParam(required = false,defaultValue = "20") Integer size){
        List<String> enrollList = null;
        try {
            Map<String,Integer> param = new HashMap<>();
            param.put("strokesCount", Constant.MAX_STROKES_COUNT);  //笔画顺序
            param.put("size", size);  //条数
            enrollList = studentsDAO.getNewEnrollList(param);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.getFail("校区读取异常");
        }
        return ResponseBean.getSuccess(enrollList);
    }


    /**
     * 报名
     * @param students
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public ResponseBean add(Students students){
        try {
            ResponseBean responseBean = validParam(students, 0);
            if (null != responseBean){
                return responseBean;
            }

            StudentsExample example = new StudentsExample();
            StudentsExample.Criteria criteria = example.createCriteria();
            criteria.andOpenIdEqualTo(students.getOpenId());
            List<Students> isEnroll = studentsDAO.selectByExample(example);
            if (null != isEnroll && isEnroll.size() > 0){
                return ResponseBean.getFail("您已报名, 请勿重复提交");
            }
            if (studentsDAO.insertSelective(students) <= 0){
                return ResponseBean.getFail("报名失败, 请联系管理员");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.getFail("报名失败, 请联系管理员");
        }
        return ResponseBean.getSuccess("报名成功");
    }

    /**
     * 修改报名信息
     * @param students
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public ResponseBean edit(Students students){
        try {
            ResponseBean responseBean = validParam(students, 0);
            if (null != responseBean){
                return responseBean;
            }

            if (studentsDAO.updateByPrimaryKeySelective(students) <= 0){
                return ResponseBean.getFail("修改失败, 请联系管理员");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.getFail("修改失败, 请联系管理员");
        }
        return ResponseBean.getSuccess("修改成功");
    }

    /**
     * 获取报名信息
     * @param openId
     * @return
     */
    @RequestMapping("/getEnroll")
    @ResponseBody
        public ResponseBean getEnroll(String openId, String helpOpenId){
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            if (StringUtils.isBlank(openId)){
                return ResponseBean.getFail("登录失效");
            }

            StudentsExample example = new StudentsExample();
            StudentsExample.Criteria criteria = example.createCriteria();
            criteria.andOpenIdEqualTo(openId);

            //查询报名信息
            List<Students> isEnroll = studentsDAO.selectByExample(example);
            Students students = null;
            if (null != isEnroll && isEnroll.size() > 0){
                students = isEnroll.get(0);
                map.put("thisEnrollInfo", students);
                map.put("isEnroll", true);
            }else{
                map.put("thisEnrollInfo", null);
                map.put("isEnroll", false);
            }
            map.put("helpName", null);
            if (StringUtils.isNotBlank(helpOpenId)){
                if (null != students && students.getOpenId().equals(openId)){
                    map.put("helpName", students.getName());
                }else{
                    criteria.andOpenIdEqualTo(openId);
                    isEnroll = studentsDAO.selectByExample(example);
                    if (null != isEnroll && isEnroll.size() > 0){
                        map.put("helpName", isEnroll.get(0).getName());
                    }
                }
            }

            //查询笔画数量
            Integer strokesCount = assistDAO.getStrokesCount(openId);
            map.put("strokesCount", strokesCount);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.getFail("报名信息查询失败, 请联系管理员");
        }
        return ResponseBean.getSuccess(map);
    }

    public ResponseBean validParam(Students students, Integer type){
        if (1 == type && null == students.getId()){
            return ResponseBean.getFail("报名信息ID不能为空");
        }

        if (StringUtils.isBlank(students.getName())){
            return ResponseBean.getFail("学生姓名不能为空");
        }else if(StringUtils.isBlank(students.getMobile())){
            return ResponseBean.getFail("联系方式不能为空");
        }else if(StringUtils.isBlank(students.getAttendingSchool())){
            return ResponseBean.getFail("就读学校不能为空");
        }else if(StringUtils.isBlank(students.getGrade())){
            return ResponseBean.getFail("年级不能为空");
        }else if(null == students.getProvince()){
            return ResponseBean.getFail("省份不能为空");
        }else if(null == students.getCity()){
            return ResponseBean.getFail("城市不能为空");
        }else if(null == students.getCity()){
            return ResponseBean.getFail("区域不能为空");
        }else if(null == students.getCampusId()){
            return ResponseBean.getFail("请选择报名校区");
        }else if(StringUtils.isBlank(students.getOpenId())){
            return ResponseBean.getFail("登录失效");
        }
        return null;
    }
}
