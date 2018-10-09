package com.szcti.lcloud.circulate.entity;

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
 * @since 2018-08-01
 */
@TableName("t_prebook")
public class TPrebook extends Model<TPrebook> {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 所属订单标识
     */
    @TableField("order_id")
    private Long orderId;
    /**
     * 图片
     */
    private String pic;
    /**
     * 书名
     */
    private String title;
    /**
     * 作者
     */
    private String author;
    /**
     * 概要
     */
    private String summary;
    /**
     * 出版社
     */
    private String publisher;
    /**
     * 出版日期
     */
    private Date pubdate;
    /**
     * 书的分类
     */
    @TableField("book_type")
    private String bookType;
    /**
     * isbn
     */
    @TableField("ISBN")
    private String isbn;
    /**
     * 单价
     */
    private Float price;
    /**
     * 书页数
     */
    @TableField("pages")
    private Integer pages;
    /**
     * 书的开本
     */
    @TableField("book_size")
    private String bookSize;
    /**
     * 采访类别
     */
    @TableField("in_type")
    private Integer inType;
    /**
     * 0未购买，状态1购买中，2已入藏
     */
    private Integer status;
    /**
     * 创建者
     */
    @TableField("create_by")
    private Long createBy;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 1新华集团2本馆图书3豆瓣图书4成员馆图书
     */
    private Integer source;
    /**
     * 货物代码
     */
    @TableField("goods_code")
    private String goodsCode;
    /**
     * 入库时间
     */
    @TableField("storage_time")
    private Date storageTime;


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

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getPubdate() {
        return pubdate;
    }

    public void setPubdate(Date pubdate) {
        this.pubdate = pubdate;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
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

    public Integer getInType() {
        return inType;
    }

    public void setInType(Integer inType) {
        this.inType = inType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public Date getStorageTime() {
        return storageTime;
    }

    public void setStorageTime(Date storageTime) {
        this.storageTime = storageTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TPrebook{" +
        ", id=" + id +
        ", orderId=" + orderId +
        ", pic=" + pic +
        ", title=" + title +
        ", author=" + author +
        ", summary=" + summary +
        ", publisher=" + publisher +
        ", pubdate=" + pubdate +
        ", bookType=" + bookType +
        ", isbn=" + isbn +
        ", price=" + price +
        ", pages=" + pages +
        ", bookSize=" + bookSize +
        ", inType=" + inType +
        ", status=" + status +
        ", createBy=" + createBy +
        ", createTime=" + createTime +
        ", source=" + source +
        ", goodsCode=" + goodsCode +
        ", storageTime=" + storageTime +
        "}";
    }
}
