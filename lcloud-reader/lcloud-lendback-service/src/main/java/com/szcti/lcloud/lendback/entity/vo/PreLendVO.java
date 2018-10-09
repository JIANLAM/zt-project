package com.szcti.lcloud.lendback.entity.vo;

import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;

import java.io.Serializable;

/**
 * @Title: 预借VO
 * @Description: 描述
 * @author: fengda
 * @date: 2018/5/16 15:13
 */
@Data
public class PreLendVO extends QueryParam implements Serializable {

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

    private Long libId;

    private String ISBN;

    private String preLendTime;

    private String dueLendTime;

    //0预借中，1预借完成，2取消预借
    private Integer preLendStatus;

    private Long shelf; //书架号

    private String  qualification;      //查询条件 微信端 根据书名或者作者查询
    private String  toLibraryName;        //所属图书馆名字
    private String  nowLibraryName;        //当前所在图书馆
    private String  pic;        //书的封面
}
