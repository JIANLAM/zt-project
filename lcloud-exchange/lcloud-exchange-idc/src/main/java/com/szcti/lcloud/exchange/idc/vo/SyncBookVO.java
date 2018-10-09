package com.szcti.lcloud.exchange.idc.vo;

import java.util.List;

import com.szcti.lcloud.exchange.idc.entity.Book;

import lombok.Data;

@Data
public class SyncBookVO {

	private Long timestamp;
	
	private List<Book> list;
}
