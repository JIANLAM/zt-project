package com.szcti.lcloud.recommbuy.entity;

import lombok.Data;

import java.util.Date;
@Data
public class RecommBuy {
    private static final long serialVersionUID = 1L;
    private Long id;

    private Long prebookId;

    private Long readerId;

    private Date createTime;

    private String recommFrom;
}