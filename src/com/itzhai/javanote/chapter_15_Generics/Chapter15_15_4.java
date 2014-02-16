package com.itzhai.javanote.chapter_15_Generics;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import temp.net.mindview.util.TwoTuple;

/**
 * 15 泛型
 *   15 混型
 *     4 与动态代理混合
 *     
 * 可以使用动态代理来创建一种比装饰器更贴近混型模型的机制，通过使用动态代理，所产生的类的动态类型
 * 将会是已经混入的组合类型。
 * 由于动态代理的限制，每个被混入的类都必须是某个接口的实现
 * 
 */
class MixinProxy implements InvocationHandler {
    Map<String,Object> delegatesByMethod;
    public MixinProxy(TwoTuple<Object,Class<?>>... pairs) {
        delegatesByMethod = new HashMap<String,Object>();
        for(TwoTuple<Object,Class<?>> pair : pairs) {
            for(Method method : pair.second.getMethods()) {
                String methodName = method.getName();
                // The first interface in the map
                // implements the method.
                if (!delegatesByMethod.containsKey(methodName))
                    delegatesByMethod.put(methodName, pair.first);
            }
        }
    } 
    public Object invoke(Object proxy, Method method,
            Object[] args) throws Throwable {
        String methodName = method.getName();
        Object delegate = delegatesByMethod.get(methodName);
        return method.invoke(delegate, args);
    }
    @SuppressWarnings("unchecked")
    public static Object newInstance(TwoTuple... pairs) {
        Class[] interfaces = new Class[pairs.length];
        for(int i = 0; i < pairs.length; i++) {
            interfaces[i] = (Class)pairs[i].second;
        }
        ClassLoader cl =
                pairs[0].first.getClass().getClassLoader();
        return Proxy.newProxyInstance(
                cl, interfaces, new MixinProxy(pairs));
    }
}   

public class Chapter15_15_4 {
    
    public static void main(String[] args) {
        Object mixin = MixinProxy.newInstance(
                tuple(new BasicImp(), Basic.class),
                tuple(new TimeStampedImp(), TimeStamped.class),
                tuple(new SerialNumberedImp(),SerialNumbered.class));
        Basic b = (Basic)mixin;
        TimeStamped t = (TimeStamped)mixin;
        SerialNumbered s = (SerialNumbered)mixin;
        b.set("Hello");
        System.out.println(b.get());
        System.out.println(t.getStamp());
        System.out.println(s.getSerialNumber());
    }
}

// 缺点：因为只有动态类型而不是非静态类型才包含所有的混入类型，因此这仍不如C++的方式好，
// 因为可以再具有这些类型的对象上调用方法之前，你被强制要求必须先将这些对象向下转型为恰当的类型。
// 但是，这明显更接近于真正的混型。

/* Output: (Sample)
Hello
1132519137015
1
*///:~