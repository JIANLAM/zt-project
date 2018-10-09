package com.szcti.lcloud.catalog.entity;

import java.util.ArrayList;
import java.util.List;

public class MarcTemplateDetailCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MarcTemplateDetailCriteria() {
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

        public Criteria andMarcIdIsNull() {
            addCriterion("marc_id is null");
            return (Criteria) this;
        }

        public Criteria andMarcIdIsNotNull() {
            addCriterion("marc_id is not null");
            return (Criteria) this;
        }

        public Criteria andMarcIdEqualTo(Long value) {
            addCriterion("marc_id =", value, "marcId");
            return (Criteria) this;
        }

        public Criteria andMarcIdNotEqualTo(Long value) {
            addCriterion("marc_id <>", value, "marcId");
            return (Criteria) this;
        }

        public Criteria andMarcIdGreaterThan(Long value) {
            addCriterion("marc_id >", value, "marcId");
            return (Criteria) this;
        }

        public Criteria andMarcIdGreaterThanOrEqualTo(Long value) {
            addCriterion("marc_id >=", value, "marcId");
            return (Criteria) this;
        }

        public Criteria andMarcIdLessThan(Long value) {
            addCriterion("marc_id <", value, "marcId");
            return (Criteria) this;
        }

        public Criteria andMarcIdLessThanOrEqualTo(Long value) {
            addCriterion("marc_id <=", value, "marcId");
            return (Criteria) this;
        }

        public Criteria andMarcIdIn(List<Long> values) {
            addCriterion("marc_id in", values, "marcId");
            return (Criteria) this;
        }

        public Criteria andMarcIdNotIn(List<Long> values) {
            addCriterion("marc_id not in", values, "marcId");
            return (Criteria) this;
        }

        public Criteria andMarcIdBetween(Long value1, Long value2) {
            addCriterion("marc_id between", value1, value2, "marcId");
            return (Criteria) this;
        }

        public Criteria andMarcIdNotBetween(Long value1, Long value2) {
            addCriterion("marc_id not between", value1, value2, "marcId");
            return (Criteria) this;
        }

        public Criteria andMainStartIsNull() {
            addCriterion("main_start is null");
            return (Criteria) this;
        }

        public Criteria andMainStartIsNotNull() {
            addCriterion("main_start is not null");
            return (Criteria) this;
        }

        public Criteria andMainStartEqualTo(String value) {
            addCriterion("main_start =", value, "mainStart");
            return (Criteria) this;
        }

        public Criteria andMainStartNotEqualTo(String value) {
            addCriterion("main_start <>", value, "mainStart");
            return (Criteria) this;
        }

        public Criteria andMainStartGreaterThan(String value) {
            addCriterion("main_start >", value, "mainStart");
            return (Criteria) this;
        }

        public Criteria andMainStartGreaterThanOrEqualTo(String value) {
            addCriterion("main_start >=", value, "mainStart");
            return (Criteria) this;
        }

        public Criteria andMainStartLessThan(String value) {
            addCriterion("main_start <", value, "mainStart");
            return (Criteria) this;
        }

        public Criteria andMainStartLessThanOrEqualTo(String value) {
            addCriterion("main_start <=", value, "mainStart");
            return (Criteria) this;
        }

        public Criteria andMainStartLike(String value) {
            addCriterion("main_start like", value, "mainStart");
            return (Criteria) this;
        }

        public Criteria andMainStartNotLike(String value) {
            addCriterion("main_start not like", value, "mainStart");
            return (Criteria) this;
        }

        public Criteria andMainStartIn(List<String> values) {
            addCriterion("main_start in", values, "mainStart");
            return (Criteria) this;
        }

        public Criteria andMainStartNotIn(List<String> values) {
            addCriterion("main_start not in", values, "mainStart");
            return (Criteria) this;
        }

        public Criteria andMainStartBetween(String value1, String value2) {
            addCriterion("main_start between", value1, value2, "mainStart");
            return (Criteria) this;
        }

        public Criteria andMainStartNotBetween(String value1, String value2) {
            addCriterion("main_start not between", value1, value2, "mainStart");
            return (Criteria) this;
        }

        public Criteria andSecondStartIsNull() {
            addCriterion("second_start is null");
            return (Criteria) this;
        }

        public Criteria andSecondStartIsNotNull() {
            addCriterion("second_start is not null");
            return (Criteria) this;
        }

        public Criteria andSecondStartEqualTo(String value) {
            addCriterion("second_start =", value, "secondStart");
            return (Criteria) this;
        }

        public Criteria andSecondStartNotEqualTo(String value) {
            addCriterion("second_start <>", value, "secondStart");
            return (Criteria) this;
        }

        public Criteria andSecondStartGreaterThan(String value) {
            addCriterion("second_start >", value, "secondStart");
            return (Criteria) this;
        }

        public Criteria andSecondStartGreaterThanOrEqualTo(String value) {
            addCriterion("second_start >=", value, "secondStart");
            return (Criteria) this;
        }

        public Criteria andSecondStartLessThan(String value) {
            addCriterion("second_start <", value, "secondStart");
            return (Criteria) this;
        }

        public Criteria andSecondStartLessThanOrEqualTo(String value) {
            addCriterion("second_start <=", value, "secondStart");
            return (Criteria) this;
        }

        public Criteria andSecondStartLike(String value) {
            addCriterion("second_start like", value, "secondStart");
            return (Criteria) this;
        }

        public Criteria andSecondStartNotLike(String value) {
            addCriterion("second_start not like", value, "secondStart");
            return (Criteria) this;
        }

        public Criteria andSecondStartIn(List<String> values) {
            addCriterion("second_start in", values, "secondStart");
            return (Criteria) this;
        }

        public Criteria andSecondStartNotIn(List<String> values) {
            addCriterion("second_start not in", values, "secondStart");
            return (Criteria) this;
        }

        public Criteria andSecondStartBetween(String value1, String value2) {
            addCriterion("second_start between", value1, value2, "secondStart");
            return (Criteria) this;
        }

        public Criteria andSecondStartNotBetween(String value1, String value2) {
            addCriterion("second_start not between", value1, value2, "secondStart");
            return (Criteria) this;
        }

        public Criteria andMarcEndIsNull() {
            addCriterion("marc_end is null");
            return (Criteria) this;
        }

        public Criteria andMarcEndIsNotNull() {
            addCriterion("marc_end is not null");
            return (Criteria) this;
        }

        public Criteria andMarcEndEqualTo(String value) {
            addCriterion("marc_end =", value, "marcEnd");
            return (Criteria) this;
        }

        public Criteria andMarcEndNotEqualTo(String value) {
            addCriterion("marc_end <>", value, "marcEnd");
            return (Criteria) this;
        }

        public Criteria andMarcEndGreaterThan(String value) {
            addCriterion("marc_end >", value, "marcEnd");
            return (Criteria) this;
        }

        public Criteria andMarcEndGreaterThanOrEqualTo(String value) {
            addCriterion("marc_end >=", value, "marcEnd");
            return (Criteria) this;
        }

        public Criteria andMarcEndLessThan(String value) {
            addCriterion("marc_end <", value, "marcEnd");
            return (Criteria) this;
        }

        public Criteria andMarcEndLessThanOrEqualTo(String value) {
            addCriterion("marc_end <=", value, "marcEnd");
            return (Criteria) this;
        }

        public Criteria andMarcEndLike(String value) {
            addCriterion("marc_end like", value, "marcEnd");
            return (Criteria) this;
        }

        public Criteria andMarcEndNotLike(String value) {
            addCriterion("marc_end not like", value, "marcEnd");
            return (Criteria) this;
        }

        public Criteria andMarcEndIn(List<String> values) {
            addCriterion("marc_end in", values, "marcEnd");
            return (Criteria) this;
        }

        public Criteria andMarcEndNotIn(List<String> values) {
            addCriterion("marc_end not in", values, "marcEnd");
            return (Criteria) this;
        }

        public Criteria andMarcEndBetween(String value1, String value2) {
            addCriterion("marc_end between", value1, value2, "marcEnd");
            return (Criteria) this;
        }

        public Criteria andMarcEndNotBetween(String value1, String value2) {
            addCriterion("marc_end not between", value1, value2, "marcEnd");
            return (Criteria) this;
        }

        public Criteria andNameDefineIsNull() {
            addCriterion("name_define is null");
            return (Criteria) this;
        }

        public Criteria andNameDefineIsNotNull() {
            addCriterion("name_define is not null");
            return (Criteria) this;
        }

        public Criteria andNameDefineEqualTo(String value) {
            addCriterion("name_define =", value, "nameDefine");
            return (Criteria) this;
        }

        public Criteria andNameDefineNotEqualTo(String value) {
            addCriterion("name_define <>", value, "nameDefine");
            return (Criteria) this;
        }

        public Criteria andNameDefineGreaterThan(String value) {
            addCriterion("name_define >", value, "nameDefine");
            return (Criteria) this;
        }

        public Criteria andNameDefineGreaterThanOrEqualTo(String value) {
            addCriterion("name_define >=", value, "nameDefine");
            return (Criteria) this;
        }

        public Criteria andNameDefineLessThan(String value) {
            addCriterion("name_define <", value, "nameDefine");
            return (Criteria) this;
        }

        public Criteria andNameDefineLessThanOrEqualTo(String value) {
            addCriterion("name_define <=", value, "nameDefine");
            return (Criteria) this;
        }

        public Criteria andNameDefineLike(String value) {
            addCriterion("name_define like", value, "nameDefine");
            return (Criteria) this;
        }

        public Criteria andNameDefineNotLike(String value) {
            addCriterion("name_define not like", value, "nameDefine");
            return (Criteria) this;
        }

        public Criteria andNameDefineIn(List<String> values) {
            addCriterion("name_define in", values, "nameDefine");
            return (Criteria) this;
        }

        public Criteria andNameDefineNotIn(List<String> values) {
            addCriterion("name_define not in", values, "nameDefine");
            return (Criteria) this;
        }

        public Criteria andNameDefineBetween(String value1, String value2) {
            addCriterion("name_define between", value1, value2, "nameDefine");
            return (Criteria) this;
        }

        public Criteria andNameDefineNotBetween(String value1, String value2) {
            addCriterion("name_define not between", value1, value2, "nameDefine");
            return (Criteria) this;
        }

        public Criteria andDesignatorDefineIsNull() {
            addCriterion("designator_define is null");
            return (Criteria) this;
        }

        public Criteria andDesignatorDefineIsNotNull() {
            addCriterion("designator_define is not null");
            return (Criteria) this;
        }

        public Criteria andDesignatorDefineEqualTo(String value) {
            addCriterion("designator_define =", value, "designatorDefine");
            return (Criteria) this;
        }

        public Criteria andDesignatorDefineNotEqualTo(String value) {
            addCriterion("designator_define <>", value, "designatorDefine");
            return (Criteria) this;
        }

        public Criteria andDesignatorDefineGreaterThan(String value) {
            addCriterion("designator_define >", value, "designatorDefine");
            return (Criteria) this;
        }

        public Criteria andDesignatorDefineGreaterThanOrEqualTo(String value) {
            addCriterion("designator_define >=", value, "designatorDefine");
            return (Criteria) this;
        }

        public Criteria andDesignatorDefineLessThan(String value) {
            addCriterion("designator_define <", value, "designatorDefine");
            return (Criteria) this;
        }

        public Criteria andDesignatorDefineLessThanOrEqualTo(String value) {
            addCriterion("designator_define <=", value, "designatorDefine");
            return (Criteria) this;
        }

        public Criteria andDesignatorDefineLike(String value) {
            addCriterion("designator_define like", value, "designatorDefine");
            return (Criteria) this;
        }

        public Criteria andDesignatorDefineNotLike(String value) {
            addCriterion("designator_define not like", value, "designatorDefine");
            return (Criteria) this;
        }

        public Criteria andDesignatorDefineIn(List<String> values) {
            addCriterion("designator_define in", values, "designatorDefine");
            return (Criteria) this;
        }

        public Criteria andDesignatorDefineNotIn(List<String> values) {
            addCriterion("designator_define not in", values, "designatorDefine");
            return (Criteria) this;
        }

        public Criteria andDesignatorDefineBetween(String value1, String value2) {
            addCriterion("designator_define between", value1, value2, "designatorDefine");
            return (Criteria) this;
        }

        public Criteria andDesignatorDefineNotBetween(String value1, String value2) {
            addCriterion("designator_define not between", value1, value2, "designatorDefine");
            return (Criteria) this;
        }

        public Criteria andOrderIndexIsNull() {
            addCriterion("order_index is null");
            return (Criteria) this;
        }

        public Criteria andOrderIndexIsNotNull() {
            addCriterion("order_index is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIndexEqualTo(Integer value) {
            addCriterion("order_index =", value, "orderIndex");
            return (Criteria) this;
        }

        public Criteria andOrderIndexNotEqualTo(Integer value) {
            addCriterion("order_index <>", value, "orderIndex");
            return (Criteria) this;
        }

        public Criteria andOrderIndexGreaterThan(Integer value) {
            addCriterion("order_index >", value, "orderIndex");
            return (Criteria) this;
        }

        public Criteria andOrderIndexGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_index >=", value, "orderIndex");
            return (Criteria) this;
        }

        public Criteria andOrderIndexLessThan(Integer value) {
            addCriterion("order_index <", value, "orderIndex");
            return (Criteria) this;
        }

        public Criteria andOrderIndexLessThanOrEqualTo(Integer value) {
            addCriterion("order_index <=", value, "orderIndex");
            return (Criteria) this;
        }

        public Criteria andOrderIndexIn(List<Integer> values) {
            addCriterion("order_index in", values, "orderIndex");
            return (Criteria) this;
        }

        public Criteria andOrderIndexNotIn(List<Integer> values) {
            addCriterion("order_index not in", values, "orderIndex");
            return (Criteria) this;
        }

        public Criteria andOrderIndexBetween(Integer value1, Integer value2) {
            addCriterion("order_index between", value1, value2, "orderIndex");
            return (Criteria) this;
        }

        public Criteria andOrderIndexNotBetween(Integer value1, Integer value2) {
            addCriterion("order_index not between", value1, value2, "orderIndex");
            return (Criteria) this;
        }

        public Criteria andMarcTemplateIdIsNull() {
            addCriterion("marc_template_id is null");
            return (Criteria) this;
        }

        public Criteria andMarcTemplateIdIsNotNull() {
            addCriterion("marc_template_id is not null");
            return (Criteria) this;
        }

        public Criteria andMarcTemplateIdEqualTo(Long value) {
            addCriterion("marc_template_id =", value, "marcTemplateId");
            return (Criteria) this;
        }

        public Criteria andMarcTemplateIdNotEqualTo(Long value) {
            addCriterion("marc_template_id <>", value, "marcTemplateId");
            return (Criteria) this;
        }

        public Criteria andMarcTemplateIdGreaterThan(Long value) {
            addCriterion("marc_template_id >", value, "marcTemplateId");
            return (Criteria) this;
        }

        public Criteria andMarcTemplateIdGreaterThanOrEqualTo(Long value) {
            addCriterion("marc_template_id >=", value, "marcTemplateId");
            return (Criteria) this;
        }

        public Criteria andMarcTemplateIdLessThan(Long value) {
            addCriterion("marc_template_id <", value, "marcTemplateId");
            return (Criteria) this;
        }

        public Criteria andMarcTemplateIdLessThanOrEqualTo(Long value) {
            addCriterion("marc_template_id <=", value, "marcTemplateId");
            return (Criteria) this;
        }

        public Criteria andMarcTemplateIdIn(List<Long> values) {
            addCriterion("marc_template_id in", values, "marcTemplateId");
            return (Criteria) this;
        }

        public Criteria andMarcTemplateIdNotIn(List<Long> values) {
            addCriterion("marc_template_id not in", values, "marcTemplateId");
            return (Criteria) this;
        }

        public Criteria andMarcTemplateIdBetween(Long value1, Long value2) {
            addCriterion("marc_template_id between", value1, value2, "marcTemplateId");
            return (Criteria) this;
        }

        public Criteria andMarcTemplateIdNotBetween(Long value1, Long value2) {
            addCriterion("marc_template_id not between", value1, value2, "marcTemplateId");
            return (Criteria) this;
        }

        public Criteria andMustValueIsNull() {
            addCriterion("must_value is null");
            return (Criteria) this;
        }

        public Criteria andMustValueIsNotNull() {
            addCriterion("must_value is not null");
            return (Criteria) this;
        }

        public Criteria andMustValueEqualTo(String value) {
            addCriterion("must_value =", value, "mustValue");
            return (Criteria) this;
        }

        public Criteria andMustValueNotEqualTo(String value) {
            addCriterion("must_value <>", value, "mustValue");
            return (Criteria) this;
        }

        public Criteria andMustValueGreaterThan(String value) {
            addCriterion("must_value >", value, "mustValue");
            return (Criteria) this;
        }

        public Criteria andMustValueGreaterThanOrEqualTo(String value) {
            addCriterion("must_value >=", value, "mustValue");
            return (Criteria) this;
        }

        public Criteria andMustValueLessThan(String value) {
            addCriterion("must_value <", value, "mustValue");
            return (Criteria) this;
        }

        public Criteria andMustValueLessThanOrEqualTo(String value) {
            addCriterion("must_value <=", value, "mustValue");
            return (Criteria) this;
        }

        public Criteria andMustValueLike(String value) {
            addCriterion("must_value like", value, "mustValue");
            return (Criteria) this;
        }

        public Criteria andMustValueNotLike(String value) {
            addCriterion("must_value not like", value, "mustValue");
            return (Criteria) this;
        }

        public Criteria andMustValueIn(List<String> values) {
            addCriterion("must_value in", values, "mustValue");
            return (Criteria) this;
        }

        public Criteria andMustValueNotIn(List<String> values) {
            addCriterion("must_value not in", values, "mustValue");
            return (Criteria) this;
        }

        public Criteria andMustValueBetween(String value1, String value2) {
            addCriterion("must_value between", value1, value2, "mustValue");
            return (Criteria) this;
        }

        public Criteria andMustValueNotBetween(String value1, String value2) {
            addCriterion("must_value not between", value1, value2, "mustValue");
            return (Criteria) this;
        }

        public Criteria andSelectValueIsNull() {
            addCriterion("select_value is null");
            return (Criteria) this;
        }

        public Criteria andSelectValueIsNotNull() {
            addCriterion("select_value is not null");
            return (Criteria) this;
        }

        public Criteria andSelectValueEqualTo(String value) {
            addCriterion("select_value =", value, "selectValue");
            return (Criteria) this;
        }

        public Criteria andSelectValueNotEqualTo(String value) {
            addCriterion("select_value <>", value, "selectValue");
            return (Criteria) this;
        }

        public Criteria andSelectValueGreaterThan(String value) {
            addCriterion("select_value >", value, "selectValue");
            return (Criteria) this;
        }

        public Criteria andSelectValueGreaterThanOrEqualTo(String value) {
            addCriterion("select_value >=", value, "selectValue");
            return (Criteria) this;
        }

        public Criteria andSelectValueLessThan(String value) {
            addCriterion("select_value <", value, "selectValue");
            return (Criteria) this;
        }

        public Criteria andSelectValueLessThanOrEqualTo(String value) {
            addCriterion("select_value <=", value, "selectValue");
            return (Criteria) this;
        }

        public Criteria andSelectValueLike(String value) {
            addCriterion("select_value like", value, "selectValue");
            return (Criteria) this;
        }

        public Criteria andSelectValueNotLike(String value) {
            addCriterion("select_value not like", value, "selectValue");
            return (Criteria) this;
        }

        public Criteria andSelectValueIn(List<String> values) {
            addCriterion("select_value in", values, "selectValue");
            return (Criteria) this;
        }

        public Criteria andSelectValueNotIn(List<String> values) {
            addCriterion("select_value not in", values, "selectValue");
            return (Criteria) this;
        }

        public Criteria andSelectValueBetween(String value1, String value2) {
            addCriterion("select_value between", value1, value2, "selectValue");
            return (Criteria) this;
        }

        public Criteria andSelectValueNotBetween(String value1, String value2) {
            addCriterion("select_value not between", value1, value2, "selectValue");
            return (Criteria) this;
        }

        public Criteria andDiscriptionDefIsNull() {
            addCriterion("discription_def is null");
            return (Criteria) this;
        }

        public Criteria andDiscriptionDefIsNotNull() {
            addCriterion("discription_def is not null");
            return (Criteria) this;
        }

        public Criteria andDiscriptionDefEqualTo(String value) {
            addCriterion("discription_def =", value, "discriptionDef");
            return (Criteria) this;
        }

        public Criteria andDiscriptionDefNotEqualTo(String value) {
            addCriterion("discription_def <>", value, "discriptionDef");
            return (Criteria) this;
        }

        public Criteria andDiscriptionDefGreaterThan(String value) {
            addCriterion("discription_def >", value, "discriptionDef");
            return (Criteria) this;
        }

        public Criteria andDiscriptionDefGreaterThanOrEqualTo(String value) {
            addCriterion("discription_def >=", value, "discriptionDef");
            return (Criteria) this;
        }

        public Criteria andDiscriptionDefLessThan(String value) {
            addCriterion("discription_def <", value, "discriptionDef");
            return (Criteria) this;
        }

        public Criteria andDiscriptionDefLessThanOrEqualTo(String value) {
            addCriterion("discription_def <=", value, "discriptionDef");
            return (Criteria) this;
        }

        public Criteria andDiscriptionDefLike(String value) {
            addCriterion("discription_def like", value, "discriptionDef");
            return (Criteria) this;
        }

        public Criteria andDiscriptionDefNotLike(String value) {
            addCriterion("discription_def not like", value, "discriptionDef");
            return (Criteria) this;
        }

        public Criteria andDiscriptionDefIn(List<String> values) {
            addCriterion("discription_def in", values, "discriptionDef");
            return (Criteria) this;
        }

        public Criteria andDiscriptionDefNotIn(List<String> values) {
            addCriterion("discription_def not in", values, "discriptionDef");
            return (Criteria) this;
        }

        public Criteria andDiscriptionDefBetween(String value1, String value2) {
            addCriterion("discription_def between", value1, value2, "discriptionDef");
            return (Criteria) this;
        }

        public Criteria andDiscriptionDefNotBetween(String value1, String value2) {
            addCriterion("discription_def not between", value1, value2, "discriptionDef");
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