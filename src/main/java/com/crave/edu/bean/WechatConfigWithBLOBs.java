package com.crave.edu.bean;

import java.io.Serializable;

/**
 * wechat_config
 * @author 
 */
public class WechatConfigWithBLOBs extends WechatConfig implements Serializable {
    /**
     * 活动说明
     */
    private String activityExplain;

    /**
     * 活动规则
     */
    private String activityRules;

    /**
     * 练脑小技巧
     */
    private String antic;

    /**
     * 五大金牌课程
     */
    private String curriculum;

    /**
     * 项目介绍
     */
    private String projectinfo;

    private static final long serialVersionUID = 1L;

    public String getActivityExplain() {
        return activityExplain;
    }

    public void setActivityExplain(String activityExplain) {
        this.activityExplain = activityExplain;
    }

    public String getActivityRules() {
        return activityRules;
    }

    public void setActivityRules(String activityRules) {
        this.activityRules = activityRules;
    }

    public String getAntic() {
        return antic;
    }

    public void setAntic(String antic) {
        this.antic = antic;
    }

    public String getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(String curriculum) {
        this.curriculum = curriculum;
    }

    public String getProjectinfo() {
        return projectinfo;
    }

    public void setProjectinfo(String projectinfo) {
        this.projectinfo = projectinfo;
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
        WechatConfigWithBLOBs other = (WechatConfigWithBLOBs) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAppId() == null ? other.getAppId() == null : this.getAppId().equals(other.getAppId()))
            && (this.getAppSecret() == null ? other.getAppSecret() == null : this.getAppSecret().equals(other.getAppSecret()))
            && (this.getPublicQRcode() == null ? other.getPublicQRcode() == null : this.getPublicQRcode().equals(other.getPublicQRcode()))
            && (this.getCustomerServiceQRcode() == null ? other.getCustomerServiceQRcode() == null : this.getCustomerServiceQRcode().equals(other.getCustomerServiceQRcode()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getActivityExplain() == null ? other.getActivityExplain() == null : this.getActivityExplain().equals(other.getActivityExplain()))
            && (this.getActivityRules() == null ? other.getActivityRules() == null : this.getActivityRules().equals(other.getActivityRules()))
            && (this.getAntic() == null ? other.getAntic() == null : this.getAntic().equals(other.getAntic()))
            && (this.getCurriculum() == null ? other.getCurriculum() == null : this.getCurriculum().equals(other.getCurriculum()))
            && (this.getProjectinfo() == null ? other.getProjectinfo() == null : this.getProjectinfo().equals(other.getProjectinfo()));
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
        result = prime * result + ((getActivityExplain() == null) ? 0 : getActivityExplain().hashCode());
        result = prime * result + ((getActivityRules() == null) ? 0 : getActivityRules().hashCode());
        result = prime * result + ((getAntic() == null) ? 0 : getAntic().hashCode());
        result = prime * result + ((getCurriculum() == null) ? 0 : getCurriculum().hashCode());
        result = prime * result + ((getProjectinfo() == null) ? 0 : getProjectinfo().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", activityExplain=").append(activityExplain);
        sb.append(", activityRules=").append(activityRules);
        sb.append(", antic=").append(antic);
        sb.append(", curriculum=").append(curriculum);
        sb.append(", projectinfo=").append(projectinfo);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}