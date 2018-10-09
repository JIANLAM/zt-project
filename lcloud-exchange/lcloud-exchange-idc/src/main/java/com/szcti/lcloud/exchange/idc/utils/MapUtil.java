package com.szcti.lcloud.exchange.idc.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;    

public class MapUtil {

	public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
		if (map == null)
			return null;
		Object obj = beanClass.newInstance();
		org.apache.commons.beanutils.BeanUtils.populate(obj, map);
		return obj;
	}

	public static Map<?, ?> objectToMap(Object obj) {
		if (obj == null) {
			return null;
		}
		return new org.apache.commons.beanutils.BeanMap(obj);
	}
	
	@SuppressWarnings("rawtypes")
	public static Map toMap(Object bean) {    
        Class<? extends Object> clazz = bean.getClass();    
        Map<Object, Object> returnMap = new HashMap<Object, Object>();    
        BeanInfo beanInfo = null;    
        try {    
            beanInfo = Introspector.getBeanInfo(clazz);    
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();    
            for (int i = 0; i < propertyDescriptors.length; i++) {    
                PropertyDescriptor descriptor = propertyDescriptors[i];    
                String propertyName = descriptor.getName();    
                if (!propertyName.equals("class")) {    
                    Method readMethod = descriptor.getReadMethod();    
                    Object result = null;    
                    result = readMethod.invoke(bean, new Object[0]);    
                    if (null != propertyName) {    
                        propertyName = propertyName.toString();    
                    }    
                    if (null != result) {    
                        result = result.toString();    
                    }    
                    returnMap.put(propertyName, result);    
                }    
            }    
        } catch (IntrospectionException e) {    
            System.out.println("分析类属性失败");    
        } catch (IllegalAccessException e) {    
            System.out.println("实例化 JavaBean 失败");    
        } catch (IllegalArgumentException e) {    
            System.out.println("映射错误");    
        } catch (InvocationTargetException e) {    
            System.out.println("调用属性的 setter 方法失败");    
        }    
        return returnMap;    
    } 
}
