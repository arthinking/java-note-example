package com.itzhai.javanote.chapter_16_Array;

import java.util.Arrays;


/**
 * 16 数组 
 *   7 Arrays实用功能
 *     1 复制数组
 * 
 * System.arraycopy(): 复制数组，比用for循环复制要快很多，该方法对有所类型做了重载，
 * 下面是处理int数组的例子：
 */
class CopyingArrays {
	public static void main(String[] args) {
	    int[] i = new int[7];
	    int[] j = new int[10];
	    Arrays.fill(i, 47);
	    Arrays.fill(j, 99);
	    System.out.println("i = " + Arrays.toString(i));
	    System.out.println("j = " + Arrays.toString(j));
	    // 复制基本类型数组
	    System.arraycopy(i, 0, j, 0, i.length);
	    System.out.println("j = " + Arrays.toString(j));
	    int[] k = new int[5];
	    Arrays.fill(k, 103);
	    System.arraycopy(i, 0, k, 0, k.length);
	    System.out.println("k = " + Arrays.toString(k));
	    Arrays.fill(k, 103);
	    System.arraycopy(k, 0, i, 0, k.length);
	    System.out.println("i = " + Arrays.toString(i));
	    // Objects:
	    Integer[] u = new Integer[10];
	    Integer[] v = new Integer[5];
	    Arrays.fill(u, new Integer(47));
	    Arrays.fill(v, new Integer(99));
	    System.out.println("u = " + Arrays.toString(u));
	    System.out.println("v = " + Arrays.toString(v));
	    // 复制对象数组，只是复制了对象的引用，是浅复制
	    System.arraycopy(v, 0, u, u.length/2, v.length);
	    System.out.println("u = " + Arrays.toString(u));
	}
} /* Output:
	i = [47, 47, 47, 47, 47, 47, 47]
	j = [99, 99, 99, 99, 99, 99, 99, 99, 99, 99]
	j = [47, 47, 47, 47, 47, 47, 47, 99, 99, 99]
	k = [47, 47, 47, 47, 47]
	i = [103, 103, 103, 103, 103, 47, 47]
	u = [47, 47, 47, 47, 47, 47, 47, 47, 47, 47]
	v = [99, 99, 99, 99, 99]
	u = [47, 47, 47, 47, 47, 99, 99, 99, 99, 99]
	*///:~

// 注意复制对象数组的时候，只是浅复制。另外，System.arraycopy()不会执行自动包装和自动拆包。

public class Chapter16_07_01 {

}
