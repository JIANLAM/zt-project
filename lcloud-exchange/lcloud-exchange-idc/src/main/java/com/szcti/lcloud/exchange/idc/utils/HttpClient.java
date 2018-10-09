package com.szcti.lcloud.exchange.idc.utils;

import java.net.URLEncoder;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class HttpClient {

	public static void main(String[] args) throws Exception {
		System.out.println(doGet("http://localhost:8080/api/user/456123"));
	}
	
	public static String doPost(String url,String reqData) throws Exception {
		HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		//设置超时时间，请求时间，连接时间，读取时间，都是1秒，这里都是用静态变量
		httpRequestFactory.setConnectionRequestTimeout(1000);
		httpRequestFactory.setConnectTimeout(1000);
		httpRequestFactory.setReadTimeout(1000);
		//获取对话
		RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
		HttpHeaders headers = new HttpHeaders();
		//JSON contentType
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		//我们发起 HTTP 请求还是最好加上"Connection","close" ，有利于程序的健壮性
		headers.set("Connection","close");
//		headers.set("token", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0IiwiZXhwIjoxNTI5NDgzMDQyLCJpYXQiOjE1Mjk0NzU4NDJ9.ReotI6h9Kcnw-fde9MpQLv3Yl8F5SUOybwowWyT6noRWQrifTB8VaWTT5Ku9HPDh04Z0vLihsDwhHQiuaYA0lA");
		HttpEntity<String> requestEntity = new HttpEntity<>(reqData, headers);

		ResponseEntity<String> response;
		//发起请求
	    response = restTemplate.exchange(url,
	            HttpMethod.POST,
	            requestEntity,
	            String.class);
	    //获取返回值
	    System.out.println("response.getStatusCodeValue(): " + response.getStatusCodeValue());
	    return response.getBody();
	}
	
	public static String doGet(String url) throws Exception {
		HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		//设置超时时间，请求时间，连接时间，读取时间，都是1秒，这里都是用静态变量
		httpRequestFactory.setConnectionRequestTimeout(1000);
		httpRequestFactory.setConnectTimeout(1000);
		httpRequestFactory.setReadTimeout(1000);
		//获取对话
		RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
		HttpHeaders headers = new HttpHeaders();
		//JSON contentType
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		//我们发起 HTTP 请求还是最好加上"Connection","close" ，有利于程序的健壮性
		headers.set("Connection","close");
//		headers.set("token", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0IiwiZXhwIjoxNTI5NDgzMDQyLCJpYXQiOjE1Mjk0NzU4NDJ9.ReotI6h9Kcnw-fde9MpQLv3Yl8F5SUOybwowWyT6noRWQrifTB8VaWTT5Ku9HPDh04Z0vLihsDwhHQiuaYA0lA");
		HttpEntity<String> requestEntity = new HttpEntity<>("content", headers);
		ResponseEntity<String> response;
		//发起请求
	    response = restTemplate.exchange(String.format(url,URLEncoder.encode("content", "utf-8")),
	            HttpMethod.GET,//get
	            requestEntity,
	            String.class);
	    //获取返回值
	    System.out.println("response.getStatusCodeValue(): " + response.getStatusCodeValue());
	    return response.getBody();
	}
}
