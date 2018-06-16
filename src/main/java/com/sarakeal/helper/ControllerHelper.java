package com.sarakeal.helper;

import com.sarakeal.annotation.Action;
import com.sarakeal.bean.Handler;
import com.sarakeal.bean.Request;
import org.apache.commons.lang.ArrayUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ControllerHelper {

    /**
     * 存放请求和映射器的映射
     */
    private static final Map<Request, Handler> ACTION_MAP = new HashMap<Request, Handler>();

    /**
     * 以下代码作用为填充ACTION_MAP,实际步骤为：
     * 1.获取并遍历所有有Controller注解的类
     * 2.获取该类中定义的方法，遍历它们，找到声明了Action注解的方法
     * 3.获取并验证Action注解的value,形式为method:path
     * 4.根据请求方法和路径初始化一个Request,根据类和方法初始化Handler
     * 5.将Request和Handler添加进ACTION_MAP中
     */
    static {
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if (!controllerClassSet.isEmpty()) {
            for (Class<?> controllerClass : controllerClassSet) {
                Method[] methods = controllerClass.getDeclaredMethods();
                if (!ArrayUtils.isEmpty(methods)) {
                    for (Method method : methods) {
                        if (method.isAnnotationPresent(Action.class)) {
                            Action action = method.getAnnotation(Action.class);
                            String requestMapping = action.value();
                            // 验证URL的映射规则 例子:"GET:/list"
                            if(requestMapping.matches("\\w+:/\\w*")) {
                                String[] array = requestMapping.split(":");
                                if (!ArrayUtils.isEmpty(array) && array.length == 2) {
                                    String requestMethod = array[0];
                                    String requestPath = array[1];
                                    Request request = new Request(requestMethod, requestPath);
                                    Handler handler = new Handler(controllerClass, method);
                                    ACTION_MAP.put(request, handler);
                                }
                            }
                        }

                    }
                }
            }
        }
    }

    public static Handler getHandler(String requestMethod, String requestPath) {
        Request request = new Request(requestMethod, requestPath);
        return ACTION_MAP.get(request);
    }
}
