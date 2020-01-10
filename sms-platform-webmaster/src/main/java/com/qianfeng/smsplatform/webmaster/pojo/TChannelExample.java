package com.qianfeng.smsplatform.webmaster.pojo;

import java.util.ArrayList;
import java.util.List;

public class TChannelExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TChannelExample() {
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andChannelnameIsNull() {
            addCriterion("channelName is null");
            return (Criteria) this;
        }

        public Criteria andChannelnameIsNotNull() {
            addCriterion("channelName is not null");
            return (Criteria) this;
        }

        public Criteria andChannelnameEqualTo(String value) {
            addCriterion("channelName =", value, "channelname");
            return (Criteria) this;
        }

        public Criteria andChannelnameNotEqualTo(String value) {
            addCriterion("channelName <>", value, "channelname");
            return (Criteria) this;
        }

        public Criteria andChannelnameGreaterThan(String value) {
            addCriterion("channelName >", value, "channelname");
            return (Criteria) this;
        }

        public Criteria andChannelnameGreaterThanOrEqualTo(String value) {
            addCriterion("channelName >=", value, "channelname");
            return (Criteria) this;
        }

        public Criteria andChannelnameLessThan(String value) {
            addCriterion("channelName <", value, "channelname");
            return (Criteria) this;
        }

        public Criteria andChannelnameLessThanOrEqualTo(String value) {
            addCriterion("channelName <=", value, "channelname");
            return (Criteria) this;
        }

        public Criteria andChannelnameLike(String value) {
            addCriterion("channelName like", value, "channelname");
            return (Criteria) this;
        }

        public Criteria andChannelnameNotLike(String value) {
            addCriterion("channelName not like", value, "channelname");
            return (Criteria) this;
        }

        public Criteria andChannelnameIn(List<String> values) {
            addCriterion("channelName in", values, "channelname");
            return (Criteria) this;
        }

        public Criteria andChannelnameNotIn(List<String> values) {
            addCriterion("channelName not in", values, "channelname");
            return (Criteria) this;
        }

        public Criteria andChannelnameBetween(String value1, String value2) {
            addCriterion("channelName between", value1, value2, "channelname");
            return (Criteria) this;
        }

        public Criteria andChannelnameNotBetween(String value1, String value2) {
            addCriterion("channelName not between", value1, value2, "channelname");
            return (Criteria) this;
        }

        public Criteria andChanneltypeIsNull() {
            addCriterion("channelType is null");
            return (Criteria) this;
        }

        public Criteria andChanneltypeIsNotNull() {
            addCriterion("channelType is not null");
            return (Criteria) this;
        }

        public Criteria andChanneltypeEqualTo(Byte value) {
            addCriterion("channelType =", value, "channeltype");
            return (Criteria) this;
        }

        public Criteria andChanneltypeNotEqualTo(Byte value) {
            addCriterion("channelType <>", value, "channeltype");
            return (Criteria) this;
        }

        public Criteria andChanneltypeGreaterThan(Byte value) {
            addCriterion("channelType >", value, "channeltype");
            return (Criteria) this;
        }

        public Criteria andChanneltypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("channelType >=", value, "channeltype");
            return (Criteria) this;
        }

        public Criteria andChanneltypeLessThan(Byte value) {
            addCriterion("channelType <", value, "channeltype");
            return (Criteria) this;
        }

        public Criteria andChanneltypeLessThanOrEqualTo(Byte value) {
            addCriterion("channelType <=", value, "channeltype");
            return (Criteria) this;
        }

        public Criteria andChanneltypeIn(List<Byte> values) {
            addCriterion("channelType in", values, "channeltype");
            return (Criteria) this;
        }

        public Criteria andChanneltypeNotIn(List<Byte> values) {
            addCriterion("channelType not in", values, "channeltype");
            return (Criteria) this;
        }

        public Criteria andChanneltypeBetween(Byte value1, Byte value2) {
            addCriterion("channelType between", value1, value2, "channeltype");
            return (Criteria) this;
        }

        public Criteria andChanneltypeNotBetween(Byte value1, Byte value2) {
            addCriterion("channelType not between", value1, value2, "channeltype");
            return (Criteria) this;
        }

        public Criteria andSpnumberIsNull() {
            addCriterion("spNumber is null");
            return (Criteria) this;
        }

        public Criteria andSpnumberIsNotNull() {
            addCriterion("spNumber is not null");
            return (Criteria) this;
        }

        public Criteria andSpnumberEqualTo(String value) {
            addCriterion("spNumber =", value, "spnumber");
            return (Criteria) this;
        }

        public Criteria andSpnumberNotEqualTo(String value) {
            addCriterion("spNumber <>", value, "spnumber");
            return (Criteria) this;
        }

        public Criteria andSpnumberGreaterThan(String value) {
            addCriterion("spNumber >", value, "spnumber");
            return (Criteria) this;
        }

        public Criteria andSpnumberGreaterThanOrEqualTo(String value) {
            addCriterion("spNumber >=", value, "spnumber");
            return (Criteria) this;
        }

        public Criteria andSpnumberLessThan(String value) {
            addCriterion("spNumber <", value, "spnumber");
            return (Criteria) this;
        }

        public Criteria andSpnumberLessThanOrEqualTo(String value) {
            addCriterion("spNumber <=", value, "spnumber");
            return (Criteria) this;
        }

        public Criteria andSpnumberLike(String value) {
            addCriterion("spNumber like", value, "spnumber");
            return (Criteria) this;
        }

        public Criteria andSpnumberNotLike(String value) {
            addCriterion("spNumber not like", value, "spnumber");
            return (Criteria) this;
        }

        public Criteria andSpnumberIn(List<String> values) {
            addCriterion("spNumber in", values, "spnumber");
            return (Criteria) this;
        }

        public Criteria andSpnumberNotIn(List<String> values) {
            addCriterion("spNumber not in", values, "spnumber");
            return (Criteria) this;
        }

        public Criteria andSpnumberBetween(String value1, String value2) {
            addCriterion("spNumber between", value1, value2, "spnumber");
            return (Criteria) this;
        }

        public Criteria andSpnumberNotBetween(String value1, String value2) {
            addCriterion("spNumber not between", value1, value2, "spnumber");
            return (Criteria) this;
        }

        public Criteria andProtocaltypeIsNull() {
            addCriterion("protocalType is null");
            return (Criteria) this;
        }

        public Criteria andProtocaltypeIsNotNull() {
            addCriterion("protocalType is not null");
            return (Criteria) this;
        }

        public Criteria andProtocaltypeEqualTo(Byte value) {
            addCriterion("protocalType =", value, "protocaltype");
            return (Criteria) this;
        }

        public Criteria andProtocaltypeNotEqualTo(Byte value) {
            addCriterion("protocalType <>", value, "protocaltype");
            return (Criteria) this;
        }

        public Criteria andProtocaltypeGreaterThan(Byte value) {
            addCriterion("protocalType >", value, "protocaltype");
            return (Criteria) this;
        }

        public Criteria andProtocaltypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("protocalType >=", value, "protocaltype");
            return (Criteria) this;
        }

        public Criteria andProtocaltypeLessThan(Byte value) {
            addCriterion("protocalType <", value, "protocaltype");
            return (Criteria) this;
        }

        public Criteria andProtocaltypeLessThanOrEqualTo(Byte value) {
            addCriterion("protocalType <=", value, "protocaltype");
            return (Criteria) this;
        }

        public Criteria andProtocaltypeIn(List<Byte> values) {
            addCriterion("protocalType in", values, "protocaltype");
            return (Criteria) this;
        }

        public Criteria andProtocaltypeNotIn(List<Byte> values) {
            addCriterion("protocalType not in", values, "protocaltype");
            return (Criteria) this;
        }

        public Criteria andProtocaltypeBetween(Byte value1, Byte value2) {
            addCriterion("protocalType between", value1, value2, "protocaltype");
            return (Criteria) this;
        }

        public Criteria andProtocaltypeNotBetween(Byte value1, Byte value2) {
            addCriterion("protocalType not between", value1, value2, "protocaltype");
            return (Criteria) this;
        }
    }

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