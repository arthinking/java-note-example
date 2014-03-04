package com.itzhai.javanote.chapter_16_Array;

import java.util.ArrayList;
import java.util.List;

/**
 * 16 数组 
 *   5 数组与泛型
 *  
 * 通常，数组与泛型不能很好结合，你不能实例化具有参数化类型的数组
 * 
 * 擦出会移除参数类型信息，而数组必须知道它们所持有的确切类型，以强制保证类型安全。
 * 
 * 但是，你可以参数化数组本身的类型：
 */
class ClassParameter<T> {
	public T[] f(T[] arg) { return arg; }
}

class MethodParameter {
	public static <T> T[] f(T[] arg) { return arg; }
}

class ParameterizedArrayType {
	public static void main(String[] args) {
		Integer[] ints = { 1, 2, 3, 4, 5 };
	    Double[] doubles = { 1.1, 2.2, 3.3, 4.4, 5.5 };
	    // 编译器不能让你实例化泛型数组，但是，允许你创建对这种数组的引用：
	    Integer[] ints2 = new ClassParameter<Integer>().f(ints);
	    Double[] doubles2 = new ClassParameter<Double>().f(doubles);
	    ints2 = MethodParameter.f(ints);
	    doubles2 = MethodParameter.f(doubles);
	}
} ///:~

/**
 * 尽管不能创建实际的持有泛型的数组，但是你可以创建非泛型的数组，然后将其转型：
 */
class ArrayOfGenerics {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		List<String>[] ls;
		List[] la = new List[10];
		// 创建非泛型的数组并将其转型（成功创建泛型数组的唯一方法就是创建一个被擦除类型的新数组，然后对其转型）
		ls = (List<String>[])la; // "Unchecked" warning
		ls[0] = new ArrayList<String>();
		// 不能直接创建泛型数组
		// Compile-time checking produces an error:
		//! ls[1] = new ArrayList<Integer>();

		// Object数组可以引用泛型类型
		// The problem: List<String> is a subtype of Object
		Object[] objects = ls; // So assignment is OK
		// Compiles and runs without complaint:
		objects[1] = new ArrayList<Integer>();

		// 如果想直接点，可以创建一个这样创建，不过会有一个"unchecked"警告
		// However, if your needs are straightforward it is
		// possible to create an array of generics, albeit
		// with an "unchecked" warning:
		List<Integer>[] spheres = (List<Integer>[])new List[10];
		for(int i = 0; i < spheres.length; i++)
			spheres[i] = new ArrayList<Integer>();
	}
} ///:~

/**
 * 一般而言，泛型在类或方法的边界处很有效，而在类或方法的内部，擦出会使得泛型变得不适用，例如，你不能创建泛型数组：
 */
class ArrayOfGenericType<T> {
	T[] array; // OK
	@SuppressWarnings("unchecked")
	public ArrayOfGenericType(int size) {
		// 非法！
	    //! array = new T[size];
	    array = (T[])new Object[size]; // "unchecked" Warning
	}
	public T[] rep(){
		return array;
	}
	// 非法！
	//! public <U> U[] makeArray() { return new U[10]; }
} ///:~

/**
 * 由于视图创建的类型已被擦出，所以是类型未知的数组，
 * 你可以创建Object数组然后将其转型 ，但是会得到一个"不受检查"的警告，
 * 因为这个数组没有真正持有或动态检查类型T。
 * 
 * 也可以参考泛型章节的擦出的补偿之泛型数组
 */

public class Chapter16_05 {
	public static void main(String[] args) {
		ArrayOfGenericType<Integer> gen = new ArrayOfGenericType<Integer>(2);
		// 运行仍然报错：java.lang.ClassCastException: [Ljava.lang.Object; cannot be cast to [Ljava.lang.Integer;  
		// 参考泛型章节的泛型数组相关内容
		Integer[] intArr = gen.rep();
    }
}