package com.itzhai.javanote.chapter_16_Array;

import static com.itzhai.javanote.util.StringUtils.*;
import java.util.Arrays;

/**
 * 16 数组  
 *   2 数组是第一级对象
 *   
 * 数组标示符其实是一个 引用，指向堆中创建的一个真实对象，
 * 可以作为数组初始化语法的一部分隐式的创建此对象，或者用new表达式显示的创建。
 * 
 * 只读成员length是唯一一个可以访问的字段或方法
 * 
 * []是访问数组对象的唯一方式
 * 
 * 对象数组和基本类型数组在使用上几乎是相同的，唯一区别是对象数组保存的是引用，基本类型数组直接保存基本类型。
 * 
 * 下面总结了初始化数组的各种方法：
 */
class ArrayOptions {
	public static void main(String[] args) {
	    // a是尚未初始化的局部变量，编译器不允许用此引用做任何事情
	    BerylliumSphere[] a; 
	    // 初始化为指向一个BerylliumSphere引用的数组，实际上并没有BerylliumSphere对象置入数组中
	    BerylliumSphere[] b = new BerylliumSphere[5];
	    // 所有引用初始化为null，数值型会初始化为0，字符型初始化为" "，布尔型初始化为false
	    print("b: " + Arrays.toString(b));
	    char[] cc = new char[5];
	    print("cc" + Arrays.toString(cc));
	    // 初始化为指向一个BerylliumSphere引用的数组并给每个元素赋值
	    BerylliumSphere[] c = new BerylliumSphere[4];
	    for(int i = 0; i < c.length; i++)
	    	if(c[i] == null) // Can test for null reference
	    		c[i] = new BerylliumSphere();
	    // 聚集初始化语法创建数组对象：隐式的使用new语法在堆中创建(Aggregate initialization):
	    BerylliumSphere[] d = { new BerylliumSphere(),
	    		new BerylliumSphere(), new BerylliumSphere()
	    };
	    // 动态聚集初始化语法创建数组对象：(Dynamic aggregate initialization):
	    a = new BerylliumSphere[]{
	    		new BerylliumSphere(), new BerylliumSphere(),
	    };
	    // (Trailing comma is optional in both cases)
	    print("a.length = " + a.length);
	    print("b.length = " + b.length);
	    print("c.length = " + c.length);
	    print("d.length = " + d.length);
	    a = d;
	    print("a.length = " + a.length);

	    // 下面是基本类型数组的初始化，与对象引用类型基本一样，只是直接保存了数值
	    // Arrays of primitives:
	    int[] e; // Null reference
	    int[] f = new int[5];
	    // The primitives inside the array are
	    // automatically initialized to zero:
	    print("f: " + Arrays.toString(f));
	    int[] g = new int[4];
	    for(int i = 0; i < g.length; i++)
	    	g[i] = i*i;
	    int[] h = { 11, 47, 93 };
	    // Compile error: variable e not initialized:
	    //!print("e.length = " + e.length);
	    print("f.length = " + f.length);
	    print("g.length = " + g.length);
	    print("h.length = " + h.length);
	    e = h;
	    print("e.length = " + e.length);
	    e = new int[]{ 1, 2 };
	    print("e.length = " + e.length);
	}
} /* Output:
	b: [null, null, null, null, null]
	cc[ ,  ,  ,  ,   ]
	a.length = 2
	b.length = 5
	c.length = 4
	d.length = 3
	a.length = 3
	f: [0, 0, 0, 0, 0]
	f.length = 5
	g.length = 4
	h.length = 3
	e.length = 3
	e.length = 2
	*///:~
public class Chapter16_02 {
	public static void main(String[] args) {
		ArrayOptions.main(args);
    }
}
