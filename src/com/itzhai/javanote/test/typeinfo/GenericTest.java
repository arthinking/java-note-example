package com.itzhai.javanote.test.typeinfo;

abstract class Animal {
}
class Dog extends Animal{
}
public class GenericTest {

	public static void main(String[] args){
		/*
		Class intCls = int.class;
		// 使用泛型限定Class指向的引用
		Class<Integer> genIntCls = int.class;
		// 没有使用泛型的Clas可以重新赋值为指向任何其他的Class对象
		intCls = double.class;
		// 下面的编译会出错
		// genIntCls = double.class;
		
		Class<?> intCls = int.class;
		intCls = String.class;
		
		Class<? extends Number> num = int.class;
		num = double.class;
		num = Number.class;
		
		
		Class<Dog> dogCls = Dog.class;
		try {
			Dog dog = dogCls.newInstance();
			// 下面的写法是错误的，只能返回 Class<? super Dog>类型
			// Class<Animal> animalCls = dogCls.getSuperclass(); 
			Class<? super Dog> animalCls = dogCls.getSuperclass();
			// 通过获取的超类引用，只能创建返回Object类型的对象
			Object obj = animalCls.newInstance();
			
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		*/
		
		Animal animal = new Dog();
		Class<Dog> dogCls = Dog.class;
		Dog dog = dogCls.cast(animal);
		// 或者直接使用下面的转型方法
		dog = (Dog)animal;
		
	}
}
