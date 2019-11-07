package com.crave.edu.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DtAreaExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public DtAreaExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getOffset() {
        return offset;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andAreaNameIsNull() {
            addCriterion("area_name is null");
            return (Criteria) this;
        }

        public Criteria andAreaNameIsNotNull() {
            addCriterion("area_name is not null");
            return (Criteria) this;
        }

        public Criteria andAreaNameEqualTo(String value) {
            addCriterion("area_name =", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameNotEqualTo(String value) {
            addCriterion("area_name <>", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameGreaterThan(String value) {
            addCriterion("area_name >", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameGreaterThanOrEqualTo(String value) {
            addCriterion("area_name >=", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameLessThan(String value) {
            addCriterion("area_name <", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameLessThanOrEqualTo(String value) {
            addCriterion("area_name <=", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameLike(String value) {
            addCriterion("area_name like", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameNotLike(String value) {
            addCriterion("area_name not like", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameIn(List<String> values) {
            addCriterion("area_name in", values, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameNotIn(List<String> values) {
            addCriterion("area_name not in", values, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameBetween(String value1, String value2) {
            addCriterion("area_name between", value1, value2, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameNotBetween(String value1, String value2) {
            addCriterion("area_name not between", value1, value2, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaCodeIsNull() {
            addCriterion("area_code is null");
            return (Criteria) this;
        }

        public Criteria andAreaCodeIsNotNull() {
            addCriterion("area_code is not null");
            return (Criteria) this;
        }

        public Criteria andAreaCodeEqualTo(String value) {
            addCriterion("area_code =", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotEqualTo(String value) {
            addCriterion("area_code <>", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeGreaterThan(String value) {
            addCriterion("area_code >", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeGreaterThanOrEqualTo(String value) {
            addCriterion("area_code >=", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeLessThan(String value) {
            addCriterion("area_code <", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeLessThanOrEqualTo(String value) {
            addCriterion("area_code <=", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeLike(String value) {
            addCriterion("area_code like", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotLike(String value) {
            addCriterion("area_code not like", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeIn(List<String> values) {
            addCriterion("area_code in", values, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotIn(List<String> values) {
            addCriterion("area_code not in", values, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeBetween(String value1, String value2) {
            addCriterion("area_code between", value1, value2, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotBetween(String value1, String value2) {
            addCriterion("area_code not between", value1, value2, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaShortIsNull() {
            addCriterion("area_short is null");
            return (Criteria) this;
        }

        public Criteria andAreaShortIsNotNull() {
            addCriterion("area_short is not null");
            return (Criteria) this;
        }

        public Criteria andAreaShortEqualTo(String value) {
            addCriterion("area_short =", value, "areaShort");
            return (Criteria) this;
        }

        public Criteria andAreaShortNotEqualTo(String value) {
            addCriterion("area_short <>", value, "areaShort");
            return (Criteria) this;
        }

        public Criteria andAreaShortGreaterThan(String value) {
            addCriterion("area_short >", value, "areaShort");
            return (Criteria) this;
        }

        public Criteria andAreaShortGreaterThanOrEqualTo(String value) {
            addCriterion("area_short >=", value, "areaShort");
            return (Criteria) this;
        }

        public Criteria andAreaShortLessThan(String value) {
            addCriterion("area_short <", value, "areaShort");
            return (Criteria) this;
        }

        public Criteria andAreaShortLessThanOrEqualTo(String value) {
            addCriterion("area_short <=", value, "areaShort");
            return (Criteria) this;
        }

        public Criteria andAreaShortLike(String value) {
            addCriterion("area_short like", value, "areaShort");
            return (Criteria) this;
        }

        public Criteria andAreaShortNotLike(String value) {
            addCriterion("area_short not like", value, "areaShort");
            return (Criteria) this;
        }

        public Criteria andAreaShortIn(List<String> values) {
            addCriterion("area_short in", values, "areaShort");
            return (Criteria) this;
        }

        public Criteria andAreaShortNotIn(List<String> values) {
            addCriterion("area_short not in", values, "areaShort");
            return (Criteria) this;
        }

        public Criteria andAreaShortBetween(String value1, String value2) {
            addCriterion("area_short between", value1, value2, "areaShort");
            return (Criteria) this;
        }

        public Criteria andAreaShortNotBetween(String value1, String value2) {
            addCriterion("area_short not between", value1, value2, "areaShort");
            return (Criteria) this;
        }

        public Criteria andAreaIsHotIsNull() {
            addCriterion("area_is_hot is null");
            return (Criteria) this;
        }

        public Criteria andAreaIsHotIsNotNull() {
            addCriterion("area_is_hot is not null");
            return (Criteria) this;
        }

        public Criteria andAreaIsHotEqualTo(String value) {
            addCriterion("area_is_hot =", value, "areaIsHot");
            return (Criteria) this;
        }

        public Criteria andAreaIsHotNotEqualTo(String value) {
            addCriterion("area_is_hot <>", value, "areaIsHot");
            return (Criteria) this;
        }

        public Criteria andAreaIsHotGreaterThan(String value) {
            addCriterion("area_is_hot >", value, "areaIsHot");
            return (Criteria) this;
        }

        public Criteria andAreaIsHotGreaterThanOrEqualTo(String value) {
            addCriterion("area_is_hot >=", value, "areaIsHot");
            return (Criteria) this;
        }

        public Criteria andAreaIsHotLessThan(String value) {
            addCriterion("area_is_hot <", value, "areaIsHot");
            return (Criteria) this;
        }

        public Criteria andAreaIsHotLessThanOrEqualTo(String value) {
            addCriterion("area_is_hot <=", value, "areaIsHot");
            return (Criteria) this;
        }

        public Criteria andAreaIsHotLike(String value) {
            addCriterion("area_is_hot like", value, "areaIsHot");
            return (Criteria) this;
        }

        public Criteria andAreaIsHotNotLike(String value) {
            addCriterion("area_is_hot not like", value, "areaIsHot");
            return (Criteria) this;
        }

        public Criteria andAreaIsHotIn(List<String> values) {
            addCriterion("area_is_hot in", values, "areaIsHot");
            return (Criteria) this;
        }

        public Criteria andAreaIsHotNotIn(List<String> values) {
            addCriterion("area_is_hot not in", values, "areaIsHot");
            return (Criteria) this;
        }

        public Criteria andAreaIsHotBetween(String value1, String value2) {
            addCriterion("area_is_hot between", value1, value2, "areaIsHot");
            return (Criteria) this;
        }

        public Criteria andAreaIsHotNotBetween(String value1, String value2) {
            addCriterion("area_is_hot not between", value1, value2, "areaIsHot");
            return (Criteria) this;
        }

        public Criteria andAreaSequenceIsNull() {
            addCriterion("area_sequence is null");
            return (Criteria) this;
        }

        public Criteria andAreaSequenceIsNotNull() {
            addCriterion("area_sequence is not null");
            return (Criteria) this;
        }

        public Criteria andAreaSequenceEqualTo(Integer value) {
            addCriterion("area_sequence =", value, "areaSequence");
            return (Criteria) this;
        }

        public Criteria andAreaSequenceNotEqualTo(Integer value) {
            addCriterion("area_sequence <>", value, "areaSequence");
            return (Criteria) this;
        }

        public Criteria andAreaSequenceGreaterThan(Integer value) {
            addCriterion("area_sequence >", value, "areaSequence");
            return (Criteria) this;
        }

        public Criteria andAreaSequenceGreaterThanOrEqualTo(Integer value) {
            addCriterion("area_sequence >=", value, "areaSequence");
            return (Criteria) this;
        }

        public Criteria andAreaSequenceLessThan(Integer value) {
            addCriterion("area_sequence <", value, "areaSequence");
            return (Criteria) this;
        }

        public Criteria andAreaSequenceLessThanOrEqualTo(Integer value) {
            addCriterion("area_sequence <=", value, "areaSequence");
            return (Criteria) this;
        }

        public Criteria andAreaSequenceIn(List<Integer> values) {
            addCriterion("area_sequence in", values, "areaSequence");
            return (Criteria) this;
        }

        public Criteria andAreaSequenceNotIn(List<Integer> values) {
            addCriterion("area_sequence not in", values, "areaSequence");
            return (Criteria) this;
        }

        public Criteria andAreaSequenceBetween(Integer value1, Integer value2) {
            addCriterion("area_sequence between", value1, value2, "areaSequence");
            return (Criteria) this;
        }

        public Criteria andAreaSequenceNotBetween(Integer value1, Integer value2) {
            addCriterion("area_sequence not between", value1, value2, "areaSequence");
            return (Criteria) this;
        }

        public Criteria andAreaParentIdIsNull() {
            addCriterion("area_parent_id is null");
            return (Criteria) this;
        }

        public Criteria andAreaParentIdIsNotNull() {
            addCriterion("area_parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andAreaParentIdEqualTo(Integer value) {
            addCriterion("area_parent_id =", value, "areaParentId");
            return (Criteria) this;
        }

        public Criteria andAreaParentIdNotEqualTo(Integer value) {
            addCriterion("area_parent_id <>", value, "areaParentId");
            return (Criteria) this;
        }

        public Criteria andAreaParentIdGreaterThan(Integer value) {
            addCriterion("area_parent_id >", value, "areaParentId");
            return (Criteria) this;
        }

        public Criteria andAreaParentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("area_parent_id >=", value, "areaParentId");
            return (Criteria) this;
        }

        public Criteria andAreaParentIdLessThan(Integer value) {
            addCriterion("area_parent_id <", value, "areaParentId");
            return (Criteria) this;
        }

        public Criteria andAreaParentIdLessThanOrEqualTo(Integer value) {
            addCriterion("area_parent_id <=", value, "areaParentId");
            return (Criteria) this;
        }

        public Criteria andAreaParentIdIn(List<Integer> values) {
            addCriterion("area_parent_id in", values, "areaParentId");
            return (Criteria) this;
        }

        public Criteria andAreaParentIdNotIn(List<Integer> values) {
            addCriterion("area_parent_id not in", values, "areaParentId");
            return (Criteria) this;
        }

        public Criteria andAreaParentIdBetween(Integer value1, Integer value2) {
            addCriterion("area_parent_id between", value1, value2, "areaParentId");
            return (Criteria) this;
        }

        public Criteria andAreaParentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("area_parent_id not between", value1, value2, "areaParentId");
            return (Criteria) this;
        }

        public Criteria andInitDateIsNull() {
            addCriterion("init_date is null");
            return (Criteria) this;
        }

        public Criteria andInitDateIsNotNull() {
            addCriterion("init_date is not null");
            return (Criteria) this;
        }

        public Criteria andInitDateEqualTo(Date value) {
            addCriterion("init_date =", value, "initDate");
            return (Criteria) this;
        }

        public Criteria andInitDateNotEqualTo(Date value) {
            addCriterion("init_date <>", value, "initDate");
            return (Criteria) this;
        }

        public Criteria andInitDateGreaterThan(Date value) {
            addCriterion("init_date >", value, "initDate");
            return (Criteria) this;
        }

        public Criteria andInitDateGreaterThanOrEqualTo(Date value) {
            addCriterion("init_date >=", value, "initDate");
            return (Criteria) this;
        }

        public Criteria andInitDateLessThan(Date value) {
            addCriterion("init_date <", value, "initDate");
            return (Criteria) this;
        }

        public Criteria andInitDateLessThanOrEqualTo(Date value) {
            addCriterion("init_date <=", value, "initDate");
            return (Criteria) this;
        }

        public Criteria andInitDateIn(List<Date> values) {
            addCriterion("init_date in", values, "initDate");
            return (Criteria) this;
        }

        public Criteria andInitDateNotIn(List<Date> values) {
            addCriterion("init_date not in", values, "initDate");
            return (Criteria) this;
        }

        public Criteria andInitDateBetween(Date value1, Date value2) {
            addCriterion("init_date between", value1, value2, "initDate");
            return (Criteria) this;
        }

        public Criteria andInitDateNotBetween(Date value1, Date value2) {
            addCriterion("init_date not between", value1, value2, "initDate");
            return (Criteria) this;
        }

        public Criteria andInitAddrIsNull() {
            addCriterion("init_addr is null");
            return (Criteria) this;
        }

        public Criteria andInitAddrIsNotNull() {
            addCriterion("init_addr is not null");
            return (Criteria) this;
        }

        public Criteria andInitAddrEqualTo(String value) {
            addCriterion("init_addr =", value, "initAddr");
            return (Criteria) this;
        }

        public Criteria andInitAddrNotEqualTo(String value) {
            addCriterion("init_addr <>", value, "initAddr");
            return (Criteria) this;
        }

        public Criteria andInitAddrGreaterThan(String value) {
            addCriterion("init_addr >", value, "initAddr");
            return (Criteria) this;
        }

        public Criteria andInitAddrGreaterThanOrEqualTo(String value) {
            addCriterion("init_addr >=", value, "initAddr");
            return (Criteria) this;
        }

        public Criteria andInitAddrLessThan(String value) {
            addCriterion("init_addr <", value, "initAddr");
            return (Criteria) this;
        }

        public Criteria andInitAddrLessThanOrEqualTo(String value) {
            addCriterion("init_addr <=", value, "initAddr");
            return (Criteria) this;
        }

        public Criteria andInitAddrLike(String value) {
            addCriterion("init_addr like", value, "initAddr");
            return (Criteria) this;
        }

        public Criteria andInitAddrNotLike(String value) {
            addCriterion("init_addr not like", value, "initAddr");
            return (Criteria) this;
        }

        public Criteria andInitAddrIn(List<String> values) {
            addCriterion("init_addr in", values, "initAddr");
            return (Criteria) this;
        }

        public Criteria andInitAddrNotIn(List<String> values) {
            addCriterion("init_addr not in", values, "initAddr");
            return (Criteria) this;
        }

        public Criteria andInitAddrBetween(String value1, String value2) {
            addCriterion("init_addr between", value1, value2, "initAddr");
            return (Criteria) this;
        }

        public Criteria andInitAddrNotBetween(String value1, String value2) {
            addCriterion("init_addr not between", value1, value2, "initAddr");
            return (Criteria) this;
        }
    }

    /**
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}