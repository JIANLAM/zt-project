package com.szcti.lcloud.exchange.dcn;

public class IdWork {

	static Long init;
	static int step = 0;

	static {
		init = System.currentTimeMillis();
	}

	public synchronized static Long getId() {
		step++;
		return init + step;
	}

}
