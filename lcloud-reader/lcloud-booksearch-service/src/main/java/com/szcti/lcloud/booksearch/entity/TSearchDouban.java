package com.szcti.lcloud.booksearch.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 豆瓣搜索结果缓存
 * </p>
 *
 * @author dw
 * @since 2018-08-13
 */
@TableName("t_search_douban")
public class TSearchDouban extends Model<TSearchDouban> {

    private static final long serialVersionUID = 1L;

    /**
     * 豆瓣id
     */
    private Long id;
    /**
     * 书名
     */
    private String title;
    /**
     * isbn13
     */
    private String isbn13;
    /**
     * isbn10
     */
    private String isbn10;
    /**
     * 书信息
     */
    private String bookinfo;
    /**
     * 图像地址
     */
    private String image;
    /**
     * 本地图像地址
     */
    @TableField("local_image")
    private String localImage;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getBookinfo() {
        return bookinfo;
    }

    public void setBookinfo(String bookinfo) {
        this.bookinfo = bookinfo;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLocalImage() {
        return localImage;
    }

    public void setLocalImage(String localImage) {
        this.localImage = localImage;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TSearchDouban{" +
        ", id=" + id +
        ", title=" + title +
        ", isbn13=" + isbn13 +
        ", isbn10=" + isbn10 +
        ", bookinfo=" + bookinfo +
        ", image=" + image +
        ", localImage=" + localImage +
        ", createTime=" + createTime +
        "}";
    }
}
