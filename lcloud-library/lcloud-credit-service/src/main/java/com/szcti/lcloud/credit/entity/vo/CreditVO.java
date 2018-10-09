package com.szcti.lcloud.credit.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CreditVO extends QueryParam implements Serializable {
    private static final long serialVersionUID = 1L;
    //查询条件
    private String searchKey;//检索字段值为:userName|readerCardNumber|
    private String searchValue;//检索值
    private String timeKey;//时间字段
    private Integer ownValueBegin;//剩余信用值起
    private Integer ownValueEnd;//剩余信用值止

    private Long id;//信用id
    private Long libraryId;//图书馆id
    private Long userId;//用户id
    private Long readerId;//读者id
    private Integer defaultValue;//开始默认值
    private Integer ownValue;//剩余信用值
    private Integer status;//启用状态
    private String readerCardNumber;//读者证号
    private String userName;//用户名
    private String sex;//性别
    private String cardNumber;//身份证号
    private Integer cardStatus;//读者证状态
    private String summary;
    private Integer islendBuy;

}