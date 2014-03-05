package com.itzhai.javanote.chapter_16_Array;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 16 数组 
 *   6 创建测试数据
 *     3 从Generator中创建数组
 */
class Generated {
	// 填充一个已存在的数组
	public static <T> T[] array(T[] a, Generator<T> gen) {
		// CollectionData会创建一个Collection对象，该对象所有填充的元素又gen产生
		// 所有的Collection子类型都拥有toArray()方法，该方法使用Collecion中的元素来填充数组
	    return new CollectionData<T>(gen, a.length).toArray(a);  
	}
	// 创建一个新数组并填充
	@SuppressWarnings("unchecked")
	public static <T> T[] array(Class<T> type, Generator<T> gen, int size) {
		// 使用反射来动态创建具有恰当类型和数量的新数组，然后使用和上面方法一样的技术填充数组
	    T[] a = (T[])java.lang.reflect.Array.newInstance(type, size);
	    return new CollectionData<T>(gen, size).toArray(a);
	}
}
/**
 * 下面是CollectionData类的实现
 */
class CollectionData<T> extends ArrayList<T> {
	public CollectionData(Generator<T> gen, int quantity) {
	    for(int i = 0; i < quantity; i++)
	      add(gen.next());
	}
	// A generic convenience method:
	public static <T> CollectionData<T> list(Generator<T> gen, int quantity) {
	    return new CollectionData<T>(gen, quantity);
	}
}
/**
 * 下面使用前一节中的CountingGenerator的一个生成器来测试Generated
 */
class TestGenerated {
	public static void main(String[] args) {
	    Integer[] a = { 9, 8, 7, 6 };
	    System.out.println(Arrays.toString(a));
	    // 填充数组
	    a = Generated.array(a,new CountingGenerator.Integer());
	    System.out.println(Arrays.toString(a));
	    // 创建一个新数组并填充
	    Integer[] b = Generated.array(Integer.class,
	        new CountingGenerator.Integer(), 15);
	    System.out.println(Arrays.toString(b));
	}
} /* Output:
	[9, 8, 7, 6]
	[0, 1, 2, 3]
	[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14]
	*///:~

/**
 * 泛型不能用于基本类型，为了填充基本类型的数组，我们需要创建一个转换器，
 * 它可以接收任意的包装器对象数组，并将其转换为基本类型数组：
 */
class ConvertTo {
	public static boolean[] primitive(Boolean[] in) {
	    boolean[] result = new boolean[in.length];
	    for(int i = 0; i < in.length; i++)
	    	result[i] = in[i]; // Autounboxing
	    return result;
	}
	public static char[] primitive(Character[] in) {
	    char[] result = new char[in.length];
	    for(int i = 0; i < in.length; i++)
	    	result[i] = in[i];
	    return result;
	}
	public static byte[] primitive(Byte[] in) {
	    byte[] result = new byte[in.length];
	    for(int i = 0; i < in.length; i++)
	    	result[i] = in[i];
	    return result;
	}
	public static short[] primitive(Short[] in) {
	    short[] result = new short[in.length];
	    for(int i = 0; i < in.length; i++)
	    	result[i] = in[i];
	    return result;
	}
	public static int[] primitive(Integer[] in) {
	    int[] result = new int[in.length];
	    for(int i = 0; i < in.length; i++)
	    	result[i] = in[i];
	    return result;
	}
	public static long[] primitive(Long[] in) {
	    long[] result = new long[in.length];
	    for(int i = 0; i < in.length; i++)
	    	result[i] = in[i];
	    return result;
	}
	public static float[] primitive(Float[] in) {
		float[] result = new float[in.length];
	    for(int i = 0; i < in.length; i++)
	    	result[i] = in[i];
	    return result;
	}
	public static double[] primitive(Double[] in) {
	    double[] result = new double[in.length];
	    for(int i = 0; i < in.length; i++)
	    	result[i] = in[i];
	    return result;
	}
} ///:~

/**
 * 最后使用RandomGenerator中的类来测试这些数组生成器：
 */
class TestArrayGeneration {
	 public static void main(String[] args) {
	    int size = 6;
	    boolean[] a1 = ConvertTo.primitive(Generated.array(
	    		Boolean.class, new RandomGenerator.Boolean(), size));
	    System.out.println("a1 = " + Arrays.toString(a1));
	    byte[] a2 = ConvertTo.primitive(Generated.array(
	    		Byte.class, new RandomGenerator.Byte(), size));
	    System.out.println("a2 = " + Arrays.toString(a2));
	    char[] a3 = ConvertTo.primitive(Generated.array(Character.class,
	      	new RandomGenerator.Character(), size));
	    System.out.println("a3 = " + Arrays.toString(a3));
	    short[] a4 = ConvertTo.primitive(Generated.array(
	    		Short.class, new RandomGenerator.Short(), size));
	    System.out.println("a4 = " + Arrays.toString(a4));
	    int[] a5 = ConvertTo.primitive(Generated.array(
	    		Integer.class, new RandomGenerator.Integer(), size));
	    System.out.println("a5 = " + Arrays.toString(a5));
	    long[] a6 = ConvertTo.primitive(Generated.array(
	    		Long.class, new RandomGenerator.Long(), size));
	    System.out.println("a6 = " + Arrays.toString(a6));
	    float[] a7 = ConvertTo.primitive(Generated.array(
	    		Float.class, new RandomGenerator.Float(), size));
	    System.out.println("a7 = " + Arrays.toString(a7));
	    double[] a8 = ConvertTo.primitive(Generated.array(
	    		Double.class, new RandomGenerator.Double(), size));
	    System.out.println("a8 = " + Arrays.toString(a8));
	  }
} /* Output:
	a1 = [true, false, true, false, false, true]
	a2 = [104, -79, -76, 126, 33, -64]
	a3 = [Z, n, T, c, Q, r]
	a4 = [-13408, 22612, 15401, 15161, -28466, -12603]
	a5 = [7704, 7383, 7706, 575, 8410, 6342]
	a6 = [7674, 8804, 8950, 7826, 4322, 896]
	a7 = [0.01, 0.2, 0.4, 0.79, 0.27, 0.45]
	a8 = [0.16, 0.87, 0.7, 0.66, 0.87, 0.59]
	*///:~


public class Chapter16_06_03 {
	
}