package com.sarakeal.helper;

import com.sarakeal.util.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BeanHelper {

    /**
     * 存放Bean类与Bean实例的映射关系
     * 由此可以看到，所有管理的对象都是单例
     */
    private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<Class<?>, Object>();

    static {
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
        for (Class<?> beanClass : beanClassSet) {
            Object obj = ReflectionUtil.newInstance(beanClass);
            BEAN_MAP.put(beanClass, obj);
        }
    }

    public static Map<Class<?>, Object> getBeanMap() {
        return BEAN_MAP;
    }

    /**
     * 获取Bean
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> tClass) {
        if(!BEAN_MAP.containsKey(tClass)) {
            throw new RuntimeException("can not get bean by class" + tClass);
        }
        return (T) BEAN_MAP.get(tClass);
    }
}
