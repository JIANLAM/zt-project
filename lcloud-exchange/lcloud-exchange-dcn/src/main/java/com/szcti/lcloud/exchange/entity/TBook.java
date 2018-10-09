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
 * @since 2018-07-20
 */
@TableName("t_book")
public class TBook extends Model<TBook> {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 书封面
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
     * 书的类目
     */
    @TableField("book_type")
    private String bookType;
    /**
     * 主题
     */
    private String subject;
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
     * 页数
     */
    private Integer pages;
    /**
     * 图创id
     */
    private Integer bookrecno;
    /**
     * 图创id
     */
    private String rowid;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
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
        return "TBook{" +
        ", id=" + id +
        ", pic=" + pic +
        ", title=" + title +
        ", author=" + author +
        ", summary=" + summary +
        ", publisher=" + publisher +
        ", pubdate=" + pubdate +
        ", bookType=" + bookType +
        ", subject=" + subject +
        ", isbn=" + isbn +
        ", price=" + price +
        ", createBy=" + createBy +
        ", createTime=" + createTime +
        ", pages=" + pages +
        ", bookrecno=" + bookrecno +
        ", rowid=" + rowid +
        "}";
    }
}
