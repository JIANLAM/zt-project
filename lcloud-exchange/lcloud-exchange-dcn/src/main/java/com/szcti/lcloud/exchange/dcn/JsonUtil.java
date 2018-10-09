package com.szcti.lcloud.exchange.dcn;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JsonUtil {

	public static String toJSONString(Object object) {
		JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
		return JSON.toJSONString(object,
				SerializerFeature.WriteNullStringAsEmpty, //
				SerializerFeature.WriteNullListAsEmpty,//
				SerializerFeature.WriteMapNullValue, //
				SerializerFeature.WriteDateUseDateFormat
				);
	}
}
