package com.szcti.lcloud.lendback.entity;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author fengda
 * @email ${email}
 * @date 2018/5/16 15:13
 */
@Data
public class ReLendEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long lendId;

	private String reLendTime;

	private String reLendBackTime;

	private String primaryBackTime;

}
