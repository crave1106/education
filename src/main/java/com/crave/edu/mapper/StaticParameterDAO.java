package com.crave.edu.mapper;

import com.crave.edu.bean.StaticParameter;
import com.crave.edu.bean.StaticParameterExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * StaticParameterDAO继承基类
 */
@Repository
public interface StaticParameterDAO extends MyBatisBaseDao<StaticParameter, Integer, StaticParameterExample> {
    List<Map<String,Object>> queryKey(@Param("key") String key);
}