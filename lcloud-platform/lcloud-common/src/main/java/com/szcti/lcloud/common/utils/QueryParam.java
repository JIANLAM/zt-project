package com.szcti.lcloud.common.utils;

import lombok.Builder;
import lombok.Data;


/**
 * @Title: 查询参数
 * @Description: 用于查询的非业务数据参数
 * @author: fengda
 * @date: 2018/5/17 17:58
 */
@Data
public class QueryParam {


    private String startTime;

    private String endTime;

    @Builder.Default
    private Integer pageNum=1;

    @Builder.Default
    private Integer pageSize=20;

    private String orderBy;

    private String keyParam;

}
