package com.szcti.lcloud.purchase.entity.vo;

import java.util.List;

import com.szcti.lcloud.purchase.entity.TBackPurchase;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BackPurchaseVO extends TBackPurchase {

	private static final long serialVersionUID = -2501288062017182937L;
	private Integer totalQuantity;
	 private Integer bookTypeQty;
	 private String operatorName;
	 
	 List<TBackPurchaseBookVO> books;
}
