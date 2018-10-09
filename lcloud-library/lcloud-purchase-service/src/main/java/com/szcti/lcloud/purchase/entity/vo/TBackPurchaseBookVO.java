package com.szcti.lcloud.purchase.entity.vo;

import com.szcti.lcloud.purchase.entity.TPurchaseBook;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TBackPurchaseBookVO extends TPurchaseBook {

	private static final long serialVersionUID = -2627654164142741280L;

	private Integer backQuantity;
}
