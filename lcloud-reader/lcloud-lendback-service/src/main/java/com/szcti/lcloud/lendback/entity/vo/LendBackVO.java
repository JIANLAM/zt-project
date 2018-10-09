package com.szcti.lcloud.lendback.entity.vo;

import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;

import java.io.Serializable;

/**
 * @Title: 借书记录VO
 * @Description: 包含借还信息，以及所借书的信息
 * @author: fengda
 * @date: 2018/5/16 15:13
 */
@Data
public class LendBackVO extends QueryParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long holdingId;

    private Long bookId;

    private Long readerId;
    private String userName;

    private String title;

    private String author;

    private String publisher;

    private String bookType;

    private Long ownlib;

    private String ISBN;

    private String lendTime;

    private String backTime;

    private String dueBackTime;

    private Integer lendStatus;

    private String  qualification;      //查询条件 微信端 根据书名或者作者查询
    private String  libraryName;        //所属图书馆名字
    private String  barcode;            //条码号  图书馆条码
    private String  pic;               //书的封面

}
