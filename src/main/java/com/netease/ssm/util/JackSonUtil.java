package com.netease.ssm.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JackSonUtil {
    private static final Logger logger = LoggerFactory.getLogger(JackSonUtil.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    public static Map<String, Object> objToStrMap(Object obj) {
        Map<String, Object> objMap = objectMapper.convertValue(obj, Map.class);
        if (objMap != null) {
            Map<String, Object> strMap = new HashMap<>(objMap.size(), 1);
            objMap.entrySet().stream().filter(entry -> entry.getValue() != null).forEach(entry -> {
                strMap.put(entry.getKey(), entry.getValue());
            });
            return strMap;
        }
        return new HashMap<>();
    }

    public static <T> T convertJsonToBean(String json, Class<T> clazz) {
        if (!StringUtil.isFine(json)) {
            return null;
        }
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            logger.warn("JackSonUtil.convertJsonToBean failed! json:" + json, e);
        }
        return null;
    }


    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        if(obj == null)
            return null;

        Map<String, Object> map = new HashMap<String, Object>();

        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            if (key.compareToIgnoreCase("class") == 0) {
                continue;
            }
            Method getter = property.getReadMethod();
            if(getter ==null){
                continue;
            }
            Object value =  getter.invoke(obj) ;
            if(value == null){
                continue;
            }
            if(getter.getReturnType() == Date.class){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                value = sdf.format(value);
            }
            map.put(key, value);
        }

        return map;
    }

    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
        if (map == null)
            return null;

        Object obj = beanClass.newInstance();

        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            Method setter = property.getWriteMethod();
            if (setter != null) {
                setter.invoke(obj, map.get(property.getName()));
            }
        }

        return obj;
    }
}
