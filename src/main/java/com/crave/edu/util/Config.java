package com.crave.edu.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 配置信息
 *
 * @author Administrator on 2017/8/15 0015
 */
public class Config {

    /**
     * 威富通交易密钥
     */
    public static String key ;

    /**
     * 威富通商户号
     */
    public static String mch_id;

    /**
     * 威富通请求url
     */
    public static String req_url;

    //微信扫码支付
    public static final String TRADE_TYPE_WEIXIN = "pay.weixin.native";
    //支付宝扫码支付
    public static final String TRADE_TYPE_ALIPAY = "pay.alipay.native";
    //统一刷卡支付
    public static final String TRADE_TYPE_UNIFIED = "unified.trade.micropay";
    //微信公众号支付
    public static final String TRADE_TYPE_JSPAY = "pay.weixin.jspay";
    //支付宝生活号支付
    public static final String TRADE_TYPE_ALIPAY_JSPAY = "pay.alipay.jspay";

    static{
        Properties prop = new Properties();
        InputStream in = Config.class.getResourceAsStream("/qmp.properties");
        try {
            prop.load(in);
            key = prop.getProperty("key").trim();
            mch_id = prop.getProperty("mch_id").trim();
            req_url = prop.getProperty("req_url").trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
