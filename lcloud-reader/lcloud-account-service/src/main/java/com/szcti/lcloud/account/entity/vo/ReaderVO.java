package com.szcti.lcloud.account.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Title: 读者VO
 * @Description: 读者信息
 * @author: fengda
 * @date: 2018/5/15 18:02
 */
@Data
public class ReaderVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long peopleId;
    private Integer readerCardType;
    private String readerCardNumber;
    private Long status;
    private Long grade;
    private Long classes;
    private Long libraryId;
    private String libraryName;        //归属
    private String remark;
    private Integer ownValue;       //信用值

}
