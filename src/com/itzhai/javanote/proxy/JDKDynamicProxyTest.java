package com.itzhai.javanote.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


interface Subject {
    abstract public void request();
}

class RealSubject implements Subject {

    public RealSubject() {}

    public void request() {
        System.out.println("真实的请求.");
    }
}

class DynamicSubject implements InvocationHandler {
    private Object sub;

    public DynamicSubject() {}

    public DynamicSubject(Object obj) {
        sub = obj;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(sub);
        method.invoke(sub, args);
        return null;
    }
}


public class JDKDynamicProxyTest {

    public static void main(String[] args){
        // 创建被代理类
        RealSubject rs = new RealSubject();
        // 通过被代理类初始化一个代理处理器，在newProxyInstance()中作为参数生成代理
        InvocationHandler ds = new DynamicSubject(rs);
        Class<?> cls = rs.getClass();
        //生成代理对象
        Subject subject = (Subject) Proxy.newProxyInstance(
                cls.getClassLoader(), cls.getInterfaces(), ds);
        //通过代理对象调用真实方法
        subject.request();
    }
}
