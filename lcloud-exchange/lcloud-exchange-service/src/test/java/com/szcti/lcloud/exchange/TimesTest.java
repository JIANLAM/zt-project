package com.szcti.lcloud.exchange;

import com.szcti.lcloud.exchange.utils.SystemClock;

public class TimesTest {

	public static void main(String[] args) {
		for (int i=0;i<20;i++) {
			
			System.out.println(System.nanoTime());
			//System.out.println(SystemClock.now());
		}
	}

}
