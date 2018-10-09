package com.szcti.lcloud.catalog.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HoldingCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public HoldingCriteria() {
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

        public Criteria andCallNoIsNull() {
            addCriterion("call_no is null");
            return (Criteria) this;
        }

        public Criteria andCallNoIsNotNull() {
            addCriterion("call_no is not null");
            return (Criteria) this;
        }

        public Criteria andCallNoEqualTo(String value) {
            addCriterion("call_no =", value, "callNo");
            return (Criteria) this;
        }

        public Criteria andCallNoNotEqualTo(String value) {
            addCriterion("call_no <>", value, "callNo");
            return (Criteria) this;
        }

        public Criteria andCallNoGreaterThan(String value) {
            addCriterion("call_no >", value, "callNo");
            return (Criteria) this;
        }

        public Criteria andCallNoGreaterThanOrEqualTo(String value) {
            addCriterion("call_no >=", value, "callNo");
            return (Criteria) this;
        }

        public Criteria andCallNoLessThan(String value) {
            addCriterion("call_no <", value, "callNo");
            return (Criteria) this;
        }

        public Criteria andCallNoLessThanOrEqualTo(String value) {
            addCriterion("call_no <=", value, "callNo");
            return (Criteria) this;
        }

        public Criteria andCallNoLike(String value) {
            addCriterion("call_no like", value, "callNo");
            return (Criteria) this;
        }

        public Criteria andCallNoNotLike(String value) {
            addCriterion("call_no not like", value, "callNo");
            return (Criteria) this;
        }

        public Criteria andCallNoIn(List<String> values) {
            addCriterion("call_no in", values, "callNo");
            return (Criteria) this;
        }

        public Criteria andCallNoNotIn(List<String> values) {
            addCriterion("call_no not in", values, "callNo");
            return (Criteria) this;
        }

        public Criteria andCallNoBetween(String value1, String value2) {
            addCriterion("call_no between", value1, value2, "callNo");
            return (Criteria) this;
        }

        public Criteria andCallNoNotBetween(String value1, String value2) {
            addCriterion("call_no not between", value1, value2, "callNo");
            return (Criteria) this;
        }

        public Criteria andBookIdIsNull() {
            addCriterion("book_id is null");
            return (Criteria) this;
        }

        public Criteria andBookIdIsNotNull() {
            addCriterion("book_id is not null");
            return (Criteria) this;
        }

        public Criteria andBookIdEqualTo(Long value) {
            addCriterion("book_id =", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdNotEqualTo(Long value) {
            addCriterion("book_id <>", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdGreaterThan(Long value) {
            addCriterion("book_id >", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdGreaterThanOrEqualTo(Long value) {
            addCriterion("book_id >=", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdLessThan(Long value) {
            addCriterion("book_id <", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdLessThanOrEqualTo(Long value) {
            addCriterion("book_id <=", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdIn(List<Long> values) {
            addCriterion("book_id in", values, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdNotIn(List<Long> values) {
            addCriterion("book_id not in", values, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdBetween(Long value1, Long value2) {
            addCriterion("book_id between", value1, value2, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdNotBetween(Long value1, Long value2) {
            addCriterion("book_id not between", value1, value2, "bookId");
            return (Criteria) this;
        }

        public Criteria andBarcodeIsNull() {
            addCriterion("barcode is null");
            return (Criteria) this;
        }

        public Criteria andBarcodeIsNotNull() {
            addCriterion("barcode is not null");
            return (Criteria) this;
        }

        public Criteria andBarcodeEqualTo(String value) {
            addCriterion("barcode =", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeNotEqualTo(String value) {
            addCriterion("barcode <>", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeGreaterThan(String value) {
            addCriterion("barcode >", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeGreaterThanOrEqualTo(String value) {
            addCriterion("barcode >=", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeLessThan(String value) {
            addCriterion("barcode <", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeLessThanOrEqualTo(String value) {
            addCriterion("barcode <=", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeLike(String value) {
            addCriterion("barcode like", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeNotLike(String value) {
            addCriterion("barcode not like", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeIn(List<String> values) {
            addCriterion("barcode in", values, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeNotIn(List<String> values) {
            addCriterion("barcode not in", values, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeBetween(String value1, String value2) {
            addCriterion("barcode between", value1, value2, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeNotBetween(String value1, String value2) {
            addCriterion("barcode not between", value1, value2, "barcode");
            return (Criteria) this;
        }

        public Criteria andOwnlibIsNull() {
            addCriterion("ownlib is null");
            return (Criteria) this;
        }

        public Criteria andOwnlibIsNotNull() {
            addCriterion("ownlib is not null");
            return (Criteria) this;
        }

        public Criteria andOwnlibEqualTo(Long value) {
            addCriterion("ownlib =", value, "ownlib");
            return (Criteria) this;
        }

        public Criteria andOwnlibNotEqualTo(Long value) {
            addCriterion("ownlib <>", value, "ownlib");
            return (Criteria) this;
        }

        public Criteria andOwnlibGreaterThan(Long value) {
            addCriterion("ownlib >", value, "ownlib");
            return (Criteria) this;
        }

        public Criteria andOwnlibGreaterThanOrEqualTo(Long value) {
            addCriterion("ownlib >=", value, "ownlib");
            return (Criteria) this;
        }

        public Criteria andOwnlibLessThan(Long value) {
            addCriterion("ownlib <", value, "ownlib");
            return (Criteria) this;
        }

        public Criteria andOwnlibLessThanOrEqualTo(Long value) {
            addCriterion("ownlib <=", value, "ownlib");
            return (Criteria) this;
        }

        public Criteria andOwnlibIn(List<Long> values) {
            addCriterion("ownlib in", values, "ownlib");
            return (Criteria) this;
        }

        public Criteria andOwnlibNotIn(List<Long> values) {
            addCriterion("ownlib not in", values, "ownlib");
            return (Criteria) this;
        }

        public Criteria andOwnlibBetween(Long value1, Long value2) {
            addCriterion("ownlib between", value1, value2, "ownlib");
            return (Criteria) this;
        }

        public Criteria andOwnlibNotBetween(Long value1, Long value2) {
            addCriterion("ownlib not between", value1, value2, "ownlib");
            return (Criteria) this;
        }

        public Criteria andCurlibIsNull() {
            addCriterion("curlib is null");
            return (Criteria) this;
        }

        public Criteria andCurlibIsNotNull() {
            addCriterion("curlib is not null");
            return (Criteria) this;
        }

        public Criteria andCurlibEqualTo(Long value) {
            addCriterion("curlib =", value, "curlib");
            return (Criteria) this;
        }

        public Criteria andCurlibNotEqualTo(Long value) {
            addCriterion("curlib <>", value, "curlib");
            return (Criteria) this;
        }

        public Criteria andCurlibGreaterThan(Long value) {
            addCriterion("curlib >", value, "curlib");
            return (Criteria) this;
        }

        public Criteria andCurlibGreaterThanOrEqualTo(Long value) {
            addCriterion("curlib >=", value, "curlib");
            return (Criteria) this;
        }

        public Criteria andCurlibLessThan(Long value) {
            addCriterion("curlib <", value, "curlib");
            return (Criteria) this;
        }

        public Criteria andCurlibLessThanOrEqualTo(Long value) {
            addCriterion("curlib <=", value, "curlib");
            return (Criteria) this;
        }

        public Criteria andCurlibIn(List<Long> values) {
            addCriterion("curlib in", values, "curlib");
            return (Criteria) this;
        }

        public Criteria andCurlibNotIn(List<Long> values) {
            addCriterion("curlib not in", values, "curlib");
            return (Criteria) this;
        }

        public Criteria andCurlibBetween(Long value1, Long value2) {
            addCriterion("curlib between", value1, value2, "curlib");
            return (Criteria) this;
        }

        public Criteria andCurlibNotBetween(Long value1, Long value2) {
            addCriterion("curlib not between", value1, value2, "curlib");
            return (Criteria) this;
        }

        public Criteria andShelfIsNull() {
            addCriterion("shelf is null");
            return (Criteria) this;
        }

        public Criteria andShelfIsNotNull() {
            addCriterion("shelf is not null");
            return (Criteria) this;
        }

        public Criteria andShelfEqualTo(Long value) {
            addCriterion("shelf =", value, "shelf");
            return (Criteria) this;
        }

        public Criteria andShelfNotEqualTo(Long value) {
            addCriterion("shelf <>", value, "shelf");
            return (Criteria) this;
        }

        public Criteria andShelfGreaterThan(Long value) {
            addCriterion("shelf >", value, "shelf");
            return (Criteria) this;
        }

        public Criteria andShelfGreaterThanOrEqualTo(Long value) {
            addCriterion("shelf >=", value, "shelf");
            return (Criteria) this;
        }

        public Criteria andShelfLessThan(Long value) {
            addCriterion("shelf <", value, "shelf");
            return (Criteria) this;
        }

        public Criteria andShelfLessThanOrEqualTo(Long value) {
            addCriterion("shelf <=", value, "shelf");
            return (Criteria) this;
        }

        public Criteria andShelfIn(List<Long> values) {
            addCriterion("shelf in", values, "shelf");
            return (Criteria) this;
        }

        public Criteria andShelfNotIn(List<Long> values) {
            addCriterion("shelf not in", values, "shelf");
            return (Criteria) this;
        }

        public Criteria andShelfBetween(Long value1, Long value2) {
            addCriterion("shelf between", value1, value2, "shelf");
            return (Criteria) this;
        }

        public Criteria andShelfNotBetween(Long value1, Long value2) {
            addCriterion("shelf not between", value1, value2, "shelf");
            return (Criteria) this;
        }

        public Criteria andIndateIsNull() {
            addCriterion("indate is null");
            return (Criteria) this;
        }

        public Criteria andIndateIsNotNull() {
            addCriterion("indate is not null");
            return (Criteria) this;
        }

        public Criteria andIndateEqualTo(Date value) {
            addCriterion("indate =", value, "indate");
            return (Criteria) this;
        }

        public Criteria andIndateNotEqualTo(Date value) {
            addCriterion("indate <>", value, "indate");
            return (Criteria) this;
        }

        public Criteria andIndateGreaterThan(Date value) {
            addCriterion("indate >", value, "indate");
            return (Criteria) this;
        }

        public Criteria andIndateGreaterThanOrEqualTo(Date value) {
            addCriterion("indate >=", value, "indate");
            return (Criteria) this;
        }

        public Criteria andIndateLessThan(Date value) {
            addCriterion("indate <", value, "indate");
            return (Criteria) this;
        }

        public Criteria andIndateLessThanOrEqualTo(Date value) {
            addCriterion("indate <=", value, "indate");
            return (Criteria) this;
        }

        public Criteria andIndateIn(List<Date> values) {
            addCriterion("indate in", values, "indate");
            return (Criteria) this;
        }

        public Criteria andIndateNotIn(List<Date> values) {
            addCriterion("indate not in", values, "indate");
            return (Criteria) this;
        }

        public Criteria andIndateBetween(Date value1, Date value2) {
            addCriterion("indate between", value1, value2, "indate");
            return (Criteria) this;
        }

        public Criteria andIndateNotBetween(Date value1, Date value2) {
            addCriterion("indate not between", value1, value2, "indate");
            return (Criteria) this;
        }

        public Criteria andSinglepriceIsNull() {
            addCriterion("singleprice is null");
            return (Criteria) this;
        }

        public Criteria andSinglepriceIsNotNull() {
            addCriterion("singleprice is not null");
            return (Criteria) this;
        }

        public Criteria andSinglepriceEqualTo(Float value) {
            addCriterion("singleprice =", value, "singleprice");
            return (Criteria) this;
        }

        public Criteria andSinglepriceNotEqualTo(Float value) {
            addCriterion("singleprice <>", value, "singleprice");
            return (Criteria) this;
        }

        public Criteria andSinglepriceGreaterThan(Float value) {
            addCriterion("singleprice >", value, "singleprice");
            return (Criteria) this;
        }

        public Criteria andSinglepriceGreaterThanOrEqualTo(Float value) {
            addCriterion("singleprice >=", value, "singleprice");
            return (Criteria) this;
        }

        public Criteria andSinglepriceLessThan(Float value) {
            addCriterion("singleprice <", value, "singleprice");
            return (Criteria) this;
        }

        public Criteria andSinglepriceLessThanOrEqualTo(Float value) {
            addCriterion("singleprice <=", value, "singleprice");
            return (Criteria) this;
        }

        public Criteria andSinglepriceIn(List<Float> values) {
            addCriterion("singleprice in", values, "singleprice");
            return (Criteria) this;
        }

        public Criteria andSinglepriceNotIn(List<Float> values) {
            addCriterion("singleprice not in", values, "singleprice");
            return (Criteria) this;
        }

        public Criteria andSinglepriceBetween(Float value1, Float value2) {
            addCriterion("singleprice between", value1, value2, "singleprice");
            return (Criteria) this;
        }

        public Criteria andSinglepriceNotBetween(Float value1, Float value2) {
            addCriterion("singleprice not between", value1, value2, "singleprice");
            return (Criteria) this;
        }

        public Criteria andTotalpriceIsNull() {
            addCriterion("totalprice is null");
            return (Criteria) this;
        }

        public Criteria andTotalpriceIsNotNull() {
            addCriterion("totalprice is not null");
            return (Criteria) this;
        }

        public Criteria andTotalpriceEqualTo(Float value) {
            addCriterion("totalprice =", value, "totalprice");
            return (Criteria) this;
        }

        public Criteria andTotalpriceNotEqualTo(Float value) {
            addCriterion("totalprice <>", value, "totalprice");
            return (Criteria) this;
        }

        public Criteria andTotalpriceGreaterThan(Float value) {
            addCriterion("totalprice >", value, "totalprice");
            return (Criteria) this;
        }

        public Criteria andTotalpriceGreaterThanOrEqualTo(Float value) {
            addCriterion("totalprice >=", value, "totalprice");
            return (Criteria) this;
        }

        public Criteria andTotalpriceLessThan(Float value) {
            addCriterion("totalprice <", value, "totalprice");
            return (Criteria) this;
        }

        public Criteria andTotalpriceLessThanOrEqualTo(Float value) {
            addCriterion("totalprice <=", value, "totalprice");
            return (Criteria) this;
        }

        public Criteria andTotalpriceIn(List<Float> values) {
            addCriterion("totalprice in", values, "totalprice");
            return (Criteria) this;
        }

        public Criteria andTotalpriceNotIn(List<Float> values) {
            addCriterion("totalprice not in", values, "totalprice");
            return (Criteria) this;
        }

        public Criteria andTotalpriceBetween(Float value1, Float value2) {
            addCriterion("totalprice between", value1, value2, "totalprice");
            return (Criteria) this;
        }

        public Criteria andTotalpriceNotBetween(Float value1, Float value2) {
            addCriterion("totalprice not between", value1, value2, "totalprice");
            return (Criteria) this;
        }

        public Criteria andSourceIsNull() {
            addCriterion("source is null");
            return (Criteria) this;
        }

        public Criteria andSourceIsNotNull() {
            addCriterion("source is not null");
            return (Criteria) this;
        }

        public Criteria andSourceEqualTo(Byte value) {
            addCriterion("source =", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotEqualTo(Byte value) {
            addCriterion("source <>", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThan(Byte value) {
            addCriterion("source >", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThanOrEqualTo(Byte value) {
            addCriterion("source >=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThan(Byte value) {
            addCriterion("source <", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThanOrEqualTo(Byte value) {
            addCriterion("source <=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceIn(List<Byte> values) {
            addCriterion("source in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotIn(List<Byte> values) {
            addCriterion("source not in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceBetween(Byte value1, Byte value2) {
            addCriterion("source between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotBetween(Byte value1, Byte value2) {
            addCriterion("source not between", value1, value2, "source");
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

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andActTypeIsNull() {
            addCriterion("act_type is null");
            return (Criteria) this;
        }

        public Criteria andActTypeIsNotNull() {
            addCriterion("act_type is not null");
            return (Criteria) this;
        }

        public Criteria andActTypeEqualTo(Long value) {
            addCriterion("act_type =", value, "actType");
            return (Criteria) this;
        }

        public Criteria andActTypeNotEqualTo(Long value) {
            addCriterion("act_type <>", value, "actType");
            return (Criteria) this;
        }

        public Criteria andActTypeGreaterThan(Long value) {
            addCriterion("act_type >", value, "actType");
            return (Criteria) this;
        }

        public Criteria andActTypeGreaterThanOrEqualTo(Long value) {
            addCriterion("act_type >=", value, "actType");
            return (Criteria) this;
        }

        public Criteria andActTypeLessThan(Long value) {
            addCriterion("act_type <", value, "actType");
            return (Criteria) this;
        }

        public Criteria andActTypeLessThanOrEqualTo(Long value) {
            addCriterion("act_type <=", value, "actType");
            return (Criteria) this;
        }

        public Criteria andActTypeIn(List<Long> values) {
            addCriterion("act_type in", values, "actType");
            return (Criteria) this;
        }

        public Criteria andActTypeNotIn(List<Long> values) {
            addCriterion("act_type not in", values, "actType");
            return (Criteria) this;
        }

        public Criteria andActTypeBetween(Long value1, Long value2) {
            addCriterion("act_type between", value1, value2, "actType");
            return (Criteria) this;
        }

        public Criteria andActTypeNotBetween(Long value1, Long value2) {
            addCriterion("act_type not between", value1, value2, "actType");
            return (Criteria) this;
        }

        public Criteria andRfidIsNull() {
            addCriterion("RFID is null");
            return (Criteria) this;
        }

        public Criteria andRfidIsNotNull() {
            addCriterion("RFID is not null");
            return (Criteria) this;
        }

        public Criteria andRfidEqualTo(String value) {
            addCriterion("RFID =", value, "rfid");
            return (Criteria) this;
        }

        public Criteria andRfidNotEqualTo(String value) {
            addCriterion("RFID <>", value, "rfid");
            return (Criteria) this;
        }

        public Criteria andRfidGreaterThan(String value) {
            addCriterion("RFID >", value, "rfid");
            return (Criteria) this;
        }

        public Criteria andRfidGreaterThanOrEqualTo(String value) {
            addCriterion("RFID >=", value, "rfid");
            return (Criteria) this;
        }

        public Criteria andRfidLessThan(String value) {
            addCriterion("RFID <", value, "rfid");
            return (Criteria) this;
        }

        public Criteria andRfidLessThanOrEqualTo(String value) {
            addCriterion("RFID <=", value, "rfid");
            return (Criteria) this;
        }

        public Criteria andRfidLike(String value) {
            addCriterion("RFID like", value, "rfid");
            return (Criteria) this;
        }

        public Criteria andRfidNotLike(String value) {
            addCriterion("RFID not like", value, "rfid");
            return (Criteria) this;
        }

        public Criteria andRfidIn(List<String> values) {
            addCriterion("RFID in", values, "rfid");
            return (Criteria) this;
        }

        public Criteria andRfidNotIn(List<String> values) {
            addCriterion("RFID not in", values, "rfid");
            return (Criteria) this;
        }

        public Criteria andRfidBetween(String value1, String value2) {
            addCriterion("RFID between", value1, value2, "rfid");
            return (Criteria) this;
        }

        public Criteria andRfidNotBetween(String value1, String value2) {
            addCriterion("RFID not between", value1, value2, "rfid");
            return (Criteria) this;
        }

        public Criteria andRecnoIsNull() {
            addCriterion("recno is null");
            return (Criteria) this;
        }

        public Criteria andRecnoIsNotNull() {
            addCriterion("recno is not null");
            return (Criteria) this;
        }

        public Criteria andRecnoEqualTo(Integer value) {
            addCriterion("recno =", value, "recno");
            return (Criteria) this;
        }

        public Criteria andRecnoNotEqualTo(Integer value) {
            addCriterion("recno <>", value, "recno");
            return (Criteria) this;
        }

        public Criteria andRecnoGreaterThan(Integer value) {
            addCriterion("recno >", value, "recno");
            return (Criteria) this;
        }

        public Criteria andRecnoGreaterThanOrEqualTo(Integer value) {
            addCriterion("recno >=", value, "recno");
            return (Criteria) this;
        }

        public Criteria andRecnoLessThan(Integer value) {
            addCriterion("recno <", value, "recno");
            return (Criteria) this;
        }

        public Criteria andRecnoLessThanOrEqualTo(Integer value) {
            addCriterion("recno <=", value, "recno");
            return (Criteria) this;
        }

        public Criteria andRecnoIn(List<Integer> values) {
            addCriterion("recno in", values, "recno");
            return (Criteria) this;
        }

        public Criteria andRecnoNotIn(List<Integer> values) {
            addCriterion("recno not in", values, "recno");
            return (Criteria) this;
        }

        public Criteria andRecnoBetween(Integer value1, Integer value2) {
            addCriterion("recno between", value1, value2, "recno");
            return (Criteria) this;
        }

        public Criteria andRecnoNotBetween(Integer value1, Integer value2) {
            addCriterion("recno not between", value1, value2, "recno");
            return (Criteria) this;
        }

        public Criteria andBookrecnoIsNull() {
            addCriterion("bookrecno is null");
            return (Criteria) this;
        }

        public Criteria andBookrecnoIsNotNull() {
            addCriterion("bookrecno is not null");
            return (Criteria) this;
        }

        public Criteria andBookrecnoEqualTo(Integer value) {
            addCriterion("bookrecno =", value, "bookrecno");
            return (Criteria) this;
        }

        public Criteria andBookrecnoNotEqualTo(Integer value) {
            addCriterion("bookrecno <>", value, "bookrecno");
            return (Criteria) this;
        }

        public Criteria andBookrecnoGreaterThan(Integer value) {
            addCriterion("bookrecno >", value, "bookrecno");
            return (Criteria) this;
        }

        public Criteria andBookrecnoGreaterThanOrEqualTo(Integer value) {
            addCriterion("bookrecno >=", value, "bookrecno");
            return (Criteria) this;
        }

        public Criteria andBookrecnoLessThan(Integer value) {
            addCriterion("bookrecno <", value, "bookrecno");
            return (Criteria) this;
        }

        public Criteria andBookrecnoLessThanOrEqualTo(Integer value) {
            addCriterion("bookrecno <=", value, "bookrecno");
            return (Criteria) this;
        }

        public Criteria andBookrecnoIn(List<Integer> values) {
            addCriterion("bookrecno in", values, "bookrecno");
            return (Criteria) this;
        }

        public Criteria andBookrecnoNotIn(List<Integer> values) {
            addCriterion("bookrecno not in", values, "bookrecno");
            return (Criteria) this;
        }

        public Criteria andBookrecnoBetween(Integer value1, Integer value2) {
            addCriterion("bookrecno between", value1, value2, "bookrecno");
            return (Criteria) this;
        }

        public Criteria andBookrecnoNotBetween(Integer value1, Integer value2) {
            addCriterion("bookrecno not between", value1, value2, "bookrecno");
            return (Criteria) this;
        }

        public Criteria andRowidIsNull() {
            addCriterion("rowid is null");
            return (Criteria) this;
        }

        public Criteria andRowidIsNotNull() {
            addCriterion("rowid is not null");
            return (Criteria) this;
        }

        public Criteria andRowidEqualTo(String value) {
            addCriterion("rowid =", value, "rowid");
            return (Criteria) this;
        }

        public Criteria andRowidNotEqualTo(String value) {
            addCriterion("rowid <>", value, "rowid");
            return (Criteria) this;
        }

        public Criteria andRowidGreaterThan(String value) {
            addCriterion("rowid >", value, "rowid");
            return (Criteria) this;
        }

        public Criteria andRowidGreaterThanOrEqualTo(String value) {
            addCriterion("rowid >=", value, "rowid");
            return (Criteria) this;
        }

        public Criteria andRowidLessThan(String value) {
            addCriterion("rowid <", value, "rowid");
            return (Criteria) this;
        }

        public Criteria andRowidLessThanOrEqualTo(String value) {
            addCriterion("rowid <=", value, "rowid");
            return (Criteria) this;
        }

        public Criteria andRowidLike(String value) {
            addCriterion("rowid like", value, "rowid");
            return (Criteria) this;
        }

        public Criteria andRowidNotLike(String value) {
            addCriterion("rowid not like", value, "rowid");
            return (Criteria) this;
        }

        public Criteria andRowidIn(List<String> values) {
            addCriterion("rowid in", values, "rowid");
            return (Criteria) this;
        }

        public Criteria andRowidNotIn(List<String> values) {
            addCriterion("rowid not in", values, "rowid");
            return (Criteria) this;
        }

        public Criteria andRowidBetween(String value1, String value2) {
            addCriterion("rowid between", value1, value2, "rowid");
            return (Criteria) this;
        }

        public Criteria andRowidNotBetween(String value1, String value2) {
            addCriterion("rowid not between", value1, value2, "rowid");
            return (Criteria) this;
        }

        public Criteria andColladdressIdIsNull() {
            addCriterion("collAddress_id is null");
            return (Criteria) this;
        }

        public Criteria andColladdressIdIsNotNull() {
            addCriterion("collAddress_id is not null");
            return (Criteria) this;
        }

        public Criteria andColladdressIdEqualTo(Long value) {
            addCriterion("collAddress_id =", value, "colladdressId");
            return (Criteria) this;
        }

        public Criteria andColladdressIdNotEqualTo(Long value) {
            addCriterion("collAddress_id <>", value, "colladdressId");
            return (Criteria) this;
        }

        public Criteria andColladdressIdGreaterThan(Long value) {
            addCriterion("collAddress_id >", value, "colladdressId");
            return (Criteria) this;
        }

        public Criteria andColladdressIdGreaterThanOrEqualTo(Long value) {
            addCriterion("collAddress_id >=", value, "colladdressId");
            return (Criteria) this;
        }

        public Criteria andColladdressIdLessThan(Long value) {
            addCriterion("collAddress_id <", value, "colladdressId");
            return (Criteria) this;
        }

        public Criteria andColladdressIdLessThanOrEqualTo(Long value) {
            addCriterion("collAddress_id <=", value, "colladdressId");
            return (Criteria) this;
        }

        public Criteria andColladdressIdIn(List<Long> values) {
            addCriterion("collAddress_id in", values, "colladdressId");
            return (Criteria) this;
        }

        public Criteria andColladdressIdNotIn(List<Long> values) {
            addCriterion("collAddress_id not in", values, "colladdressId");
            return (Criteria) this;
        }

        public Criteria andColladdressIdBetween(Long value1, Long value2) {
            addCriterion("collAddress_id between", value1, value2, "colladdressId");
            return (Criteria) this;
        }

        public Criteria andColladdressIdNotBetween(Long value1, Long value2) {
            addCriterion("collAddress_id not between", value1, value2, "colladdressId");
            return (Criteria) this;
        }

        public Criteria andCatalogBatchIsNull() {
            addCriterion("catalog_batch is null");
            return (Criteria) this;
        }

        public Criteria andCatalogBatchIsNotNull() {
            addCriterion("catalog_batch is not null");
            return (Criteria) this;
        }

        public Criteria andCatalogBatchEqualTo(String value) {
            addCriterion("catalog_batch =", value, "catalogBatch");
            return (Criteria) this;
        }

        public Criteria andCatalogBatchNotEqualTo(String value) {
            addCriterion("catalog_batch <>", value, "catalogBatch");
            return (Criteria) this;
        }

        public Criteria andCatalogBatchGreaterThan(String value) {
            addCriterion("catalog_batch >", value, "catalogBatch");
            return (Criteria) this;
        }

        public Criteria andCatalogBatchGreaterThanOrEqualTo(String value) {
            addCriterion("catalog_batch >=", value, "catalogBatch");
            return (Criteria) this;
        }

        public Criteria andCatalogBatchLessThan(String value) {
            addCriterion("catalog_batch <", value, "catalogBatch");
            return (Criteria) this;
        }

        public Criteria andCatalogBatchLessThanOrEqualTo(String value) {
            addCriterion("catalog_batch <=", value, "catalogBatch");
            return (Criteria) this;
        }

        public Criteria andCatalogBatchLike(String value) {
            addCriterion("catalog_batch like", value, "catalogBatch");
            return (Criteria) this;
        }

        public Criteria andCatalogBatchNotLike(String value) {
            addCriterion("catalog_batch not like", value, "catalogBatch");
            return (Criteria) this;
        }

        public Criteria andCatalogBatchIn(List<String> values) {
            addCriterion("catalog_batch in", values, "catalogBatch");
            return (Criteria) this;
        }

        public Criteria andCatalogBatchNotIn(List<String> values) {
            addCriterion("catalog_batch not in", values, "catalogBatch");
            return (Criteria) this;
        }

        public Criteria andCatalogBatchBetween(String value1, String value2) {
            addCriterion("catalog_batch between", value1, value2, "catalogBatch");
            return (Criteria) this;
        }

        public Criteria andCatalogBatchNotBetween(String value1, String value2) {
            addCriterion("catalog_batch not between", value1, value2, "catalogBatch");
            return (Criteria) this;
        }

        public Criteria andPartitionIsNull() {
            addCriterion("partition is null");
            return (Criteria) this;
        }

        public Criteria andPartitionIsNotNull() {
            addCriterion("partition is not null");
            return (Criteria) this;
        }

        public Criteria andPartitionEqualTo(String value) {
            addCriterion("partition =", value, "partition");
            return (Criteria) this;
        }

        public Criteria andPartitionNotEqualTo(String value) {
            addCriterion("partition <>", value, "partition");
            return (Criteria) this;
        }

        public Criteria andPartitionGreaterThan(String value) {
            addCriterion("partition >", value, "partition");
            return (Criteria) this;
        }

        public Criteria andPartitionGreaterThanOrEqualTo(String value) {
            addCriterion("partition >=", value, "partition");
            return (Criteria) this;
        }

        public Criteria andPartitionLessThan(String value) {
            addCriterion("partition <", value, "partition");
            return (Criteria) this;
        }

        public Criteria andPartitionLessThanOrEqualTo(String value) {
            addCriterion("partition <=", value, "partition");
            return (Criteria) this;
        }

        public Criteria andPartitionLike(String value) {
            addCriterion("partition like", value, "partition");
            return (Criteria) this;
        }

        public Criteria andPartitionNotLike(String value) {
            addCriterion("partition not like", value, "partition");
            return (Criteria) this;
        }

        public Criteria andPartitionIn(List<String> values) {
            addCriterion("partition in", values, "partition");
            return (Criteria) this;
        }

        public Criteria andPartitionNotIn(List<String> values) {
            addCriterion("partition not in", values, "partition");
            return (Criteria) this;
        }

        public Criteria andPartitionBetween(String value1, String value2) {
            addCriterion("partition between", value1, value2, "partition");
            return (Criteria) this;
        }

        public Criteria andPartitionNotBetween(String value1, String value2) {
            addCriterion("partition not between", value1, value2, "partition");
            return (Criteria) this;
        }

        public Criteria andVolumeIsNull() {
            addCriterion("volume is null");
            return (Criteria) this;
        }

        public Criteria andVolumeIsNotNull() {
            addCriterion("volume is not null");
            return (Criteria) this;
        }

        public Criteria andVolumeEqualTo(String value) {
            addCriterion("volume =", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeNotEqualTo(String value) {
            addCriterion("volume <>", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeGreaterThan(String value) {
            addCriterion("volume >", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeGreaterThanOrEqualTo(String value) {
            addCriterion("volume >=", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeLessThan(String value) {
            addCriterion("volume <", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeLessThanOrEqualTo(String value) {
            addCriterion("volume <=", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeLike(String value) {
            addCriterion("volume like", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeNotLike(String value) {
            addCriterion("volume not like", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeIn(List<String> values) {
            addCriterion("volume in", values, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeNotIn(List<String> values) {
            addCriterion("volume not in", values, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeBetween(String value1, String value2) {
            addCriterion("volume between", value1, value2, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeNotBetween(String value1, String value2) {
            addCriterion("volume not between", value1, value2, "volume");
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