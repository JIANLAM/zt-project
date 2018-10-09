package com.szcti.lcloud.circulate.entity.vo;

import java.util.Date;

import com.szcti.lcloud.circulate.entity.TPrebook;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class LendBuyVO extends TPrebook {
	private static final long serialVersionUID = 6696885573202281096L;

	private Date backTime;

	private Date dueBackTime;
	

}
