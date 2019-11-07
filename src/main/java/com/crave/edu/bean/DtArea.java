package com.crave.edu.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * dt_area
 * @author 
 */
public class DtArea implements Serializable {
    /**
     * 区域主键
     */
    private Integer id;

    /**
     * 区域名称
     */
    private String areaName;

    /**
     * 区域代码
     */
    private String areaCode;

    /**
     * 区域简称
     */
    private String areaShort;

    /**
     * 是否热门(0:否、1:是)
     */
    private String areaIsHot;

    /**
     * 区域序列
     */
    private Integer areaSequence;

    /**
     * 上级主键
     */
    private Integer areaParentId;

    /**
     * 初始时间
     */
    private Date initDate;

    /**
     * 初始地址
     */
    private String initAddr;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaShort() {
        return areaShort;
    }

    public void setAreaShort(String areaShort) {
        this.areaShort = areaShort;
    }

    public String getAreaIsHot() {
        return areaIsHot;
    }

    public void setAreaIsHot(String areaIsHot) {
        this.areaIsHot = areaIsHot;
    }

    public Integer getAreaSequence() {
        return areaSequence;
    }

    public void setAreaSequence(Integer areaSequence) {
        this.areaSequence = areaSequence;
    }

    public Integer getAreaParentId() {
        return areaParentId;
    }

    public void setAreaParentId(Integer areaParentId) {
        this.areaParentId = areaParentId;
    }

    public Date getInitDate() {
        return initDate;
    }

    public void setInitDate(Date initDate) {
        this.initDate = initDate;
    }

    public String getInitAddr() {
        return initAddr;
    }

    public void setInitAddr(String initAddr) {
        this.initAddr = initAddr;
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
        DtArea other = (DtArea) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAreaName() == null ? other.getAreaName() == null : this.getAreaName().equals(other.getAreaName()))
            && (this.getAreaCode() == null ? other.getAreaCode() == null : this.getAreaCode().equals(other.getAreaCode()))
            && (this.getAreaShort() == null ? other.getAreaShort() == null : this.getAreaShort().equals(other.getAreaShort()))
            && (this.getAreaIsHot() == null ? other.getAreaIsHot() == null : this.getAreaIsHot().equals(other.getAreaIsHot()))
            && (this.getAreaSequence() == null ? other.getAreaSequence() == null : this.getAreaSequence().equals(other.getAreaSequence()))
            && (this.getAreaParentId() == null ? other.getAreaParentId() == null : this.getAreaParentId().equals(other.getAreaParentId()))
            && (this.getInitDate() == null ? other.getInitDate() == null : this.getInitDate().equals(other.getInitDate()))
            && (this.getInitAddr() == null ? other.getInitAddr() == null : this.getInitAddr().equals(other.getInitAddr()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAreaName() == null) ? 0 : getAreaName().hashCode());
        result = prime * result + ((getAreaCode() == null) ? 0 : getAreaCode().hashCode());
        result = prime * result + ((getAreaShort() == null) ? 0 : getAreaShort().hashCode());
        result = prime * result + ((getAreaIsHot() == null) ? 0 : getAreaIsHot().hashCode());
        result = prime * result + ((getAreaSequence() == null) ? 0 : getAreaSequence().hashCode());
        result = prime * result + ((getAreaParentId() == null) ? 0 : getAreaParentId().hashCode());
        result = prime * result + ((getInitDate() == null) ? 0 : getInitDate().hashCode());
        result = prime * result + ((getInitAddr() == null) ? 0 : getInitAddr().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", areaName=").append(areaName);
        sb.append(", areaCode=").append(areaCode);
        sb.append(", areaShort=").append(areaShort);
        sb.append(", areaIsHot=").append(areaIsHot);
        sb.append(", areaSequence=").append(areaSequence);
        sb.append(", areaParentId=").append(areaParentId);
        sb.append(", initDate=").append(initDate);
        sb.append(", initAddr=").append(initAddr);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}