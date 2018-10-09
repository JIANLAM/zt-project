package com.szcti.lcloud.exchange.idc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.szcti.lcloud.exchange.idc.security.Sign;
import com.szcti.lcloud.exchange.idc.service.BookService;

@RestController
@RequestMapping("api")
public class ApiSyncController {

	@Autowired
	BookService bookService;
	
	@PreAuthorize("hasRole('USER')")
    @RequestMapping(value="/book", method = RequestMethod.POST)
	@Sign//开启签名认证
	ResponseEntity<?> addUser(HttpServletRequest request,@RequestBody JSONObject reqJson) {
		return ResponseEntity.ok(bookService.bookSync(reqJson));
    }
	
	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value="/order/{order_no}", method=RequestMethod.GET)
	@Sign
	ResponseEntity<?> getPurchaseOrder(HttpServletRequest request,@PathVariable(value="order_no") Long order_no) { 
	    return ResponseEntity.ok(order_no);
	}
	
	@PreAuthorize("hasRole('USER')")
    @RequestMapping(value="/order", method = RequestMethod.POST)
	@Sign
	ResponseEntity<?> addPurchaseOrder(HttpServletRequest request,@RequestBody JSONObject reqJson) {
		return ResponseEntity.ok(reqJson);
    }
	
	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value="/order/lend/{order_no}", method=RequestMethod.GET)
	@Sign
	ResponseEntity<?> getLendBuyOrder(HttpServletRequest request,@PathVariable(value="order_no") Long order_no) { 
	    return ResponseEntity.ok(order_no);
	}
	
	@PreAuthorize("hasRole('USER')")
    @RequestMapping(value="/order/lend", method = RequestMethod.POST)
	@Sign
	ResponseEntity<?> addLendBuyOrder(HttpServletRequest request,@RequestBody JSONObject reqJson) {
		return ResponseEntity.ok(reqJson);
    }
}
