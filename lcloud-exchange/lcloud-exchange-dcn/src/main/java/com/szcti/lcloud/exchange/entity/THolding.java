package com.szcti.lcloud.exchange.entity;

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
 * @since 2018-07-10
 */
@TableName("t_holding")
public class THolding extends Model<THolding> {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 分类号
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
     * 所属馆id
     */
    private Long ownlib;
    /**
     * 当前所在的馆id
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
     * 书的来源
     */
    private Integer source;
    /**
     * 状态
     */
    private Integer status;
    private Integer recno;
    private Integer bookrecno;
    private String rowid;


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
        ", recno=" + recno +
        ", bookrecno=" + bookrecno +
        ", rowid=" + rowid +
        "}";
    }
}
