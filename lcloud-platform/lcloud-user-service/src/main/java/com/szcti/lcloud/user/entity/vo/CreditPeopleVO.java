package com.szcti.lcloud.user.entity.vo;

import com.szcti.lcloud.user.entity.TPeople;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CreditPeopleVO extends TPeople {
	private static final long serialVersionUID = -7706166689744293717L;

	 private Integer ownValue;
	 private String readerCardNumber;
}
