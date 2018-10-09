package com.szcti.lcloud.credit.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ReaderCreditVO extends QueryParam implements Serializable {
    private static final long serialVersionUID = 1L;

    //查询条件
    private String searchKey;//检索字段值为:userName|readCard|sex|cardStatus
    private String searchValue;//检索值
    private String timeKey;//时间字段
    private Integer statusSearch;//读者证状态条件

    private Long id;

    private Long readerId;

    private Integer beforeDeductCreditValue;

    private String deductScoreTime;//扣分时间

    private Integer deductScore;

    private Integer afterDeductCreditValue;

    private String remark;

    private String deductScoreReason;

    private Integer status;
    private String readerCardNumber;//读者证号
    private String userName;//用户名

}