package com.szcti.lcloud.parameter.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;

import java.io.Serializable;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class SupplierVO extends QueryParam implements Serializable {
    private static final long serialVersionUID = 1L;
    //查询条件
    private String searchKey;//检索字段值为:
    private String searchValue;//检索值
    private String timeKey;//时间字段
    private Long id;

    private String coding;//供应商编码

    private String name;//供应商名字

    private String address;//供应商地址

    private String contact;//联系人

    private String phone;//电话

    private String email;//邮件

    private String fax;//传真

    private String remark;//备注

    private Byte status;//状态

    private Long libraryId;//图书馆id

    private Long orgId;//组织id
    
    private String postcode;//邮编
}