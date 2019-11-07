package com.crave.edu.mapper;

import com.crave.edu.bean.Campus;
import com.crave.edu.bean.CampusExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * CampusDAO继承基类
 */
@Repository
public interface CampusDAO extends MyBatisBaseDao<Campus, Integer, CampusExample> {

    List<Map<String, String>> queryList(Map<String,Object> param);

    List<Map<String, Object>> wechatQueryList(@Param("area") Integer area);
}