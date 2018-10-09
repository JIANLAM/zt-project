package com.szcti.lcloud.credit.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ReaderCredit {
    private Long id;

    private Long readerId;

    private Integer beforeDeductCreditValue;

    private Date deductScoreTime;

    private Integer deductScore;

    private Integer afterDeductCreditValue;

    private String remark;

    private String deductScoreReason;

    private Integer status;
}