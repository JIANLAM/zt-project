package com.szcti.lcloud.purchase.entity;

import java.util.Date;

public class PurchaseRule {
    private Long id;

    private String ruleName;

    private String isUseStatus;

    public String getIsUseStatus() {
        return isUseStatus;
    }

    public void setIsUseStatus(String isUseStatus) {
        this.isUseStatus = isUseStatus;
    }

    private Long checker;

    private Date startTime;

    private Date endTime;

    private Long creator;

    private Date createTime;

    private Long budgetId;

    private String budgetPrice;

    private Integer status;

    private Integer duplicateQty;

    private String publishYear;

    private String categoryNo;

    private String bookPrice;

    private String bookSize;

    private String bookPages;

    private String publisher;

    private Long libraryId;

    private Integer publishYearAllow;

    private Integer categoryNoAllow;

    private Integer bookSizeAllow;

    private Integer bookPagesAllow;

    private Integer publisherAllow;

    private String prefix;

    private String totalnum;

    private String startnum;

    private String summary;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName == null ? null : ruleName.trim();
    }

    public Long getChecker() {
        return checker;
    }

    public void setChecker(Long checker) {
        this.checker = checker;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Long budgetId) {
        this.budgetId = budgetId;
    }

    public String getBudgetPrice() {
        return budgetPrice;
    }

    public void setBudgetPrice(String budgetPrice) {
        this.budgetPrice = budgetPrice == null ? null : budgetPrice.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDuplicateQty() {
        return duplicateQty;
    }

    public void setDuplicateQty(Integer duplicateQty) {
        this.duplicateQty = duplicateQty;
    }

    public String getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(String publishYear) {
        this.publishYear = publishYear == null ? null : publishYear.trim();
    }

    public String getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(String categoryNo) {
        this.categoryNo = categoryNo == null ? null : categoryNo.trim();
    }

    public String getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(String bookPrice) {
        this.bookPrice = bookPrice == null ? null : bookPrice.trim();
    }

    public String getBookSize() {
        return bookSize;
    }

    public void setBookSize(String bookSize) {
        this.bookSize = bookSize == null ? null : bookSize.trim();
    }

    public String getBookPages() {
        return bookPages;
    }

    public void setBookPages(String bookPages) {
        this.bookPages = bookPages == null ? null : bookPages.trim();
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher == null ? null : publisher.trim();
    }

    public Long getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(Long libraryId) {
        this.libraryId = libraryId;
    }

    public Integer getPublishYearAllow() {
        return publishYearAllow;
    }

    public void setPublishYearAllow(Integer publishYearAllow) {
        this.publishYearAllow = publishYearAllow;
    }

    public Integer getCategoryNoAllow() {
        return categoryNoAllow;
    }

    public void setCategoryNoAllow(Integer categoryNoAllow) {
        this.categoryNoAllow = categoryNoAllow;
    }

    public Integer getBookSizeAllow() {
        return bookSizeAllow;
    }

    public void setBookSizeAllow(Integer bookSizeAllow) {
        this.bookSizeAllow = bookSizeAllow;
    }

    public Integer getBookPagesAllow() {
        return bookPagesAllow;
    }

    public void setBookPagesAllow(Integer bookPagesAllow) {
        this.bookPagesAllow = bookPagesAllow;
    }

    public Integer getPublisherAllow() {
        return publisherAllow;
    }

    public void setPublisherAllow(Integer publisherAllow) {
        this.publisherAllow = publisherAllow;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix == null ? null : prefix.trim();
    }

    public String getTotalnum() {
        return totalnum;
    }

    public void setTotalnum(String totalnum) {
        this.totalnum = totalnum == null ? null : totalnum.trim();
    }

    public String getStartnum() {
        return startnum;
    }

    public void setStartnum(String startnum) {
        this.startnum = startnum == null ? null : startnum.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }
}