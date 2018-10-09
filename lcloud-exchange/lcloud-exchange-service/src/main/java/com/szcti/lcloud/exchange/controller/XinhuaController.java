package com.szcti.lcloud.exchange.controller;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.math.NumberUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.szcti.lcloud.exchange.entity.TBookXh;
import com.szcti.lcloud.exchange.entity.TLendbuyOrder;
import com.szcti.lcloud.exchange.mapper.TBookXhMapper;
import com.szcti.lcloud.exchange.mapper.TLendbuyOrderMapper;
import com.szcti.lcloud.exchange.utils.FileUploader;
import com.szcti.lcloud.exchange.utils.RedisUtil;

@RestController
@RequestMapping("xinhua")
public class XinhuaController {
	
	private static final Logger log = LoggerFactory.getLogger(XinhuaController.class);

	@Autowired
	TLendbuyOrderMapper lendbuyOrderMapper;
	
	@Autowired
	private FileUploader fileUploader;
	
	@Autowired
	private TBookXhMapper bookMapper;
	
	@Autowired
	private RedisUtil redisUtil;
	
	@GetMapping("/lendBuyStatus")
	public String lendBuyStatus(String orderId,String expressCompany, String expressNo) {
		
		log.debug(orderId);
		log.debug(expressNo);
		log.debug(expressCompany);
		
		TLendbuyOrder entity=new TLendbuyOrder();
		entity.setId(NumberUtils.toLong(orderId));
		entity.setExpressCompany(expressCompany);
		entity.setExpressNo(expressNo);
		entity.setSendTime(new Date());
		lendbuyOrderMapper.updateById(entity);
		
		return "OK";
	}
	
	@Scheduled(fixedRate = 1 * 60 * 1000)
	@GetMapping("/syncBook")
	public String syncBook() {
		Map<String, String> map = redisUtil.hash().hgetAll("xinhuabook");
		Iterator<Entry<String, String>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String> e= it.next();
			String id = e.getKey();
			String val = e.getValue();
			TBookXh b = JSON.parseObject(val, TBookXh.class);
//			String p = b.getPic();
//			if(StringUtils.hasText(p)) {
//				InputStream inputStream;
//				try {
//					inputStream = getConnection(p).execute().bodyStream();
//					String localFileName = fileUploader.uploadPic(inputStream,"xhcover");
//					b.setPicLocal(localFileName);
//					inputStream.close();
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				}
//			}
			TBookXh bk = bookMapper.selectById(id);
			if(bk==null) {
				bookMapper.insert(b);
			}
			redisUtil.hash().hdel("xinhuabook", id);
			log.info("book_xh add id:"+id);
		}
		
		return "OK";
	}
	
	public static Connection getConnection(String url) {
		Connection conn = Jsoup.connect(url)
				.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
				.header("Accept-Encoding", "gzip, deflate, sdch")
				.header("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6")
				.header("Cache-Control", "max-age=0")
				.header("User-Agent",
						"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36")
				.ignoreContentType(true)
				.timeout(10 * 1000);

		return conn;
	}
}
