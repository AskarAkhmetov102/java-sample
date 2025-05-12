package com.askarakhmetov.java.core;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * <p>Пример создания прокси через библиотеку CGLIB</p>
 * <p>В отличие от JDK Proxy работает с обычными классами</p>
 * <p>Для запуска примера нужно использовать VM Option:</p>
 * <code>--add-opens java.base/java.lang=ALL-UNNAMED</code>
 */
public class CglibProxySample {

    static class RealService {
        public void doWork() {
            System.out.println("RealService. Doing real work...");
        }
    }

    static class LoggingInterceptor implements MethodInterceptor {
        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            System.out.println("LoggingInterceptor. Before: " + method.getName());
            Object result = proxy.invokeSuper(obj, args); // вызов оригинального метода
            System.out.println("LoggingInterceptor. After: " + method.getName());
            return result;
        }
    }

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(RealService.class);
        enhancer.setCallback(new LoggingInterceptor());

        RealService proxy = (RealService) enhancer.create();
        proxy.doWork();
    }
}
