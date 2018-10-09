package com.szcti.lcloud.purchase.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 订购单的图书
 * </p>
 *
 * @author dw
 * @since 2018-08-16
 */
@TableName("t_purchase_book")
public class TPurchaseBook extends Model<TPurchaseBook> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 订购单id
     */
    @TableField("order_id")
    private Long orderId;
    /**
     * 书库id
     */
    @TableField("book_id")
    private Long bookId;
    /**
     * 书的数量
     */
    @TableField("book_qty")
    private Integer bookQty;
    /**
     * 总额
     */
    @TableField("total_price")
    private Float totalPrice;
    /**
     * 采访书库id
     */
    @TableField("prebook_id")
    private Long prebookId;
    /**
     * 0待审核1通过2不通过3已提交4已发货5部分验收6验收完全7已到馆8退回
     */
    private Integer status;
    /**
     * 商品编号
     */
    @TableField("goods_code")
    private String goodsCode;
    /**
     * 审核结果
     */
    private String checkedmsg;
    /**
     * 单价
     */
    private Float price;
    /**
     * isbn
     */
    private String isbn;
    /**
     * 书名
     */
    private String title;
    /**
     * 作者
     */
    private String author;
    /**
     * 出版社
     */
    private String publisher;
    /**
     * 出版日期
     */
    private String pubdate;
    /**
     * 书的种类
     */
    @TableField("book_type")
    private String bookType;
    /**
     * 页数
     */
    private Integer pages;
    /**
     * 书的开本尺寸大小
     */
    @TableField("book_size")
    private String bookSize;
    /**
     * 来源
     */
    private Integer source;
    private String remark;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Integer getBookQty() {
        return bookQty;
    }

    public void setBookQty(Integer bookQty) {
        this.bookQty = bookQty;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getPrebookId() {
        return prebookId;
    }

    public void setPrebookId(Long prebookId) {
        this.prebookId = prebookId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getCheckedmsg() {
        return checkedmsg;
    }

    public void setCheckedmsg(String checkedmsg) {
        this.checkedmsg = checkedmsg;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getBookSize() {
        return bookSize;
    }

    public void setBookSize(String bookSize) {
        this.bookSize = bookSize;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TPurchaseBook{" +
        ", id=" + id +
        ", orderId=" + orderId +
        ", bookId=" + bookId +
        ", bookQty=" + bookQty +
        ", totalPrice=" + totalPrice +
        ", prebookId=" + prebookId +
        ", status=" + status +
        ", goodsCode=" + goodsCode +
        ", checkedmsg=" + checkedmsg +
        ", price=" + price +
        ", isbn=" + isbn +
        ", title=" + title +
        ", author=" + author +
        ", publisher=" + publisher +
        ", pubdate=" + pubdate +
        ", bookType=" + bookType +
        ", pages=" + pages +
        ", bookSize=" + bookSize +
        ", source=" + source +
        ", remark=" + remark +
        "}";
    }
}
