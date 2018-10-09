package com.szcti.lcloud.exchange.idc.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
public class MyExceptionHandler {
	
    @ExceptionHandler(MyRuntimeException.class)
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> handlerMyRuntimeException(MyRuntimeException ex) {
        Map<String,Object> result = new HashMap<>();
        result.put("message", ex.getMessage());
        result.put("status", ex.getR().getStatus());
        result.put("timestamp", ex.getR().getTimestamp());
        return result;
    }
}