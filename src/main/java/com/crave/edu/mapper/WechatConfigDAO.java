package com.crave.edu.mapper;

import com.crave.edu.bean.WechatConfig;
import com.crave.edu.bean.WechatConfigExample;
import com.crave.edu.bean.WechatConfigWithBLOBs;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * WechatConfigDAO继承基类
 */
@Repository
public interface WechatConfigDAO extends MyBatisBaseDao<WechatConfig, Integer, WechatConfigExample> {
    List<WechatConfigWithBLOBs> selectByExampleWithBLOBs(WechatConfigExample example);
}