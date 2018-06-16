package com.sarakeal.helper;

import com.sarakeal.util.ClassUtil;

public class HelperLoader {
    public static void init() {
        Class<?>[] classList  = {
                ClassHelper.class,
                BeanHelper.class,
                IoCHelper.class,
                ControllerHelper.class
        };
        for(Class<?> cls : classList) {
            ClassUtil.loadClass(cls.getName(), true);
        }
    }
}
