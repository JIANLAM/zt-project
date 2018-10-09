package com.szcti.lcloud.purchase.entity;

public class PurchaseBook
{
    private Long    id;
    
    private Long    orderId;   //订单号
    
    private Long    bookId;    //数id
    
    private Integer bookQty;   //书的数量
    
    private Float   totalPrice;//总额
    
    private Long    prebookId;
    
    private Integer status;    //0待审核1通过2不通过3已提交4已发货5部分验收6验收完全7已到馆8退回
    
    private String  goodsCode; //商品编号
    
    private String  checkedmsg;//审核结果
    
    private Float   price;     //单价
    
    private String  isbn;
    
    private String  title;
    
    private String  author;
    
    private String  publisher;
    
    private String  pubdate;
    
    private String  bookType;
    
    private Integer pages;
    
    private String  bookSize;
    
    private Integer source;    //来源
    
    private String  libraryId;
    
    public String getLibraryId()
    {
        return libraryId;
    }
    
    public void setLibraryId(String libraryId)
    {
        this.libraryId = libraryId;
    }
    
    public Long getId()
    {
        return id;
    }
    
    public void setId(Long id)
    {
        this.id = id;
    }
    
    public Long getOrderId()
    {
        return orderId;
    }
    
    public void setOrderId(Long orderId)
    {
        this.orderId = orderId;
    }
    
    public Long getBookId()
    {
        return bookId;
    }
    
    public void setBookId(Long bookId)
    {
        this.bookId = bookId;
    }
    
    public Integer getBookQty()
    {
        return bookQty;
    }
    
    public void setBookQty(Integer bookQty)
    {
        this.bookQty = bookQty;
    }
    
    public Float getTotalPrice()
    {
        return totalPrice;
    }
    
    public void setTotalPrice(Float totalPrice)
    {
        this.totalPrice = totalPrice;
    }
    
    public Long getPrebookId()
    {
        return prebookId;
    }
    
    public void setPrebookId(Long prebookId)
    {
        this.prebookId = prebookId;
    }
    
    public Integer getStatus()
    {
        return status;
    }
    
    public void setStatus(Integer status)
    {
        this.status = status;
    }
    
    public String getGoodsCode()
    {
        return goodsCode;
    }
    
    public void setGoodsCode(String goodsCode)
    {
        this.goodsCode = goodsCode == null ? null : goodsCode.trim();
    }
    
    public String getCheckedmsg()
    {
        return checkedmsg;
    }
    
    public void setCheckedmsg(String checkedmsg)
    {
        this.checkedmsg = checkedmsg == null ? null : checkedmsg.trim();
    }
    
    public Float getPrice()
    {
        return price;
    }
    
    public void setPrice(Float price)
    {
        this.price = price;
    }
    
    public String getIsbn()
    {
        return isbn;
    }
    
    public void setIsbn(String isbn)
    {
        this.isbn = isbn == null ? null : isbn.trim();
    }
    
    public String getTitle()
    {
        return title;
    }
    
    public void setTitle(String title)
    {
        this.title = title == null ? null : title.trim();
    }
    
    public String getAuthor()
    {
        return author;
    }
    
    public void setAuthor(String author)
    {
        this.author = author == null ? null : author.trim();
    }
    
    public String getPublisher()
    {
        return publisher;
    }
    
    public void setPublisher(String publisher)
    {
        this.publisher = publisher == null ? null : publisher.trim();
    }
    
    public String getPubdate()
    {
        return pubdate;
    }
    
    public void setPubdate(String pubdate)
    {
        this.pubdate = pubdate == null ? null : pubdate.trim();
    }
    
    public String getBookType()
    {
        return bookType;
    }
    
    public void setBookType(String bookType)
    {
        this.bookType = bookType == null ? null : bookType.trim();
    }
    
    public Integer getPages()
    {
        return pages;
    }
    
    public void setPages(Integer pages)
    {
        this.pages = pages;
    }
    
    public String getBookSize()
    {
        return bookSize;
    }
    
    public void setBookSize(String bookSize)
    {
        this.bookSize = bookSize == null ? null : bookSize.trim();
    }
    
    public Integer getSource()
    {
        return source;
    }
    
    public void setSource(Integer source)
    {
        this.source = source;
    }
}
