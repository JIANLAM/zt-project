package com.szcti.lcloud.exchange.service;

public interface DcnSycnService {

	void handle(String topic, String jsonData,int type);

		
}
