package com.szcti.lcloud.parameter.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class LibraryAddressVO extends QueryParam implements Serializable {
    private static final long serialVersionUID = 1L;
    //查询条件
    private String searchKey;//检索字段值为:
    private String searchValue;//检索值
    private String timeKey;//时间字段
    private Long id;//记录id

    private Long libraryId;//图书馆id

    private Integer provinceId;//省id

    private Integer cityId;//市id

    private Integer areaId;//地区id

    private String address;//地址

    private String contact;//联系人

    private String phone;//电话

    private String email;//邮箱

    private String billCode;//发票号

    private String ratepayerCode;//纳税人识别号

    private String status;//状态

    private Long creator;//创建者

    private String createTime;//创建时间
    private String postcode;//邮箱

    private String defaultSet;//默认设置
}