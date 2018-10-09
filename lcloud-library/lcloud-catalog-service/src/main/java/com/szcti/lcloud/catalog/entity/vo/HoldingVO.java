package com.szcti.lcloud.catalog.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class HoldingVO extends QueryParam implements Serializable {
    private static final long serialVersionUID = 1L;
    //查询条件
    private String searchKey;//检索字段值为:title|isbn|author|publisher
    private String searchValue;//检索值
    private String timeKey;//时间字段
    private Long id;
    private String callNo;
    private Long bookId;
    private String barcode;
    private Long ownlib;
    private Long curlib;
    private Long shelf;
    private String indate;
    private Float singleprice;
    private Float totalprice;
    private String source;
    private String status;
    private String actType;
    private String rfid;
    private Integer recno;
    private Integer bookrecno;
    private Long libraryId;
    private String rowid;
    private String title;
    private String author;
    private String isbn;
    private String libraryName;
    private String acceptCode;//验收单号
    private String colladdressId;//馆藏地点
    private String catalogBatch;//批次号
    private String partition;//条码分区号
    private String currSeednumber;//种次号
    private String seriesNo;//从书号
    private String remark;//备注
    private String operation;//操作 数据库并无此字段 前端调用删除操作此字段值为delete
    private String prefix;//条码前缀
	private String currUser;//条码使用者
    }