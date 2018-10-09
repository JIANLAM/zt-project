package com.szcti.lcloud.datacount.entity.vo;

import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;

import java.io.Serializable;

/**
 * @Title: 采访统计  荐购订单记录VO
 * @Description: 所有荐购订单信息
 * @author: wangsiyi
 * @date: 2018/7/23 2:57
 */
@Data
public class RecommBuyBookVO implements Serializable {

    private Long id;    //荐购书籍（t_recomm_buy） 主键

    private String createTime;  //创建时间

    private Long readerId;    //读者ID

    private Long preBookId;    //荐购图书ID 采访书库（t_prebook） 主键

    private String pic; //书的封面

    private String title;   //图书名称

    private String author;  //作者

    private String publisher;   //出版社

    private String ISBN;    //图书ISBN编号

    private String bookType;    //分类

    private Long prebookStatus;     //0未购买，状态1购买中，2已入藏

    private Float price;    //单价

    private String pubDate; //出版时间

    private Long recommBuyCount; //荐购次数

}
