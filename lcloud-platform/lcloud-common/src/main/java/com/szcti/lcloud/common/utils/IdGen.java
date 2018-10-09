/**
 * Copyright &copy; 2016-2018 <a href="http://gitlab.ipubtrans.cn">ipubase</a> All rights reserved.
 */
package com.szcti.lcloud.common.utils;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * 封装各种生成唯一性ID算法的工具类.
 * @author fd
 * @date 2013-01-15
 */
public class IdGen {

	private static SecureRandom random = new SecureRandom();
	
	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/**
	 * 使用SecureRandom随机生成Long. 
	 */
	public static long randomLong() {
	//	return Math.abs(random.nextLong());
	//	return IdWorker.getId();
		return Long.valueOf(getRandom620(12));
	}


	public static String getDateUUId(){
		//格式化当前时间
		SimpleDateFormat sfDate = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String strDate = sfDate.format(new Date());
		//得到17位时间如：20170411094039080
		System.out.println("时间17位：" + strDate);
		//为了防止高并发重复,再获取3个随机数
		String random = getRandom620(3);
		//最后得到20位订单编号。
		String orderNum=strDate + random;
		System.out.println("订单号20位：" + strDate + random);
		return orderNum;
	}
	/**
	 * 获取6-10 的随机位数数字
	 * @param length	想要生成的长度
	 * @return result
	 */
	public  static String getRandom620(Integer length) {
		String result = "";
		Random rand = new Random();
		int n = 20;
		if (null != length && length > 0) {
			n = length;
		}
		int randInt = 0;
		for (int i = 0; i < n; i++) {
			randInt = rand.nextInt(10);
			result += randInt;
		}
		return result;
	}
	public static void main(String[] args) {
		System.out.println(randomLong());
		System.out.println(randomLong());
		System.out.println(randomLong());
		/*System.out.println(randomLong());
		System.out.println(getDateUUId());*/
	}

}
