package com.szcti.lcloud.exchange.idc.service;

import com.alibaba.fastjson.JSONObject;
import com.szcti.lcloud.exchange.idc.vo.R;

public interface BookService {

	R bookSync(JSONObject reqJson);
}
