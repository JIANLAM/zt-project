package com.szcti.lcloud.catalog.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class MarcTemplateDetailVO
 extends QueryParam implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long marcId;
    private String description;
    private String nameDefine;
    private String mainStart;
    private String secondStart;
    private String marcEnd;
    private String designatorDefine;
    private Integer orderIndex;
    private Long marcTemplateId;
    private String mustValue;
    private String selectValue;
    private String discriptionDef;
    private String allValue;

}