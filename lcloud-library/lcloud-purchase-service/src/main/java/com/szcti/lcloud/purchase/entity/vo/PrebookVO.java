package com.szcti.lcloud.purchase.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PrebookVO extends QueryParam implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    //查询参数
    private String            searchKey;            //检索字段值为:|title|isbn|author|publisher
    
    private String            searchValue;          //检索值
    
    private String            timeKey;              //时间字段
    
    private Integer           source;               //书的来源：1新华集团2本馆图书3豆瓣图书4成员馆图书
    
    private Long              orderId;              //订购单ID参数
    
    private Long              libraryId;            //图书馆id
    
    private String            libraryName;
    
    private Long              id;
    
    //private Long orderId;
    private String            title;
    
    private String            author;
    
    private String            publisher;
    
    private String            pubdate;
    
    private String            bookType;             //分类号
    
    private String            isbn;
    
    private Float             price;
    
    private Byte              inType;
    
    private Byte              status;
    
    private Long              createBy;
    
    private String            createTime;
    
    private String            summary;
    
    private String            goodsCode;
    
    private String            storageTime;
    
    private String            seriesTitle;
    
    private String            secondTitle;
    
    private String            subjectWord;
    
    private String            firstDuty;
    
    private String            revision;
    
    private String            language;
    
    private String            carrierType;
    
    private String            binding;
    
    private List              ids;
    
    private List              isbnList;
    
    private List              libraryIdList;
    
    private Integer           pages;
    
    private Integer           flag;
    
    private String            startTime;
    
    private String            endTime;
    
    private Long              ruleId;               //订购规则id
}
