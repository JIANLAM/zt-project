package com.szcti.lcloud.purchase.entity;

import java.util.ArrayList;
import java.util.List;

public class AcceptanceDetailCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AcceptanceDetailCriteria() {
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

        public Criteria andAcceptanceIdIsNull() {
            addCriterion("acceptance_id is null");
            return (Criteria) this;
        }

        public Criteria andAcceptanceIdIsNotNull() {
            addCriterion("acceptance_id is not null");
            return (Criteria) this;
        }

        public Criteria andAcceptanceIdEqualTo(Long value) {
            addCriterion("acceptance_id =", value, "acceptanceId");
            return (Criteria) this;
        }

        public Criteria andAcceptanceIdNotEqualTo(Long value) {
            addCriterion("acceptance_id <>", value, "acceptanceId");
            return (Criteria) this;
        }

        public Criteria andAcceptanceIdGreaterThan(Long value) {
            addCriterion("acceptance_id >", value, "acceptanceId");
            return (Criteria) this;
        }

        public Criteria andAcceptanceIdGreaterThanOrEqualTo(Long value) {
            addCriterion("acceptance_id >=", value, "acceptanceId");
            return (Criteria) this;
        }

        public Criteria andAcceptanceIdLessThan(Long value) {
            addCriterion("acceptance_id <", value, "acceptanceId");
            return (Criteria) this;
        }

        public Criteria andAcceptanceIdLessThanOrEqualTo(Long value) {
            addCriterion("acceptance_id <=", value, "acceptanceId");
            return (Criteria) this;
        }

        public Criteria andAcceptanceIdIn(List<Long> values) {
            addCriterion("acceptance_id in", values, "acceptanceId");
            return (Criteria) this;
        }

        public Criteria andAcceptanceIdNotIn(List<Long> values) {
            addCriterion("acceptance_id not in", values, "acceptanceId");
            return (Criteria) this;
        }

        public Criteria andAcceptanceIdBetween(Long value1, Long value2) {
            addCriterion("acceptance_id between", value1, value2, "acceptanceId");
            return (Criteria) this;
        }

        public Criteria andAcceptanceIdNotBetween(Long value1, Long value2) {
            addCriterion("acceptance_id not between", value1, value2, "acceptanceId");
            return (Criteria) this;
        }

        public Criteria andOrderbatchBookIdIsNull() {
            addCriterion("orderbatch_book_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderbatchBookIdIsNotNull() {
            addCriterion("orderbatch_book_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderbatchBookIdEqualTo(Long value) {
            addCriterion("orderbatch_book_id =", value, "orderbatchBookId");
            return (Criteria) this;
        }

        public Criteria andOrderbatchBookIdNotEqualTo(Long value) {
            addCriterion("orderbatch_book_id <>", value, "orderbatchBookId");
            return (Criteria) this;
        }

        public Criteria andOrderbatchBookIdGreaterThan(Long value) {
            addCriterion("orderbatch_book_id >", value, "orderbatchBookId");
            return (Criteria) this;
        }

        public Criteria andOrderbatchBookIdGreaterThanOrEqualTo(Long value) {
            addCriterion("orderbatch_book_id >=", value, "orderbatchBookId");
            return (Criteria) this;
        }

        public Criteria andOrderbatchBookIdLessThan(Long value) {
            addCriterion("orderbatch_book_id <", value, "orderbatchBookId");
            return (Criteria) this;
        }

        public Criteria andOrderbatchBookIdLessThanOrEqualTo(Long value) {
            addCriterion("orderbatch_book_id <=", value, "orderbatchBookId");
            return (Criteria) this;
        }

        public Criteria andOrderbatchBookIdIn(List<Long> values) {
            addCriterion("orderbatch_book_id in", values, "orderbatchBookId");
            return (Criteria) this;
        }

        public Criteria andOrderbatchBookIdNotIn(List<Long> values) {
            addCriterion("orderbatch_book_id not in", values, "orderbatchBookId");
            return (Criteria) this;
        }

        public Criteria andOrderbatchBookIdBetween(Long value1, Long value2) {
            addCriterion("orderbatch_book_id between", value1, value2, "orderbatchBookId");
            return (Criteria) this;
        }

        public Criteria andOrderbatchBookIdNotBetween(Long value1, Long value2) {
            addCriterion("orderbatch_book_id not between", value1, value2, "orderbatchBookId");
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

        public Criteria andIsbnIsNull() {
            addCriterion("isbn is null");
            return (Criteria) this;
        }

        public Criteria andIsbnIsNotNull() {
            addCriterion("isbn is not null");
            return (Criteria) this;
        }

        public Criteria andIsbnEqualTo(String value) {
            addCriterion("isbn =", value, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnNotEqualTo(String value) {
            addCriterion("isbn <>", value, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnGreaterThan(String value) {
            addCriterion("isbn >", value, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnGreaterThanOrEqualTo(String value) {
            addCriterion("isbn >=", value, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnLessThan(String value) {
            addCriterion("isbn <", value, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnLessThanOrEqualTo(String value) {
            addCriterion("isbn <=", value, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnLike(String value) {
            addCriterion("isbn like", value, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnNotLike(String value) {
            addCriterion("isbn not like", value, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnIn(List<String> values) {
            addCriterion("isbn in", values, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnNotIn(List<String> values) {
            addCriterion("isbn not in", values, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnBetween(String value1, String value2) {
            addCriterion("isbn between", value1, value2, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnNotBetween(String value1, String value2) {
            addCriterion("isbn not between", value1, value2, "isbn");
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

        public Criteria andShipQuantityIsNull() {
            addCriterion("ship_quantity is null");
            return (Criteria) this;
        }

        public Criteria andShipQuantityIsNotNull() {
            addCriterion("ship_quantity is not null");
            return (Criteria) this;
        }

        public Criteria andShipQuantityEqualTo(Integer value) {
            addCriterion("ship_quantity =", value, "shipQuantity");
            return (Criteria) this;
        }

        public Criteria andShipQuantityNotEqualTo(Integer value) {
            addCriterion("ship_quantity <>", value, "shipQuantity");
            return (Criteria) this;
        }

        public Criteria andShipQuantityGreaterThan(Integer value) {
            addCriterion("ship_quantity >", value, "shipQuantity");
            return (Criteria) this;
        }

        public Criteria andShipQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("ship_quantity >=", value, "shipQuantity");
            return (Criteria) this;
        }

        public Criteria andShipQuantityLessThan(Integer value) {
            addCriterion("ship_quantity <", value, "shipQuantity");
            return (Criteria) this;
        }

        public Criteria andShipQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("ship_quantity <=", value, "shipQuantity");
            return (Criteria) this;
        }

        public Criteria andShipQuantityIn(List<Integer> values) {
            addCriterion("ship_quantity in", values, "shipQuantity");
            return (Criteria) this;
        }

        public Criteria andShipQuantityNotIn(List<Integer> values) {
            addCriterion("ship_quantity not in", values, "shipQuantity");
            return (Criteria) this;
        }

        public Criteria andShipQuantityBetween(Integer value1, Integer value2) {
            addCriterion("ship_quantity between", value1, value2, "shipQuantity");
            return (Criteria) this;
        }

        public Criteria andShipQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("ship_quantity not between", value1, value2, "shipQuantity");
            return (Criteria) this;
        }

        public Criteria andAcceptQuantityIsNull() {
            addCriterion("accept_quantity is null");
            return (Criteria) this;
        }

        public Criteria andAcceptQuantityIsNotNull() {
            addCriterion("accept_quantity is not null");
            return (Criteria) this;
        }

        public Criteria andAcceptQuantityEqualTo(Integer value) {
            addCriterion("accept_quantity =", value, "acceptQuantity");
            return (Criteria) this;
        }

        public Criteria andAcceptQuantityNotEqualTo(Integer value) {
            addCriterion("accept_quantity <>", value, "acceptQuantity");
            return (Criteria) this;
        }

        public Criteria andAcceptQuantityGreaterThan(Integer value) {
            addCriterion("accept_quantity >", value, "acceptQuantity");
            return (Criteria) this;
        }

        public Criteria andAcceptQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("accept_quantity >=", value, "acceptQuantity");
            return (Criteria) this;
        }

        public Criteria andAcceptQuantityLessThan(Integer value) {
            addCriterion("accept_quantity <", value, "acceptQuantity");
            return (Criteria) this;
        }

        public Criteria andAcceptQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("accept_quantity <=", value, "acceptQuantity");
            return (Criteria) this;
        }

        public Criteria andAcceptQuantityIn(List<Integer> values) {
            addCriterion("accept_quantity in", values, "acceptQuantity");
            return (Criteria) this;
        }

        public Criteria andAcceptQuantityNotIn(List<Integer> values) {
            addCriterion("accept_quantity not in", values, "acceptQuantity");
            return (Criteria) this;
        }

        public Criteria andAcceptQuantityBetween(Integer value1, Integer value2) {
            addCriterion("accept_quantity between", value1, value2, "acceptQuantity");
            return (Criteria) this;
        }

        public Criteria andAcceptQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("accept_quantity not between", value1, value2, "acceptQuantity");
            return (Criteria) this;
        }

        public Criteria andcatalogQtyIsNull() {
            addCriterion("catalog_qty is null");
            return (Criteria) this;
        }

        public Criteria andcatalogQtyIsNotNull() {
            addCriterion("catalog_qty is not null");
            return (Criteria) this;
        }

        public Criteria andcatalogQtyEqualTo(Integer value) {
            addCriterion("catalog_qty =", value, "catalogQty");
            return (Criteria) this;
        }

        public Criteria andcatalogQtyNotEqualTo(Integer value) {
            addCriterion("catalog_qty <>", value, "catalogQty");
            return (Criteria) this;
        }

        public Criteria andcatalogQtyGreaterThan(Integer value) {
            addCriterion("catalog_qty >", value, "catalogQty");
            return (Criteria) this;
        }

        public Criteria andcatalogQtyGreaterThanOrEqualTo(Integer value) {
            addCriterion("catalog_qty >=", value, "catalogQty");
            return (Criteria) this;
        }

        public Criteria andcatalogQtyLessThan(Integer value) {
            addCriterion("catalog_qty <", value, "catalogQty");
            return (Criteria) this;
        }

        public Criteria andcatalogQtyLessThanOrEqualTo(Integer value) {
            addCriterion("catalog_qty <=", value, "catalogQty");
            return (Criteria) this;
        }

        public Criteria andcatalogQtyIn(List<Integer> values) {
            addCriterion("catalog_qty in", values, "catalogQty");
            return (Criteria) this;
        }

        public Criteria andcatalogQtyNotIn(List<Integer> values) {
            addCriterion("catalog_qty not in", values, "catalogQty");
            return (Criteria) this;
        }

        public Criteria andcatalogQtyBetween(Integer value1, Integer value2) {
            addCriterion("catalog_qty between", value1, value2, "catalogQty");
            return (Criteria) this;
        }

        public Criteria andcatalogQtyNotBetween(Integer value1, Integer value2) {
            addCriterion("catalog_qty not between", value1, value2, "catalogQty");
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