package com.szcti.lcloud.parameter.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;

import java.io.Serializable;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PublisherVO extends QueryParam implements Serializable {
    private static final long serialVersionUID = 1L;
    //查询条件
    private String searchKey;//检索字段值为:
    private String searchValue;//检索值
    private String timeKey;//时间字段
    private Integer id;

    private String name;

    private String status;

    private String remark;
}