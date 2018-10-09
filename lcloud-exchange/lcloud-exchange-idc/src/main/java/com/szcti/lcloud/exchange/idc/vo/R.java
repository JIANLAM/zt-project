package com.szcti.lcloud.exchange.idc.vo;

import java.util.Map;

import lombok.Data;

@Data
public class R {

	private String status;
	private String message;
	private Long timestamp = System.currentTimeMillis()/1000;
	
	Map<String, Object> data;
	public R(String status,String message,Map<String, Object> data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}
	
	public R(String status,String message) {
		this.status = status;
		this.message = message;
	}
}
