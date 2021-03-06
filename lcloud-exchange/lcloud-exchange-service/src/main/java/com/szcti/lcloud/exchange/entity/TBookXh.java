package com.szcti.lcloud.exchange.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * <p>
 * 同步过来的新华图书
 * </p>
 *
 * @author dw
 * @since 2018-08-23
 */
@TableName("t_book_xh")
public class TBookXh extends Model<TBookXh>
{
    
    private static final long serialVersionUID = 1L;
    
    @TableId(value = "id", type = IdType.AUTO)
    private String            id;
    
    private String            title;
    
    private String            pic;
    
    @TableField("pic_local")
    private String            picLocal;
    
    private String            author;
    
    private String            describe;
    
    private String            publisher;
    
    private String            pubdate;
    
    @TableField("book_type")
    private String            bookType;
    
    private String            isbn;
    
    private String            price;
    
    private Integer           pages;
    
    @TableField("key_word")
    private String            keyWord;
    
    private Date              createtime;
    
    private Date              modifytime;
    
    private String            book_size;
    
    private String            cip;
    
    private String            yddx;
    
    private String            sapcode;
    
    private String            nrty;
    
    public String getBook_size()
    {
        return book_size;
    }
    
    public void setBook_size(String book_size)
    {
        this.book_size = book_size;
    }
    
    public String getCip()
    {
        return cip;
    }
    
    public void setCip(String cip)
    {
        this.cip = cip;
    }
    
    public String getYddx()
    {
        return yddx;
    }
    
    public void setYddx(String yddx)
    {
        this.yddx = yddx;
    }
    
    public String getSapcode()
    {
        return sapcode;
    }
    
    public void setSapcode(String sapcode)
    {
        this.sapcode = sapcode;
    }
    
    public String getNrty()
    {
        return nrty;
    }
    
    public void setNrty(String nrty)
    {
        this.nrty = nrty;
    }
    
    public String getPrice()
    {
        return price;
    }
    
    public void setPrice(String price)
    {
        this.price = price;
    }
    
    public String getId()
    {
        return id;
    }
    
    public void setId(String id)
    {
        this.id = id;
    }
    
    public String getTitle()
    {
        return title;
    }
    
    public void setTitle(String title)
    {
        this.title = title;
    }
    
    public String getPic()
    {
        return pic;
    }
    
    public void setPic(String pic)
    {
        this.pic = pic;
    }
    
    public String getPicLocal()
    {
        return picLocal;
    }
    
    public void setPicLocal(String picLocal)
    {
        this.picLocal = picLocal;
    }
    
    public String getAuthor()
    {
        return author;
    }
    
    public void setAuthor(String author)
    {
        this.author = author;
    }
    
    public String getDescribe()
    {
        return describe;
    }
    
    public void setDescribe(String describe)
    {
        this.describe = describe;
    }
    
    public String getPublisher()
    {
        return publisher;
    }
    
    public void setPublisher(String publisher)
    {
        this.publisher = publisher;
    }
    
    public String getPubdate()
    {
        return pubdate;
    }
    
    public void setPubdate(String pubdate)
    {
        this.pubdate = pubdate;
    }
    
    public String getBookType()
    {
        return bookType;
    }
    
    public void setBookType(String bookType)
    {
        this.bookType = bookType;
    }
    
    public String getIsbn()
    {
        return isbn;
    }
    
    public void setIsbn(String isbn)
    {
        this.isbn = isbn;
    }
    
    public Integer getPages()
    {
        return pages;
    }
    
    public void setPages(Integer pages)
    {
        this.pages = pages;
    }
    
    public String getKeyWord()
    {
        return keyWord;
    }
    
    public void setKeyWord(String keyWord)
    {
        this.keyWord = keyWord;
    }
    
    public Date getCreatetime()
    {
        return createtime;
    }
    
    public void setCreatetime(Date createtime)
    {
        this.createtime = createtime;
    }
    
    public Date getModifytime()
    {
        return modifytime;
    }
    
    public void setModifytime(Date modifytime)
    {
        this.modifytime = modifytime;
    }
    
    @Override
    protected Serializable pkVal()
    {
        return this.id;
    }
    
    @Override
    public String toString()
    {
        return "TBookXh{" +", id=" + id+", title=" + title + ", pic=" + pic + ", picLocal=" + picLocal + ", author="
                + author + ", describe=" + describe + ", publisher=" + publisher + ", pubdate=" + pubdate
                + ", bookType=" + bookType + ", isbn=" + isbn + ", pages=" + pages + ", keyWord=" + keyWord
                + ", createtime=" + createtime + ", modifytime=" + modifytime + ", book_size=" + book_size + ", cip="
                + cip + ",yddx" + yddx + ",sapcode" + sapcode + ",nrty" + nrty + "}";
    }
}
