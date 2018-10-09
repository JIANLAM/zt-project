package com.szcti.lcloud.exchange.dcn;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.szcti.lcloud.exchange.controller.SyncController;
import com.szcti.lcloud.exchange.service.DcnSycnService;

@Component
public class SyncData {

	private static final Logger log = LoggerFactory.getLogger(SyncData.class);

	@Autowired
	DBUtil dbUtil;
	@Autowired
	DcnSycnService dcnSycnService;

	public void syncREADER(int limit) {
		JdbcTemplate jt = dbUtil.getJdbcTemplate();
		List<String> rids = jt.queryForList("select ROWID from reader", null, String.class);

		String topic = "dcn/" + "INTERLIB.READER" + "/" + "INSERT";
		int i = 1;
		int sp = 1;

		for (String rid : rids) {

			Map<String, Object> map = jt.queryForMap("select * from READER where rowid='" + rid + "'");
			map.put("ROWID", rid);
			String content = JsonUtil.toJSONString(map);
			
			//MqttClientUtil.publish(topic, content);
			dcnSycnService.handle(topic, content,1);
			
			log.info("sync data::  topic: {}, content: {}", topic, content);

			if (limit > 0) {
				i++;
				if (i > limit) {
					break;
				}
			}
			sp++;
			if (sp % 500 == 0) {
				try {
					Thread.sleep(2 * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		SyncController.isSync1 = 0;
	}

	public void syncBIBLIOS(int limit) {
		JdbcTemplate jt = dbUtil.getJdbcTemplate();
		List<String> rids = jt.queryForList("select ROWID from BIBLIOS", null, String.class);

		String topic = "dcn/" + "INTERLIB.BIBLIOS" + "/" + "INSERT";
		int i = 0;
		int sp = 1;
		for (String rid : rids) {

			Map<String, Object> map = jt.queryForMap("select * from BIBLIOS where rowid='" + rid + "'");
			map.put("ROWID", rid);
			String content = JsonUtil.toJSONString(map);
//			MqttClientUtil.publish(topic, content);
			log.info("sync data:  topic: {}, content: {}", topic, content);

			dcnSycnService.handle(topic, content,1);
			
			if (limit > 0) {
				i++;
				if (i > limit) {
					break;
				}
			}

			sp++;
			if (sp % 500 == 0) {
				try {
					Thread.sleep(2 * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
		SyncController.isSync2 = 0;
	}

	public void syncHOLDING(int limit) {
		JdbcTemplate jt = dbUtil.getJdbcTemplate();
		List<String> rids = jt.queryForList("select ROWID from HOLDING", null, String.class);

		String topic = "dcn/" + "INTERLIB.HOLDING" + "/" + "INSERT";
		int i = 0;
		int sp = 1;
		for (String rid : rids) {

			Map<String, Object> map = jt.queryForMap("select * from HOLDING where rowid='" + rid + "'");
			map.put("ROWID", rid);
			String content = JsonUtil.toJSONString(map);
//			MqttClientUtil.publish(topic, content);
			log.info("sync data:  topic: {}, content: {}", topic, content);
			dcnSycnService.handle(topic, content,1);
			
			if (limit > 0) {
				i++;
				if (i > limit) {
					break;
				}
			}

			sp++;
			if (sp % 500 == 0) {
				try {
					Thread.sleep(2 * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		SyncController.isSync3 = 0;
	}

}
