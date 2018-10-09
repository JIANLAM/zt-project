package com.szcti.lcloud.purchase.entity;

import java.util.Date;

public class OrderbatchBook {
    private Long id;

    private Long orderBatchId;

    private Long purchaseBookId;

    private Integer bookQty;

    private Integer checkedQty;

    private Long checker;

    private Date checkTime;

    private String status;

    private String checkmsg;

    private String remark;

    public String getIsHandIn() {
        return isHandIn;
    }

    public void setIsHandIn(String isHandIn) {
        this.isHandIn = isHandIn;
    }

    private String isHandIn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderBatchId() {
        return orderBatchId;
    }

    public void setOrderBatchId(Long orderBatchId) {
        this.orderBatchId = orderBatchId;
    }

    public Long getPurchaseBookId() {
        return purchaseBookId;
    }

    public void setPurchaseBookId(Long purchaseBookId) {
        this.purchaseBookId = purchaseBookId;
    }

    public Integer getBookQty() {
        return bookQty;
    }

    public void setBookQty(Integer bookQty) {
        this.bookQty = bookQty;
    }

    public Integer getCheckedQty() {
        return checkedQty;
    }

    public void setCheckedQty(Integer checkedQty) {
        this.checkedQty = checkedQty;
    }

    public Long getChecker() {
        return checker;
    }

    public void setChecker(Long checker) {
        this.checker = checker;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCheckmsg() {
        return checkmsg;
    }

    public void setCheckmsg(String checkmsg) {
        this.checkmsg = checkmsg == null ? null : checkmsg.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}