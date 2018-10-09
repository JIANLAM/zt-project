package com.szcti.lcloud.budget.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class BudgetVO extends QueryParam implements Serializable {
    private static final long serialVersionUID = 1L;
    //查询条件
    private String searchKey;//检索字段值为:purchaseCode|orderCode|creatorAccount|creatorName|title|isbn|author|publisher
    private String searchValue;//检索值
    private String timeKey;//时间字段
    private Long id;

    private Long libraryId;
    private String libraryName;
    private String name;
    private String coding;
    private String type;
    private Float total;
    private Float remain;
    private String purchaseCode;
    private Long creator;
    private String creatorName;
    private String creatorLoginName;
    private String creatTime;
    private String status;
    private String remark;
    private List ids;
}