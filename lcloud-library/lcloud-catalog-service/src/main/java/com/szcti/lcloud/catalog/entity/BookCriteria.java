package com.szcti.lcloud.catalog.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BookCriteria() {
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

        public Criteria andPicIsNull() {
            addCriterion("pic is null");
            return (Criteria) this;
        }

        public Criteria andPicIsNotNull() {
            addCriterion("pic is not null");
            return (Criteria) this;
        }

        public Criteria andPicEqualTo(String value) {
            addCriterion("pic =", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotEqualTo(String value) {
            addCriterion("pic <>", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicGreaterThan(String value) {
            addCriterion("pic >", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicGreaterThanOrEqualTo(String value) {
            addCriterion("pic >=", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLessThan(String value) {
            addCriterion("pic <", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLessThanOrEqualTo(String value) {
            addCriterion("pic <=", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLike(String value) {
            addCriterion("pic like", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotLike(String value) {
            addCriterion("pic not like", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicIn(List<String> values) {
            addCriterion("pic in", values, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotIn(List<String> values) {
            addCriterion("pic not in", values, "pic");
            return (Criteria) this;
        }

        public Criteria andPicBetween(String value1, String value2) {
            addCriterion("pic between", value1, value2, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotBetween(String value1, String value2) {
            addCriterion("pic not between", value1, value2, "pic");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNull() {
            addCriterion("author is null");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNotNull() {
            addCriterion("author is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorEqualTo(String value) {
            addCriterion("author =", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotEqualTo(String value) {
            addCriterion("author <>", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThan(String value) {
            addCriterion("author >", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThanOrEqualTo(String value) {
            addCriterion("author >=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThan(String value) {
            addCriterion("author <", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThanOrEqualTo(String value) {
            addCriterion("author <=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLike(String value) {
            addCriterion("author like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotLike(String value) {
            addCriterion("author not like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorIn(List<String> values) {
            addCriterion("author in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotIn(List<String> values) {
            addCriterion("author not in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorBetween(String value1, String value2) {
            addCriterion("author between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotBetween(String value1, String value2) {
            addCriterion("author not between", value1, value2, "author");
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

        public Criteria andPubdateIsNull() {
            addCriterion("pubdate is null");
            return (Criteria) this;
        }

        public Criteria andPubdateIsNotNull() {
            addCriterion("pubdate is not null");
            return (Criteria) this;
        }

        public Criteria andPubdateEqualTo(Date value) {
            addCriterion("pubdate =", value, "pubdate");
            return (Criteria) this;
        }

        public Criteria andPubdateNotEqualTo(Date value) {
            addCriterion("pubdate <>", value, "pubdate");
            return (Criteria) this;
        }

        public Criteria andPubdateGreaterThan(Date value) {
            addCriterion("pubdate >", value, "pubdate");
            return (Criteria) this;
        }

        public Criteria andPubdateGreaterThanOrEqualTo(Date value) {
            addCriterion("pubdate >=", value, "pubdate");
            return (Criteria) this;
        }

        public Criteria andPubdateLessThan(Date value) {
            addCriterion("pubdate <", value, "pubdate");
            return (Criteria) this;
        }

        public Criteria andPubdateLessThanOrEqualTo(Date value) {
            addCriterion("pubdate <=", value, "pubdate");
            return (Criteria) this;
        }

        public Criteria andPubdateIn(List<Date> values) {
            addCriterion("pubdate in", values, "pubdate");
            return (Criteria) this;
        }

        public Criteria andPubdateNotIn(List<Date> values) {
            addCriterion("pubdate not in", values, "pubdate");
            return (Criteria) this;
        }

        public Criteria andPubdateBetween(Date value1, Date value2) {
            addCriterion("pubdate between", value1, value2, "pubdate");
            return (Criteria) this;
        }

        public Criteria andPubdateNotBetween(Date value1, Date value2) {
            addCriterion("pubdate not between", value1, value2, "pubdate");
            return (Criteria) this;
        }

        public Criteria andBookTypeIsNull() {
            addCriterion("book_type is null");
            return (Criteria) this;
        }

        public Criteria andBookTypeIsNotNull() {
            addCriterion("book_type is not null");
            return (Criteria) this;
        }

        public Criteria andBookTypeEqualTo(String value) {
            addCriterion("book_type =", value, "bookType");
            return (Criteria) this;
        }

        public Criteria andBookTypeNotEqualTo(String value) {
            addCriterion("book_type <>", value, "bookType");
            return (Criteria) this;
        }

        public Criteria andBookTypeGreaterThan(String value) {
            addCriterion("book_type >", value, "bookType");
            return (Criteria) this;
        }

        public Criteria andBookTypeGreaterThanOrEqualTo(String value) {
            addCriterion("book_type >=", value, "bookType");
            return (Criteria) this;
        }

        public Criteria andBookTypeLessThan(String value) {
            addCriterion("book_type <", value, "bookType");
            return (Criteria) this;
        }

        public Criteria andBookTypeLessThanOrEqualTo(String value) {
            addCriterion("book_type <=", value, "bookType");
            return (Criteria) this;
        }

        public Criteria andBookTypeLike(String value) {
            addCriterion("book_type like", value, "bookType");
            return (Criteria) this;
        }

        public Criteria andBookTypeNotLike(String value) {
            addCriterion("book_type not like", value, "bookType");
            return (Criteria) this;
        }

        public Criteria andBookTypeIn(List<String> values) {
            addCriterion("book_type in", values, "bookType");
            return (Criteria) this;
        }

        public Criteria andBookTypeNotIn(List<String> values) {
            addCriterion("book_type not in", values, "bookType");
            return (Criteria) this;
        }

        public Criteria andBookTypeBetween(String value1, String value2) {
            addCriterion("book_type between", value1, value2, "bookType");
            return (Criteria) this;
        }

        public Criteria andBookTypeNotBetween(String value1, String value2) {
            addCriterion("book_type not between", value1, value2, "bookType");
            return (Criteria) this;
        }

        public Criteria andIsbnIsNull() {
            addCriterion("ISBN is null");
            return (Criteria) this;
        }

        public Criteria andIsbnIsNotNull() {
            addCriterion("ISBN is not null");
            return (Criteria) this;
        }

        public Criteria andIsbnEqualTo(String value) {
            addCriterion("ISBN =", value, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnNotEqualTo(String value) {
            addCriterion("ISBN <>", value, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnGreaterThan(String value) {
            addCriterion("ISBN >", value, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnGreaterThanOrEqualTo(String value) {
            addCriterion("ISBN >=", value, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnLessThan(String value) {
            addCriterion("ISBN <", value, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnLessThanOrEqualTo(String value) {
            addCriterion("ISBN <=", value, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnLike(String value) {
            addCriterion("ISBN like", value, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnNotLike(String value) {
            addCriterion("ISBN not like", value, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnIn(List<String> values) {
            addCriterion("ISBN in", values, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnNotIn(List<String> values) {
            addCriterion("ISBN not in", values, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnBetween(String value1, String value2) {
            addCriterion("ISBN between", value1, value2, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnNotBetween(String value1, String value2) {
            addCriterion("ISBN not between", value1, value2, "isbn");
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

        public Criteria andPriceEqualTo(Float value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(Float value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(Float value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(Float value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(Float value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(Float value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<Float> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<Float> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(Float value1, Float value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(Float value1, Float value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNull() {
            addCriterion("create_by is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("create_by is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(Long value) {
            addCriterion("create_by =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(Long value) {
            addCriterion("create_by <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(Long value) {
            addCriterion("create_by >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(Long value) {
            addCriterion("create_by >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(Long value) {
            addCriterion("create_by <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(Long value) {
            addCriterion("create_by <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<Long> values) {
            addCriterion("create_by in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<Long> values) {
            addCriterion("create_by not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(Long value1, Long value2) {
            addCriterion("create_by between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(Long value1, Long value2) {
            addCriterion("create_by not between", value1, value2, "createBy");
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

        public Criteria andPagesIsNull() {
            addCriterion("pages is null");
            return (Criteria) this;
        }

        public Criteria andPagesIsNotNull() {
            addCriterion("pages is not null");
            return (Criteria) this;
        }

        public Criteria andPagesEqualTo(Integer value) {
            addCriterion("pages =", value, "pages");
            return (Criteria) this;
        }

        public Criteria andPagesNotEqualTo(Integer value) {
            addCriterion("pages <>", value, "pages");
            return (Criteria) this;
        }

        public Criteria andPagesGreaterThan(Integer value) {
            addCriterion("pages >", value, "pages");
            return (Criteria) this;
        }

        public Criteria andPagesGreaterThanOrEqualTo(Integer value) {
            addCriterion("pages >=", value, "pages");
            return (Criteria) this;
        }

        public Criteria andPagesLessThan(Integer value) {
            addCriterion("pages <", value, "pages");
            return (Criteria) this;
        }

        public Criteria andPagesLessThanOrEqualTo(Integer value) {
            addCriterion("pages <=", value, "pages");
            return (Criteria) this;
        }

        public Criteria andPagesIn(List<Integer> values) {
            addCriterion("pages in", values, "pages");
            return (Criteria) this;
        }

        public Criteria andPagesNotIn(List<Integer> values) {
            addCriterion("pages not in", values, "pages");
            return (Criteria) this;
        }

        public Criteria andPagesBetween(Integer value1, Integer value2) {
            addCriterion("pages between", value1, value2, "pages");
            return (Criteria) this;
        }

        public Criteria andPagesNotBetween(Integer value1, Integer value2) {
            addCriterion("pages not between", value1, value2, "pages");
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

        public Criteria andSeriesTitleIsNull() {
            addCriterion("series_title is null");
            return (Criteria) this;
        }

        public Criteria andSeriesTitleIsNotNull() {
            addCriterion("series_title is not null");
            return (Criteria) this;
        }

        public Criteria andSeriesTitleEqualTo(String value) {
            addCriterion("series_title =", value, "seriesTitle");
            return (Criteria) this;
        }

        public Criteria andSeriesTitleNotEqualTo(String value) {
            addCriterion("series_title <>", value, "seriesTitle");
            return (Criteria) this;
        }

        public Criteria andSeriesTitleGreaterThan(String value) {
            addCriterion("series_title >", value, "seriesTitle");
            return (Criteria) this;
        }

        public Criteria andSeriesTitleGreaterThanOrEqualTo(String value) {
            addCriterion("series_title >=", value, "seriesTitle");
            return (Criteria) this;
        }

        public Criteria andSeriesTitleLessThan(String value) {
            addCriterion("series_title <", value, "seriesTitle");
            return (Criteria) this;
        }

        public Criteria andSeriesTitleLessThanOrEqualTo(String value) {
            addCriterion("series_title <=", value, "seriesTitle");
            return (Criteria) this;
        }

        public Criteria andSeriesTitleLike(String value) {
            addCriterion("series_title like", value, "seriesTitle");
            return (Criteria) this;
        }

        public Criteria andSeriesTitleNotLike(String value) {
            addCriterion("series_title not like", value, "seriesTitle");
            return (Criteria) this;
        }

        public Criteria andSeriesTitleIn(List<String> values) {
            addCriterion("series_title in", values, "seriesTitle");
            return (Criteria) this;
        }

        public Criteria andSeriesTitleNotIn(List<String> values) {
            addCriterion("series_title not in", values, "seriesTitle");
            return (Criteria) this;
        }

        public Criteria andSeriesTitleBetween(String value1, String value2) {
            addCriterion("series_title between", value1, value2, "seriesTitle");
            return (Criteria) this;
        }

        public Criteria andSeriesTitleNotBetween(String value1, String value2) {
            addCriterion("series_title not between", value1, value2, "seriesTitle");
            return (Criteria) this;
        }

        public Criteria andSecondTitleIsNull() {
            addCriterion("second_title is null");
            return (Criteria) this;
        }

        public Criteria andSecondTitleIsNotNull() {
            addCriterion("second_title is not null");
            return (Criteria) this;
        }

        public Criteria andSecondTitleEqualTo(String value) {
            addCriterion("second_title =", value, "secondTitle");
            return (Criteria) this;
        }

        public Criteria andSecondTitleNotEqualTo(String value) {
            addCriterion("second_title <>", value, "secondTitle");
            return (Criteria) this;
        }

        public Criteria andSecondTitleGreaterThan(String value) {
            addCriterion("second_title >", value, "secondTitle");
            return (Criteria) this;
        }

        public Criteria andSecondTitleGreaterThanOrEqualTo(String value) {
            addCriterion("second_title >=", value, "secondTitle");
            return (Criteria) this;
        }

        public Criteria andSecondTitleLessThan(String value) {
            addCriterion("second_title <", value, "secondTitle");
            return (Criteria) this;
        }

        public Criteria andSecondTitleLessThanOrEqualTo(String value) {
            addCriterion("second_title <=", value, "secondTitle");
            return (Criteria) this;
        }

        public Criteria andSecondTitleLike(String value) {
            addCriterion("second_title like", value, "secondTitle");
            return (Criteria) this;
        }

        public Criteria andSecondTitleNotLike(String value) {
            addCriterion("second_title not like", value, "secondTitle");
            return (Criteria) this;
        }

        public Criteria andSecondTitleIn(List<String> values) {
            addCriterion("second_title in", values, "secondTitle");
            return (Criteria) this;
        }

        public Criteria andSecondTitleNotIn(List<String> values) {
            addCriterion("second_title not in", values, "secondTitle");
            return (Criteria) this;
        }

        public Criteria andSecondTitleBetween(String value1, String value2) {
            addCriterion("second_title between", value1, value2, "secondTitle");
            return (Criteria) this;
        }

        public Criteria andSecondTitleNotBetween(String value1, String value2) {
            addCriterion("second_title not between", value1, value2, "secondTitle");
            return (Criteria) this;
        }

        public Criteria andSubjectWordIsNull() {
            addCriterion("subject_word is null");
            return (Criteria) this;
        }

        public Criteria andSubjectWordIsNotNull() {
            addCriterion("subject_word is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectWordEqualTo(String value) {
            addCriterion("subject_word =", value, "subjectWord");
            return (Criteria) this;
        }

        public Criteria andSubjectWordNotEqualTo(String value) {
            addCriterion("subject_word <>", value, "subjectWord");
            return (Criteria) this;
        }

        public Criteria andSubjectWordGreaterThan(String value) {
            addCriterion("subject_word >", value, "subjectWord");
            return (Criteria) this;
        }

        public Criteria andSubjectWordGreaterThanOrEqualTo(String value) {
            addCriterion("subject_word >=", value, "subjectWord");
            return (Criteria) this;
        }

        public Criteria andSubjectWordLessThan(String value) {
            addCriterion("subject_word <", value, "subjectWord");
            return (Criteria) this;
        }

        public Criteria andSubjectWordLessThanOrEqualTo(String value) {
            addCriterion("subject_word <=", value, "subjectWord");
            return (Criteria) this;
        }

        public Criteria andSubjectWordLike(String value) {
            addCriterion("subject_word like", value, "subjectWord");
            return (Criteria) this;
        }

        public Criteria andSubjectWordNotLike(String value) {
            addCriterion("subject_word not like", value, "subjectWord");
            return (Criteria) this;
        }

        public Criteria andSubjectWordIn(List<String> values) {
            addCriterion("subject_word in", values, "subjectWord");
            return (Criteria) this;
        }

        public Criteria andSubjectWordNotIn(List<String> values) {
            addCriterion("subject_word not in", values, "subjectWord");
            return (Criteria) this;
        }

        public Criteria andSubjectWordBetween(String value1, String value2) {
            addCriterion("subject_word between", value1, value2, "subjectWord");
            return (Criteria) this;
        }

        public Criteria andSubjectWordNotBetween(String value1, String value2) {
            addCriterion("subject_word not between", value1, value2, "subjectWord");
            return (Criteria) this;
        }

        public Criteria andFirstDutyIsNull() {
            addCriterion("first_duty is null");
            return (Criteria) this;
        }

        public Criteria andFirstDutyIsNotNull() {
            addCriterion("first_duty is not null");
            return (Criteria) this;
        }

        public Criteria andFirstDutyEqualTo(String value) {
            addCriterion("first_duty =", value, "firstDuty");
            return (Criteria) this;
        }

        public Criteria andFirstDutyNotEqualTo(String value) {
            addCriterion("first_duty <>", value, "firstDuty");
            return (Criteria) this;
        }

        public Criteria andFirstDutyGreaterThan(String value) {
            addCriterion("first_duty >", value, "firstDuty");
            return (Criteria) this;
        }

        public Criteria andFirstDutyGreaterThanOrEqualTo(String value) {
            addCriterion("first_duty >=", value, "firstDuty");
            return (Criteria) this;
        }

        public Criteria andFirstDutyLessThan(String value) {
            addCriterion("first_duty <", value, "firstDuty");
            return (Criteria) this;
        }

        public Criteria andFirstDutyLessThanOrEqualTo(String value) {
            addCriterion("first_duty <=", value, "firstDuty");
            return (Criteria) this;
        }

        public Criteria andFirstDutyLike(String value) {
            addCriterion("first_duty like", value, "firstDuty");
            return (Criteria) this;
        }

        public Criteria andFirstDutyNotLike(String value) {
            addCriterion("first_duty not like", value, "firstDuty");
            return (Criteria) this;
        }

        public Criteria andFirstDutyIn(List<String> values) {
            addCriterion("first_duty in", values, "firstDuty");
            return (Criteria) this;
        }

        public Criteria andFirstDutyNotIn(List<String> values) {
            addCriterion("first_duty not in", values, "firstDuty");
            return (Criteria) this;
        }

        public Criteria andFirstDutyBetween(String value1, String value2) {
            addCriterion("first_duty between", value1, value2, "firstDuty");
            return (Criteria) this;
        }

        public Criteria andFirstDutyNotBetween(String value1, String value2) {
            addCriterion("first_duty not between", value1, value2, "firstDuty");
            return (Criteria) this;
        }

        public Criteria andRevisionIsNull() {
            addCriterion("revision is null");
            return (Criteria) this;
        }

        public Criteria andRevisionIsNotNull() {
            addCriterion("revision is not null");
            return (Criteria) this;
        }

        public Criteria andRevisionEqualTo(String value) {
            addCriterion("revision =", value, "revision");
            return (Criteria) this;
        }

        public Criteria andRevisionNotEqualTo(String value) {
            addCriterion("revision <>", value, "revision");
            return (Criteria) this;
        }

        public Criteria andRevisionGreaterThan(String value) {
            addCriterion("revision >", value, "revision");
            return (Criteria) this;
        }

        public Criteria andRevisionGreaterThanOrEqualTo(String value) {
            addCriterion("revision >=", value, "revision");
            return (Criteria) this;
        }

        public Criteria andRevisionLessThan(String value) {
            addCriterion("revision <", value, "revision");
            return (Criteria) this;
        }

        public Criteria andRevisionLessThanOrEqualTo(String value) {
            addCriterion("revision <=", value, "revision");
            return (Criteria) this;
        }

        public Criteria andRevisionLike(String value) {
            addCriterion("revision like", value, "revision");
            return (Criteria) this;
        }

        public Criteria andRevisionNotLike(String value) {
            addCriterion("revision not like", value, "revision");
            return (Criteria) this;
        }

        public Criteria andRevisionIn(List<String> values) {
            addCriterion("revision in", values, "revision");
            return (Criteria) this;
        }

        public Criteria andRevisionNotIn(List<String> values) {
            addCriterion("revision not in", values, "revision");
            return (Criteria) this;
        }

        public Criteria andRevisionBetween(String value1, String value2) {
            addCriterion("revision between", value1, value2, "revision");
            return (Criteria) this;
        }

        public Criteria andRevisionNotBetween(String value1, String value2) {
            addCriterion("revision not between", value1, value2, "revision");
            return (Criteria) this;
        }

        public Criteria andLanguageIsNull() {
            addCriterion("language is null");
            return (Criteria) this;
        }

        public Criteria andLanguageIsNotNull() {
            addCriterion("language is not null");
            return (Criteria) this;
        }

        public Criteria andLanguageEqualTo(String value) {
            addCriterion("language =", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageNotEqualTo(String value) {
            addCriterion("language <>", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageGreaterThan(String value) {
            addCriterion("language >", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageGreaterThanOrEqualTo(String value) {
            addCriterion("language >=", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageLessThan(String value) {
            addCriterion("language <", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageLessThanOrEqualTo(String value) {
            addCriterion("language <=", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageLike(String value) {
            addCriterion("language like", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageNotLike(String value) {
            addCriterion("language not like", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageIn(List<String> values) {
            addCriterion("language in", values, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageNotIn(List<String> values) {
            addCriterion("language not in", values, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageBetween(String value1, String value2) {
            addCriterion("language between", value1, value2, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageNotBetween(String value1, String value2) {
            addCriterion("language not between", value1, value2, "language");
            return (Criteria) this;
        }

        public Criteria andCarrierTypeIsNull() {
            addCriterion("carrier_type is null");
            return (Criteria) this;
        }

        public Criteria andCarrierTypeIsNotNull() {
            addCriterion("carrier_type is not null");
            return (Criteria) this;
        }

        public Criteria andCarrierTypeEqualTo(String value) {
            addCriterion("carrier_type =", value, "carrierType");
            return (Criteria) this;
        }

        public Criteria andCarrierTypeNotEqualTo(String value) {
            addCriterion("carrier_type <>", value, "carrierType");
            return (Criteria) this;
        }

        public Criteria andCarrierTypeGreaterThan(String value) {
            addCriterion("carrier_type >", value, "carrierType");
            return (Criteria) this;
        }

        public Criteria andCarrierTypeGreaterThanOrEqualTo(String value) {
            addCriterion("carrier_type >=", value, "carrierType");
            return (Criteria) this;
        }

        public Criteria andCarrierTypeLessThan(String value) {
            addCriterion("carrier_type <", value, "carrierType");
            return (Criteria) this;
        }

        public Criteria andCarrierTypeLessThanOrEqualTo(String value) {
            addCriterion("carrier_type <=", value, "carrierType");
            return (Criteria) this;
        }

        public Criteria andCarrierTypeLike(String value) {
            addCriterion("carrier_type like", value, "carrierType");
            return (Criteria) this;
        }

        public Criteria andCarrierTypeNotLike(String value) {
            addCriterion("carrier_type not like", value, "carrierType");
            return (Criteria) this;
        }

        public Criteria andCarrierTypeIn(List<String> values) {
            addCriterion("carrier_type in", values, "carrierType");
            return (Criteria) this;
        }

        public Criteria andCarrierTypeNotIn(List<String> values) {
            addCriterion("carrier_type not in", values, "carrierType");
            return (Criteria) this;
        }

        public Criteria andCarrierTypeBetween(String value1, String value2) {
            addCriterion("carrier_type between", value1, value2, "carrierType");
            return (Criteria) this;
        }

        public Criteria andCarrierTypeNotBetween(String value1, String value2) {
            addCriterion("carrier_type not between", value1, value2, "carrierType");
            return (Criteria) this;
        }

        public Criteria andBindingIsNull() {
            addCriterion("binding is null");
            return (Criteria) this;
        }

        public Criteria andBindingIsNotNull() {
            addCriterion("binding is not null");
            return (Criteria) this;
        }

        public Criteria andBindingEqualTo(String value) {
            addCriterion("binding =", value, "binding");
            return (Criteria) this;
        }

        public Criteria andBindingNotEqualTo(String value) {
            addCriterion("binding <>", value, "binding");
            return (Criteria) this;
        }

        public Criteria andBindingGreaterThan(String value) {
            addCriterion("binding >", value, "binding");
            return (Criteria) this;
        }

        public Criteria andBindingGreaterThanOrEqualTo(String value) {
            addCriterion("binding >=", value, "binding");
            return (Criteria) this;
        }

        public Criteria andBindingLessThan(String value) {
            addCriterion("binding <", value, "binding");
            return (Criteria) this;
        }

        public Criteria andBindingLessThanOrEqualTo(String value) {
            addCriterion("binding <=", value, "binding");
            return (Criteria) this;
        }

        public Criteria andBindingLike(String value) {
            addCriterion("binding like", value, "binding");
            return (Criteria) this;
        }

        public Criteria andBindingNotLike(String value) {
            addCriterion("binding not like", value, "binding");
            return (Criteria) this;
        }

        public Criteria andBindingIn(List<String> values) {
            addCriterion("binding in", values, "binding");
            return (Criteria) this;
        }

        public Criteria andBindingNotIn(List<String> values) {
            addCriterion("binding not in", values, "binding");
            return (Criteria) this;
        }

        public Criteria andBindingBetween(String value1, String value2) {
            addCriterion("binding between", value1, value2, "binding");
            return (Criteria) this;
        }

        public Criteria andBindingNotBetween(String value1, String value2) {
            addCriterion("binding not between", value1, value2, "binding");
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

        public Criteria andParallelTitleIsNull() {
            addCriterion("parallel_title is null");
            return (Criteria) this;
        }

        public Criteria andParallelTitleIsNotNull() {
            addCriterion("parallel_title is not null");
            return (Criteria) this;
        }

        public Criteria andParallelTitleEqualTo(String value) {
            addCriterion("parallel_title =", value, "parallelTitle");
            return (Criteria) this;
        }

        public Criteria andParallelTitleNotEqualTo(String value) {
            addCriterion("parallel_title <>", value, "parallelTitle");
            return (Criteria) this;
        }

        public Criteria andParallelTitleGreaterThan(String value) {
            addCriterion("parallel_title >", value, "parallelTitle");
            return (Criteria) this;
        }

        public Criteria andParallelTitleGreaterThanOrEqualTo(String value) {
            addCriterion("parallel_title >=", value, "parallelTitle");
            return (Criteria) this;
        }

        public Criteria andParallelTitleLessThan(String value) {
            addCriterion("parallel_title <", value, "parallelTitle");
            return (Criteria) this;
        }

        public Criteria andParallelTitleLessThanOrEqualTo(String value) {
            addCriterion("parallel_title <=", value, "parallelTitle");
            return (Criteria) this;
        }

        public Criteria andParallelTitleLike(String value) {
            addCriterion("parallel_title like", value, "parallelTitle");
            return (Criteria) this;
        }

        public Criteria andParallelTitleNotLike(String value) {
            addCriterion("parallel_title not like", value, "parallelTitle");
            return (Criteria) this;
        }

        public Criteria andParallelTitleIn(List<String> values) {
            addCriterion("parallel_title in", values, "parallelTitle");
            return (Criteria) this;
        }

        public Criteria andParallelTitleNotIn(List<String> values) {
            addCriterion("parallel_title not in", values, "parallelTitle");
            return (Criteria) this;
        }

        public Criteria andParallelTitleBetween(String value1, String value2) {
            addCriterion("parallel_title between", value1, value2, "parallelTitle");
            return (Criteria) this;
        }

        public Criteria andParallelTitleNotBetween(String value1, String value2) {
            addCriterion("parallel_title not between", value1, value2, "parallelTitle");
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