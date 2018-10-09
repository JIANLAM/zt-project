package com.szcti.lcloud.circulate.entity.vo;

import java.util.Date;

import com.szcti.lcloud.circulate.entity.THolding;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class HoldBookVO extends THolding
{
    
    private static final long serialVersionUID = -6264572056818164722L;
    
    private String            title;
    
    /**
     * 作者
     */
    private String            author;
    
    /**
     * 出版社
     */
    private String            publisher;
    
    /**
     * 出版日期
     */
    private Date              pubdate;
    
    private String            isbn;
    
    /**
     * 页数
     */
    private Integer           pages;
    
    private String            libraryName;
    
    private Date              dueBackTime;
    
    private Date              lendTime;
    
    private Integer           relendNum;
    
    private Long              lendId;
    
    private String            relendMsg;
    
}
