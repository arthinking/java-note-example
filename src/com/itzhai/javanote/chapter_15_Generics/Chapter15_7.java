package com.itzhai.javanote.chapter_15_Generics;

/**
 * 15 泛型  
 *   7 擦除的神秘之处
 *   
 * 看个奇怪的问题，考虑下面输出的结果：
 * Class c1 = new ArrayList<String>().getClass();
 * Class c2 = new ArrayList<Integer>().getClass();
 * System.out.println(c1 == c2);
 * 输出的结果竟然是true。
 * 
 * 下面我们用Class.getTypeParameters()方法返回TypeVariable对象数组看个究竟：
 * System.out.println(Arrays.toString(c1.getTypeParameters()));
 * System.out.println(Arrays.toString(c2.getTypeParameters()));
 * 我们发现输出结果为：
 * [E]
 * [E]
 * 
 * 这里只是参数的占位符，所以，在泛型代码内部，无法获得任何有关泛型参数类型的信息。你可以知道诸如类型参数标示符和泛型类型边界这类信息，但却无法知道用来创建某个特定实例的实际的类型参数。
 * Java中的泛型是使用擦除来实现的，所以在使用泛型的时候，任何具体的类型信息都被擦除了，只知道当前使用的是一个对象。所以上面才会出现相等的情况。
 * 
 */
public class Chapter15_7 {
	
}