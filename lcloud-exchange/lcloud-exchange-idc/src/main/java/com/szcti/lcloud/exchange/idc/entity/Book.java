package com.szcti.lcloud.exchange.idc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.SQLInsert;

import lombok.Data;

@Data
@Entity
@Table(name = "t_book")
@SQLInsert(sql = "insert ignore into t_book (isbn, author, book_type, data_source, pages, pic, price, pubdate, publisher, summary, title, id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")
public class Book {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
    @SequenceGenerator(name = "book_seq", sequenceName = "book_seq", allocationSize = 1)
    private Long id;

    @Column(name = "DATA_SOURCE", length = 50)
    @NotNull
    @Size(min = 1, max = 50)
    private String dataSource;
    
    @Column(name = "TITLE", length = 255)
    @NotNull
    @Size(min = 1, max = 255)
    private String title;

    @Column(name = "PIC", length = 255)
    @Size(min = 1, max = 255)
    private String pic;

    @Column(name = "AUTHOR", length = 255)
    @NotNull
    @Size(min = 1, max = 255)
    private String author;

    @Column(name = "SUMMARY", length = 255)
    @NotNull
    @Size(min = 1, max = 255)
    private String summary;
    
    @Column(name = "PUBLISHER", length = 255)
    @NotNull
    @Size(min = 1, max = 255)
    private String publisher;
    
    @Column(name = "PUBDATE")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date pubdate;
    
    @Column(name = "BOOK_TYPE", length = 8)
    @NotNull
    @Size(min = 1, max = 8)
    private String book_type;
    
    @Column(name = "ISBN", length = 100, unique = true)
    @NotNull
    @Size(min = 1, max = 100)
    private String ISBN;

    @Column(name = "PRICE")
    @NotNull
    @Digits(integer = 10, fraction = 2)
    private Double price;
    
    @Column(name = "PAGES")
    @NotNull
    @Digits(integer = 10, fraction = 0)
    private Integer pages;
    
}