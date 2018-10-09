package com.szcti.lcloud.exchange.controller;

import java.io.InputStream;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.szcti.lcloud.exchange.entity.TAudio;
import com.szcti.lcloud.exchange.entity.TExcCfg;
import com.szcti.lcloud.exchange.mapper.TAudioMapper;
import com.szcti.lcloud.exchange.mapper.TExcCfgMapper;
import com.szcti.lcloud.exchange.utils.FileUploader;


@RestController
@RequestMapping("/audio")
public class AudioController {
	/**
	 * Logger for this class
	 */
	private static final Logger log = LoggerFactory.getLogger(AudioController.class);

	@Autowired
	private FileUploader fileUploader;
	@Autowired
	private TAudioMapper audioMapper;
	@Autowired
	private TExcCfgMapper cfgMapper;
	
	@Scheduled(fixedRate = 1 * 60 * 60 * 1000)
	public String sync() {
		
		List<TExcCfg> cfgls = cfgMapper.selectList(null);
		if(cfgls.size()<4) {
			return "no config";
		}
		String baseUrl="";
		String custCode="";
		String custPwd="";
		String postUrl="";
		int currPage=0;
		
		for (TExcCfg tAudioCfg : cfgls) {
			if(tAudioCfg.getCfgKey().equals("ld_baseUrl")) {
				baseUrl=tAudioCfg.getCfgVal();
			}
			if(tAudioCfg.getCfgKey().equals("ld_custCode")) {
				custCode= tAudioCfg.getCfgVal();
			}
			if(tAudioCfg.getCfgKey().equals("ld_custPwd")) {
				custPwd = tAudioCfg.getCfgVal();
			}
			if(tAudioCfg.getCfgKey().equals("ld_postUrl")) {
				postUrl = tAudioCfg.getCfgVal();
			}
			if(tAudioCfg.getCfgKey().equals("ld_currPage")) {
				currPage = NumberUtils.toInt(tAudioCfg.getCfgVal());
			}
		}
		if(StringUtils.isBlank(baseUrl) || StringUtils.isBlank(custCode) || StringUtils.isBlank(custPwd) || StringUtils.isBlank(postUrl)) {
			return "error config";
		}
		int count=1;
		do {
			
			JSONObject o1 = new JSONObject();
			JSONObject o2 = new JSONObject();
			o2.put("mac", "1831BF0E8B1C");
			o2.put("custCode", custCode);
			o2.put("custPwd", custPwd);
			o2.put("currPage", currPage);
			o2.put("recordPerPage", "10");

			o1.put("data", o2);
			
			try {
				 Response resp =  getConnection(postUrl).header("Content-Type", "application/json").method(Method.POST).requestBody(o1.toJSONString()).execute();
				 log.debug(resp.statusCode()+"");
				 String data = resp.body();
				log.debug(data);
				JSONObject obj = JSON.parseObject(data);
				JSONObject dd = obj.getJSONObject("d");
				if(dd ==null) {
					break;
				}
				JSONArray ja = dd.getJSONArray("retValue");
				if(ja ==null) {
					break;
				}
				
				for (int i = 0;i<ja.size();i++) {
					JSONObject d = ja.getJSONObject(i);
					TAudio a = new TAudio();
					a.setId(d.getLong("ID"));
					a.setMemberId(d.getLong("memberID"));
					a.setDeviceId(d.getLong("deviceID"));
					a.setMac(d.getString("mac"));
					a.setDocId(d.getLong("docID"));
					a.setInterestDocId(d.getLong("interestDocID"));
					a.setFileName(d.getString("fileName"));
					a.setServerFileName(d.getString("serverFileName"));
					a.setServerFileNameFull(baseUrl+"/"+d.getString("serverFileName"));
					a.setWorksName(d.getString("worksName"));
					a.setReaderNo(d.getString("readerNo"));
					a.setReaderName(d.getString("readerName"));
					a.setCustName(d.getString("custName"));
					a.setActivityId(d.getLong("activityID"));
					a.setRecordTime(d.getString("recordTime"));
					a.setFileSize(d.getDouble("fileSize"));
					a.setDuration(d.getDouble("duration"));
					a.setImageFile1(d.getString("ImageFile1"));
					a.setImageFile2(d.getString("ImageFile2"));
					a.setImageFile3(d.getString("ImageFile3"));
					a.setMakeTime(d.getString("makeTime"));
					a.setShare(d.getInteger("share"));
					a.setIsChoiceness(d.getString("isChoiceness"));
					a.setWxIsShow(d.getString("wxIsShow"));
					a.setCoverImage(d.getString("CoverImage"));
					a.setOrderSeq(d.getInteger("OrderSeq"));
					a.setCreateDate(d.getString("createDate"));
					a.setDeletedFlag(d.getString("deletedFlag"));
					a.setAuditStatus(d.getString("auditStatus"));
					a.setName(d.getString("name"));
					a.setMemberName(d.getString("memberName"));
					a.setInstallAddress(d.getString("installAddress"));
					a.setCustomerId(d.getLong("customerID"));
					a.setCustName(d.getString("customerName"));
					a.setDeviceName(d.getString("deviceName"));
					a.setAuthor(d.getString("author"));
					a.setShareCount(d.getInteger("shareCount"));
					a.setCommentCount(d.getInteger("commentCount"));
					a.setListenCount(d.getInteger("ListenCount"));
					a.setListenCount20(d.getInteger("ListenCount20"));
					a.setNeworderseq(d.getInteger("neworderseq"));
					a.setRawJson(d.toJSONString());
					
					try {
						
						InputStream inputStream=getConnection(a.getServerFileNameFull()).execute().bodyStream();
						
						String localFileName = fileUploader.uploadFile(inputStream, a.getServerFileName(), "audio");
						a.setLocalFileName(localFileName);
						TAudio au = audioMapper.selectById(a.getId());
						if(au!=null) {
							audioMapper.updateById(a);
						}else {
							audioMapper.insert(a);
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				
				TExcCfg entity=new TExcCfg();
				entity.setCfgKey("ld_currPage");
				entity.setCfgVal(currPage+"");
				cfgMapper.updateById(entity);
				
				currPage++;
				
				if(ja.size()<10) {
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} while (count>60);
		
		
		return "";
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
