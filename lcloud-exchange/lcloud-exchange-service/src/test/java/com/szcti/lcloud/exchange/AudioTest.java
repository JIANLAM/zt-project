package com.szcti.lcloud.exchange;

import java.io.IOException;

import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;

import com.alibaba.fastjson.JSONObject;

public class AudioTest {

	public static void main(String[] args) throws IOException {
		JSONObject o1 = new JSONObject();
		JSONObject o2 = new JSONObject();
		o2.put("mac", "1831BF0E8B1C");
		o2.put("custCode", "C2018080009");
		o2.put("custPwd", "20180821");
		o2.put("currPage", "1");
		o2.put("recordPerPage", "10");

		o1.put("data", o2);
		System.out.println(o1.toJSONString());

		String body = Jsoup.connect("http://wx.welangdu.com/Readweb/SI/IService.svc/RequesAudioListPageByCustCode")
				.ignoreContentType(true)
				.header("Content-Type", "application/json").method(Method.POST).requestBody(o1.toJSONString()).execute()
				.body();
		System.out.println(body);

	}

}
