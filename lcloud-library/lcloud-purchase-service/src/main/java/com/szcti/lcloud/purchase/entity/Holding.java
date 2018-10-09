package com.szcti.lcloud.purchase.entity;

import java.util.Date;

public class Holding {
    private Long id;

    private String callNo;

    private Long bookId;

    private String barcode;

    private Long ownlib;

    private Long curlib;

    private Long shelf;

    private Date indate;

    private Float singleprice;

    private Float totalprice;

    private Integer source;

    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCallNo() {
        return callNo;
    }

    public void setCallNo(String callNo) {
        this.callNo = callNo == null ? null : callNo.trim();
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode == null ? null : barcode.trim();
    }

    public Long getOwnlib() {
        return ownlib;
    }

    public void setOwnlib(Long ownlib) {
        this.ownlib = ownlib;
    }

    public Long getCurlib() {
        return curlib;
    }

    public void setCurlib(Long curlib) {
        this.curlib = curlib;
    }

    public Long getShelf() {
        return shelf;
    }

    public void setShelf(Long shelf) {
        this.shelf = shelf;
    }

    public Date getIndate() {
        return indate;
    }

    public void setIndate(Date indate) {
        this.indate = indate;
    }

    public Float getSingleprice() {
        return singleprice;
    }

    public void setSingleprice(Float singleprice) {
        this.singleprice = singleprice;
    }

    public Float getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Float totalprice) {
        this.totalprice = totalprice;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}