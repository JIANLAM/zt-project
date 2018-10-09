package com.szcti.lcloud.purchase.entity;

public class AcceptanceDetail {
    private Long id;

    private Long acceptanceId;

    private Long orderbatchBookId;

    private String purchaseCode;

    private Long purchaseBookId;

    private String isbn;

    private Long libraryId;

    private Integer shipQuantity;

    private Integer acceptQuantity;

    private Integer catalogQty;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAcceptanceId() {
        return acceptanceId;
    }

    public void setAcceptanceId(Long acceptanceId) {
        this.acceptanceId = acceptanceId;
    }

    public Long getOrderbatchBookId() {
        return orderbatchBookId;
    }

    public void setOrderbatchBookId(Long orderbatchBookId) {
        this.orderbatchBookId = orderbatchBookId;
    }

    public String getPurchaseCode() {
        return purchaseCode;
    }

    public void setPurchaseCode(String purchaseCode) {
        this.purchaseCode = purchaseCode == null ? null : purchaseCode.trim();
    }

    public Long getPurchaseBookId() {
        return purchaseBookId;
    }

    public void setPurchaseBookId(Long purchaseBookId) {
        this.purchaseBookId = purchaseBookId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn == null ? null : isbn.trim();
    }

    public Long getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(Long libraryId) {
        this.libraryId = libraryId;
    }

    public Integer getShipQuantity() {
        return shipQuantity;
    }

    public void setShipQuantity(Integer shipQuantity) {
        this.shipQuantity = shipQuantity;
    }

    public Integer getAcceptQuantity() {
        return acceptQuantity;
    }

    public void setAcceptQuantity(Integer acceptQuantity) {
        this.acceptQuantity = acceptQuantity;
    }

    public Integer getcatalogQty() {
        return catalogQty;
    }

    public void setcatalogQty(Integer catalogQty) {
        this.catalogQty = catalogQty;
    }
}