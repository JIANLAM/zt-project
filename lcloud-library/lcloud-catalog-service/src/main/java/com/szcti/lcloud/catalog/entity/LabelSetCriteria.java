package com.szcti.lcloud.catalog.entity;

import java.util.ArrayList;
import java.util.List;

public class LabelSetCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LabelSetCriteria() {
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

        public Criteria andLabelNameIsNull() {
            addCriterion("label_name is null");
            return (Criteria) this;
        }

        public Criteria andLabelNameIsNotNull() {
            addCriterion("label_name is not null");
            return (Criteria) this;
        }

        public Criteria andLabelNameEqualTo(String value) {
            addCriterion("label_name =", value, "labelName");
            return (Criteria) this;
        }

        public Criteria andLabelNameNotEqualTo(String value) {
            addCriterion("label_name <>", value, "labelName");
            return (Criteria) this;
        }

        public Criteria andLabelNameGreaterThan(String value) {
            addCriterion("label_name >", value, "labelName");
            return (Criteria) this;
        }

        public Criteria andLabelNameGreaterThanOrEqualTo(String value) {
            addCriterion("label_name >=", value, "labelName");
            return (Criteria) this;
        }

        public Criteria andLabelNameLessThan(String value) {
            addCriterion("label_name <", value, "labelName");
            return (Criteria) this;
        }

        public Criteria andLabelNameLessThanOrEqualTo(String value) {
            addCriterion("label_name <=", value, "labelName");
            return (Criteria) this;
        }

        public Criteria andLabelNameLike(String value) {
            addCriterion("label_name like", value, "labelName");
            return (Criteria) this;
        }

        public Criteria andLabelNameNotLike(String value) {
            addCriterion("label_name not like", value, "labelName");
            return (Criteria) this;
        }

        public Criteria andLabelNameIn(List<String> values) {
            addCriterion("label_name in", values, "labelName");
            return (Criteria) this;
        }

        public Criteria andLabelNameNotIn(List<String> values) {
            addCriterion("label_name not in", values, "labelName");
            return (Criteria) this;
        }

        public Criteria andLabelNameBetween(String value1, String value2) {
            addCriterion("label_name between", value1, value2, "labelName");
            return (Criteria) this;
        }

        public Criteria andLabelNameNotBetween(String value1, String value2) {
            addCriterion("label_name not between", value1, value2, "labelName");
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

        public Criteria andCreateTimeEqualTo(String value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(String value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(String value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(String value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(String value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(String value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLike(String value) {
            addCriterion("create_time like", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotLike(String value) {
            addCriterion("create_time not like", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<String> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<String> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(String value1, String value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(String value1, String value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
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

        public Criteria andPaperSizeIsNull() {
            addCriterion("paper_size is null");
            return (Criteria) this;
        }

        public Criteria andPaperSizeIsNotNull() {
            addCriterion("paper_size is not null");
            return (Criteria) this;
        }

        public Criteria andPaperSizeEqualTo(String value) {
            addCriterion("paper_size =", value, "paperSize");
            return (Criteria) this;
        }

        public Criteria andPaperSizeNotEqualTo(String value) {
            addCriterion("paper_size <>", value, "paperSize");
            return (Criteria) this;
        }

        public Criteria andPaperSizeGreaterThan(String value) {
            addCriterion("paper_size >", value, "paperSize");
            return (Criteria) this;
        }

        public Criteria andPaperSizeGreaterThanOrEqualTo(String value) {
            addCriterion("paper_size >=", value, "paperSize");
            return (Criteria) this;
        }

        public Criteria andPaperSizeLessThan(String value) {
            addCriterion("paper_size <", value, "paperSize");
            return (Criteria) this;
        }

        public Criteria andPaperSizeLessThanOrEqualTo(String value) {
            addCriterion("paper_size <=", value, "paperSize");
            return (Criteria) this;
        }

        public Criteria andPaperSizeLike(String value) {
            addCriterion("paper_size like", value, "paperSize");
            return (Criteria) this;
        }

        public Criteria andPaperSizeNotLike(String value) {
            addCriterion("paper_size not like", value, "paperSize");
            return (Criteria) this;
        }

        public Criteria andPaperSizeIn(List<String> values) {
            addCriterion("paper_size in", values, "paperSize");
            return (Criteria) this;
        }

        public Criteria andPaperSizeNotIn(List<String> values) {
            addCriterion("paper_size not in", values, "paperSize");
            return (Criteria) this;
        }

        public Criteria andPaperSizeBetween(String value1, String value2) {
            addCriterion("paper_size between", value1, value2, "paperSize");
            return (Criteria) this;
        }

        public Criteria andPaperSizeNotBetween(String value1, String value2) {
            addCriterion("paper_size not between", value1, value2, "paperSize");
            return (Criteria) this;
        }

        public Criteria andPaperHeightIsNull() {
            addCriterion("paper_height is null");
            return (Criteria) this;
        }

        public Criteria andPaperHeightIsNotNull() {
            addCriterion("paper_height is not null");
            return (Criteria) this;
        }

        public Criteria andPaperHeightEqualTo(String value) {
            addCriterion("paper_height =", value, "paperHeight");
            return (Criteria) this;
        }

        public Criteria andPaperHeightNotEqualTo(String value) {
            addCriterion("paper_height <>", value, "paperHeight");
            return (Criteria) this;
        }

        public Criteria andPaperHeightGreaterThan(String value) {
            addCriterion("paper_height >", value, "paperHeight");
            return (Criteria) this;
        }

        public Criteria andPaperHeightGreaterThanOrEqualTo(String value) {
            addCriterion("paper_height >=", value, "paperHeight");
            return (Criteria) this;
        }

        public Criteria andPaperHeightLessThan(String value) {
            addCriterion("paper_height <", value, "paperHeight");
            return (Criteria) this;
        }

        public Criteria andPaperHeightLessThanOrEqualTo(String value) {
            addCriterion("paper_height <=", value, "paperHeight");
            return (Criteria) this;
        }

        public Criteria andPaperHeightLike(String value) {
            addCriterion("paper_height like", value, "paperHeight");
            return (Criteria) this;
        }

        public Criteria andPaperHeightNotLike(String value) {
            addCriterion("paper_height not like", value, "paperHeight");
            return (Criteria) this;
        }

        public Criteria andPaperHeightIn(List<String> values) {
            addCriterion("paper_height in", values, "paperHeight");
            return (Criteria) this;
        }

        public Criteria andPaperHeightNotIn(List<String> values) {
            addCriterion("paper_height not in", values, "paperHeight");
            return (Criteria) this;
        }

        public Criteria andPaperHeightBetween(String value1, String value2) {
            addCriterion("paper_height between", value1, value2, "paperHeight");
            return (Criteria) this;
        }

        public Criteria andPaperHeightNotBetween(String value1, String value2) {
            addCriterion("paper_height not between", value1, value2, "paperHeight");
            return (Criteria) this;
        }

        public Criteria andPaperWeightIsNull() {
            addCriterion("paper_weight is null");
            return (Criteria) this;
        }

        public Criteria andPaperWeightIsNotNull() {
            addCriterion("paper_weight is not null");
            return (Criteria) this;
        }

        public Criteria andPaperWeightEqualTo(String value) {
            addCriterion("paper_weight =", value, "paperWeight");
            return (Criteria) this;
        }

        public Criteria andPaperWeightNotEqualTo(String value) {
            addCriterion("paper_weight <>", value, "paperWeight");
            return (Criteria) this;
        }

        public Criteria andPaperWeightGreaterThan(String value) {
            addCriterion("paper_weight >", value, "paperWeight");
            return (Criteria) this;
        }

        public Criteria andPaperWeightGreaterThanOrEqualTo(String value) {
            addCriterion("paper_weight >=", value, "paperWeight");
            return (Criteria) this;
        }

        public Criteria andPaperWeightLessThan(String value) {
            addCriterion("paper_weight <", value, "paperWeight");
            return (Criteria) this;
        }

        public Criteria andPaperWeightLessThanOrEqualTo(String value) {
            addCriterion("paper_weight <=", value, "paperWeight");
            return (Criteria) this;
        }

        public Criteria andPaperWeightLike(String value) {
            addCriterion("paper_weight like", value, "paperWeight");
            return (Criteria) this;
        }

        public Criteria andPaperWeightNotLike(String value) {
            addCriterion("paper_weight not like", value, "paperWeight");
            return (Criteria) this;
        }

        public Criteria andPaperWeightIn(List<String> values) {
            addCriterion("paper_weight in", values, "paperWeight");
            return (Criteria) this;
        }

        public Criteria andPaperWeightNotIn(List<String> values) {
            addCriterion("paper_weight not in", values, "paperWeight");
            return (Criteria) this;
        }

        public Criteria andPaperWeightBetween(String value1, String value2) {
            addCriterion("paper_weight between", value1, value2, "paperWeight");
            return (Criteria) this;
        }

        public Criteria andPaperWeightNotBetween(String value1, String value2) {
            addCriterion("paper_weight not between", value1, value2, "paperWeight");
            return (Criteria) this;
        }

        public Criteria andRowCountIsNull() {
            addCriterion("row_count is null");
            return (Criteria) this;
        }

        public Criteria andRowCountIsNotNull() {
            addCriterion("row_count is not null");
            return (Criteria) this;
        }

        public Criteria andRowCountEqualTo(Integer value) {
            addCriterion("row_count =", value, "rowCount");
            return (Criteria) this;
        }

        public Criteria andRowCountNotEqualTo(Integer value) {
            addCriterion("row_count <>", value, "rowCount");
            return (Criteria) this;
        }

        public Criteria andRowCountGreaterThan(Integer value) {
            addCriterion("row_count >", value, "rowCount");
            return (Criteria) this;
        }

        public Criteria andRowCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("row_count >=", value, "rowCount");
            return (Criteria) this;
        }

        public Criteria andRowCountLessThan(Integer value) {
            addCriterion("row_count <", value, "rowCount");
            return (Criteria) this;
        }

        public Criteria andRowCountLessThanOrEqualTo(Integer value) {
            addCriterion("row_count <=", value, "rowCount");
            return (Criteria) this;
        }

        public Criteria andRowCountIn(List<Integer> values) {
            addCriterion("row_count in", values, "rowCount");
            return (Criteria) this;
        }

        public Criteria andRowCountNotIn(List<Integer> values) {
            addCriterion("row_count not in", values, "rowCount");
            return (Criteria) this;
        }

        public Criteria andRowCountBetween(Integer value1, Integer value2) {
            addCriterion("row_count between", value1, value2, "rowCount");
            return (Criteria) this;
        }

        public Criteria andRowCountNotBetween(Integer value1, Integer value2) {
            addCriterion("row_count not between", value1, value2, "rowCount");
            return (Criteria) this;
        }

        public Criteria andColumnCountIsNull() {
            addCriterion("column_count is null");
            return (Criteria) this;
        }

        public Criteria andColumnCountIsNotNull() {
            addCriterion("column_count is not null");
            return (Criteria) this;
        }

        public Criteria andColumnCountEqualTo(Integer value) {
            addCriterion("column_count =", value, "columnCount");
            return (Criteria) this;
        }

        public Criteria andColumnCountNotEqualTo(Integer value) {
            addCriterion("column_count <>", value, "columnCount");
            return (Criteria) this;
        }

        public Criteria andColumnCountGreaterThan(Integer value) {
            addCriterion("column_count >", value, "columnCount");
            return (Criteria) this;
        }

        public Criteria andColumnCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("column_count >=", value, "columnCount");
            return (Criteria) this;
        }

        public Criteria andColumnCountLessThan(Integer value) {
            addCriterion("column_count <", value, "columnCount");
            return (Criteria) this;
        }

        public Criteria andColumnCountLessThanOrEqualTo(Integer value) {
            addCriterion("column_count <=", value, "columnCount");
            return (Criteria) this;
        }

        public Criteria andColumnCountIn(List<Integer> values) {
            addCriterion("column_count in", values, "columnCount");
            return (Criteria) this;
        }

        public Criteria andColumnCountNotIn(List<Integer> values) {
            addCriterion("column_count not in", values, "columnCount");
            return (Criteria) this;
        }

        public Criteria andColumnCountBetween(Integer value1, Integer value2) {
            addCriterion("column_count between", value1, value2, "columnCount");
            return (Criteria) this;
        }

        public Criteria andColumnCountNotBetween(Integer value1, Integer value2) {
            addCriterion("column_count not between", value1, value2, "columnCount");
            return (Criteria) this;
        }

        public Criteria andFontSizeIsNull() {
            addCriterion("font_size is null");
            return (Criteria) this;
        }

        public Criteria andFontSizeIsNotNull() {
            addCriterion("font_size is not null");
            return (Criteria) this;
        }

        public Criteria andFontSizeEqualTo(String value) {
            addCriterion("font_size =", value, "fontSize");
            return (Criteria) this;
        }

        public Criteria andFontSizeNotEqualTo(String value) {
            addCriterion("font_size <>", value, "fontSize");
            return (Criteria) this;
        }

        public Criteria andFontSizeGreaterThan(String value) {
            addCriterion("font_size >", value, "fontSize");
            return (Criteria) this;
        }

        public Criteria andFontSizeGreaterThanOrEqualTo(String value) {
            addCriterion("font_size >=", value, "fontSize");
            return (Criteria) this;
        }

        public Criteria andFontSizeLessThan(String value) {
            addCriterion("font_size <", value, "fontSize");
            return (Criteria) this;
        }

        public Criteria andFontSizeLessThanOrEqualTo(String value) {
            addCriterion("font_size <=", value, "fontSize");
            return (Criteria) this;
        }

        public Criteria andFontSizeLike(String value) {
            addCriterion("font_size like", value, "fontSize");
            return (Criteria) this;
        }

        public Criteria andFontSizeNotLike(String value) {
            addCriterion("font_size not like", value, "fontSize");
            return (Criteria) this;
        }

        public Criteria andFontSizeIn(List<String> values) {
            addCriterion("font_size in", values, "fontSize");
            return (Criteria) this;
        }

        public Criteria andFontSizeNotIn(List<String> values) {
            addCriterion("font_size not in", values, "fontSize");
            return (Criteria) this;
        }

        public Criteria andFontSizeBetween(String value1, String value2) {
            addCriterion("font_size between", value1, value2, "fontSize");
            return (Criteria) this;
        }

        public Criteria andFontSizeNotBetween(String value1, String value2) {
            addCriterion("font_size not between", value1, value2, "fontSize");
            return (Criteria) this;
        }

        public Criteria andFontThickIsNull() {
            addCriterion("font_thick is null");
            return (Criteria) this;
        }

        public Criteria andFontThickIsNotNull() {
            addCriterion("font_thick is not null");
            return (Criteria) this;
        }

        public Criteria andFontThickEqualTo(String value) {
            addCriterion("font_thick =", value, "fontThick");
            return (Criteria) this;
        }

        public Criteria andFontThickNotEqualTo(String value) {
            addCriterion("font_thick <>", value, "fontThick");
            return (Criteria) this;
        }

        public Criteria andFontThickGreaterThan(String value) {
            addCriterion("font_thick >", value, "fontThick");
            return (Criteria) this;
        }

        public Criteria andFontThickGreaterThanOrEqualTo(String value) {
            addCriterion("font_thick >=", value, "fontThick");
            return (Criteria) this;
        }

        public Criteria andFontThickLessThan(String value) {
            addCriterion("font_thick <", value, "fontThick");
            return (Criteria) this;
        }

        public Criteria andFontThickLessThanOrEqualTo(String value) {
            addCriterion("font_thick <=", value, "fontThick");
            return (Criteria) this;
        }

        public Criteria andFontThickLike(String value) {
            addCriterion("font_thick like", value, "fontThick");
            return (Criteria) this;
        }

        public Criteria andFontThickNotLike(String value) {
            addCriterion("font_thick not like", value, "fontThick");
            return (Criteria) this;
        }

        public Criteria andFontThickIn(List<String> values) {
            addCriterion("font_thick in", values, "fontThick");
            return (Criteria) this;
        }

        public Criteria andFontThickNotIn(List<String> values) {
            addCriterion("font_thick not in", values, "fontThick");
            return (Criteria) this;
        }

        public Criteria andFontThickBetween(String value1, String value2) {
            addCriterion("font_thick between", value1, value2, "fontThick");
            return (Criteria) this;
        }

        public Criteria andFontThickNotBetween(String value1, String value2) {
            addCriterion("font_thick not between", value1, value2, "fontThick");
            return (Criteria) this;
        }

        public Criteria andLableHeghtIsNull() {
            addCriterion("lable_heght is null");
            return (Criteria) this;
        }

        public Criteria andLableHeghtIsNotNull() {
            addCriterion("lable_heght is not null");
            return (Criteria) this;
        }

        public Criteria andLableHeghtEqualTo(String value) {
            addCriterion("lable_heght =", value, "lableHeght");
            return (Criteria) this;
        }

        public Criteria andLableHeghtNotEqualTo(String value) {
            addCriterion("lable_heght <>", value, "lableHeght");
            return (Criteria) this;
        }

        public Criteria andLableHeghtGreaterThan(String value) {
            addCriterion("lable_heght >", value, "lableHeght");
            return (Criteria) this;
        }

        public Criteria andLableHeghtGreaterThanOrEqualTo(String value) {
            addCriterion("lable_heght >=", value, "lableHeght");
            return (Criteria) this;
        }

        public Criteria andLableHeghtLessThan(String value) {
            addCriterion("lable_heght <", value, "lableHeght");
            return (Criteria) this;
        }

        public Criteria andLableHeghtLessThanOrEqualTo(String value) {
            addCriterion("lable_heght <=", value, "lableHeght");
            return (Criteria) this;
        }

        public Criteria andLableHeghtLike(String value) {
            addCriterion("lable_heght like", value, "lableHeght");
            return (Criteria) this;
        }

        public Criteria andLableHeghtNotLike(String value) {
            addCriterion("lable_heght not like", value, "lableHeght");
            return (Criteria) this;
        }

        public Criteria andLableHeghtIn(List<String> values) {
            addCriterion("lable_heght in", values, "lableHeght");
            return (Criteria) this;
        }

        public Criteria andLableHeghtNotIn(List<String> values) {
            addCriterion("lable_heght not in", values, "lableHeght");
            return (Criteria) this;
        }

        public Criteria andLableHeghtBetween(String value1, String value2) {
            addCriterion("lable_heght between", value1, value2, "lableHeght");
            return (Criteria) this;
        }

        public Criteria andLableHeghtNotBetween(String value1, String value2) {
            addCriterion("lable_heght not between", value1, value2, "lableHeght");
            return (Criteria) this;
        }

        public Criteria andLableWeightIsNull() {
            addCriterion("lable_weight is null");
            return (Criteria) this;
        }

        public Criteria andLableWeightIsNotNull() {
            addCriterion("lable_weight is not null");
            return (Criteria) this;
        }

        public Criteria andLableWeightEqualTo(String value) {
            addCriterion("lable_weight =", value, "lableWeight");
            return (Criteria) this;
        }

        public Criteria andLableWeightNotEqualTo(String value) {
            addCriterion("lable_weight <>", value, "lableWeight");
            return (Criteria) this;
        }

        public Criteria andLableWeightGreaterThan(String value) {
            addCriterion("lable_weight >", value, "lableWeight");
            return (Criteria) this;
        }

        public Criteria andLableWeightGreaterThanOrEqualTo(String value) {
            addCriterion("lable_weight >=", value, "lableWeight");
            return (Criteria) this;
        }

        public Criteria andLableWeightLessThan(String value) {
            addCriterion("lable_weight <", value, "lableWeight");
            return (Criteria) this;
        }

        public Criteria andLableWeightLessThanOrEqualTo(String value) {
            addCriterion("lable_weight <=", value, "lableWeight");
            return (Criteria) this;
        }

        public Criteria andLableWeightLike(String value) {
            addCriterion("lable_weight like", value, "lableWeight");
            return (Criteria) this;
        }

        public Criteria andLableWeightNotLike(String value) {
            addCriterion("lable_weight not like", value, "lableWeight");
            return (Criteria) this;
        }

        public Criteria andLableWeightIn(List<String> values) {
            addCriterion("lable_weight in", values, "lableWeight");
            return (Criteria) this;
        }

        public Criteria andLableWeightNotIn(List<String> values) {
            addCriterion("lable_weight not in", values, "lableWeight");
            return (Criteria) this;
        }

        public Criteria andLableWeightBetween(String value1, String value2) {
            addCriterion("lable_weight between", value1, value2, "lableWeight");
            return (Criteria) this;
        }

        public Criteria andLableWeightNotBetween(String value1, String value2) {
            addCriterion("lable_weight not between", value1, value2, "lableWeight");
            return (Criteria) this;
        }

        public Criteria andLeftSpaceIsNull() {
            addCriterion("left_space is null");
            return (Criteria) this;
        }

        public Criteria andLeftSpaceIsNotNull() {
            addCriterion("left_space is not null");
            return (Criteria) this;
        }

        public Criteria andLeftSpaceEqualTo(String value) {
            addCriterion("left_space =", value, "leftSpace");
            return (Criteria) this;
        }

        public Criteria andLeftSpaceNotEqualTo(String value) {
            addCriterion("left_space <>", value, "leftSpace");
            return (Criteria) this;
        }

        public Criteria andLeftSpaceGreaterThan(String value) {
            addCriterion("left_space >", value, "leftSpace");
            return (Criteria) this;
        }

        public Criteria andLeftSpaceGreaterThanOrEqualTo(String value) {
            addCriterion("left_space >=", value, "leftSpace");
            return (Criteria) this;
        }

        public Criteria andLeftSpaceLessThan(String value) {
            addCriterion("left_space <", value, "leftSpace");
            return (Criteria) this;
        }

        public Criteria andLeftSpaceLessThanOrEqualTo(String value) {
            addCriterion("left_space <=", value, "leftSpace");
            return (Criteria) this;
        }

        public Criteria andLeftSpaceLike(String value) {
            addCriterion("left_space like", value, "leftSpace");
            return (Criteria) this;
        }

        public Criteria andLeftSpaceNotLike(String value) {
            addCriterion("left_space not like", value, "leftSpace");
            return (Criteria) this;
        }

        public Criteria andLeftSpaceIn(List<String> values) {
            addCriterion("left_space in", values, "leftSpace");
            return (Criteria) this;
        }

        public Criteria andLeftSpaceNotIn(List<String> values) {
            addCriterion("left_space not in", values, "leftSpace");
            return (Criteria) this;
        }

        public Criteria andLeftSpaceBetween(String value1, String value2) {
            addCriterion("left_space between", value1, value2, "leftSpace");
            return (Criteria) this;
        }

        public Criteria andLeftSpaceNotBetween(String value1, String value2) {
            addCriterion("left_space not between", value1, value2, "leftSpace");
            return (Criteria) this;
        }

        public Criteria andTopSpaceIsNull() {
            addCriterion("top_space is null");
            return (Criteria) this;
        }

        public Criteria andTopSpaceIsNotNull() {
            addCriterion("top_space is not null");
            return (Criteria) this;
        }

        public Criteria andTopSpaceEqualTo(String value) {
            addCriterion("top_space =", value, "topSpace");
            return (Criteria) this;
        }

        public Criteria andTopSpaceNotEqualTo(String value) {
            addCriterion("top_space <>", value, "topSpace");
            return (Criteria) this;
        }

        public Criteria andTopSpaceGreaterThan(String value) {
            addCriterion("top_space >", value, "topSpace");
            return (Criteria) this;
        }

        public Criteria andTopSpaceGreaterThanOrEqualTo(String value) {
            addCriterion("top_space >=", value, "topSpace");
            return (Criteria) this;
        }

        public Criteria andTopSpaceLessThan(String value) {
            addCriterion("top_space <", value, "topSpace");
            return (Criteria) this;
        }

        public Criteria andTopSpaceLessThanOrEqualTo(String value) {
            addCriterion("top_space <=", value, "topSpace");
            return (Criteria) this;
        }

        public Criteria andTopSpaceLike(String value) {
            addCriterion("top_space like", value, "topSpace");
            return (Criteria) this;
        }

        public Criteria andTopSpaceNotLike(String value) {
            addCriterion("top_space not like", value, "topSpace");
            return (Criteria) this;
        }

        public Criteria andTopSpaceIn(List<String> values) {
            addCriterion("top_space in", values, "topSpace");
            return (Criteria) this;
        }

        public Criteria andTopSpaceNotIn(List<String> values) {
            addCriterion("top_space not in", values, "topSpace");
            return (Criteria) this;
        }

        public Criteria andTopSpaceBetween(String value1, String value2) {
            addCriterion("top_space between", value1, value2, "topSpace");
            return (Criteria) this;
        }

        public Criteria andTopSpaceNotBetween(String value1, String value2) {
            addCriterion("top_space not between", value1, value2, "topSpace");
            return (Criteria) this;
        }

        public Criteria andAlignmentIsNull() {
            addCriterion("alignment is null");
            return (Criteria) this;
        }

        public Criteria andAlignmentIsNotNull() {
            addCriterion("alignment is not null");
            return (Criteria) this;
        }

        public Criteria andAlignmentEqualTo(String value) {
            addCriterion("alignment =", value, "alignment");
            return (Criteria) this;
        }

        public Criteria andAlignmentNotEqualTo(String value) {
            addCriterion("alignment <>", value, "alignment");
            return (Criteria) this;
        }

        public Criteria andAlignmentGreaterThan(String value) {
            addCriterion("alignment >", value, "alignment");
            return (Criteria) this;
        }

        public Criteria andAlignmentGreaterThanOrEqualTo(String value) {
            addCriterion("alignment >=", value, "alignment");
            return (Criteria) this;
        }

        public Criteria andAlignmentLessThan(String value) {
            addCriterion("alignment <", value, "alignment");
            return (Criteria) this;
        }

        public Criteria andAlignmentLessThanOrEqualTo(String value) {
            addCriterion("alignment <=", value, "alignment");
            return (Criteria) this;
        }

        public Criteria andAlignmentLike(String value) {
            addCriterion("alignment like", value, "alignment");
            return (Criteria) this;
        }

        public Criteria andAlignmentNotLike(String value) {
            addCriterion("alignment not like", value, "alignment");
            return (Criteria) this;
        }

        public Criteria andAlignmentIn(List<String> values) {
            addCriterion("alignment in", values, "alignment");
            return (Criteria) this;
        }

        public Criteria andAlignmentNotIn(List<String> values) {
            addCriterion("alignment not in", values, "alignment");
            return (Criteria) this;
        }

        public Criteria andAlignmentBetween(String value1, String value2) {
            addCriterion("alignment between", value1, value2, "alignment");
            return (Criteria) this;
        }

        public Criteria andAlignmentNotBetween(String value1, String value2) {
            addCriterion("alignment not between", value1, value2, "alignment");
            return (Criteria) this;
        }

        public Criteria andRepeatNumIsNull() {
            addCriterion("repeat_num is null");
            return (Criteria) this;
        }

        public Criteria andRepeatNumIsNotNull() {
            addCriterion("repeat_num is not null");
            return (Criteria) this;
        }

        public Criteria andRepeatNumEqualTo(String value) {
            addCriterion("repeat_num =", value, "repeatNum");
            return (Criteria) this;
        }

        public Criteria andRepeatNumNotEqualTo(String value) {
            addCriterion("repeat_num <>", value, "repeatNum");
            return (Criteria) this;
        }

        public Criteria andRepeatNumGreaterThan(String value) {
            addCriterion("repeat_num >", value, "repeatNum");
            return (Criteria) this;
        }

        public Criteria andRepeatNumGreaterThanOrEqualTo(String value) {
            addCriterion("repeat_num >=", value, "repeatNum");
            return (Criteria) this;
        }

        public Criteria andRepeatNumLessThan(String value) {
            addCriterion("repeat_num <", value, "repeatNum");
            return (Criteria) this;
        }

        public Criteria andRepeatNumLessThanOrEqualTo(String value) {
            addCriterion("repeat_num <=", value, "repeatNum");
            return (Criteria) this;
        }

        public Criteria andRepeatNumLike(String value) {
            addCriterion("repeat_num like", value, "repeatNum");
            return (Criteria) this;
        }

        public Criteria andRepeatNumNotLike(String value) {
            addCriterion("repeat_num not like", value, "repeatNum");
            return (Criteria) this;
        }

        public Criteria andRepeatNumIn(List<String> values) {
            addCriterion("repeat_num in", values, "repeatNum");
            return (Criteria) this;
        }

        public Criteria andRepeatNumNotIn(List<String> values) {
            addCriterion("repeat_num not in", values, "repeatNum");
            return (Criteria) this;
        }

        public Criteria andRepeatNumBetween(String value1, String value2) {
            addCriterion("repeat_num between", value1, value2, "repeatNum");
            return (Criteria) this;
        }

        public Criteria andRepeatNumNotBetween(String value1, String value2) {
            addCriterion("repeat_num not between", value1, value2, "repeatNum");
            return (Criteria) this;
        }

        public Criteria andOneRowIsNull() {
            addCriterion("one_row is null");
            return (Criteria) this;
        }

        public Criteria andOneRowIsNotNull() {
            addCriterion("one_row is not null");
            return (Criteria) this;
        }

        public Criteria andOneRowEqualTo(String value) {
            addCriterion("one_row =", value, "oneRow");
            return (Criteria) this;
        }

        public Criteria andOneRowNotEqualTo(String value) {
            addCriterion("one_row <>", value, "oneRow");
            return (Criteria) this;
        }

        public Criteria andOneRowGreaterThan(String value) {
            addCriterion("one_row >", value, "oneRow");
            return (Criteria) this;
        }

        public Criteria andOneRowGreaterThanOrEqualTo(String value) {
            addCriterion("one_row >=", value, "oneRow");
            return (Criteria) this;
        }

        public Criteria andOneRowLessThan(String value) {
            addCriterion("one_row <", value, "oneRow");
            return (Criteria) this;
        }

        public Criteria andOneRowLessThanOrEqualTo(String value) {
            addCriterion("one_row <=", value, "oneRow");
            return (Criteria) this;
        }

        public Criteria andOneRowLike(String value) {
            addCriterion("one_row like", value, "oneRow");
            return (Criteria) this;
        }

        public Criteria andOneRowNotLike(String value) {
            addCriterion("one_row not like", value, "oneRow");
            return (Criteria) this;
        }

        public Criteria andOneRowIn(List<String> values) {
            addCriterion("one_row in", values, "oneRow");
            return (Criteria) this;
        }

        public Criteria andOneRowNotIn(List<String> values) {
            addCriterion("one_row not in", values, "oneRow");
            return (Criteria) this;
        }

        public Criteria andOneRowBetween(String value1, String value2) {
            addCriterion("one_row between", value1, value2, "oneRow");
            return (Criteria) this;
        }

        public Criteria andOneRowNotBetween(String value1, String value2) {
            addCriterion("one_row not between", value1, value2, "oneRow");
            return (Criteria) this;
        }

        public Criteria andTwoRowIsNull() {
            addCriterion("two_row is null");
            return (Criteria) this;
        }

        public Criteria andTwoRowIsNotNull() {
            addCriterion("two_row is not null");
            return (Criteria) this;
        }

        public Criteria andTwoRowEqualTo(String value) {
            addCriterion("two_row =", value, "twoRow");
            return (Criteria) this;
        }

        public Criteria andTwoRowNotEqualTo(String value) {
            addCriterion("two_row <>", value, "twoRow");
            return (Criteria) this;
        }

        public Criteria andTwoRowGreaterThan(String value) {
            addCriterion("two_row >", value, "twoRow");
            return (Criteria) this;
        }

        public Criteria andTwoRowGreaterThanOrEqualTo(String value) {
            addCriterion("two_row >=", value, "twoRow");
            return (Criteria) this;
        }

        public Criteria andTwoRowLessThan(String value) {
            addCriterion("two_row <", value, "twoRow");
            return (Criteria) this;
        }

        public Criteria andTwoRowLessThanOrEqualTo(String value) {
            addCriterion("two_row <=", value, "twoRow");
            return (Criteria) this;
        }

        public Criteria andTwoRowLike(String value) {
            addCriterion("two_row like", value, "twoRow");
            return (Criteria) this;
        }

        public Criteria andTwoRowNotLike(String value) {
            addCriterion("two_row not like", value, "twoRow");
            return (Criteria) this;
        }

        public Criteria andTwoRowIn(List<String> values) {
            addCriterion("two_row in", values, "twoRow");
            return (Criteria) this;
        }

        public Criteria andTwoRowNotIn(List<String> values) {
            addCriterion("two_row not in", values, "twoRow");
            return (Criteria) this;
        }

        public Criteria andTwoRowBetween(String value1, String value2) {
            addCriterion("two_row between", value1, value2, "twoRow");
            return (Criteria) this;
        }

        public Criteria andTwoRowNotBetween(String value1, String value2) {
            addCriterion("two_row not between", value1, value2, "twoRow");
            return (Criteria) this;
        }

        public Criteria andThreeRowIsNull() {
            addCriterion("three_row is null");
            return (Criteria) this;
        }

        public Criteria andThreeRowIsNotNull() {
            addCriterion("three_row is not null");
            return (Criteria) this;
        }

        public Criteria andThreeRowEqualTo(String value) {
            addCriterion("three_row =", value, "threeRow");
            return (Criteria) this;
        }

        public Criteria andThreeRowNotEqualTo(String value) {
            addCriterion("three_row <>", value, "threeRow");
            return (Criteria) this;
        }

        public Criteria andThreeRowGreaterThan(String value) {
            addCriterion("three_row >", value, "threeRow");
            return (Criteria) this;
        }

        public Criteria andThreeRowGreaterThanOrEqualTo(String value) {
            addCriterion("three_row >=", value, "threeRow");
            return (Criteria) this;
        }

        public Criteria andThreeRowLessThan(String value) {
            addCriterion("three_row <", value, "threeRow");
            return (Criteria) this;
        }

        public Criteria andThreeRowLessThanOrEqualTo(String value) {
            addCriterion("three_row <=", value, "threeRow");
            return (Criteria) this;
        }

        public Criteria andThreeRowLike(String value) {
            addCriterion("three_row like", value, "threeRow");
            return (Criteria) this;
        }

        public Criteria andThreeRowNotLike(String value) {
            addCriterion("three_row not like", value, "threeRow");
            return (Criteria) this;
        }

        public Criteria andThreeRowIn(List<String> values) {
            addCriterion("three_row in", values, "threeRow");
            return (Criteria) this;
        }

        public Criteria andThreeRowNotIn(List<String> values) {
            addCriterion("three_row not in", values, "threeRow");
            return (Criteria) this;
        }

        public Criteria andThreeRowBetween(String value1, String value2) {
            addCriterion("three_row between", value1, value2, "threeRow");
            return (Criteria) this;
        }

        public Criteria andThreeRowNotBetween(String value1, String value2) {
            addCriterion("three_row not between", value1, value2, "threeRow");
            return (Criteria) this;
        }

        public Criteria andFourRowIsNull() {
            addCriterion("four_row is null");
            return (Criteria) this;
        }

        public Criteria andFourRowIsNotNull() {
            addCriterion("four_row is not null");
            return (Criteria) this;
        }

        public Criteria andFourRowEqualTo(String value) {
            addCriterion("four_row =", value, "fourRow");
            return (Criteria) this;
        }

        public Criteria andFourRowNotEqualTo(String value) {
            addCriterion("four_row <>", value, "fourRow");
            return (Criteria) this;
        }

        public Criteria andFourRowGreaterThan(String value) {
            addCriterion("four_row >", value, "fourRow");
            return (Criteria) this;
        }

        public Criteria andFourRowGreaterThanOrEqualTo(String value) {
            addCriterion("four_row >=", value, "fourRow");
            return (Criteria) this;
        }

        public Criteria andFourRowLessThan(String value) {
            addCriterion("four_row <", value, "fourRow");
            return (Criteria) this;
        }

        public Criteria andFourRowLessThanOrEqualTo(String value) {
            addCriterion("four_row <=", value, "fourRow");
            return (Criteria) this;
        }

        public Criteria andFourRowLike(String value) {
            addCriterion("four_row like", value, "fourRow");
            return (Criteria) this;
        }

        public Criteria andFourRowNotLike(String value) {
            addCriterion("four_row not like", value, "fourRow");
            return (Criteria) this;
        }

        public Criteria andFourRowIn(List<String> values) {
            addCriterion("four_row in", values, "fourRow");
            return (Criteria) this;
        }

        public Criteria andFourRowNotIn(List<String> values) {
            addCriterion("four_row not in", values, "fourRow");
            return (Criteria) this;
        }

        public Criteria andFourRowBetween(String value1, String value2) {
            addCriterion("four_row between", value1, value2, "fourRow");
            return (Criteria) this;
        }

        public Criteria andFourRowNotBetween(String value1, String value2) {
            addCriterion("four_row not between", value1, value2, "fourRow");
            return (Criteria) this;
        }

        public Criteria andFiveRowIsNull() {
            addCriterion("five_row is null");
            return (Criteria) this;
        }

        public Criteria andFiveRowIsNotNull() {
            addCriterion("five_row is not null");
            return (Criteria) this;
        }

        public Criteria andFiveRowEqualTo(String value) {
            addCriterion("five_row =", value, "fiveRow");
            return (Criteria) this;
        }

        public Criteria andFiveRowNotEqualTo(String value) {
            addCriterion("five_row <>", value, "fiveRow");
            return (Criteria) this;
        }

        public Criteria andFiveRowGreaterThan(String value) {
            addCriterion("five_row >", value, "fiveRow");
            return (Criteria) this;
        }

        public Criteria andFiveRowGreaterThanOrEqualTo(String value) {
            addCriterion("five_row >=", value, "fiveRow");
            return (Criteria) this;
        }

        public Criteria andFiveRowLessThan(String value) {
            addCriterion("five_row <", value, "fiveRow");
            return (Criteria) this;
        }

        public Criteria andFiveRowLessThanOrEqualTo(String value) {
            addCriterion("five_row <=", value, "fiveRow");
            return (Criteria) this;
        }

        public Criteria andFiveRowLike(String value) {
            addCriterion("five_row like", value, "fiveRow");
            return (Criteria) this;
        }

        public Criteria andFiveRowNotLike(String value) {
            addCriterion("five_row not like", value, "fiveRow");
            return (Criteria) this;
        }

        public Criteria andFiveRowIn(List<String> values) {
            addCriterion("five_row in", values, "fiveRow");
            return (Criteria) this;
        }

        public Criteria andFiveRowNotIn(List<String> values) {
            addCriterion("five_row not in", values, "fiveRow");
            return (Criteria) this;
        }

        public Criteria andFiveRowBetween(String value1, String value2) {
            addCriterion("five_row between", value1, value2, "fiveRow");
            return (Criteria) this;
        }

        public Criteria andFiveRowNotBetween(String value1, String value2) {
            addCriterion("five_row not between", value1, value2, "fiveRow");
            return (Criteria) this;
        }

        public Criteria andSixRowIsNull() {
            addCriterion("six_row is null");
            return (Criteria) this;
        }

        public Criteria andSixRowIsNotNull() {
            addCriterion("six_row is not null");
            return (Criteria) this;
        }

        public Criteria andSixRowEqualTo(String value) {
            addCriterion("six_row =", value, "sixRow");
            return (Criteria) this;
        }

        public Criteria andSixRowNotEqualTo(String value) {
            addCriterion("six_row <>", value, "sixRow");
            return (Criteria) this;
        }

        public Criteria andSixRowGreaterThan(String value) {
            addCriterion("six_row >", value, "sixRow");
            return (Criteria) this;
        }

        public Criteria andSixRowGreaterThanOrEqualTo(String value) {
            addCriterion("six_row >=", value, "sixRow");
            return (Criteria) this;
        }

        public Criteria andSixRowLessThan(String value) {
            addCriterion("six_row <", value, "sixRow");
            return (Criteria) this;
        }

        public Criteria andSixRowLessThanOrEqualTo(String value) {
            addCriterion("six_row <=", value, "sixRow");
            return (Criteria) this;
        }

        public Criteria andSixRowLike(String value) {
            addCriterion("six_row like", value, "sixRow");
            return (Criteria) this;
        }

        public Criteria andSixRowNotLike(String value) {
            addCriterion("six_row not like", value, "sixRow");
            return (Criteria) this;
        }

        public Criteria andSixRowIn(List<String> values) {
            addCriterion("six_row in", values, "sixRow");
            return (Criteria) this;
        }

        public Criteria andSixRowNotIn(List<String> values) {
            addCriterion("six_row not in", values, "sixRow");
            return (Criteria) this;
        }

        public Criteria andSixRowBetween(String value1, String value2) {
            addCriterion("six_row between", value1, value2, "sixRow");
            return (Criteria) this;
        }

        public Criteria andSixRowNotBetween(String value1, String value2) {
            addCriterion("six_row not between", value1, value2, "sixRow");
            return (Criteria) this;
        }

        public Criteria andSevenRowIsNull() {
            addCriterion("seven_row is null");
            return (Criteria) this;
        }

        public Criteria andSevenRowIsNotNull() {
            addCriterion("seven_row is not null");
            return (Criteria) this;
        }

        public Criteria andSevenRowEqualTo(String value) {
            addCriterion("seven_row =", value, "sevenRow");
            return (Criteria) this;
        }

        public Criteria andSevenRowNotEqualTo(String value) {
            addCriterion("seven_row <>", value, "sevenRow");
            return (Criteria) this;
        }

        public Criteria andSevenRowGreaterThan(String value) {
            addCriterion("seven_row >", value, "sevenRow");
            return (Criteria) this;
        }

        public Criteria andSevenRowGreaterThanOrEqualTo(String value) {
            addCriterion("seven_row >=", value, "sevenRow");
            return (Criteria) this;
        }

        public Criteria andSevenRowLessThan(String value) {
            addCriterion("seven_row <", value, "sevenRow");
            return (Criteria) this;
        }

        public Criteria andSevenRowLessThanOrEqualTo(String value) {
            addCriterion("seven_row <=", value, "sevenRow");
            return (Criteria) this;
        }

        public Criteria andSevenRowLike(String value) {
            addCriterion("seven_row like", value, "sevenRow");
            return (Criteria) this;
        }

        public Criteria andSevenRowNotLike(String value) {
            addCriterion("seven_row not like", value, "sevenRow");
            return (Criteria) this;
        }

        public Criteria andSevenRowIn(List<String> values) {
            addCriterion("seven_row in", values, "sevenRow");
            return (Criteria) this;
        }

        public Criteria andSevenRowNotIn(List<String> values) {
            addCriterion("seven_row not in", values, "sevenRow");
            return (Criteria) this;
        }

        public Criteria andSevenRowBetween(String value1, String value2) {
            addCriterion("seven_row between", value1, value2, "sevenRow");
            return (Criteria) this;
        }

        public Criteria andSevenRowNotBetween(String value1, String value2) {
            addCriterion("seven_row not between", value1, value2, "sevenRow");
            return (Criteria) this;
        }

        public Criteria andSpaceRowIsNull() {
            addCriterion("space_row is null");
            return (Criteria) this;
        }

        public Criteria andSpaceRowIsNotNull() {
            addCriterion("space_row is not null");
            return (Criteria) this;
        }

        public Criteria andSpaceRowEqualTo(String value) {
            addCriterion("space_row =", value, "spaceRow");
            return (Criteria) this;
        }

        public Criteria andSpaceRowNotEqualTo(String value) {
            addCriterion("space_row <>", value, "spaceRow");
            return (Criteria) this;
        }

        public Criteria andSpaceRowGreaterThan(String value) {
            addCriterion("space_row >", value, "spaceRow");
            return (Criteria) this;
        }

        public Criteria andSpaceRowGreaterThanOrEqualTo(String value) {
            addCriterion("space_row >=", value, "spaceRow");
            return (Criteria) this;
        }

        public Criteria andSpaceRowLessThan(String value) {
            addCriterion("space_row <", value, "spaceRow");
            return (Criteria) this;
        }

        public Criteria andSpaceRowLessThanOrEqualTo(String value) {
            addCriterion("space_row <=", value, "spaceRow");
            return (Criteria) this;
        }

        public Criteria andSpaceRowLike(String value) {
            addCriterion("space_row like", value, "spaceRow");
            return (Criteria) this;
        }

        public Criteria andSpaceRowNotLike(String value) {
            addCriterion("space_row not like", value, "spaceRow");
            return (Criteria) this;
        }

        public Criteria andSpaceRowIn(List<String> values) {
            addCriterion("space_row in", values, "spaceRow");
            return (Criteria) this;
        }

        public Criteria andSpaceRowNotIn(List<String> values) {
            addCriterion("space_row not in", values, "spaceRow");
            return (Criteria) this;
        }

        public Criteria andSpaceRowBetween(String value1, String value2) {
            addCriterion("space_row between", value1, value2, "spaceRow");
            return (Criteria) this;
        }

        public Criteria andSpaceRowNotBetween(String value1, String value2) {
            addCriterion("space_row not between", value1, value2, "spaceRow");
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