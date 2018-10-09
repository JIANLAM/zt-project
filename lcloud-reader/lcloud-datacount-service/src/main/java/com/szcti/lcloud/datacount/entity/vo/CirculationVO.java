package com.szcti.lcloud.datacount.entity.vo;

import com.szcti.lcloud.common.utils.QueryParam;
import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;

/**
 * @Title: 采访统计 条件查询 条件VO
 * @Description: 所有查询条件
 * @author: wangsiyi
 * @date: 2018/7/23 2:57
 */
@Data
public class CirculationVO extends QueryParam implements Serializable {

    private String readerTypeIds;       //选择的读者证类型

    private String startTime;          //时间范围   开始时间

    private String endTime;            //时间范围   结束时间

    private Long libraryId;            //图书馆ID

    private String commTypeId;        //选择类型 子项的id

    private Long commReaderId;        //读者证类型id

    private Integer sexStatus;        //性别的状态  1代表男     2代表女

    private Long grade;               //年级

    private Long classes;             //班级

    private Long actType;       //图书流通类型

    private String bookType;     //图书分类 开始

    private String bookTypeEnd;     //图书分类  结束

    private Long collAddressId;     //馆藏地

    private Integer type;       //图书查询类型 type = 202 污损 type = 2032 丢失

    private Integer column;     // 1 种数 2 册数 3 总金额

    private String collAdsTypeIds;       //选择的馆藏地类型

    private String circulationTypeIds;       //选择的流通类型

    private String collStatusIds;          //馆藏状态

    private Long[] idArray;           //馆藏状态

    private String callNoStart;           //索书号     开始

    private String callNoEnd;           //索书号       结束

    private Long[] collIdArray;         //馆藏地数组

    private Long[] circIdArray;         //图书流通类型数组

    private Integer columnType;         //选择横向还是纵向      0代表馆藏地点纵向 流通类型横向   1代表 流通类型纵向   馆藏地点横向

    private String barcodeStart;        //条码号开始

    private String barcodeEnd;        //条码号结束

    private String bookTypeCount;       //1 种数

    private String totalCount;       // 2 册数

    private String totalPrice;      // 3 总价

    private Object[] barcodeArray;      //条码号数组     馆藏处理统计

    private String operationType;       //选择的是 入藏  变更  剔除

    private Long userId;        //馆藏处理统计    操作人员ID

}
