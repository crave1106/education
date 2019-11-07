package com.crave.edu.mapper;

import com.crave.edu.bean.ChangeLog;
import com.crave.edu.bean.ChangeLogExample;
import org.springframework.stereotype.Repository;

/**
 * ChangeLogDAO继承基类
 */
@Repository
public interface ChangeLogDAO extends MyBatisBaseDao<ChangeLog, Integer, ChangeLogExample> {
}