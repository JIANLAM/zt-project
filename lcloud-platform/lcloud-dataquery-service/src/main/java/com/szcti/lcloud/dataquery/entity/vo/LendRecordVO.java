package com.szcti.lcloud.dataquery.entity.vo;

import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;

import java.io.Serializable;

/**
 * @Title: 借阅记录信息VO
 * @Description: 借书的记录信息
 * @author: fengda
 * @date: 2018/8/7 15:13
 */
@Data
public class LendRecordVO extends QueryParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long bookId;

    private Long libId;
    private String libName;

    private String userName;
    private String readerCardNumber;

    private String lendTime;

    private Integer lendStatus;

    private String operator;

    private Integer shelf;

    private String title;

    private String callNo;

    private String barCode;

}
