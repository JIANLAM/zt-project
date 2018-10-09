package com.szcti.lcloud.catalog.entity;

public class MarcTemplateDetail {
    private Long id;

    private Long marcId;

    private String mainStart;

    private String secondStart;

    private String marcEnd;

    private String nameDefine;

    private String designatorDefine;

    private Integer orderIndex;

    private Long marcTemplateId;

    private String mustValue;

    private String selectValue;
    private String discriptionDef;
    private String allValue;


    public String getDiscriptionDef() {
        return discriptionDef;
    }

    public void setDiscriptionDef(String discriptionDef) {
        this.discriptionDef = discriptionDef;
    }


    public String getAllValue() {
        return allValue;
    }

    public void setAllValue(String allValue) {
        this.allValue = allValue;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMarcId() {
        return marcId;
    }

    public void setMarcId(Long marcId) {
        this.marcId = marcId;
    }

    public String getMainStart() {
        return mainStart;
    }

    public void setMainStart(String mainStart) {
        this.mainStart = mainStart == null ? null : mainStart.trim();
    }

    public String getSecondStart() {
        return secondStart;
    }

    public void setSecondStart(String secondStart) {
        this.secondStart = secondStart == null ? null : secondStart.trim();
    }

    public String getMarcEnd() {
        return marcEnd;
    }

    public void setMarcEnd(String marcEnd) {
        this.marcEnd = marcEnd == null ? null : marcEnd.trim();
    }

    public String getNameDefine() {
        return nameDefine;
    }

    public void setNameDefine(String nameDefine) {
        this.nameDefine = nameDefine == null ? null : nameDefine.trim();
    }

    public String getDesignatorDefine() {
        return designatorDefine;
    }

    public void setDesignatorDefine(String designatorDefine) {
        this.designatorDefine = designatorDefine == null ? null : designatorDefine.trim();
    }

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    public Long getMarcTemplateId() {
        return marcTemplateId;
    }

    public void setMarcTemplateId(Long marcTemplateId) {
        this.marcTemplateId = marcTemplateId;
    }

    public String getMustValue() {
        return mustValue;
    }

    public void setMustValue(String mustValue) {
        this.mustValue = mustValue == null ? null : mustValue.trim();
    }

    public String getSelectValue() {
        return selectValue;
    }

    public void setSelectValue(String selectValue) {
        this.selectValue = selectValue == null ? null : selectValue.trim();
    }
}