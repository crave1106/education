package com.crave.edu.mapper;

import com.crave.edu.bean.Users;
import com.crave.edu.bean.UsersExample;
import org.springframework.stereotype.Repository;

/**
 * UsersDAO继承基类
 */
@Repository
public interface UsersDAO extends MyBatisBaseDao<Users, Integer, UsersExample> {
}