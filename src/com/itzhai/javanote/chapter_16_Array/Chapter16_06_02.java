package com.itzhai.javanote.chapter_16_Array;

import java.util.Random;

/**
 * 16 数组 
 *   6 创建测试数据
 *     2 数据生成器
 * 
 * 下面是各个基本类型的包装类和String类的生成器：
 */
interface Generator<T> { T next(); } ///:~

class CountingGenerator {
	public static class Boolean implements Generator<java.lang.Boolean> {
	    private boolean value = false;
	    public java.lang.Boolean next() {
	    	value = !value; // Just flips back and forth
	    	return value;
	    }
	}
	public static class Byte implements Generator<java.lang.Byte> {
	    private byte value = 0;
	    public java.lang.Byte next() { return value++; }
	}
	
	static char[] chars = ("abcdefghijklmnopqrstuvwxyz" +
	    "ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
	
	public static class Character implements Generator<java.lang.Character> {
	    int index = -1;
	    public java.lang.Character next() {
	      index = (index + 1) % chars.length;
	      return chars[index];
	    }
	}
	
	/**
	 * 该类使用CountingGenerator.Character来填充一个字符数组，该数组最终转换为String
	 */
	public static class String implements Generator<java.lang.String> {
	    private int length = 7;
	    Generator<java.lang.Character> cg = new Character();
	    public String() {}
	    public String(int length) { this.length = length; }
	    public java.lang.String next() {
	      char[] buf = new char[length];
	      for(int i = 0; i < length; i++)
	        buf[i] = cg.next();
	      return new java.lang.String(buf);
	    }
	}
	
	public static class Short implements Generator<java.lang.Short> {
	    private short value = 0;
	    public java.lang.Short next() { return value++; }
	}
	
	public static class Integer implements Generator<java.lang.Integer> {
	    private int value = 0;
	    public java.lang.Integer next() { return value++; }
	}
	  
	public static class Long implements Generator<java.lang.Long> {
	    private long value = 0;
	    public java.lang.Long next() { return value++; }
	}
	
	public static class Float implements Generator<java.lang.Float> {
	    private float value = 0;
	    public java.lang.Float next() {
	      float result = value;
	      value += 1.0;
	      return result;
	    }
	}
	
	public static class Double implements Generator<java.lang.Double> {
	    private double value = 0.0;
	    public java.lang.Double next() {
	      double result = value;
	      value += 1.0;
	      return result;
	    }
	}
}

/**
 * 下面测试一下这些生成器
 * Tips：使用反射进行测试
 */
class GeneratorsTest {
	public static int size = 10;
	public static void test(Class<?> surroundingClass) {
	    for(Class<?> type : surroundingClass.getClasses()) {
	    	System.out.print(type.getSimpleName() + ": ");
	    	try {
	    		Generator<?> g = (Generator<?>)type.newInstance();
	    		for(int i = 0; i < size; i++)
	    			System.out.printf(g.next() + " ");
	    		System.out.println();
	    	} catch(Exception e) {
	    		throw new RuntimeException(e);
	    	}
	    }
	}
	public static void main(String[] args) {
	    test(CountingGenerator.class);
	}
} /* Output:
	Double: 0.0 1.0 2.0 3.0 4.0 5.0 6.0 7.0 8.0 9.0
	Float: 0.0 1.0 2.0 3.0 4.0 5.0 6.0 7.0 8.0 9.0
	Long: 0 1 2 3 4 5 6 7 8 9
	Integer: 0 1 2 3 4 5 6 7 8 9
	Short: 0 1 2 3 4 5 6 7 8 9
	String: abcdefg hijklmn opqrstu vwxyzAB CDEFGHI JKLMNOP QRSTUVW XYZabcd efghijk lmnopqr
	Character: a b c d e f g h i j
	Byte: 0 1 2 3 4 5 6 7 8 9
	Boolean: true false true false true false true false true false
	*///:~


/**
 * 下面是一组使用随机数生成器的Generator：
 */
class RandomGenerator {
	private static Random r = new Random(47);
	public static class	Boolean implements Generator<java.lang.Boolean> {
	    public java.lang.Boolean next() {
	    	return r.nextBoolean();
	    }
	}
	
	public static class Byte implements Generator<java.lang.Byte> {
	    public java.lang.Byte next() {
	      return (byte)r.nextInt();
	    }
	}
	
	public static class Character implements Generator<java.lang.Character> {
	    public java.lang.Character next() {
	      return CountingGenerator.chars[
	        r.nextInt(CountingGenerator.chars.length)];
	    }
	}
	
	// 继承自CountingGenerator.String，并且只是插入了一个新的 Character
	public static class String extends CountingGenerator.String {
	    // Plug in the random Character generator:
	    { cg = new Character(); } // Instance initializer
	    public String() {}
	    public String(int length) { super(length); }
	}
	  
	public static class Short implements Generator<java.lang.Short> {
	    public java.lang.Short next() {
	      return (short)r.nextInt();
	    }
	}
	
	// 为了不生成过大的数字，设置默认的模数为 10000，够在其运行你选择更小的值
	public static class Integer implements Generator<java.lang.Integer> {
	    private int mod = 10000;
	    public Integer() {}
	    public Integer(int modulo) { mod = modulo; }
	    public java.lang.Integer next() {
	      return r.nextInt(mod);
	    }
	}
	  
	public static class Long implements Generator<java.lang.Long> {
	    private int mod = 10000;
	    public Long() {}
	    public Long(int modulo) { mod = modulo; }
	    public java.lang.Long next() {
	      return new java.lang.Long(r.nextInt(mod));
	    }
	}
	  
	// 只保留小数点后两位数字
	public static class Float implements Generator<java.lang.Float> {
	    public java.lang.Float next() {
	      // Trim all but the first two decimal places:
	      int trimmed = Math.round(r.nextFloat() * 100);
	      return ((float)trimmed) / 100;
	    }
	}
	  
	// 只保留小数点后两位数字
	public static class Double implements Generator<java.lang.Double> {
	    public java.lang.Double next() {
	      long trimmed = Math.round(r.nextDouble() * 100);
	      return ((double)trimmed) / 100;
	    }
	}
}



public class Chapter16_06_02 {

	public static void main(String[] args) {
		GeneratorsTest.test(RandomGenerator.class);
    }
}
