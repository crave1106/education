package com.crave.edu.mapper;

import com.crave.edu.bean.DtArea;
import com.crave.edu.bean.DtAreaExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * DtAreaDAO继承基类
 */
@Repository
public interface DtAreaDAO extends MyBatisBaseDao<DtArea, Integer, DtAreaExample> {

    List<Map<String, String>> getArea(@Param("code") Integer code);
}