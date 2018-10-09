package com.szcti.lcloud.user.entity.vo;

import com.szcti.lcloud.user.entity.TReaderType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ReaderTypeVO extends TReaderType {

	
	private static final long serialVersionUID = -4564703269053618603L;

	private String inLibLendruleName;

    private String outLibLendruleName;
}
