package com.szcti.lcloud.holding.entity.vo;

import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;

import java.io.Serializable;

/**
 * @Title: 图书副本VO
 * @Description: 图书副本信息以及当前借阅信息
 * @author: fengda
 * @date: 2018/7/26 15:13
 */
@Data
public class BookCopyVO extends QueryParam implements Serializable {

    private static final long serialVersionUID = 1L;

    //馆藏ID
    private Long id;

    private String callNo;

    private String pic;

    private Long bookId;

    private Long libId;
    private String libName;

    private String title;

    private String ISBN;

    private String barCode;

    private Long shelf;
    private String shelfName;

    private Integer status;

    private Long actType;

    private String actName;

    private Long lendId;
    private String lendTime;

    private String dueBackTime;

    private String backTime;

    private String readerCardNumber;

    private String userName;
    private Long readerId;
    private Long userId;



    private String barCodeStart;
    private String barCodeEnd;

    private String callNoStart;
    private String callNoEnd;

}
