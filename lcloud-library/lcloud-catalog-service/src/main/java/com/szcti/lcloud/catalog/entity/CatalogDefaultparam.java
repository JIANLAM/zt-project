package com.szcti.lcloud.catalog.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CatalogDefaultparam extends QueryParam implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;                // 编目默认参数设置（t_catalog_defaultparam）主键

    private Long collAddressId;            // 馆藏地点

    private Long barcodeId;           //条码分区

    private Long circulationId;           //流通类型

    private Long revisionId;    //版次

    private Long languageId;    //语种

    private Long bookSize;          //书的开本

    private Long carrierType;         //载体类型

    private Long bindingId;     //装帧类型

    private Long literatureId;      //文献来源

    private String catalogBatch;      //批次号

    private Long currUser;         //使用者

    private Long libraryId;     //图书馆

    private Long createBy;           //创建者

    private String createTime ;        //创建时间

    private Long updateBy;          //修改者

    private String updateTime;       //修改时间

    private String remark;        //备注

}