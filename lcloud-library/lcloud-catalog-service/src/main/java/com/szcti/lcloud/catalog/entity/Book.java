package com.szcti.lcloud.catalog.entity;

public class Book {
    private Long id;

    private String pic;

    private String title;

    private String author;

    private String pubArea;

    private String publisher;

    private String pubdate;

    private String bookType;

    private String bookTypeStar;

    private String bookTypeEnd;

    private String isbn;

    private Float price;

    private Long createBy;

    private String createTime;

    private Integer pages;

    private Integer bookrecno;

    private String rowid;

    private String seriesTitle;

    private String secondTitle;

    private String subjectWord;

    private String firstDuty;

    private String revision;

    private String language;

    private String carrierType;

    private String binding;
    
    private String parallelTitle;
    
    private String callno;//索书号
    
    private long ownlib;//图书馆id

    private String priceTest;//单价文本

    private String pagesTest;   //页数 文本

    public String getBookTypeStar()
    {
        return bookTypeStar;
    }

    public void setBookTypeStar(String bookTypeStar)
    {
        this.bookTypeStar = bookTypeStar;
    }

    public String getBookTypeEnd()
    {
        return bookTypeEnd;
    }

    public void setBookTypeEnd(String bookTypeEnd)
    {
        this.bookTypeEnd = bookTypeEnd;
    }

    public String getPagesTest()
    {
        return pagesTest;
    }

    public void setPagesTest(String pagesTest)
    {
        this.pagesTest = pagesTest;
    }

    public String getPriceTest()
    {
        return priceTest;
    }

    public void setPriceTest(String priceTest)
    {
        this.priceTest = priceTest;
    }
    
    public String getCallno()
    {
        return callno;
    }

    public void setCallno(String callno)
    {
        this.callno = callno;
    }

    public long getOwnlib()
    {
        return ownlib;
    }

    public void setOwnlib(long ownlib)
    {
        this.ownlib = ownlib;
    }

    public String getParallelTitle() {
        return parallelTitle;
    }

    public void setParallelTitle(String parallelTitle) {
        this.parallelTitle = parallelTitle;
    }

    public String getBookSize() {
        return bookSize;
    }

    public void setBookSize(String bookSize) {
        this.bookSize = bookSize;
    }

    private String bookSize;

    private String summary;

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
        this.pic = pic == null ? null : pic.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public void setPubArea(String pubArea){
        this.pubArea = pubArea == null ? null : pubArea.trim();
    }

    public String getPubArea(){
        return pubArea;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher == null ? null : publisher.trim();
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
        this.bookType = bookType == null ? null : bookType.trim();
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn == null ? null : isbn.trim();
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
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
        this.rowid = rowid == null ? null : rowid.trim();
    }

    public String getSeriesTitle() {
        return seriesTitle;
    }

    public void setSeriesTitle(String seriesTitle) {
        this.seriesTitle = seriesTitle == null ? null : seriesTitle.trim();
    }

    public String getSecondTitle() {
        return secondTitle;
    }

    public void setSecondTitle(String secondTitle) {
        this.secondTitle = secondTitle == null ? null : secondTitle.trim();
    }

    public String getSubjectWord() {
        return subjectWord;
    }

    public void setSubjectWord(String subjectWord) {
        this.subjectWord = subjectWord == null ? null : subjectWord.trim();
    }

    public String getFirstDuty() {
        return firstDuty;
    }

    public void setFirstDuty(String firstDuty) {
        this.firstDuty = firstDuty == null ? null : firstDuty.trim();
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision == null ? null : revision.trim();
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language == null ? null : language.trim();
    }

    public String getCarrierType() {
        return carrierType;
    }

    public void setCarrierType(String carrierType) {
        this.carrierType = carrierType == null ? null : carrierType.trim();
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding == null ? null : binding.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

}