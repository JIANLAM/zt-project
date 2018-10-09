package com.szcti.lcloud.purchase.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PurchaseRuleCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PurchaseRuleCriteria() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andRuleNameIsNull() {
            addCriterion("rule_name is null");
            return (Criteria) this;
        }

        public Criteria andRuleNameIsNotNull() {
            addCriterion("rule_name is not null");
            return (Criteria) this;
        }

        public Criteria andRuleNameEqualTo(String value) {
            addCriterion("rule_name =", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameNotEqualTo(String value) {
            addCriterion("rule_name <>", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameGreaterThan(String value) {
            addCriterion("rule_name >", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameGreaterThanOrEqualTo(String value) {
            addCriterion("rule_name >=", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameLessThan(String value) {
            addCriterion("rule_name <", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameLessThanOrEqualTo(String value) {
            addCriterion("rule_name <=", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameLike(String value) {
            addCriterion("rule_name like", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameNotLike(String value) {
            addCriterion("rule_name not like", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameIn(List<String> values) {
            addCriterion("rule_name in", values, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameNotIn(List<String> values) {
            addCriterion("rule_name not in", values, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameBetween(String value1, String value2) {
            addCriterion("rule_name between", value1, value2, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameNotBetween(String value1, String value2) {
            addCriterion("rule_name not between", value1, value2, "ruleName");
            return (Criteria) this;
        }

        public Criteria andCheckerIsNull() {
            addCriterion("checker is null");
            return (Criteria) this;
        }

        public Criteria andCheckerIsNotNull() {
            addCriterion("checker is not null");
            return (Criteria) this;
        }

        public Criteria andCheckerEqualTo(Long value) {
            addCriterion("checker =", value, "checker");
            return (Criteria) this;
        }

        public Criteria andCheckerNotEqualTo(Long value) {
            addCriterion("checker <>", value, "checker");
            return (Criteria) this;
        }

        public Criteria andCheckerGreaterThan(Long value) {
            addCriterion("checker >", value, "checker");
            return (Criteria) this;
        }

        public Criteria andCheckerGreaterThanOrEqualTo(Long value) {
            addCriterion("checker >=", value, "checker");
            return (Criteria) this;
        }

        public Criteria andCheckerLessThan(Long value) {
            addCriterion("checker <", value, "checker");
            return (Criteria) this;
        }

        public Criteria andCheckerLessThanOrEqualTo(Long value) {
            addCriterion("checker <=", value, "checker");
            return (Criteria) this;
        }

        public Criteria andCheckerIn(List<Long> values) {
            addCriterion("checker in", values, "checker");
            return (Criteria) this;
        }

        public Criteria andCheckerNotIn(List<Long> values) {
            addCriterion("checker not in", values, "checker");
            return (Criteria) this;
        }

        public Criteria andCheckerBetween(Long value1, Long value2) {
            addCriterion("checker between", value1, value2, "checker");
            return (Criteria) this;
        }

        public Criteria andCheckerNotBetween(Long value1, Long value2) {
            addCriterion("checker not between", value1, value2, "checker");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Date value) {
            addCriterionForJDBCDate("start_time =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("start_time <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("start_time >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("start_time >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Date value) {
            addCriterionForJDBCDate("start_time <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("start_time <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Date> values) {
            addCriterionForJDBCDate("start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("start_time not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterionForJDBCDate("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterionForJDBCDate("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterionForJDBCDate("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNull() {
            addCriterion("creator is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNotNull() {
            addCriterion("creator is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorEqualTo(Long value) {
            addCriterion("creator =", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotEqualTo(Long value) {
            addCriterion("creator <>", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThan(Long value) {
            addCriterion("creator >", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThanOrEqualTo(Long value) {
            addCriterion("creator >=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThan(Long value) {
            addCriterion("creator <", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThanOrEqualTo(Long value) {
            addCriterion("creator <=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorIn(List<Long> values) {
            addCriterion("creator in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotIn(List<Long> values) {
            addCriterion("creator not in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorBetween(Long value1, Long value2) {
            addCriterion("creator between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotBetween(Long value1, Long value2) {
            addCriterion("creator not between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andBudgetIdIsNull() {
            addCriterion("budget_id is null");
            return (Criteria) this;
        }

        public Criteria andBudgetIdIsNotNull() {
            addCriterion("budget_id is not null");
            return (Criteria) this;
        }

        public Criteria andBudgetIdEqualTo(Long value) {
            addCriterion("budget_id =", value, "budgetId");
            return (Criteria) this;
        }

        public Criteria andBudgetIdNotEqualTo(Long value) {
            addCriterion("budget_id <>", value, "budgetId");
            return (Criteria) this;
        }

        public Criteria andBudgetIdGreaterThan(Long value) {
            addCriterion("budget_id >", value, "budgetId");
            return (Criteria) this;
        }

        public Criteria andBudgetIdGreaterThanOrEqualTo(Long value) {
            addCriterion("budget_id >=", value, "budgetId");
            return (Criteria) this;
        }

        public Criteria andBudgetIdLessThan(Long value) {
            addCriterion("budget_id <", value, "budgetId");
            return (Criteria) this;
        }

        public Criteria andBudgetIdLessThanOrEqualTo(Long value) {
            addCriterion("budget_id <=", value, "budgetId");
            return (Criteria) this;
        }

        public Criteria andBudgetIdIn(List<Long> values) {
            addCriterion("budget_id in", values, "budgetId");
            return (Criteria) this;
        }

        public Criteria andBudgetIdNotIn(List<Long> values) {
            addCriterion("budget_id not in", values, "budgetId");
            return (Criteria) this;
        }

        public Criteria andBudgetIdBetween(Long value1, Long value2) {
            addCriterion("budget_id between", value1, value2, "budgetId");
            return (Criteria) this;
        }

        public Criteria andBudgetIdNotBetween(Long value1, Long value2) {
            addCriterion("budget_id not between", value1, value2, "budgetId");
            return (Criteria) this;
        }

        public Criteria andBudgetPriceIsNull() {
            addCriterion("budget_price is null");
            return (Criteria) this;
        }

        public Criteria andBudgetPriceIsNotNull() {
            addCriterion("budget_price is not null");
            return (Criteria) this;
        }

        public Criteria andBudgetPriceEqualTo(String value) {
            addCriterion("budget_price =", value, "budgetPrice");
            return (Criteria) this;
        }

        public Criteria andBudgetPriceNotEqualTo(String value) {
            addCriterion("budget_price <>", value, "budgetPrice");
            return (Criteria) this;
        }

        public Criteria andBudgetPriceGreaterThan(String value) {
            addCriterion("budget_price >", value, "budgetPrice");
            return (Criteria) this;
        }

        public Criteria andBudgetPriceGreaterThanOrEqualTo(String value) {
            addCriterion("budget_price >=", value, "budgetPrice");
            return (Criteria) this;
        }

        public Criteria andBudgetPriceLessThan(String value) {
            addCriterion("budget_price <", value, "budgetPrice");
            return (Criteria) this;
        }

        public Criteria andBudgetPriceLessThanOrEqualTo(String value) {
            addCriterion("budget_price <=", value, "budgetPrice");
            return (Criteria) this;
        }

        public Criteria andBudgetPriceLike(String value) {
            addCriterion("budget_price like", value, "budgetPrice");
            return (Criteria) this;
        }

        public Criteria andBudgetPriceNotLike(String value) {
            addCriterion("budget_price not like", value, "budgetPrice");
            return (Criteria) this;
        }

        public Criteria andBudgetPriceIn(List<String> values) {
            addCriterion("budget_price in", values, "budgetPrice");
            return (Criteria) this;
        }

        public Criteria andBudgetPriceNotIn(List<String> values) {
            addCriterion("budget_price not in", values, "budgetPrice");
            return (Criteria) this;
        }

        public Criteria andBudgetPriceBetween(String value1, String value2) {
            addCriterion("budget_price between", value1, value2, "budgetPrice");
            return (Criteria) this;
        }

        public Criteria andBudgetPriceNotBetween(String value1, String value2) {
            addCriterion("budget_price not between", value1, value2, "budgetPrice");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andDuplicateQtyIsNull() {
            addCriterion("duplicate_qty is null");
            return (Criteria) this;
        }

        public Criteria andDuplicateQtyIsNotNull() {
            addCriterion("duplicate_qty is not null");
            return (Criteria) this;
        }

        public Criteria andDuplicateQtyEqualTo(Integer value) {
            addCriterion("duplicate_qty =", value, "duplicateQty");
            return (Criteria) this;
        }

        public Criteria andDuplicateQtyNotEqualTo(Integer value) {
            addCriterion("duplicate_qty <>", value, "duplicateQty");
            return (Criteria) this;
        }

        public Criteria andDuplicateQtyGreaterThan(Integer value) {
            addCriterion("duplicate_qty >", value, "duplicateQty");
            return (Criteria) this;
        }

        public Criteria andDuplicateQtyGreaterThanOrEqualTo(Integer value) {
            addCriterion("duplicate_qty >=", value, "duplicateQty");
            return (Criteria) this;
        }

        public Criteria andDuplicateQtyLessThan(Integer value) {
            addCriterion("duplicate_qty <", value, "duplicateQty");
            return (Criteria) this;
        }

        public Criteria andDuplicateQtyLessThanOrEqualTo(Integer value) {
            addCriterion("duplicate_qty <=", value, "duplicateQty");
            return (Criteria) this;
        }

        public Criteria andDuplicateQtyIn(List<Integer> values) {
            addCriterion("duplicate_qty in", values, "duplicateQty");
            return (Criteria) this;
        }

        public Criteria andDuplicateQtyNotIn(List<Integer> values) {
            addCriterion("duplicate_qty not in", values, "duplicateQty");
            return (Criteria) this;
        }

        public Criteria andDuplicateQtyBetween(Integer value1, Integer value2) {
            addCriterion("duplicate_qty between", value1, value2, "duplicateQty");
            return (Criteria) this;
        }

        public Criteria andDuplicateQtyNotBetween(Integer value1, Integer value2) {
            addCriterion("duplicate_qty not between", value1, value2, "duplicateQty");
            return (Criteria) this;
        }

        public Criteria andPublishYearIsNull() {
            addCriterion("publish_year is null");
            return (Criteria) this;
        }

        public Criteria andPublishYearIsNotNull() {
            addCriterion("publish_year is not null");
            return (Criteria) this;
        }

        public Criteria andPublishYearEqualTo(String value) {
            addCriterion("publish_year =", value, "publishYear");
            return (Criteria) this;
        }

        public Criteria andPublishYearNotEqualTo(String value) {
            addCriterion("publish_year <>", value, "publishYear");
            return (Criteria) this;
        }

        public Criteria andPublishYearGreaterThan(String value) {
            addCriterion("publish_year >", value, "publishYear");
            return (Criteria) this;
        }

        public Criteria andPublishYearGreaterThanOrEqualTo(String value) {
            addCriterion("publish_year >=", value, "publishYear");
            return (Criteria) this;
        }

        public Criteria andPublishYearLessThan(String value) {
            addCriterion("publish_year <", value, "publishYear");
            return (Criteria) this;
        }

        public Criteria andPublishYearLessThanOrEqualTo(String value) {
            addCriterion("publish_year <=", value, "publishYear");
            return (Criteria) this;
        }

        public Criteria andPublishYearLike(String value) {
            addCriterion("publish_year like", value, "publishYear");
            return (Criteria) this;
        }

        public Criteria andPublishYearNotLike(String value) {
            addCriterion("publish_year not like", value, "publishYear");
            return (Criteria) this;
        }

        public Criteria andPublishYearIn(List<String> values) {
            addCriterion("publish_year in", values, "publishYear");
            return (Criteria) this;
        }

        public Criteria andPublishYearNotIn(List<String> values) {
            addCriterion("publish_year not in", values, "publishYear");
            return (Criteria) this;
        }

        public Criteria andPublishYearBetween(String value1, String value2) {
            addCriterion("publish_year between", value1, value2, "publishYear");
            return (Criteria) this;
        }

        public Criteria andPublishYearNotBetween(String value1, String value2) {
            addCriterion("publish_year not between", value1, value2, "publishYear");
            return (Criteria) this;
        }

        public Criteria andCategoryNoIsNull() {
            addCriterion("category_no is null");
            return (Criteria) this;
        }

        public Criteria andCategoryNoIsNotNull() {
            addCriterion("category_no is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryNoEqualTo(String value) {
            addCriterion("category_no =", value, "categoryNo");
            return (Criteria) this;
        }

        public Criteria andCategoryNoNotEqualTo(String value) {
            addCriterion("category_no <>", value, "categoryNo");
            return (Criteria) this;
        }

        public Criteria andCategoryNoGreaterThan(String value) {
            addCriterion("category_no >", value, "categoryNo");
            return (Criteria) this;
        }

        public Criteria andCategoryNoGreaterThanOrEqualTo(String value) {
            addCriterion("category_no >=", value, "categoryNo");
            return (Criteria) this;
        }

        public Criteria andCategoryNoLessThan(String value) {
            addCriterion("category_no <", value, "categoryNo");
            return (Criteria) this;
        }

        public Criteria andCategoryNoLessThanOrEqualTo(String value) {
            addCriterion("category_no <=", value, "categoryNo");
            return (Criteria) this;
        }

        public Criteria andCategoryNoLike(String value) {
            addCriterion("category_no like", value, "categoryNo");
            return (Criteria) this;
        }

        public Criteria andCategoryNoNotLike(String value) {
            addCriterion("category_no not like", value, "categoryNo");
            return (Criteria) this;
        }

        public Criteria andCategoryNoIn(List<String> values) {
            addCriterion("category_no in", values, "categoryNo");
            return (Criteria) this;
        }

        public Criteria andCategoryNoNotIn(List<String> values) {
            addCriterion("category_no not in", values, "categoryNo");
            return (Criteria) this;
        }

        public Criteria andCategoryNoBetween(String value1, String value2) {
            addCriterion("category_no between", value1, value2, "categoryNo");
            return (Criteria) this;
        }

        public Criteria andCategoryNoNotBetween(String value1, String value2) {
            addCriterion("category_no not between", value1, value2, "categoryNo");
            return (Criteria) this;
        }

        public Criteria andBookPriceIsNull() {
            addCriterion("book_price is null");
            return (Criteria) this;
        }

        public Criteria andBookPriceIsNotNull() {
            addCriterion("book_price is not null");
            return (Criteria) this;
        }

        public Criteria andBookPriceEqualTo(String value) {
            addCriterion("book_price =", value, "bookPrice");
            return (Criteria) this;
        }

        public Criteria andBookPriceNotEqualTo(String value) {
            addCriterion("book_price <>", value, "bookPrice");
            return (Criteria) this;
        }

        public Criteria andBookPriceGreaterThan(String value) {
            addCriterion("book_price >", value, "bookPrice");
            return (Criteria) this;
        }

        public Criteria andBookPriceGreaterThanOrEqualTo(String value) {
            addCriterion("book_price >=", value, "bookPrice");
            return (Criteria) this;
        }

        public Criteria andBookPriceLessThan(String value) {
            addCriterion("book_price <", value, "bookPrice");
            return (Criteria) this;
        }

        public Criteria andBookPriceLessThanOrEqualTo(String value) {
            addCriterion("book_price <=", value, "bookPrice");
            return (Criteria) this;
        }

        public Criteria andBookPriceLike(String value) {
            addCriterion("book_price like", value, "bookPrice");
            return (Criteria) this;
        }

        public Criteria andBookPriceNotLike(String value) {
            addCriterion("book_price not like", value, "bookPrice");
            return (Criteria) this;
        }

        public Criteria andBookPriceIn(List<String> values) {
            addCriterion("book_price in", values, "bookPrice");
            return (Criteria) this;
        }

        public Criteria andBookPriceNotIn(List<String> values) {
            addCriterion("book_price not in", values, "bookPrice");
            return (Criteria) this;
        }

        public Criteria andBookPriceBetween(String value1, String value2) {
            addCriterion("book_price between", value1, value2, "bookPrice");
            return (Criteria) this;
        }

        public Criteria andBookPriceNotBetween(String value1, String value2) {
            addCriterion("book_price not between", value1, value2, "bookPrice");
            return (Criteria) this;
        }

        public Criteria andBookSizeIsNull() {
            addCriterion("book_size is null");
            return (Criteria) this;
        }

        public Criteria andBookSizeIsNotNull() {
            addCriterion("book_size is not null");
            return (Criteria) this;
        }

        public Criteria andBookSizeEqualTo(String value) {
            addCriterion("book_size =", value, "bookSize");
            return (Criteria) this;
        }

        public Criteria andBookSizeNotEqualTo(String value) {
            addCriterion("book_size <>", value, "bookSize");
            return (Criteria) this;
        }

        public Criteria andBookSizeGreaterThan(String value) {
            addCriterion("book_size >", value, "bookSize");
            return (Criteria) this;
        }

        public Criteria andBookSizeGreaterThanOrEqualTo(String value) {
            addCriterion("book_size >=", value, "bookSize");
            return (Criteria) this;
        }

        public Criteria andBookSizeLessThan(String value) {
            addCriterion("book_size <", value, "bookSize");
            return (Criteria) this;
        }

        public Criteria andBookSizeLessThanOrEqualTo(String value) {
            addCriterion("book_size <=", value, "bookSize");
            return (Criteria) this;
        }

        public Criteria andBookSizeLike(String value) {
            addCriterion("book_size like", value, "bookSize");
            return (Criteria) this;
        }

        public Criteria andBookSizeNotLike(String value) {
            addCriterion("book_size not like", value, "bookSize");
            return (Criteria) this;
        }

        public Criteria andBookSizeIn(List<String> values) {
            addCriterion("book_size in", values, "bookSize");
            return (Criteria) this;
        }

        public Criteria andBookSizeNotIn(List<String> values) {
            addCriterion("book_size not in", values, "bookSize");
            return (Criteria) this;
        }

        public Criteria andBookSizeBetween(String value1, String value2) {
            addCriterion("book_size between", value1, value2, "bookSize");
            return (Criteria) this;
        }

        public Criteria andBookSizeNotBetween(String value1, String value2) {
            addCriterion("book_size not between", value1, value2, "bookSize");
            return (Criteria) this;
        }

        public Criteria andBookPagesIsNull() {
            addCriterion("book_pages is null");
            return (Criteria) this;
        }

        public Criteria andBookPagesIsNotNull() {
            addCriterion("book_pages is not null");
            return (Criteria) this;
        }

        public Criteria andBookPagesEqualTo(String value) {
            addCriterion("book_pages =", value, "bookPages");
            return (Criteria) this;
        }

        public Criteria andBookPagesNotEqualTo(String value) {
            addCriterion("book_pages <>", value, "bookPages");
            return (Criteria) this;
        }

        public Criteria andBookPagesGreaterThan(String value) {
            addCriterion("book_pages >", value, "bookPages");
            return (Criteria) this;
        }

        public Criteria andBookPagesGreaterThanOrEqualTo(String value) {
            addCriterion("book_pages >=", value, "bookPages");
            return (Criteria) this;
        }

        public Criteria andBookPagesLessThan(String value) {
            addCriterion("book_pages <", value, "bookPages");
            return (Criteria) this;
        }

        public Criteria andBookPagesLessThanOrEqualTo(String value) {
            addCriterion("book_pages <=", value, "bookPages");
            return (Criteria) this;
        }

        public Criteria andBookPagesLike(String value) {
            addCriterion("book_pages like", value, "bookPages");
            return (Criteria) this;
        }

        public Criteria andBookPagesNotLike(String value) {
            addCriterion("book_pages not like", value, "bookPages");
            return (Criteria) this;
        }

        public Criteria andBookPagesIn(List<String> values) {
            addCriterion("book_pages in", values, "bookPages");
            return (Criteria) this;
        }

        public Criteria andBookPagesNotIn(List<String> values) {
            addCriterion("book_pages not in", values, "bookPages");
            return (Criteria) this;
        }

        public Criteria andBookPagesBetween(String value1, String value2) {
            addCriterion("book_pages between", value1, value2, "bookPages");
            return (Criteria) this;
        }

        public Criteria andBookPagesNotBetween(String value1, String value2) {
            addCriterion("book_pages not between", value1, value2, "bookPages");
            return (Criteria) this;
        }

        public Criteria andPublisherIsNull() {
            addCriterion("publisher is null");
            return (Criteria) this;
        }

        public Criteria andPublisherIsNotNull() {
            addCriterion("publisher is not null");
            return (Criteria) this;
        }

        public Criteria andPublisherEqualTo(String value) {
            addCriterion("publisher =", value, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherNotEqualTo(String value) {
            addCriterion("publisher <>", value, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherGreaterThan(String value) {
            addCriterion("publisher >", value, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherGreaterThanOrEqualTo(String value) {
            addCriterion("publisher >=", value, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherLessThan(String value) {
            addCriterion("publisher <", value, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherLessThanOrEqualTo(String value) {
            addCriterion("publisher <=", value, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherLike(String value) {
            addCriterion("publisher like", value, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherNotLike(String value) {
            addCriterion("publisher not like", value, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherIn(List<String> values) {
            addCriterion("publisher in", values, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherNotIn(List<String> values) {
            addCriterion("publisher not in", values, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherBetween(String value1, String value2) {
            addCriterion("publisher between", value1, value2, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherNotBetween(String value1, String value2) {
            addCriterion("publisher not between", value1, value2, "publisher");
            return (Criteria) this;
        }

        public Criteria andLibraryIdIsNull() {
            addCriterion("library_id is null");
            return (Criteria) this;
        }

        public Criteria andLibraryIdIsNotNull() {
            addCriterion("library_id is not null");
            return (Criteria) this;
        }

        public Criteria andLibraryIdEqualTo(Long value) {
            addCriterion("library_id =", value, "libraryId");
            return (Criteria) this;
        }

        public Criteria andLibraryIdNotEqualTo(Long value) {
            addCriterion("library_id <>", value, "libraryId");
            return (Criteria) this;
        }

        public Criteria andLibraryIdGreaterThan(Long value) {
            addCriterion("library_id >", value, "libraryId");
            return (Criteria) this;
        }

        public Criteria andLibraryIdGreaterThanOrEqualTo(Long value) {
            addCriterion("library_id >=", value, "libraryId");
            return (Criteria) this;
        }

        public Criteria andLibraryIdLessThan(Long value) {
            addCriterion("library_id <", value, "libraryId");
            return (Criteria) this;
        }

        public Criteria andLibraryIdLessThanOrEqualTo(Long value) {
            addCriterion("library_id <=", value, "libraryId");
            return (Criteria) this;
        }

        public Criteria andLibraryIdIn(List<Long> values) {
            addCriterion("library_id in", values, "libraryId");
            return (Criteria) this;
        }

        public Criteria andLibraryIdNotIn(List<Long> values) {
            addCriterion("library_id not in", values, "libraryId");
            return (Criteria) this;
        }

        public Criteria andLibraryIdBetween(Long value1, Long value2) {
            addCriterion("library_id between", value1, value2, "libraryId");
            return (Criteria) this;
        }

        public Criteria andLibraryIdNotBetween(Long value1, Long value2) {
            addCriterion("library_id not between", value1, value2, "libraryId");
            return (Criteria) this;
        }

        public Criteria andPublishYearAllowIsNull() {
            addCriterion("publish_year_allow is null");
            return (Criteria) this;
        }

        public Criteria andPublishYearAllowIsNotNull() {
            addCriterion("publish_year_allow is not null");
            return (Criteria) this;
        }

        public Criteria andPublishYearAllowEqualTo(Integer value) {
            addCriterion("publish_year_allow =", value, "publishYearAllow");
            return (Criteria) this;
        }

        public Criteria andPublishYearAllowNotEqualTo(Integer value) {
            addCriterion("publish_year_allow <>", value, "publishYearAllow");
            return (Criteria) this;
        }

        public Criteria andPublishYearAllowGreaterThan(Integer value) {
            addCriterion("publish_year_allow >", value, "publishYearAllow");
            return (Criteria) this;
        }

        public Criteria andPublishYearAllowGreaterThanOrEqualTo(Integer value) {
            addCriterion("publish_year_allow >=", value, "publishYearAllow");
            return (Criteria) this;
        }

        public Criteria andPublishYearAllowLessThan(Integer value) {
            addCriterion("publish_year_allow <", value, "publishYearAllow");
            return (Criteria) this;
        }

        public Criteria andPublishYearAllowLessThanOrEqualTo(Integer value) {
            addCriterion("publish_year_allow <=", value, "publishYearAllow");
            return (Criteria) this;
        }

        public Criteria andPublishYearAllowIn(List<Integer> values) {
            addCriterion("publish_year_allow in", values, "publishYearAllow");
            return (Criteria) this;
        }

        public Criteria andPublishYearAllowNotIn(List<Integer> values) {
            addCriterion("publish_year_allow not in", values, "publishYearAllow");
            return (Criteria) this;
        }

        public Criteria andPublishYearAllowBetween(Integer value1, Integer value2) {
            addCriterion("publish_year_allow between", value1, value2, "publishYearAllow");
            return (Criteria) this;
        }

        public Criteria andPublishYearAllowNotBetween(Integer value1, Integer value2) {
            addCriterion("publish_year_allow not between", value1, value2, "publishYearAllow");
            return (Criteria) this;
        }

        public Criteria andCategoryNoAllowIsNull() {
            addCriterion("category_no_allow is null");
            return (Criteria) this;
        }

        public Criteria andCategoryNoAllowIsNotNull() {
            addCriterion("category_no_allow is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryNoAllowEqualTo(Integer value) {
            addCriterion("category_no_allow =", value, "categoryNoAllow");
            return (Criteria) this;
        }

        public Criteria andCategoryNoAllowNotEqualTo(Integer value) {
            addCriterion("category_no_allow <>", value, "categoryNoAllow");
            return (Criteria) this;
        }

        public Criteria andCategoryNoAllowGreaterThan(Integer value) {
            addCriterion("category_no_allow >", value, "categoryNoAllow");
            return (Criteria) this;
        }

        public Criteria andCategoryNoAllowGreaterThanOrEqualTo(Integer value) {
            addCriterion("category_no_allow >=", value, "categoryNoAllow");
            return (Criteria) this;
        }

        public Criteria andCategoryNoAllowLessThan(Integer value) {
            addCriterion("category_no_allow <", value, "categoryNoAllow");
            return (Criteria) this;
        }

        public Criteria andCategoryNoAllowLessThanOrEqualTo(Integer value) {
            addCriterion("category_no_allow <=", value, "categoryNoAllow");
            return (Criteria) this;
        }

        public Criteria andCategoryNoAllowIn(List<Integer> values) {
            addCriterion("category_no_allow in", values, "categoryNoAllow");
            return (Criteria) this;
        }

        public Criteria andCategoryNoAllowNotIn(List<Integer> values) {
            addCriterion("category_no_allow not in", values, "categoryNoAllow");
            return (Criteria) this;
        }

        public Criteria andCategoryNoAllowBetween(Integer value1, Integer value2) {
            addCriterion("category_no_allow between", value1, value2, "categoryNoAllow");
            return (Criteria) this;
        }

        public Criteria andCategoryNoAllowNotBetween(Integer value1, Integer value2) {
            addCriterion("category_no_allow not between", value1, value2, "categoryNoAllow");
            return (Criteria) this;
        }

        public Criteria andBookSizeAllowIsNull() {
            addCriterion("book_size_allow is null");
            return (Criteria) this;
        }

        public Criteria andBookSizeAllowIsNotNull() {
            addCriterion("book_size_allow is not null");
            return (Criteria) this;
        }

        public Criteria andBookSizeAllowEqualTo(Integer value) {
            addCriterion("book_size_allow =", value, "bookSizeAllow");
            return (Criteria) this;
        }

        public Criteria andBookSizeAllowNotEqualTo(Integer value) {
            addCriterion("book_size_allow <>", value, "bookSizeAllow");
            return (Criteria) this;
        }

        public Criteria andBookSizeAllowGreaterThan(Integer value) {
            addCriterion("book_size_allow >", value, "bookSizeAllow");
            return (Criteria) this;
        }

        public Criteria andBookSizeAllowGreaterThanOrEqualTo(Integer value) {
            addCriterion("book_size_allow >=", value, "bookSizeAllow");
            return (Criteria) this;
        }

        public Criteria andBookSizeAllowLessThan(Integer value) {
            addCriterion("book_size_allow <", value, "bookSizeAllow");
            return (Criteria) this;
        }

        public Criteria andBookSizeAllowLessThanOrEqualTo(Integer value) {
            addCriterion("book_size_allow <=", value, "bookSizeAllow");
            return (Criteria) this;
        }

        public Criteria andBookSizeAllowIn(List<Integer> values) {
            addCriterion("book_size_allow in", values, "bookSizeAllow");
            return (Criteria) this;
        }

        public Criteria andBookSizeAllowNotIn(List<Integer> values) {
            addCriterion("book_size_allow not in", values, "bookSizeAllow");
            return (Criteria) this;
        }

        public Criteria andBookSizeAllowBetween(Integer value1, Integer value2) {
            addCriterion("book_size_allow between", value1, value2, "bookSizeAllow");
            return (Criteria) this;
        }

        public Criteria andBookSizeAllowNotBetween(Integer value1, Integer value2) {
            addCriterion("book_size_allow not between", value1, value2, "bookSizeAllow");
            return (Criteria) this;
        }

        public Criteria andBookPagesAllowIsNull() {
            addCriterion("book_pages_allow is null");
            return (Criteria) this;
        }

        public Criteria andBookPagesAllowIsNotNull() {
            addCriterion("book_pages_allow is not null");
            return (Criteria) this;
        }

        public Criteria andBookPagesAllowEqualTo(Integer value) {
            addCriterion("book_pages_allow =", value, "bookPagesAllow");
            return (Criteria) this;
        }

        public Criteria andBookPagesAllowNotEqualTo(Integer value) {
            addCriterion("book_pages_allow <>", value, "bookPagesAllow");
            return (Criteria) this;
        }

        public Criteria andBookPagesAllowGreaterThan(Integer value) {
            addCriterion("book_pages_allow >", value, "bookPagesAllow");
            return (Criteria) this;
        }

        public Criteria andBookPagesAllowGreaterThanOrEqualTo(Integer value) {
            addCriterion("book_pages_allow >=", value, "bookPagesAllow");
            return (Criteria) this;
        }

        public Criteria andBookPagesAllowLessThan(Integer value) {
            addCriterion("book_pages_allow <", value, "bookPagesAllow");
            return (Criteria) this;
        }

        public Criteria andBookPagesAllowLessThanOrEqualTo(Integer value) {
            addCriterion("book_pages_allow <=", value, "bookPagesAllow");
            return (Criteria) this;
        }

        public Criteria andBookPagesAllowIn(List<Integer> values) {
            addCriterion("book_pages_allow in", values, "bookPagesAllow");
            return (Criteria) this;
        }

        public Criteria andBookPagesAllowNotIn(List<Integer> values) {
            addCriterion("book_pages_allow not in", values, "bookPagesAllow");
            return (Criteria) this;
        }

        public Criteria andBookPagesAllowBetween(Integer value1, Integer value2) {
            addCriterion("book_pages_allow between", value1, value2, "bookPagesAllow");
            return (Criteria) this;
        }

        public Criteria andBookPagesAllowNotBetween(Integer value1, Integer value2) {
            addCriterion("book_pages_allow not between", value1, value2, "bookPagesAllow");
            return (Criteria) this;
        }

        public Criteria andPublisherAllowIsNull() {
            addCriterion("publisher_allow is null");
            return (Criteria) this;
        }

        public Criteria andPublisherAllowIsNotNull() {
            addCriterion("publisher_allow is not null");
            return (Criteria) this;
        }

        public Criteria andPublisherAllowEqualTo(Integer value) {
            addCriterion("publisher_allow =", value, "publisherAllow");
            return (Criteria) this;
        }

        public Criteria andPublisherAllowNotEqualTo(Integer value) {
            addCriterion("publisher_allow <>", value, "publisherAllow");
            return (Criteria) this;
        }

        public Criteria andPublisherAllowGreaterThan(Integer value) {
            addCriterion("publisher_allow >", value, "publisherAllow");
            return (Criteria) this;
        }

        public Criteria andPublisherAllowGreaterThanOrEqualTo(Integer value) {
            addCriterion("publisher_allow >=", value, "publisherAllow");
            return (Criteria) this;
        }

        public Criteria andPublisherAllowLessThan(Integer value) {
            addCriterion("publisher_allow <", value, "publisherAllow");
            return (Criteria) this;
        }

        public Criteria andPublisherAllowLessThanOrEqualTo(Integer value) {
            addCriterion("publisher_allow <=", value, "publisherAllow");
            return (Criteria) this;
        }

        public Criteria andPublisherAllowIn(List<Integer> values) {
            addCriterion("publisher_allow in", values, "publisherAllow");
            return (Criteria) this;
        }

        public Criteria andPublisherAllowNotIn(List<Integer> values) {
            addCriterion("publisher_allow not in", values, "publisherAllow");
            return (Criteria) this;
        }

        public Criteria andPublisherAllowBetween(Integer value1, Integer value2) {
            addCriterion("publisher_allow between", value1, value2, "publisherAllow");
            return (Criteria) this;
        }

        public Criteria andPublisherAllowNotBetween(Integer value1, Integer value2) {
            addCriterion("publisher_allow not between", value1, value2, "publisherAllow");
            return (Criteria) this;
        }

        public Criteria andPrefixIsNull() {
            addCriterion("prefix is null");
            return (Criteria) this;
        }

        public Criteria andPrefixIsNotNull() {
            addCriterion("prefix is not null");
            return (Criteria) this;
        }

        public Criteria andPrefixEqualTo(String value) {
            addCriterion("prefix =", value, "prefix");
            return (Criteria) this;
        }

        public Criteria andPrefixNotEqualTo(String value) {
            addCriterion("prefix <>", value, "prefix");
            return (Criteria) this;
        }

        public Criteria andPrefixGreaterThan(String value) {
            addCriterion("prefix >", value, "prefix");
            return (Criteria) this;
        }

        public Criteria andPrefixGreaterThanOrEqualTo(String value) {
            addCriterion("prefix >=", value, "prefix");
            return (Criteria) this;
        }

        public Criteria andPrefixLessThan(String value) {
            addCriterion("prefix <", value, "prefix");
            return (Criteria) this;
        }

        public Criteria andPrefixLessThanOrEqualTo(String value) {
            addCriterion("prefix <=", value, "prefix");
            return (Criteria) this;
        }

        public Criteria andPrefixLike(String value) {
            addCriterion("prefix like", value, "prefix");
            return (Criteria) this;
        }

        public Criteria andPrefixNotLike(String value) {
            addCriterion("prefix not like", value, "prefix");
            return (Criteria) this;
        }

        public Criteria andPrefixIn(List<String> values) {
            addCriterion("prefix in", values, "prefix");
            return (Criteria) this;
        }

        public Criteria andPrefixNotIn(List<String> values) {
            addCriterion("prefix not in", values, "prefix");
            return (Criteria) this;
        }

        public Criteria andPrefixBetween(String value1, String value2) {
            addCriterion("prefix between", value1, value2, "prefix");
            return (Criteria) this;
        }

        public Criteria andPrefixNotBetween(String value1, String value2) {
            addCriterion("prefix not between", value1, value2, "prefix");
            return (Criteria) this;
        }

        public Criteria andTotalnumIsNull() {
            addCriterion("totalnum is null");
            return (Criteria) this;
        }

        public Criteria andTotalnumIsNotNull() {
            addCriterion("totalnum is not null");
            return (Criteria) this;
        }

        public Criteria andTotalnumEqualTo(String value) {
            addCriterion("totalnum =", value, "totalnum");
            return (Criteria) this;
        }

        public Criteria andTotalnumNotEqualTo(String value) {
            addCriterion("totalnum <>", value, "totalnum");
            return (Criteria) this;
        }

        public Criteria andTotalnumGreaterThan(String value) {
            addCriterion("totalnum >", value, "totalnum");
            return (Criteria) this;
        }

        public Criteria andTotalnumGreaterThanOrEqualTo(String value) {
            addCriterion("totalnum >=", value, "totalnum");
            return (Criteria) this;
        }

        public Criteria andTotalnumLessThan(String value) {
            addCriterion("totalnum <", value, "totalnum");
            return (Criteria) this;
        }

        public Criteria andTotalnumLessThanOrEqualTo(String value) {
            addCriterion("totalnum <=", value, "totalnum");
            return (Criteria) this;
        }

        public Criteria andTotalnumLike(String value) {
            addCriterion("totalnum like", value, "totalnum");
            return (Criteria) this;
        }

        public Criteria andTotalnumNotLike(String value) {
            addCriterion("totalnum not like", value, "totalnum");
            return (Criteria) this;
        }

        public Criteria andTotalnumIn(List<String> values) {
            addCriterion("totalnum in", values, "totalnum");
            return (Criteria) this;
        }

        public Criteria andTotalnumNotIn(List<String> values) {
            addCriterion("totalnum not in", values, "totalnum");
            return (Criteria) this;
        }

        public Criteria andTotalnumBetween(String value1, String value2) {
            addCriterion("totalnum between", value1, value2, "totalnum");
            return (Criteria) this;
        }

        public Criteria andTotalnumNotBetween(String value1, String value2) {
            addCriterion("totalnum not between", value1, value2, "totalnum");
            return (Criteria) this;
        }

        public Criteria andStartnumIsNull() {
            addCriterion("startnum is null");
            return (Criteria) this;
        }

        public Criteria andStartnumIsNotNull() {
            addCriterion("startnum is not null");
            return (Criteria) this;
        }

        public Criteria andStartnumEqualTo(String value) {
            addCriterion("startnum =", value, "startnum");
            return (Criteria) this;
        }

        public Criteria andStartnumNotEqualTo(String value) {
            addCriterion("startnum <>", value, "startnum");
            return (Criteria) this;
        }

        public Criteria andStartnumGreaterThan(String value) {
            addCriterion("startnum >", value, "startnum");
            return (Criteria) this;
        }

        public Criteria andStartnumGreaterThanOrEqualTo(String value) {
            addCriterion("startnum >=", value, "startnum");
            return (Criteria) this;
        }

        public Criteria andStartnumLessThan(String value) {
            addCriterion("startnum <", value, "startnum");
            return (Criteria) this;
        }

        public Criteria andStartnumLessThanOrEqualTo(String value) {
            addCriterion("startnum <=", value, "startnum");
            return (Criteria) this;
        }

        public Criteria andStartnumLike(String value) {
            addCriterion("startnum like", value, "startnum");
            return (Criteria) this;
        }

        public Criteria andStartnumNotLike(String value) {
            addCriterion("startnum not like", value, "startnum");
            return (Criteria) this;
        }

        public Criteria andStartnumIn(List<String> values) {
            addCriterion("startnum in", values, "startnum");
            return (Criteria) this;
        }

        public Criteria andStartnumNotIn(List<String> values) {
            addCriterion("startnum not in", values, "startnum");
            return (Criteria) this;
        }

        public Criteria andStartnumBetween(String value1, String value2) {
            addCriterion("startnum between", value1, value2, "startnum");
            return (Criteria) this;
        }

        public Criteria andStartnumNotBetween(String value1, String value2) {
            addCriterion("startnum not between", value1, value2, "startnum");
            return (Criteria) this;
        }

        public Criteria andSummaryIsNull() {
            addCriterion("summary is null");
            return (Criteria) this;
        }

        public Criteria andSummaryIsNotNull() {
            addCriterion("summary is not null");
            return (Criteria) this;
        }

        public Criteria andSummaryEqualTo(String value) {
            addCriterion("summary =", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryNotEqualTo(String value) {
            addCriterion("summary <>", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryGreaterThan(String value) {
            addCriterion("summary >", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryGreaterThanOrEqualTo(String value) {
            addCriterion("summary >=", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryLessThan(String value) {
            addCriterion("summary <", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryLessThanOrEqualTo(String value) {
            addCriterion("summary <=", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryLike(String value) {
            addCriterion("summary like", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryNotLike(String value) {
            addCriterion("summary not like", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryIn(List<String> values) {
            addCriterion("summary in", values, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryNotIn(List<String> values) {
            addCriterion("summary not in", values, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryBetween(String value1, String value2) {
            addCriterion("summary between", value1, value2, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryNotBetween(String value1, String value2) {
            addCriterion("summary not between", value1, value2, "summary");
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