package com.szcti.lcloud.parameter.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class BarCodeVO extends QueryParam implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;                // 条码表 t_barcode 主键

/*    private Long bpmId;            // 基本参数 馆藏地 外键

    private String bpmCode;           //馆藏地代码

    private String bpmName;           //馆藏地点名称*/

    private Long barcodeNumber;    //条码区号

    private String barcodeName;    //条码区名称

    private String prefix;          //条码前缀

    private Long totalBit;         //条码总位数

    private Long startBarcode;     //起止条码号

    private Long stopBarcode;      //终止条码号

    private Long currBarcode;      //当前条码号

    private Long currUser;         //分区当前使用者 用户表

    private String createTime;     //创建时间

    private Long userId;           //创建者ID

    private Long libraryId ;        //图书馆ID

    private String remark;          //备注

    private String loginName;       //分区当前使用者   登录名

    private String username;        //分区当前使用者   真实姓名

}