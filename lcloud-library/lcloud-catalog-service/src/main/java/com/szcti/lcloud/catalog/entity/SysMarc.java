package com.szcti.lcloud.catalog.entity;

import java.util.Date;

public class SysMarc {
    private Long id;

    private String description;

    private String name;

    private Byte designator;

    private Byte status;

    private Date createDate;

    private Long createBy;

    private Long libraryId;

    private String remarks;

    private String mustValues;

    private String selectValues;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Byte getDesignator() {
        return designator;
    }

    public void setDesignator(Byte designator) {
        this.designator = designator;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Long getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(Long libraryId) {
        this.libraryId = libraryId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getMustValues() {
        return mustValues;
    }

    public void setMustValues(String mustValues) {
        this.mustValues = mustValues == null ? null : mustValues.trim();
    }

    public String getSelectValues() {
        return selectValues;
    }

    public void setSelectValues(String selectValues) {
        this.selectValues = selectValues == null ? null : selectValues.trim();
    }
}