package com.szcti.lcloud.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JSONUtil {
    /**
     * 将json转化为实体POJO
     * @param jsonStr
     * @param obj
     * @return
     */
    public static<T> Object JSONToObj(String jsonStr,Class<T> obj) {
        T t = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            t = objectMapper.readValue(jsonStr,
                    obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }
     
    /**
     * 将实体POJO转化为JSON
     * @param obj
     * @return
     * @throws JSONException
     * @throws IOException
     */
    public static<T> JSONObject objectToJson(T obj) throws JSONException, IOException {
        ObjectMapper mapper = new ObjectMapper();  
        // Convert object to JSON string  
        String jsonStr = "";
        try {
             jsonStr =  mapper.writeValueAsString(obj);
        } catch (IOException e) {
            throw e;
        }
        return new JSONObject(jsonStr);
    }

    /**
     * 将json字符串转化为实体POJO(支持单引号)
     * @param jsonStr
     * @param obj
     * @return
     */
    public static Object json2Object(String jsonStr, Class obj) {
        return net.sf.json.JSONObject.toBean(net.sf.json.JSONObject.fromObject(jsonStr), obj);
    }

    /**
     * 将json转化为Map
     * @param jsonStr
     * @return
     */
    public static Map<String,String> json2Map(String jsonStr) {
        Map<String, String> map = new HashMap(16);
        // 将json字符串转换成jsonObject
        net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(jsonStr);
        Iterator ite = jsonObject.keys();
        // 遍历jsonObject数据,添加到Map对象
        while (ite.hasNext()) {
            String key = ite.next().toString();
            String value = jsonObject.get(key).toString();
            map.put(key, value);
        }
        return map;
    }
    
    public static String object2String(Object obj) {
    	String s = net.sf.json.JSONSerializer.toJSON(obj).toString();
    	return s ;
    }

   /* public static void main(String[] args)  {
    	System.out.println(object2String(R.error(400, "000")));
    }*/
}