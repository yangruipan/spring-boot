package com.buy.util;

import org.springframework.util.StringUtils;
import org.thymeleaf.util.ListUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Administrator on 2017/11/6.
 */
public class TMap {

    /**
     * 格式化map，去除无value的key
     * @param queryMap
     * @return
     */
    public static Map<String,Object> getNotEmptyMap(Map<String,Object> queryMap){
        Map<String,Object> resultMap = new HashMap<>();
        for (Map.Entry<String,Object> entry : queryMap.entrySet()) {
            if (entry.getValue()!=null && !StringUtils.isEmpty(entry.getValue().toString())) {
                if(!"undefined".equals(entry.getValue().toString())){
                    resultMap.put(entry.getKey(),entry.getValue());
                }
            }
        }
        return resultMap.size()>0 ? resultMap : null;
    }

    /**
     * 将list<Map>转map
     * @param map
     * @param mapKey map的key
     * @param valueKey map的value
     * @return
     */
    public static Map<String,Object> parseMap(List<Map<String, Object>> map,String mapKey,String valueKey){
        Map<String,Object> resultMap = new HashMap<>();
        if(map != null && map.size()>0){
            for(Map m : map){
                if(m.get(mapKey) != null && m.get(valueKey) != null){
                    resultMap.put(m.get(mapKey).toString(),m.get(valueKey));
                }
            }
        }
        return resultMap;
    }
    
    /**把对象转换为map
     * @param obj
     * @return
     * @throws Exception
     */
    public static Map<String, Object> objectToMap(Object obj,boolean needNull) throws Exception {    
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
            Object value = getter!=null ? getter.invoke(obj) : null;  
            if((needNull && null == value)|| null!=value){
            	map.put(key, value);  
            }
        }    
  
        return map;  
    } 
    
    
    /**map转换为对象
     * @param map
     * @param beanClass
     * @return
     * @throws Exception
     */
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
    /**
     * 将任意list转换为map<keyName.value, object>
     * @param list
     * @param keyName 字段名称
     * @return
     * @throws Exception
     * <keyname.value
     */
    public static Map<String,Object> parseList(List<?> list, String keyName){
        Map<String, Object> resultMap = new HashMap<>();
        try{
            if (!ListUtils.isEmpty(list)) {
                for(Object object:list){
                    PropertyDescriptor prop = new PropertyDescriptor(keyName, object.getClass());
                    if (prop.getReadMethod().invoke(object) == null){//判断字段值是否存在
                        continue;
                    }
                    resultMap.put(prop.getReadMethod().invoke(object).toString(),object);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 将map倒序存放
     * @param parameter
     * @return
     */
    public static Map<String,Object> sortReversekMap(Map<String,Object> parameter){
        TreeMap<String,Object> thisMap = new TreeMap<>(parameter);
        Map<String,Object> resultMap = thisMap.descendingMap();
        return resultMap;
    }

}
