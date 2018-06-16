package com.sarakeal.helper;

import com.sarakeal.annotation.Autowired;
import com.sarakeal.util.ReflectionUtil;
import org.apache.commons.lang.ArrayUtils;

import java.lang.reflect.Field;
import java.util.Map;

public class IoCHelper {

    /**
     * 这一段的作用比较清晰，分为以下几步：
     * 1.遍历BeanMap
     * 2.通过反射获取类的成员变量，接下来通过遍历找到带有Autowired注解的成员变量
     * 3.从BeanMap找到需要注入的实例
     * 4.通过反射修改该成员变量的值
     */
    static {
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if (!beanMap.isEmpty()) {
            for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();
                Field[] fields = beanClass.getDeclaredFields();
                if (!ArrayUtils.isEmpty(fields)) {
                    for (Field field : fields) {
                        if (field.isAnnotationPresent(Autowired.class)) {
                            Class<?> beanFieldClass = field.getType();
                            Object beanFieldInstance = beanMap.get(beanFieldClass);
                            if (beanFieldInstance != null) {
                                ReflectionUtil.setField(beanInstance, field, beanFieldInstance);
                            }
                        }
                    }
                }
            }
        }
    }
}
