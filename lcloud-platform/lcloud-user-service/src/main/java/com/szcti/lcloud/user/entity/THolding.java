package com.szcti.lcloud.user.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author dw
 * @since 2018-08-08
 */
@TableName("t_holding")
public class THolding extends Model<THolding> {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 索书号
     */
    @TableField("call_no")
    private String callNo;
    /**
     * 书id
     */
    @TableField("book_id")
    private Long bookId;
    /**
     * 图书馆条码
     */
    private String barcode;
    /**
     * 所属图书馆id
     */
    private Long ownlib;
    /**
     * 当前所在的图书馆id
     */
    private Long curlib;
    /**
     * 书架号
     */
    private Long shelf;
    /**
     * 入馆日期
     */
    private Date indate;
    /**
     * 单价
     */
    private Float singleprice;
    /**
     * 总价
     */
    private Float totalprice;
    /**
     * 获得方式1购买2捐赠3交换4自编
     */
    private Integer source;
    /**
     * 状态0可借阅,1在借中,2可阅览,3污损,4遗失,5剔除
     */
    private Integer status;
    /**
     * 文献流通类型:
     */
    @TableField("act_type")
    private String actType;
    /**
     * RFID号码
     */
    @TableField("RFID")
    private String rfid;
    /**
     * 图创id
     */
    private Integer recno;
    /**
     * 图创id
     */
    private Integer bookrecno;
    /**
     * 图创id
     */
    private String rowid;
    /**
     * 馆藏地点
     */
    @TableField("collAddress_id")
    private Long colladdressId;
    /**
     * 编目批次号
     */
    @TableField("catalog_batch")
    private String catalogBatch;
    /**
     * 条码分区号
     */
    private String partition;
    /**
     * 分册号
     */
    private String volume;


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
        this.callNo = callNo;
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
        this.barcode = barcode;
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
        this.rfid = rfid;
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
        this.rowid = rowid;
    }

    public Long getColladdressId() {
        return colladdressId;
    }

    public void setColladdressId(Long colladdressId) {
        this.colladdressId = colladdressId;
    }

    public String getCatalogBatch() {
        return catalogBatch;
    }

    public void setCatalogBatch(String catalogBatch) {
        this.catalogBatch = catalogBatch;
    }

    public String getPartition() {
        return partition;
    }

    public void setPartition(String partition) {
        this.partition = partition;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "THolding{" +
        ", id=" + id +
        ", callNo=" + callNo +
        ", bookId=" + bookId +
        ", barcode=" + barcode +
        ", ownlib=" + ownlib +
        ", curlib=" + curlib +
        ", shelf=" + shelf +
        ", indate=" + indate +
        ", singleprice=" + singleprice +
        ", totalprice=" + totalprice +
        ", source=" + source +
        ", status=" + status +
        ", actType=" + actType +
        ", rfid=" + rfid +
        ", recno=" + recno +
        ", bookrecno=" + bookrecno +
        ", rowid=" + rowid +
        ", colladdressId=" + colladdressId +
        ", catalogBatch=" + catalogBatch +
        ", partition=" + partition +
        ", volume=" + volume +
        "}";
    }
}
