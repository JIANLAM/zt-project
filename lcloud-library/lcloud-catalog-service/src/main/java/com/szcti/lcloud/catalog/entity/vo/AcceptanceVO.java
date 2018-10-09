package com.szcti.lcloud.catalog.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;
import java.io.Serializable;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class AcceptanceVO extends QueryParam implements Serializable {
    private static final long serialVersionUID = 1L;
    //查询条件
    private String searchKey;//检索字段值为:title|isbn|author|publisher
    private String searchValue;//检索值
    private String timeKey;//时间字段
    private Long id;

    private Long libraryId;

    private String acceptCode;

    private String status;

    private Long creator;

    private String createTime;

    private String remark;

    private String billNo;

    private String totalPrice;

    }