package com.askarakhmetov.java.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Пример создания прокси через стандартный JDK Proxy
 * <p>
 * <ol>
 *  <li>Прокси создаётся только для интерфейсов</li>
 *  <li>Все вызовы методов делегируются через InvocationHandler</li>
 * </ol>
 */
public class JDKProxySample {

    interface HelloService {
        void sayHello(String name);
    }

    static class RealHelloService implements HelloService {
        public void sayHello(String name) {
            System.out.println("RealHelloService. Hello, " + name);
        }
    }

    static class LoggingHandler implements InvocationHandler {
        private final Object target;

        LoggingHandler(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("LoggingHandler. Calling method: " + method.getName());
            return method.invoke(target, args);
        }
    }

    public static void main(String[] args) {
        HelloService original = new RealHelloService();

        HelloService proxy = (HelloService) Proxy.newProxyInstance(
                HelloService.class.getClassLoader(),
                new Class[]{HelloService.class},
                new LoggingHandler(original)
        );

        proxy.sayHello("Java");
    }
}
