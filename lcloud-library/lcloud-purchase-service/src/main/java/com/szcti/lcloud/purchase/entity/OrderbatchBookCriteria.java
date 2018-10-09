package com.szcti.lcloud.purchase.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderbatchBookCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderbatchBookCriteria() {
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

        public Criteria andOrderBatchIdIsNull() {
            addCriterion("order_batch_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderBatchIdIsNotNull() {
            addCriterion("order_batch_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderBatchIdEqualTo(Long value) {
            addCriterion("order_batch_id =", value, "orderBatchId");
            return (Criteria) this;
        }

        public Criteria andOrderBatchIdNotEqualTo(Long value) {
            addCriterion("order_batch_id <>", value, "orderBatchId");
            return (Criteria) this;
        }

        public Criteria andOrderBatchIdGreaterThan(Long value) {
            addCriterion("order_batch_id >", value, "orderBatchId");
            return (Criteria) this;
        }

        public Criteria andOrderBatchIdGreaterThanOrEqualTo(Long value) {
            addCriterion("order_batch_id >=", value, "orderBatchId");
            return (Criteria) this;
        }

        public Criteria andOrderBatchIdLessThan(Long value) {
            addCriterion("order_batch_id <", value, "orderBatchId");
            return (Criteria) this;
        }

        public Criteria andOrderBatchIdLessThanOrEqualTo(Long value) {
            addCriterion("order_batch_id <=", value, "orderBatchId");
            return (Criteria) this;
        }

        public Criteria andOrderBatchIdIn(List<Long> values) {
            addCriterion("order_batch_id in", values, "orderBatchId");
            return (Criteria) this;
        }

        public Criteria andOrderBatchIdNotIn(List<Long> values) {
            addCriterion("order_batch_id not in", values, "orderBatchId");
            return (Criteria) this;
        }

        public Criteria andOrderBatchIdBetween(Long value1, Long value2) {
            addCriterion("order_batch_id between", value1, value2, "orderBatchId");
            return (Criteria) this;
        }

        public Criteria andOrderBatchIdNotBetween(Long value1, Long value2) {
            addCriterion("order_batch_id not between", value1, value2, "orderBatchId");
            return (Criteria) this;
        }

        public Criteria andPurchaseBookIdIsNull() {
            addCriterion("purchase_book_id is null");
            return (Criteria) this;
        }

        public Criteria andPurchaseBookIdIsNotNull() {
            addCriterion("purchase_book_id is not null");
            return (Criteria) this;
        }

        public Criteria andPurchaseBookIdEqualTo(Long value) {
            addCriterion("purchase_book_id =", value, "purchaseBookId");
            return (Criteria) this;
        }

        public Criteria andPurchaseBookIdNotEqualTo(Long value) {
            addCriterion("purchase_book_id <>", value, "purchaseBookId");
            return (Criteria) this;
        }

        public Criteria andPurchaseBookIdGreaterThan(Long value) {
            addCriterion("purchase_book_id >", value, "purchaseBookId");
            return (Criteria) this;
        }

        public Criteria andPurchaseBookIdGreaterThanOrEqualTo(Long value) {
            addCriterion("purchase_book_id >=", value, "purchaseBookId");
            return (Criteria) this;
        }

        public Criteria andPurchaseBookIdLessThan(Long value) {
            addCriterion("purchase_book_id <", value, "purchaseBookId");
            return (Criteria) this;
        }

        public Criteria andPurchaseBookIdLessThanOrEqualTo(Long value) {
            addCriterion("purchase_book_id <=", value, "purchaseBookId");
            return (Criteria) this;
        }

        public Criteria andPurchaseBookIdIn(List<Long> values) {
            addCriterion("purchase_book_id in", values, "purchaseBookId");
            return (Criteria) this;
        }

        public Criteria andPurchaseBookIdNotIn(List<Long> values) {
            addCriterion("purchase_book_id not in", values, "purchaseBookId");
            return (Criteria) this;
        }

        public Criteria andPurchaseBookIdBetween(Long value1, Long value2) {
            addCriterion("purchase_book_id between", value1, value2, "purchaseBookId");
            return (Criteria) this;
        }

        public Criteria andPurchaseBookIdNotBetween(Long value1, Long value2) {
            addCriterion("purchase_book_id not between", value1, value2, "purchaseBookId");
            return (Criteria) this;
        }

        public Criteria andBookQtyIsNull() {
            addCriterion("book_qty is null");
            return (Criteria) this;
        }

        public Criteria andBookQtyIsNotNull() {
            addCriterion("book_qty is not null");
            return (Criteria) this;
        }

        public Criteria andBookQtyEqualTo(Integer value) {
            addCriterion("book_qty =", value, "bookQty");
            return (Criteria) this;
        }

        public Criteria andBookQtyNotEqualTo(Integer value) {
            addCriterion("book_qty <>", value, "bookQty");
            return (Criteria) this;
        }

        public Criteria andBookQtyGreaterThan(Integer value) {
            addCriterion("book_qty >", value, "bookQty");
            return (Criteria) this;
        }

        public Criteria andBookQtyGreaterThanOrEqualTo(Integer value) {
            addCriterion("book_qty >=", value, "bookQty");
            return (Criteria) this;
        }

        public Criteria andBookQtyLessThan(Integer value) {
            addCriterion("book_qty <", value, "bookQty");
            return (Criteria) this;
        }

        public Criteria andBookQtyLessThanOrEqualTo(Integer value) {
            addCriterion("book_qty <=", value, "bookQty");
            return (Criteria) this;
        }

        public Criteria andBookQtyIn(List<Integer> values) {
            addCriterion("book_qty in", values, "bookQty");
            return (Criteria) this;
        }

        public Criteria andBookQtyNotIn(List<Integer> values) {
            addCriterion("book_qty not in", values, "bookQty");
            return (Criteria) this;
        }

        public Criteria andBookQtyBetween(Integer value1, Integer value2) {
            addCriterion("book_qty between", value1, value2, "bookQty");
            return (Criteria) this;
        }

        public Criteria andBookQtyNotBetween(Integer value1, Integer value2) {
            addCriterion("book_qty not between", value1, value2, "bookQty");
            return (Criteria) this;
        }

        public Criteria andCheckedQtyIsNull() {
            addCriterion("checked_qty is null");
            return (Criteria) this;
        }

        public Criteria andCheckedQtyIsNotNull() {
            addCriterion("checked_qty is not null");
            return (Criteria) this;
        }

        public Criteria andCheckedQtyEqualTo(Integer value) {
            addCriterion("checked_qty =", value, "checkedQty");
            return (Criteria) this;
        }

        public Criteria andCheckedQtyNotEqualTo(Integer value) {
            addCriterion("checked_qty <>", value, "checkedQty");
            return (Criteria) this;
        }

        public Criteria andCheckedQtyGreaterThan(Integer value) {
            addCriterion("checked_qty >", value, "checkedQty");
            return (Criteria) this;
        }

        public Criteria andCheckedQtyGreaterThanOrEqualTo(Integer value) {
            addCriterion("checked_qty >=", value, "checkedQty");
            return (Criteria) this;
        }

        public Criteria andCheckedQtyLessThan(Integer value) {
            addCriterion("checked_qty <", value, "checkedQty");
            return (Criteria) this;
        }

        public Criteria andCheckedQtyLessThanOrEqualTo(Integer value) {
            addCriterion("checked_qty <=", value, "checkedQty");
            return (Criteria) this;
        }

        public Criteria andCheckedQtyIn(List<Integer> values) {
            addCriterion("checked_qty in", values, "checkedQty");
            return (Criteria) this;
        }

        public Criteria andCheckedQtyNotIn(List<Integer> values) {
            addCriterion("checked_qty not in", values, "checkedQty");
            return (Criteria) this;
        }

        public Criteria andCheckedQtyBetween(Integer value1, Integer value2) {
            addCriterion("checked_qty between", value1, value2, "checkedQty");
            return (Criteria) this;
        }

        public Criteria andCheckedQtyNotBetween(Integer value1, Integer value2) {
            addCriterion("checked_qty not between", value1, value2, "checkedQty");
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

        public Criteria andCheckTimeIsNull() {
            addCriterion("check_time is null");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIsNotNull() {
            addCriterion("check_time is not null");
            return (Criteria) this;
        }

        public Criteria andCheckTimeEqualTo(Date value) {
            addCriterion("check_time =", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotEqualTo(Date value) {
            addCriterion("check_time <>", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeGreaterThan(Date value) {
            addCriterion("check_time >", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("check_time >=", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeLessThan(Date value) {
            addCriterion("check_time <", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeLessThanOrEqualTo(Date value) {
            addCriterion("check_time <=", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIn(List<Date> values) {
            addCriterion("check_time in", values, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotIn(List<Date> values) {
            addCriterion("check_time not in", values, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeBetween(Date value1, Date value2) {
            addCriterion("check_time between", value1, value2, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotBetween(Date value1, Date value2) {
            addCriterion("check_time not between", value1, value2, "checkTime");
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

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCheckmsgIsNull() {
            addCriterion("checkmsg is null");
            return (Criteria) this;
        }

        public Criteria andCheckmsgIsNotNull() {
            addCriterion("checkmsg is not null");
            return (Criteria) this;
        }

        public Criteria andCheckmsgEqualTo(String value) {
            addCriterion("checkmsg =", value, "checkmsg");
            return (Criteria) this;
        }

        public Criteria andCheckmsgNotEqualTo(String value) {
            addCriterion("checkmsg <>", value, "checkmsg");
            return (Criteria) this;
        }

        public Criteria andCheckmsgGreaterThan(String value) {
            addCriterion("checkmsg >", value, "checkmsg");
            return (Criteria) this;
        }

        public Criteria andCheckmsgGreaterThanOrEqualTo(String value) {
            addCriterion("checkmsg >=", value, "checkmsg");
            return (Criteria) this;
        }

        public Criteria andCheckmsgLessThan(String value) {
            addCriterion("checkmsg <", value, "checkmsg");
            return (Criteria) this;
        }

        public Criteria andCheckmsgLessThanOrEqualTo(String value) {
            addCriterion("checkmsg <=", value, "checkmsg");
            return (Criteria) this;
        }

        public Criteria andCheckmsgLike(String value) {
            addCriterion("checkmsg like", value, "checkmsg");
            return (Criteria) this;
        }

        public Criteria andCheckmsgNotLike(String value) {
            addCriterion("checkmsg not like", value, "checkmsg");
            return (Criteria) this;
        }

        public Criteria andCheckmsgIn(List<String> values) {
            addCriterion("checkmsg in", values, "checkmsg");
            return (Criteria) this;
        }

        public Criteria andCheckmsgNotIn(List<String> values) {
            addCriterion("checkmsg not in", values, "checkmsg");
            return (Criteria) this;
        }

        public Criteria andCheckmsgBetween(String value1, String value2) {
            addCriterion("checkmsg between", value1, value2, "checkmsg");
            return (Criteria) this;
        }

        public Criteria andCheckmsgNotBetween(String value1, String value2) {
            addCriterion("checkmsg not between", value1, value2, "checkmsg");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
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