package com.szcti.lcloud.parameter.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class OutBoxVO extends QueryParam implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;                // 发件箱设置(t_outbox） 主键

    private String address;            // 发件地址

    private String smtpServer;           //SMTP服务器

    private String licenseKey;           //授权码

    private String createDate;     //创建时间

    private Long createBy;           //创建者ID

    private Long libraryId ;        //图书馆ID

}