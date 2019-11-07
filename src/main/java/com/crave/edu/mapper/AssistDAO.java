package com.crave.edu.mapper;

import com.crave.edu.bean.Assist;
import com.crave.edu.bean.AssistExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * AssistDAO继承基类
 */
@Repository
public interface AssistDAO extends MyBatisBaseDao<Assist, Integer, AssistExample> {
    Integer getStrokesCount(@Param("openId") String openId);
}