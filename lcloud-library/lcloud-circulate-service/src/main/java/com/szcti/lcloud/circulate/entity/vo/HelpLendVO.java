package com.szcti.lcloud.circulate.entity.vo;

import com.szcti.lcloud.circulate.entity.THelpLendback;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class HelpLendVO extends THelpLendback {
	private static final long serialVersionUID = 7042405915897931712L;

	private String readerCardNumber;
	private String readerLibName;
	private String barcode;
	private String bookName;
	private String callNo;
	private String bookLibName;
}
