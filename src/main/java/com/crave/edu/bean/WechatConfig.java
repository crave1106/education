package com.crave.edu.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * wechat_config
 * @author 
 */
public class WechatConfig implements Serializable {
    private Integer id;

    /**
     * 公众号APPID
     */
    private String appId;

    /**
     * 公众号SECRET
     */
    private String appSecret;

    /**
     * 公众号二维码
     */
    private String publicQRcode;

    /**
     * 人工客服二维码
     */
    private String customerServiceQRcode;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getPublicQRcode() {
        return publicQRcode;
    }

    public void setPublicQRcode(String publicQRcode) {
        this.publicQRcode = publicQRcode;
    }

    public String getCustomerServiceQRcode() {
        return customerServiceQRcode;
    }

    public void setCustomerServiceQRcode(String customerServiceQRcode) {
        this.customerServiceQRcode = customerServiceQRcode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        WechatConfig other = (WechatConfig) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAppId() == null ? other.getAppId() == null : this.getAppId().equals(other.getAppId()))
            && (this.getAppSecret() == null ? other.getAppSecret() == null : this.getAppSecret().equals(other.getAppSecret()))
            && (this.getPublicQRcode() == null ? other.getPublicQRcode() == null : this.getPublicQRcode().equals(other.getPublicQRcode()))
            && (this.getCustomerServiceQRcode() == null ? other.getCustomerServiceQRcode() == null : this.getCustomerServiceQRcode().equals(other.getCustomerServiceQRcode()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAppId() == null) ? 0 : getAppId().hashCode());
        result = prime * result + ((getAppSecret() == null) ? 0 : getAppSecret().hashCode());
        result = prime * result + ((getPublicQRcode() == null) ? 0 : getPublicQRcode().hashCode());
        result = prime * result + ((getCustomerServiceQRcode() == null) ? 0 : getCustomerServiceQRcode().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", appId=").append(appId);
        sb.append(", appSecret=").append(appSecret);
        sb.append(", publicQRcode=").append(publicQRcode);
        sb.append(", customerServiceQRcode=").append(customerServiceQRcode);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}