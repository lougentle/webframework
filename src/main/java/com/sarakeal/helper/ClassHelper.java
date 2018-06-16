package com.sarakeal.helper;

import com.sarakeal.annotation.Controller;
import com.sarakeal.annotation.Service;
import com.sarakeal.util.ClassUtil;

import java.util.HashSet;
import java.util.Set;

public class ClassHelper {

    /**
     * 存放所有加载类的集合
     */
    private static final Set<Class<?>> CLASS_SET;

    static {
        String basePackage = ConfigHelper.getAppBasePackage();
        CLASS_SET = ClassUtil.getClassSet(basePackage);
    }

    public static Set<Class<?>> getBeanClassSet() {
        Set<Class<?>> classes = new HashSet<>();
        classes.addAll(getServiceClassSet());
        classes.addAll(getControllerClassSet());
        return classes;
    }

    /**
     * 获取应用包名下的所有Controller类
     * 方式：查找CLASS_SET中有声明Controller注解的类
     * @return
     */
    public static Set<Class<?>> getControllerClassSet() {
        Set<Class<?>> classes = new HashSet<>();
        for(Class<?> cls : CLASS_SET) {
            if(cls.isAnnotationPresent(Controller.class)) {
                classes.add(cls);
            }
        }
        return classes;
    }

    /**
     * 获取应用包名下的所有Service类
     * @return
     */
    public static Set<Class<?>> getServiceClassSet() {
        Set<Class<?>> classes = new HashSet<>();
        for(Class<?> cls : CLASS_SET) {
            if(cls.isAnnotationPresent(Service.class)) {
                classes.add(cls);
            }
        }
        return classes;
    }

    public static Set<Class<?>> getClassSet() {
        return CLASS_SET;
    }
}
