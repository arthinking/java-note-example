package com.itzhai.javanote.test.typeinfo;

class Data1{
	static final int a = 1;
	static final double b = Math.random();
	static {
		System.out.println("init Data1...");
	}
}

class Data2{
	static int a = 12;
	static {
		System.out.println("init Data2...");
	}
}

class Data3{
	static int a = 23;
	static {
		System.out.println("init Data3...");
	}
}

public class ClassTest2 {
	public static void main(String[] args){
		System.out.println("Data1.class: ");
		Class data1 = Data1.class;
		System.out.println(Data1.a);  // 没有初始化Data1
		System.out.println(Data1.b);  // 初始化了Data1
		System.out.println(Data2.a);  // 初始化了Data2
		try {
			Class data3 = Class.forName("com.itzhai.test.type.Data3");  // 初始化了Data3
		} catch (ClassNotFoundException e) {
			System.out.println("can not found com.itzhai.test.type.Data3...");
		}
		System.out.println(Data3.a);
	}
}
