package com.szcti.lcloud.catalog.entity;

import java.util.Date;

public class Holding {
	private Long id;

	private String callNo;

	private Long bookId;

	private String barcode;

	private String prefix;// 条码前缀

	private String currUser;//条码使用者

	private Long ownlib;

	private Long curlib;

	private Long shelf;

	private Date indate;

	private Float singleprice;

	private Float totalprice;

	private String source;

	private String status;

	private String actType;

	private String rfid;

	private Integer recno;

	private Integer bookrecno;

	private String rowid;

	private String colladdressId;

	private String catalogBatch;

	private String partition;

	private String volume;

	private String remark;

	private String operation;// 操作 数据库并无此字段 前端调用删除操作此字段值为delete

	public String getOperation() {
		return this.operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

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

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getActType() {
		return actType;
	}

	public void setActType(String actType) {
		this.actType = actType;
	}

	public String getRfid() {
		return rfid;
	}

	public void setRfid(String rfid) {
		this.rfid = rfid == null ? null : rfid.trim();
	}

	public Integer getRecno() {
		return recno;
	}

	public void setRecno(Integer recno) {
		this.recno = recno;
	}

	public Integer getBookrecno() {
		return bookrecno;
	}

	public void setBookrecno(Integer bookrecno) {
		this.bookrecno = bookrecno;
	}

	public String getRowid() {
		return rowid;
	}

	public void setRowid(String rowid) {
		this.rowid = rowid == null ? null : rowid.trim();
	}

	public String getColladdressId() {
		return colladdressId;
	}

	public void setColladdressId(String colladdressId) {
		this.colladdressId = colladdressId;
	}

	public String getCatalogBatch() {
		return catalogBatch;
	}

	public void setCatalogBatch(String catalogBatch) {
		this.catalogBatch = catalogBatch == null ? null : catalogBatch.trim();
	}

	public String getPartition() {
		return partition;
	}

	public void setPartition(String partition) {
		this.partition = partition == null ? null : partition.trim();
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume == null ? null : volume.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getCurrUser() {
		return currUser;
	}

	public void setCurrUser(String currUser) {
		this.currUser = currUser;
	}
}