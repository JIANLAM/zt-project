package com.szcti.lcloud.holding.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;

import java.io.Serializable;

/**
 *
 *
 * @author fengda
 * @date 2018-07-26 14:25:42
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class OpLogVO extends QueryParam implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    private Integer moduleId;

    private String moduleName;

    private String operationType;

    private Long libraryId;

    private String libraryName;

    private Long userId;

    private String loginName;

    private String userName;

    private String ip;

    private String opContent;

    private String remark;

    private String createTime;
}