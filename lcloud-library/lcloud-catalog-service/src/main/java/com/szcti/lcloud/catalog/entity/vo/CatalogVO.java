package com.szcti.lcloud.catalog.entity.vo;

import java.io.Serializable;
import java.util.List;

import com.szcti.lcloud.catalog.entity.Book;
import com.szcti.lcloud.catalog.entity.MarcTemplateDetail;
import com.szcti.lcloud.common.utils.QueryParam;

import lombok.Data;

@Data
public class CatalogVO extends QueryParam implements Serializable {
    private static final long serialVersionUID = 1L;
    //查询条件
    private String searchKey;//检索字段值为:isbn|id|title|author|barcode|subject
    private String searchValue;//检索值
    private String timeKey;//时间字段

    private Long id;//编目记录号
    private Long marcTemplateId;//marc模板id
    private Long bookId;//书的id
    private Long libraryId;//图书馆id
    private String createTime;//编目时间
    private Long creator;//编目人
    private String creatorName;//编目者名字
    private String isbn;//isbn
    private String callNo;//索书号
    private String barcode;//条形码
    private String bookType;//图书分类名称
    private String bookTypeStar;//图书分类 分类号范围 开始

    private String bookTypeEnd;//图书分类 分类号范围 结束
    private String subject;//分类号
    private String libraryName;//图书馆名称
    private String title;//书的标题
    private String author;//作者
    private String publisher;//出版社
    private String pubdate;//出版时间
    private String source;//获得方式
    private int bookQty;//订购数
    private int acceptQuantity;//已到货数
    private int recount;//副本数
    private String acceptCode;//验收单号
    private String purchaseCode;//订购号
    private Book book;
    private List<HoldingVO> holdingList;
    private List<Long> ids;//借购的书记录id
    private String backTime;//借购还回时间
    private Long articleTypeId;//文献类型
    private Long colladdressId;//馆藏地点
    private String catalogBatch;//批次号
    private String partition;//条码分区号
    private String volume;//分册号
    private List<MarcTemplateDetail> marcList;//marc编目项
    private String type;
    private String ip;

}