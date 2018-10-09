package com.szcti.lcloud.user.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 读者财经
 * </p>
 *
 * @author dw
 * @since 2018-07-24
 */
@TableName("t_reader_finance")
public class TReaderFinance extends Model<TReaderFinance> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 读者id
     */
    @TableField("reader_id")
    private Long readerId;
    /**
     * 读者证号
     */
    @TableField("reader_card_number")
    private String readerCardNumber;
    /**
     * 财经类型
     */
    private Integer type;
    /**
     * 财经类型名称
     */
    @TableField("type_name")
    private String typeName;
    /**
     * 日期
     */
    @TableField("finance_datetime")
    private Date financeDatetime;
    /**
     * 罚款金额
     */
    private Double forfeit;
    /**
     * 0.未缴纳； 1，已缴纳
     */
    private Integer status;
    /**
     * 书籍id
     */
    @TableField("book_id")
    private Long bookId;
    /**
     * 书籍名称
     */
    @TableField("book_name")
    private String bookName;
    /**
     * 书籍条码
     */
    private String barcode;
    /**
     * 创建日期
     */
    @TableField("create_date")
    private Date createDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReaderId() {
        return readerId;
    }

    public void setReaderId(Long readerId) {
        this.readerId = readerId;
    }

    public String getReaderCardNumber() {
        return readerCardNumber;
    }

    public void setReaderCardNumber(String readerCardNumber) {
        this.readerCardNumber = readerCardNumber;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Date getFinanceDatetime() {
        return financeDatetime;
    }

    public void setFinanceDatetime(Date financeDatetime) {
        this.financeDatetime = financeDatetime;
    }

    public Double getForfeit() {
        return forfeit;
    }

    public void setForfeit(Double forfeit) {
        this.forfeit = forfeit;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TReaderFinance{" +
        ", id=" + id +
        ", readerId=" + readerId +
        ", readerCardNumber=" + readerCardNumber +
        ", type=" + type +
        ", typeName=" + typeName +
        ", financeDatetime=" + financeDatetime +
        ", forfeit=" + forfeit +
        ", status=" + status +
        ", bookId=" + bookId +
        ", bookName=" + bookName +
        ", barcode=" + barcode +
        ", createDate=" + createDate +
        "}";
    }
}
