package com.itzhai.javanote.chapter_16_Array;

import java.util.Arrays;
import java.util.Random;

/**
 * 16 数组 
 *   4 多维数组
 *  
 *  使用花括号创建多维数组，每对花括号括起来的集合都会把你带到下一级数组：
 */
class MultidimensionalPrimitiveArray {
	public static void main(String[] args) {
	    int[][] a = {
	    	{ 1, 2, 3, },
	    	{ 4, 5, 6, },
	    };
	    System.out.println(Arrays.deepToString(a));
	}
} /* Output:
	[[1, 2, 3], [4, 5, 6]]
	*///:~

/**
 * 使用Arrays.deepToString()将多维数组转换为多个String
 *
 */
class ThreeDWithNew {
	public static void main(String[] args) {
	    // 3-D array with fixed length:
		// 使用new创建多维数组
	    int[][][] a = new int[2][2][4];
	    System.out.println(Arrays.deepToString(a));
	}
} /* Output:
	[[[0, 0, 0, 0], [0, 0, 0, 0]], [[0, 0, 0, 0], [0, 0, 0, 0]]]
	*///:~

/**
 * 粗糙数组：数组中构成矩阵的每个向量都可以具有任意的长度
 */
class RaggedArray {
	public static void main(String[] args) {
	    Random rand = new Random(47);
	    // 3-D array with varied-length vectors:
	    int[][][] a = new int[rand.nextInt(7)][][];
	    for(int i = 0; i < a.length; i++) {
	    	a[i] = new int[rand.nextInt(5)][];
	    	for(int j = 0; j < a[i].length; j++)
	    		a[i][j] = new int[rand.nextInt(5)];
	    }
	    System.out.println(Arrays.deepToString(a));
	}
} /* Output:
	[[], [[0], [0], [0, 0, 0, 0]], [[], [0, 0], [0, 0]], [[0, 0, 0], [0], [0, 0, 0, 0]], [[0, 0, 0], [0, 0, 0], [0], []], [[0], [], [0]]]
	*///:~

/**
 * 可以用类似的方式处理非基本类型的对象数组，下面使用花括号的方式创建：
 */
class MultidimensionalObjectArrays {
	public static void main(String[] args) {
		BerylliumSphere[][] spheres = {
	      { new BerylliumSphere(), new BerylliumSphere() },
	      { new BerylliumSphere(), new BerylliumSphere(),
	        new BerylliumSphere(), new BerylliumSphere() },
	      { new BerylliumSphere(), new BerylliumSphere(),
	        new BerylliumSphere(), new BerylliumSphere(),
	        new BerylliumSphere(), new BerylliumSphere(),
	        new BerylliumSphere(), new BerylliumSphere() },
	    };
	    System.out.println(Arrays.deepToString(spheres));
	}
} /* Output:
	[[Sphere 0, Sphere 1], [Sphere 2, Sphere 3, Sphere 4, Sphere 5], [Sphere 6, Sphere 7, Sphere 8, Sphere 9, Sphere 10, Sphere 11, Sphere 12, Sphere 13]]
	*///:~

/**
 * 自动包装机制对数组初始化器也起作用
 */
class AutoboxingArrays {
	public static void main(String[] args) {
	    Integer[][] a = { // Autoboxing:
	    	{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
	    	{ 21, 22, 23, 24, 25, 26, 27, 28, 29, 30 },
	    	{ 51, 52, 53, 54, 55, 56, 57, 58, 59, 60 },
	    	{ 71, 72, 73, 74, 75, 76, 77, 78, 79, 80 },
	    };
	    System.out.println(Arrays.deepToString(a));
	}
} /* Output:
	[[1, 2, 3, 4, 5, 6, 7, 8, 9, 10], [21, 22, 23, 24, 25, 26, 27, 28, 29, 30], [51, 52, 53, 54, 55, 56, 57, 58, 59, 60], [71, 72, 73, 74, 75, 76, 77, 78, 79, 80]]
	*///:~

/**
 * 也可以逐个地，部分的构建一个非基本类型的对象数组：
 */
class AssemblingMultidimensionalArrays {
	public static void main(String[] args) {
	    Integer[][] a;
	    a = new Integer[3][];
	    for(int i = 0; i < a.length; i++) {
	      a[i] = new Integer[3];
	      for(int j = 0; j < a[i].length; j++)
	        a[i][j] = i * j; // Autoboxing
	    }
	    System.out.println(Arrays.deepToString(a));
	}
} /* Output:
	[[0, 0, 0], [0, 1, 2], [0, 2, 4]]
	*///:~

// Arrays.deepToString()方法对基本类型数组和对象数组都起到了作用

public class Chapter16_04 {

}
