package com.crave.edu.mapper;

import com.crave.edu.bean.Students;
import com.crave.edu.bean.StudentsExample;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * StudentsDAO继承基类
 */
@Repository
public interface StudentsDAO extends MyBatisBaseDao<Students, Integer, StudentsExample> {

    List<Map<String, String>> queryList(Map<String,Object> param);

    List<String> getNewEnrollList(Map<String, Integer> param);
}