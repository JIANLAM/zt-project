package com.szcti.lcloud.purchase.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderBatchCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderBatchCriteria() {
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

        public Criteria andCodingIsNull() {
            addCriterion("coding is null");
            return (Criteria) this;
        }

        public Criteria andCodingIsNotNull() {
            addCriterion("coding is not null");
            return (Criteria) this;
        }

        public Criteria andCodingEqualTo(String value) {
            addCriterion("coding =", value, "coding");
            return (Criteria) this;
        }

        public Criteria andCodingNotEqualTo(String value) {
            addCriterion("coding <>", value, "coding");
            return (Criteria) this;
        }

        public Criteria andCodingGreaterThan(String value) {
            addCriterion("coding >", value, "coding");
            return (Criteria) this;
        }

        public Criteria andCodingGreaterThanOrEqualTo(String value) {
            addCriterion("coding >=", value, "coding");
            return (Criteria) this;
        }

        public Criteria andCodingLessThan(String value) {
            addCriterion("coding <", value, "coding");
            return (Criteria) this;
        }

        public Criteria andCodingLessThanOrEqualTo(String value) {
            addCriterion("coding <=", value, "coding");
            return (Criteria) this;
        }

        public Criteria andCodingLike(String value) {
            addCriterion("coding like", value, "coding");
            return (Criteria) this;
        }

        public Criteria andCodingNotLike(String value) {
            addCriterion("coding not like", value, "coding");
            return (Criteria) this;
        }

        public Criteria andCodingIn(List<String> values) {
            addCriterion("coding in", values, "coding");
            return (Criteria) this;
        }

        public Criteria andCodingNotIn(List<String> values) {
            addCriterion("coding not in", values, "coding");
            return (Criteria) this;
        }

        public Criteria andCodingBetween(String value1, String value2) {
            addCriterion("coding between", value1, value2, "coding");
            return (Criteria) this;
        }

        public Criteria andCodingNotBetween(String value1, String value2) {
            addCriterion("coding not between", value1, value2, "coding");
            return (Criteria) this;
        }

        public Criteria andPurchaseCodeIsNull() {
            addCriterion("purchase_code is null");
            return (Criteria) this;
        }

        public Criteria andPurchaseCodeIsNotNull() {
            addCriterion("purchase_code is not null");
            return (Criteria) this;
        }

        public Criteria andPurchaseCodeEqualTo(String value) {
            addCriterion("purchase_code =", value, "purchaseCode");
            return (Criteria) this;
        }

        public Criteria andPurchaseCodeNotEqualTo(String value) {
            addCriterion("purchase_code <>", value, "purchaseCode");
            return (Criteria) this;
        }

        public Criteria andPurchaseCodeGreaterThan(String value) {
            addCriterion("purchase_code >", value, "purchaseCode");
            return (Criteria) this;
        }

        public Criteria andPurchaseCodeGreaterThanOrEqualTo(String value) {
            addCriterion("purchase_code >=", value, "purchaseCode");
            return (Criteria) this;
        }

        public Criteria andPurchaseCodeLessThan(String value) {
            addCriterion("purchase_code <", value, "purchaseCode");
            return (Criteria) this;
        }

        public Criteria andPurchaseCodeLessThanOrEqualTo(String value) {
            addCriterion("purchase_code <=", value, "purchaseCode");
            return (Criteria) this;
        }

        public Criteria andPurchaseCodeLike(String value) {
            addCriterion("purchase_code like", value, "purchaseCode");
            return (Criteria) this;
        }

        public Criteria andPurchaseCodeNotLike(String value) {
            addCriterion("purchase_code not like", value, "purchaseCode");
            return (Criteria) this;
        }

        public Criteria andPurchaseCodeIn(List<String> values) {
            addCriterion("purchase_code in", values, "purchaseCode");
            return (Criteria) this;
        }

        public Criteria andPurchaseCodeNotIn(List<String> values) {
            addCriterion("purchase_code not in", values, "purchaseCode");
            return (Criteria) this;
        }

        public Criteria andPurchaseCodeBetween(String value1, String value2) {
            addCriterion("purchase_code between", value1, value2, "purchaseCode");
            return (Criteria) this;
        }

        public Criteria andPurchaseCodeNotBetween(String value1, String value2) {
            addCriterion("purchase_code not between", value1, value2, "purchaseCode");
            return (Criteria) this;
        }

        public Criteria andExpressCodeIsNull() {
            addCriterion("express_code is null");
            return (Criteria) this;
        }

        public Criteria andExpressCodeIsNotNull() {
            addCriterion("express_code is not null");
            return (Criteria) this;
        }

        public Criteria andExpressCodeEqualTo(String value) {
            addCriterion("express_code =", value, "expressCode");
            return (Criteria) this;
        }

        public Criteria andExpressCodeNotEqualTo(String value) {
            addCriterion("express_code <>", value, "expressCode");
            return (Criteria) this;
        }

        public Criteria andExpressCodeGreaterThan(String value) {
            addCriterion("express_code >", value, "expressCode");
            return (Criteria) this;
        }

        public Criteria andExpressCodeGreaterThanOrEqualTo(String value) {
            addCriterion("express_code >=", value, "expressCode");
            return (Criteria) this;
        }

        public Criteria andExpressCodeLessThan(String value) {
            addCriterion("express_code <", value, "expressCode");
            return (Criteria) this;
        }

        public Criteria andExpressCodeLessThanOrEqualTo(String value) {
            addCriterion("express_code <=", value, "expressCode");
            return (Criteria) this;
        }

        public Criteria andExpressCodeLike(String value) {
            addCriterion("express_code like", value, "expressCode");
            return (Criteria) this;
        }

        public Criteria andExpressCodeNotLike(String value) {
            addCriterion("express_code not like", value, "expressCode");
            return (Criteria) this;
        }

        public Criteria andExpressCodeIn(List<String> values) {
            addCriterion("express_code in", values, "expressCode");
            return (Criteria) this;
        }

        public Criteria andExpressCodeNotIn(List<String> values) {
            addCriterion("express_code not in", values, "expressCode");
            return (Criteria) this;
        }

        public Criteria andExpressCodeBetween(String value1, String value2) {
            addCriterion("express_code between", value1, value2, "expressCode");
            return (Criteria) this;
        }

        public Criteria andExpressCodeNotBetween(String value1, String value2) {
            addCriterion("express_code not between", value1, value2, "expressCode");
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

        public Criteria andBookTypeQtyIsNull() {
            addCriterion("book_type_qty is null");
            return (Criteria) this;
        }

        public Criteria andBookTypeQtyIsNotNull() {
            addCriterion("book_type_qty is not null");
            return (Criteria) this;
        }

        public Criteria andBookTypeQtyEqualTo(Integer value) {
            addCriterion("book_type_qty =", value, "bookTypeQty");
            return (Criteria) this;
        }

        public Criteria andBookTypeQtyNotEqualTo(Integer value) {
            addCriterion("book_type_qty <>", value, "bookTypeQty");
            return (Criteria) this;
        }

        public Criteria andBookTypeQtyGreaterThan(Integer value) {
            addCriterion("book_type_qty >", value, "bookTypeQty");
            return (Criteria) this;
        }

        public Criteria andBookTypeQtyGreaterThanOrEqualTo(Integer value) {
            addCriterion("book_type_qty >=", value, "bookTypeQty");
            return (Criteria) this;
        }

        public Criteria andBookTypeQtyLessThan(Integer value) {
            addCriterion("book_type_qty <", value, "bookTypeQty");
            return (Criteria) this;
        }

        public Criteria andBookTypeQtyLessThanOrEqualTo(Integer value) {
            addCriterion("book_type_qty <=", value, "bookTypeQty");
            return (Criteria) this;
        }

        public Criteria andBookTypeQtyIn(List<Integer> values) {
            addCriterion("book_type_qty in", values, "bookTypeQty");
            return (Criteria) this;
        }

        public Criteria andBookTypeQtyNotIn(List<Integer> values) {
            addCriterion("book_type_qty not in", values, "bookTypeQty");
            return (Criteria) this;
        }

        public Criteria andBookTypeQtyBetween(Integer value1, Integer value2) {
            addCriterion("book_type_qty between", value1, value2, "bookTypeQty");
            return (Criteria) this;
        }

        public Criteria andBookTypeQtyNotBetween(Integer value1, Integer value2) {
            addCriterion("book_type_qty not between", value1, value2, "bookTypeQty");
            return (Criteria) this;
        }

        public Criteria andTotalQuantityIsNull() {
            addCriterion("total_quantity is null");
            return (Criteria) this;
        }

        public Criteria andTotalQuantityIsNotNull() {
            addCriterion("total_quantity is not null");
            return (Criteria) this;
        }

        public Criteria andTotalQuantityEqualTo(Integer value) {
            addCriterion("total_quantity =", value, "totalQuantity");
            return (Criteria) this;
        }

        public Criteria andTotalQuantityNotEqualTo(Integer value) {
            addCriterion("total_quantity <>", value, "totalQuantity");
            return (Criteria) this;
        }

        public Criteria andTotalQuantityGreaterThan(Integer value) {
            addCriterion("total_quantity >", value, "totalQuantity");
            return (Criteria) this;
        }

        public Criteria andTotalQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_quantity >=", value, "totalQuantity");
            return (Criteria) this;
        }

        public Criteria andTotalQuantityLessThan(Integer value) {
            addCriterion("total_quantity <", value, "totalQuantity");
            return (Criteria) this;
        }

        public Criteria andTotalQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("total_quantity <=", value, "totalQuantity");
            return (Criteria) this;
        }

        public Criteria andTotalQuantityIn(List<Integer> values) {
            addCriterion("total_quantity in", values, "totalQuantity");
            return (Criteria) this;
        }

        public Criteria andTotalQuantityNotIn(List<Integer> values) {
            addCriterion("total_quantity not in", values, "totalQuantity");
            return (Criteria) this;
        }

        public Criteria andTotalQuantityBetween(Integer value1, Integer value2) {
            addCriterion("total_quantity between", value1, value2, "totalQuantity");
            return (Criteria) this;
        }

        public Criteria andTotalQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("total_quantity not between", value1, value2, "totalQuantity");
            return (Criteria) this;
        }

        public Criteria andSupplierIdIsNull() {
            addCriterion("supplier_id is null");
            return (Criteria) this;
        }

        public Criteria andSupplierIdIsNotNull() {
            addCriterion("supplier_id is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierIdEqualTo(Long value) {
            addCriterion("supplier_id =", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdNotEqualTo(Long value) {
            addCriterion("supplier_id <>", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdGreaterThan(Long value) {
            addCriterion("supplier_id >", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdGreaterThanOrEqualTo(Long value) {
            addCriterion("supplier_id >=", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdLessThan(Long value) {
            addCriterion("supplier_id <", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdLessThanOrEqualTo(Long value) {
            addCriterion("supplier_id <=", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdIn(List<Long> values) {
            addCriterion("supplier_id in", values, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdNotIn(List<Long> values) {
            addCriterion("supplier_id not in", values, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdBetween(Long value1, Long value2) {
            addCriterion("supplier_id between", value1, value2, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdNotBetween(Long value1, Long value2) {
            addCriterion("supplier_id not between", value1, value2, "supplierId");
            return (Criteria) this;
        }

        public Criteria andButgetIdIsNull() {
            addCriterion("butget_id is null");
            return (Criteria) this;
        }

        public Criteria andButgetIdIsNotNull() {
            addCriterion("butget_id is not null");
            return (Criteria) this;
        }

        public Criteria andButgetIdEqualTo(Long value) {
            addCriterion("butget_id =", value, "butgetId");
            return (Criteria) this;
        }

        public Criteria andButgetIdNotEqualTo(Long value) {
            addCriterion("butget_id <>", value, "butgetId");
            return (Criteria) this;
        }

        public Criteria andButgetIdGreaterThan(Long value) {
            addCriterion("butget_id >", value, "butgetId");
            return (Criteria) this;
        }

        public Criteria andButgetIdGreaterThanOrEqualTo(Long value) {
            addCriterion("butget_id >=", value, "butgetId");
            return (Criteria) this;
        }

        public Criteria andButgetIdLessThan(Long value) {
            addCriterion("butget_id <", value, "butgetId");
            return (Criteria) this;
        }

        public Criteria andButgetIdLessThanOrEqualTo(Long value) {
            addCriterion("butget_id <=", value, "butgetId");
            return (Criteria) this;
        }

        public Criteria andButgetIdIn(List<Long> values) {
            addCriterion("butget_id in", values, "butgetId");
            return (Criteria) this;
        }

        public Criteria andButgetIdNotIn(List<Long> values) {
            addCriterion("butget_id not in", values, "butgetId");
            return (Criteria) this;
        }

        public Criteria andButgetIdBetween(Long value1, Long value2) {
            addCriterion("butget_id between", value1, value2, "butgetId");
            return (Criteria) this;
        }

        public Criteria andButgetIdNotBetween(Long value1, Long value2) {
            addCriterion("butget_id not between", value1, value2, "butgetId");
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

        public Criteria andAcceptStatusIsNull() {
            addCriterion("accept_status is null");
            return (Criteria) this;
        }

        public Criteria andAcceptStatusIsNotNull() {
            addCriterion("accept_status is not null");
            return (Criteria) this;
        }

        public Criteria andAcceptStatusEqualTo(String value) {
            addCriterion("accept_status =", value, "acceptStatus");
            return (Criteria) this;
        }

        public Criteria andAcceptStatusNotEqualTo(String value) {
            addCriterion("accept_status <>", value, "acceptStatus");
            return (Criteria) this;
        }

        public Criteria andAcceptStatusGreaterThan(String value) {
            addCriterion("accept_status >", value, "acceptStatus");
            return (Criteria) this;
        }

        public Criteria andAcceptStatusGreaterThanOrEqualTo(String value) {
            addCriterion("accept_status >=", value, "acceptStatus");
            return (Criteria) this;
        }

        public Criteria andAcceptStatusLessThan(String value) {
            addCriterion("accept_status <", value, "acceptStatus");
            return (Criteria) this;
        }

        public Criteria andAcceptStatusLessThanOrEqualTo(String value) {
            addCriterion("accept_status <=", value, "acceptStatus");
            return (Criteria) this;
        }

        public Criteria andAcceptStatusLike(String value) {
            addCriterion("accept_status like", value, "acceptStatus");
            return (Criteria) this;
        }

        public Criteria andAcceptStatusNotLike(String value) {
            addCriterion("accept_status not like", value, "acceptStatus");
            return (Criteria) this;
        }

        public Criteria andAcceptStatusIn(List<String> values) {
            addCriterion("accept_status in", values, "acceptStatus");
            return (Criteria) this;
        }

        public Criteria andAcceptStatusNotIn(List<String> values) {
            addCriterion("accept_status not in", values, "acceptStatus");
            return (Criteria) this;
        }

        public Criteria andAcceptStatusBetween(String value1, String value2) {
            addCriterion("accept_status between", value1, value2, "acceptStatus");
            return (Criteria) this;
        }

        public Criteria andAcceptStatusNotBetween(String value1, String value2) {
            addCriterion("accept_status not between", value1, value2, "acceptStatus");
            return (Criteria) this;
        }

        public Criteria andAcceptCodeIsNull() {
            addCriterion("accept_code is null");
            return (Criteria) this;
        }

        public Criteria andAcceptCodeIsNotNull() {
            addCriterion("accept_code is not null");
            return (Criteria) this;
        }

        public Criteria andAcceptCodeEqualTo(String value) {
            addCriterion("accept_code =", value, "acceptCode");
            return (Criteria) this;
        }

        public Criteria andAcceptCodeNotEqualTo(String value) {
            addCriterion("accept_code <>", value, "acceptCode");
            return (Criteria) this;
        }

        public Criteria andAcceptCodeGreaterThan(String value) {
            addCriterion("accept_code >", value, "acceptCode");
            return (Criteria) this;
        }

        public Criteria andAcceptCodeGreaterThanOrEqualTo(String value) {
            addCriterion("accept_code >=", value, "acceptCode");
            return (Criteria) this;
        }

        public Criteria andAcceptCodeLessThan(String value) {
            addCriterion("accept_code <", value, "acceptCode");
            return (Criteria) this;
        }

        public Criteria andAcceptCodeLessThanOrEqualTo(String value) {
            addCriterion("accept_code <=", value, "acceptCode");
            return (Criteria) this;
        }

        public Criteria andAcceptCodeLike(String value) {
            addCriterion("accept_code like", value, "acceptCode");
            return (Criteria) this;
        }

        public Criteria andAcceptCodeNotLike(String value) {
            addCriterion("accept_code not like", value, "acceptCode");
            return (Criteria) this;
        }

        public Criteria andAcceptCodeIn(List<String> values) {
            addCriterion("accept_code in", values, "acceptCode");
            return (Criteria) this;
        }

        public Criteria andAcceptCodeNotIn(List<String> values) {
            addCriterion("accept_code not in", values, "acceptCode");
            return (Criteria) this;
        }

        public Criteria andAcceptCodeBetween(String value1, String value2) {
            addCriterion("accept_code between", value1, value2, "acceptCode");
            return (Criteria) this;
        }

        public Criteria andAcceptCodeNotBetween(String value1, String value2) {
            addCriterion("accept_code not between", value1, value2, "acceptCode");
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