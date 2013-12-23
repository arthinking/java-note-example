package com.itzhai.javanote.test.typeinfo;

interface X{}
interface Y{}
interface Z{}
class Letter {
	Letter(){};
	Letter(int i){};
}
class NewLetter extends Letter implements X, Y, Z{
	NewLetter(){ super(1); };
}
public class ClassTest {
	
	/**
	 * 打印类型信息
	 * @param c
	 */
	static void printInfo(Class c){
		// getName()获得全限定的类名
		System.out.println("Class name： " + c.getName() + " is interface? " + c.isInterface());
		// 获得不包含包名的类名
		System.out.println("Simple name: " + c.getSimpleName());
		// 获得全限定类名
		System.out.println("Canonical name: " + c.getCanonicalName());
	}
	
	public static void main(String[] args){
		Class c = null;
		try {
			// 获得Class引用
			c = Class.forName("com.itzhai.test.type.NewLetter");
		} catch (ClassNotFoundException e) {
			System.out.println("Can not find com.itzhai.test.type.NewLetter");
			System.exit(1);
		}
		// 打印接口类型信息
		for(Class face : c.getInterfaces()){
			printInfo(face);
		}
		// 获取超类Class引用
		Class up = c.getSuperclass();
		Object obj = null;
		try {
			// 通过newInstance()方法创建Class的实例
			obj = up.newInstance();
		} catch (InstantiationException e) {
			System.out.println("Can not instantiate");
		} catch (IllegalAccessException e) {
			System.out.println("Can not access");
		}
		// 打印超类类型信息
		printInfo(obj.getClass());
	}
}
