package com.qianfeng.smsplatform.webmaster.pojo;

import java.util.ArrayList;
import java.util.List;

public class TClientChannelExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TClientChannelExample() {
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

        public Criteria andClientidIsNull() {
            addCriterion("clientId is null");
            return (Criteria) this;
        }

        public Criteria andClientidIsNotNull() {
            addCriterion("clientId is not null");
            return (Criteria) this;
        }

        public Criteria andClientidEqualTo(Long value) {
            addCriterion("clientId =", value, "clientid");
            return (Criteria) this;
        }

        public Criteria andClientidNotEqualTo(Long value) {
            addCriterion("clientId <>", value, "clientid");
            return (Criteria) this;
        }

        public Criteria andClientidGreaterThan(Long value) {
            addCriterion("clientId >", value, "clientid");
            return (Criteria) this;
        }

        public Criteria andClientidGreaterThanOrEqualTo(Long value) {
            addCriterion("clientId >=", value, "clientid");
            return (Criteria) this;
        }

        public Criteria andClientidLessThan(Long value) {
            addCriterion("clientId <", value, "clientid");
            return (Criteria) this;
        }

        public Criteria andClientidLessThanOrEqualTo(Long value) {
            addCriterion("clientId <=", value, "clientid");
            return (Criteria) this;
        }

        public Criteria andClientidIn(List<Long> values) {
            addCriterion("clientId in", values, "clientid");
            return (Criteria) this;
        }

        public Criteria andClientidNotIn(List<Long> values) {
            addCriterion("clientId not in", values, "clientid");
            return (Criteria) this;
        }

        public Criteria andClientidBetween(Long value1, Long value2) {
            addCriterion("clientId between", value1, value2, "clientid");
            return (Criteria) this;
        }

        public Criteria andClientidNotBetween(Long value1, Long value2) {
            addCriterion("clientId not between", value1, value2, "clientid");
            return (Criteria) this;
        }

        public Criteria andExtendnumberIsNull() {
            addCriterion("extendNumber is null");
            return (Criteria) this;
        }

        public Criteria andExtendnumberIsNotNull() {
            addCriterion("extendNumber is not null");
            return (Criteria) this;
        }

        public Criteria andExtendnumberEqualTo(String value) {
            addCriterion("extendNumber =", value, "extendnumber");
            return (Criteria) this;
        }

        public Criteria andExtendnumberNotEqualTo(String value) {
            addCriterion("extendNumber <>", value, "extendnumber");
            return (Criteria) this;
        }

        public Criteria andExtendnumberGreaterThan(String value) {
            addCriterion("extendNumber >", value, "extendnumber");
            return (Criteria) this;
        }

        public Criteria andExtendnumberGreaterThanOrEqualTo(String value) {
            addCriterion("extendNumber >=", value, "extendnumber");
            return (Criteria) this;
        }

        public Criteria andExtendnumberLessThan(String value) {
            addCriterion("extendNumber <", value, "extendnumber");
            return (Criteria) this;
        }

        public Criteria andExtendnumberLessThanOrEqualTo(String value) {
            addCriterion("extendNumber <=", value, "extendnumber");
            return (Criteria) this;
        }

        public Criteria andExtendnumberLike(String value) {
            addCriterion("extendNumber like", value, "extendnumber");
            return (Criteria) this;
        }

        public Criteria andExtendnumberNotLike(String value) {
            addCriterion("extendNumber not like", value, "extendnumber");
            return (Criteria) this;
        }

        public Criteria andExtendnumberIn(List<String> values) {
            addCriterion("extendNumber in", values, "extendnumber");
            return (Criteria) this;
        }

        public Criteria andExtendnumberNotIn(List<String> values) {
            addCriterion("extendNumber not in", values, "extendnumber");
            return (Criteria) this;
        }

        public Criteria andExtendnumberBetween(String value1, String value2) {
            addCriterion("extendNumber between", value1, value2, "extendnumber");
            return (Criteria) this;
        }

        public Criteria andExtendnumberNotBetween(String value1, String value2) {
            addCriterion("extendNumber not between", value1, value2, "extendnumber");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(Integer value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(Integer value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(Integer value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(Integer value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(Integer value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<Integer> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<Integer> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(Integer value1, Integer value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(Integer value1, Integer value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andChannelidIsNull() {
            addCriterion("channelId is null");
            return (Criteria) this;
        }

        public Criteria andChannelidIsNotNull() {
            addCriterion("channelId is not null");
            return (Criteria) this;
        }

        public Criteria andChannelidEqualTo(Integer value) {
            addCriterion("channelId =", value, "channelid");
            return (Criteria) this;
        }

        public Criteria andChannelidNotEqualTo(Integer value) {
            addCriterion("channelId <>", value, "channelid");
            return (Criteria) this;
        }

        public Criteria andChannelidGreaterThan(Integer value) {
            addCriterion("channelId >", value, "channelid");
            return (Criteria) this;
        }

        public Criteria andChannelidGreaterThanOrEqualTo(Integer value) {
            addCriterion("channelId >=", value, "channelid");
            return (Criteria) this;
        }

        public Criteria andChannelidLessThan(Integer value) {
            addCriterion("channelId <", value, "channelid");
            return (Criteria) this;
        }

        public Criteria andChannelidLessThanOrEqualTo(Integer value) {
            addCriterion("channelId <=", value, "channelid");
            return (Criteria) this;
        }

        public Criteria andChannelidIn(List<Integer> values) {
            addCriterion("channelId in", values, "channelid");
            return (Criteria) this;
        }

        public Criteria andChannelidNotIn(List<Integer> values) {
            addCriterion("channelId not in", values, "channelid");
            return (Criteria) this;
        }

        public Criteria andChannelidBetween(Integer value1, Integer value2) {
            addCriterion("channelId between", value1, value2, "channelid");
            return (Criteria) this;
        }

        public Criteria andChannelidNotBetween(Integer value1, Integer value2) {
            addCriterion("channelId not between", value1, value2, "channelid");
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