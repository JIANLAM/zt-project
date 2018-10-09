package com.szcti.lcloud.catalog.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.szcti.lcloud.catalog.entity.LabelSet;
import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class LableVO extends QueryParam implements Serializable {
	private static final long serialVersionUID = 1L;
	// 查询条件
	private String searchKey;// 检索字段值为
	private String searchValue;// 检索值
	private String timeKey;// 时间字段
	private String barcodeStart;// 条形码起
	private String barcodeEnd;// 条形码未
	private String bookTypeStart;// 分类号起
	private String bookTypeEnd;// 分类号未
	private String callNoStart;// 索书号起
	private String callNoEnd;// 索书号未

	private Long libraryId;// 图书馆id
	private Long holdingId;// 馆藏id
	private Long labelSetId;// 标签设置id
	private String title;
	private String author;
	private String barcode;// 条码号
	private String callNo;// 索书号
	private String currSeednumber;// 种次号
	private String seriesNo;// 从书号
	private String one;
	private String two;
	private String three;
	private String four;
	private String five;
	private String six;
	private String seven;
	private String eight;
	private LabelSet labelSet;

	private String bpm_id;// 基本参数馆藏地 外键
	private String barcodeNumber;// 条码区号
	private String barcodeName;// 条码区名称
	private String prefix;// 条码前缀
	private long totalBit;// 条码总位数
	private long startBarcode;// 起止条码号
	private long stopBarcode;// 终止条码号
	private long currBarcode;// 当前条码号
	private long surplusBarcode;// 剩余条码数
	private String currUser;// 分区当前使用者
	private String createTime;// 创建时间
	private String userId;// 外键 创建者
	private String remark;// 备注

}
