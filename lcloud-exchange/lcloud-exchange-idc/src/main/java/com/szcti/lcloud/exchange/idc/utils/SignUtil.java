package com.szcti.lcloud.exchange.idc.utils;

import static java.util.Arrays.asList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.MapUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.szcti.lcloud.exchange.idc.entity.Book;
import com.szcti.lcloud.exchange.idc.exception.MyRuntimeException;
import com.szcti.lcloud.exchange.idc.vo.R;

/*
'============================================================================
'api说明：
'createSHA1Sign创建签名SHA1
'getSha1()Sha1签名
'============================================================================
'*/
public class SignUtil {

	public static void main(String[] args) throws Exception {
		SortedMap<String, String> sp = new TreeMap<>();
		sp.put("token", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0IiwiZXhwIjoxNTI4OTgxOTUyLCJpYXQiOjE1Mjg5NzQ3NTJ9.OrUDgnjMAyKvweTY_nQFDoWQCW9IC_uuIMQIBvA5jC2RiaHmYl_26DS1dDpIY2Rp-IbacXkXBUbIcyCuKu9jkQ");
		sp.put("noncestr", "23423424");
		sp.put("sign", "586143f26f66b513210515667871fea1a4645ab1");
		sp.put("timestamp", "1528965579");
		sp.put("format", "json");
		sp.put("v", "1.0");
		System.out.println("sha1之前:v=1.0&format=json&noncestr=23423424&token=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0IiwiZXhwIjoxNTI4OTgxOTUyLCJpYXQiOjE1Mjg5NzQ3NTJ9.OrUDgnjMAyKvweTY_nQFDoWQCW9IC_uuIMQIBvA5jC2RiaHmYl_26DS1dDpIY2Rp-IbacXkXBUbIcyCuKu9jkQ&timestamp=1528965579");
		System.out.println(createSHA1Sign(sp));
		JSONObject body = new JSONObject();
		body.put("a", "b");
		SortedMap<String, String> map = new TreeMap<>();
//		MapUtils.emptyIfNull(body).keySet().forEach(key -> {map.put(key, body.getString(key));});
		MapUtils.emptyIfNull(body).forEach((k,v) -> map.put(k, v.toString()));
		map.forEach((k, v) -> System.out.println("key:value = " + k + ":" + v));
		Book b1 = new Book();
		b1.setAuthor("a");
		b1.setBook_type("t1");
		SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Book.class, "author","book_type","summary"); 
		System.out.println(JSONObject.toJSONString(b1,filter,SerializerFeature.WriteMapNullValue));
		Book b2 = new Book();
		b2.setAuthor("b");
		b2.setBook_type("t1");
		List<Book> a = Arrays.asList(b1,b2);
		a.forEach(System.out::println);
		a.parallelStream().filter(distinctByKey(Book::getBook_type)).forEach(System.out::println);
	}
	
	public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
	    Set<Object> seen = ConcurrentHashMap.newKeySet();
	    return t -> seen.add(keyExtractor.apply(t));
	}
	
	public static String getTimeStamp() {
		return String.valueOf(System.currentTimeMillis() / 1000);
	}
	
	private static List<String> headers = asList("token","noncestr","timestamp","format","v");
	
	public static void checkSign(HttpServletRequest request,boolean chkTimestamp){
		String sign = request.getHeader("sign");
		if(chkTimestamp) {
			try {
				long reqTS = Long.parseLong(request.getHeader("timestamp"));
				if(System.currentTimeMillis()/1000 - reqTS > 360) {
					throw new MyRuntimeException(new R("SVC0003","请求过期"));
				}
			} catch (Exception e) {
				throw new MyRuntimeException(new R("SVC0003","请求过期"));
			}
		}
		try {
			if(!sign.equals(createSHA1Sign(request))) {
				throw new MyRuntimeException(new R("SVC0002","签名认证失败"));
			}
		} catch (Exception e) {
			throw new MyRuntimeException(new R("SVC0002","签名认证失败"));
		}
	}
	
	// 创建签名SHA1
	public static String createSHA1Sign(HttpServletRequest request) throws IOException{
		StringBuffer sb = new StringBuffer();
		SortedMap<String, String> map = new TreeMap<>();
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			if(headers.contains(key.toLowerCase())) {
				String value = request.getHeader(key);
				map.put(key, value);
			}
		}
		//get params
		if(request.getMethod().toUpperCase().equals(RequestMethod.GET.name())) {
			Enumeration<String> getParams = request.getParameterNames();
			while (getParams.hasMoreElements()) {
				String key = (String) getParams.nextElement();
				String value = request.getParameter(key);
				map.put(key, value);
			}
		}else{
			BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String line = null;
			StringBuilder bodySb = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				bodySb.append(line);
			}
			JSONObject body = JSONObject.parseObject(bodySb.toString());
			MapUtils.emptyIfNull(body).forEach((k,v) -> map.put(k, v.toString()));
		}
		
		map.forEach((k,v) -> {
			if (!"sign".equals(k)) {
				// 要采用URLENCODER的原始值！
				sb.append(k + "=" + v + "&");
			}
		});
		String params = sb.substring(0, sb.lastIndexOf("&"));
		System.out.println("sha1之前:" + params);
		String sha1Str = getSha1(params);
		System.out.println("SHA1签名为：" + sha1Str);
		return sha1Str;
	}

	// 创建签名SHA1
	public static String createSHA1Sign(SortedMap<String, String> signParams) throws Exception {
		StringBuffer sb = new StringBuffer();
		signParams.forEach((k,v) -> {
			if (!"sign".equals(k)) {
				// 要采用URLENCODER的原始值！
				sb.append(k + "=" + v + "&");
			}
		});
		String params = sb.substring(0, sb.lastIndexOf("&"));
		System.out.println("sha1之前:" + params);
		String sha1Str = getSha1(params);
		System.out.println("SHA1签名为：" + sha1Str);
		return sha1Str;
	}

	// Sha1签名
	public static String getSha1(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

		try {
			MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
			mdTemp.update(str.getBytes("UTF-8"));

			byte[] md = mdTemp.digest();
			int j = md.length;
			char buf[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
				buf[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(buf);
		} catch (Exception e) {
			return null;
		}
	}

}
