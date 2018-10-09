package com.szcti.lcloud.exchange.idc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.szcti.lcloud.exchange.idc.entity.Book;
import com.szcti.lcloud.exchange.idc.exception.MyRuntimeException;
import com.szcti.lcloud.exchange.idc.repository.BookRepository;
import com.szcti.lcloud.exchange.idc.security.JwtUser;
import com.szcti.lcloud.exchange.idc.service.BookService;
import com.szcti.lcloud.exchange.idc.utils.MqttClientUtil;
import com.szcti.lcloud.exchange.idc.vo.R;
import com.szcti.lcloud.exchange.idc.vo.SyncBookVO;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
    private BookRepository repository;
	
	public static void main(String[] args) {
		List<Book> bList = new ArrayList<>();
		Book book = new Book();
		book.setAuthor("1");
		bList.add(book);
		bList.forEach(b -> {
			System.out.println(b.getAuthor());
			System.out.println(b.getBook_type());
			b.setBook_type("1");
		});
		bList.forEach(b -> {
			System.out.println(b.getAuthor());
			System.out.println(b.getBook_type());
		});
	}
	
	@Override
	public R bookSync(JSONObject reqJson) {
		JwtUser user = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SyncBookVO syncBookVO = JSONObject.toJavaObject(reqJson, SyncBookVO.class);
		if(syncBookVO.getList().size() > 10000) {
			throw new MyRuntimeException(new R("APP0001", "传输数据过大："+syncBookVO.getList().size()));
		}
		try {
			syncBookVO.getList().forEach(book -> {book.setDataSource(user.getOrganization());});
			repository.saveAll(syncBookVO.getList());
		} catch (RuntimeException t) {
			MyRuntimeException.showTraces(t);
		}
		MqttClientUtil.publish("sap-new-book", JSONArray.toJSONString(syncBookVO.getList()));
		Map<String, Object> data = new HashMap<>();
		data.put("code", "APP0000");
		data.put("msg", "success");
		data.put("timestamp", syncBookVO.getTimestamp());
		return new R("SVC0000", "success",data);
	}

}
