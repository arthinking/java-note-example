package com.itzhai.javanote.test.typeinfo;

import java.lang.reflect.Proxy;

import com.itzhai.javanote.entity.DynamicProxyHandler;
import com.itzhai.javanote.entity.RealSubject;
import com.itzhai.javanote.entity.Subject;

public class SimpleDynamicProxy {

	public static void consumer(Subject subject){
		subject.doSomething();
		subject.somethingElse("haha~~");
	}
	
	public static void main(String[] args){
		// 创建真实的对象
		RealSubject real = new RealSubject();
		consumer(real);
		// 通过调用该方法创建动态代理
		Subject proxy = (Subject)Proxy.newProxyInstance(
				Subject.class.getClassLoader(), // 类加载器
				new Class[]{Subject.class}, // 你希望该代理实现的接口列表
				new DynamicProxyHandler(real));  // InvocationHandler接口的一个实现
		consumer(proxy);
	}
}
