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
 * @since 2018-08-07
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
    /**
     * 从题名
     */
    @TableField("series_title")
    private String seriesTitle;
    /**
     * 副题名
     */
    @TableField("second_title")
    private String secondTitle;
    /**
     * 主题词
     */
    @TableField("subject_word")
    private String subjectWord;
    /**
     * 第一责任人
     */
    @TableField("first_duty")
    private String firstDuty;
    /**
     * 版次
     */
    private String revision;
    /**
     * 语种
     */
    private String language;
    /**
     * 载体类型:图书、报刊、录相带、光碟
     */
    @TableField("carrier_type")
    private String carrierType;
    /**
     * 装帧类型：平装、精装
     */
    private String binding;


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

    public String getSeriesTitle() {
        return seriesTitle;
    }

    public void setSeriesTitle(String seriesTitle) {
        this.seriesTitle = seriesTitle;
    }

    public String getSecondTitle() {
        return secondTitle;
    }

    public void setSecondTitle(String secondTitle) {
        this.secondTitle = secondTitle;
    }

    public String getSubjectWord() {
        return subjectWord;
    }

    public void setSubjectWord(String subjectWord) {
        this.subjectWord = subjectWord;
    }

    public String getFirstDuty() {
        return firstDuty;
    }

    public void setFirstDuty(String firstDuty) {
        this.firstDuty = firstDuty;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCarrierType() {
        return carrierType;
    }

    public void setCarrierType(String carrierType) {
        this.carrierType = carrierType;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
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
        ", isbn=" + isbn +
        ", price=" + price +
        ", createBy=" + createBy +
        ", createTime=" + createTime +
        ", pages=" + pages +
        ", bookrecno=" + bookrecno +
        ", rowid=" + rowid +
        ", seriesTitle=" + seriesTitle +
        ", secondTitle=" + secondTitle +
        ", subjectWord=" + subjectWord +
        ", firstDuty=" + firstDuty +
        ", revision=" + revision +
        ", language=" + language +
        ", carrierType=" + carrierType +
        ", binding=" + binding +
        "}";
    }
}
