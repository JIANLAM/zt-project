package com.szcti.lcloud.helplendback.entity.vo;

import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;

import java.io.Serializable;

/**
 * @Title: 代借与代还的记录VO
 * @Description: 包含代借还信息，以及所借还书的信息
 * @author: fengda
 * @date: 2018/5/16 15:13
 */
@Data
public class HelpLendBackVO extends QueryParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long holdingId;
    private String barcode;

    private Long lendId;

    private Long boxId;

    private Long readerId;
    private String userName;

    private String dueTime;

    private Integer status;//0待处理，1待领取，2已领取，3已还书，4已取消

    private Integer type;



    private String title;

    private String author;

    private String publisher;

    private String bookType;

    private Long ownlib;

    private String ISBN;

    private String  qualification;      //查询条件 微信端 根据书名或者作者查询
    private String  libraryName;        //所属图书馆名字
    private String pic;

}
