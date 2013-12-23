package com.itzhai.javanote.test.typeinfo;

class A {
	// 静态代码库，在第一次被加载时执行，通过打印信息知道该类什么时候被加载
	static { System.out.println("Loading A"); }
}
class B {
	static { System.out.println("Loading B"); }
}
class C {
	static { System.out.println("Loading C"); }
}
public class Load {
	public static void main(String[] args){
		System.out.println("execute main...");
		new A();
		System.out.println("after new A");
		try {
			Class.forName("com.itzhai.test.type.B");
		} catch (ClassNotFoundException e) {
			System.out.println("cloud not find class B");
		}
		System.out.println("after Class.forName B");
		new C();
		System.out.println("after new C");
	}
}
