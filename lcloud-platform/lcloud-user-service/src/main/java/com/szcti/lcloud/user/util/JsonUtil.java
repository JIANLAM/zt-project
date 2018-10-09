package com.szcti.lcloud.user.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JsonUtil {

	public static String beanToJson(Object object) {
		JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
		return JSON.toJSONString(object, //
				SerializerFeature.WriteNullNumberAsZero, //
				SerializerFeature.WriteNullStringAsEmpty, //
				SerializerFeature.WriteNullListAsEmpty, //
				SerializerFeature.WriteMapNullValue, //
				SerializerFeature.WriteNullBooleanAsFalse, //
				SerializerFeature.WriteDateUseDateFormat);
	}

	public static String beanToJsonWithFilter(Object object, SerializeFilter... filters) {
		JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
		return JSON.toJSONString(object, //
				filters, //
				SerializerFeature.WriteNullNumberAsZero, //
				SerializerFeature.WriteNullStringAsEmpty, //
				SerializerFeature.WriteNullListAsEmpty, //
				SerializerFeature.WriteMapNullValue, //
				SerializerFeature.WriteNullBooleanAsFalse, //
				SerializerFeature.WriteDateUseDateFormat);//
	}
}
