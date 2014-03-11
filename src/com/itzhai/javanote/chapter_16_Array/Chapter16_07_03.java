package com.itzhai.javanote.chapter_16_Array;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 * 16 数组 
 *   7 Arrays实用功能
 *     3 数组元素的比较
 * 
 * 下面的类实现了Comparable接口（此接口只有一个compareTo()方法），
 * 并且使用Arrays.sort()方法演示了比较的效果
 */
class CompType implements Comparable<CompType> {
	  int i;
	  int j;
	  private static int count = 1;
	  public CompType(int n1, int n2) {
	    i = n1;
	    j = n2;
	  }
	  public String toString() {
	    String result = "[i = " + i + ", j = " + j + "]";
	    if(count++ % 3 == 0)
	      result += "\n";
	    return result;
	  }
	  @Override
	  public int compareTo(CompType rv) {
	    return (i < rv.i ? -1 : (i == rv.i ? 0 : 1));
	  }
	  private static Random r = new Random(47);
	  public static Generator<CompType> generator() {
	    return new Generator<CompType>() {
	      public CompType next() {
	        return new CompType(r.nextInt(100),r.nextInt(100));
	      }
	    };
	  }
	  public static void main(String[] args) {
	    CompType[] a =
	      Generated.array(new CompType[12], generator());
	    System.out.println("before sorting:");
	    System.out.println(Arrays.toString(a));
	    Arrays.sort(a);
	    System.out.println("after sorting:");
	    System.out.println(Arrays.toString(a));
	  }
} /* Output:
	before sorting:
	[[i = 58, j = 55], [i = 93, j = 61], [i = 61, j = 29]
	, [i = 68, j = 0], [i = 22, j = 7], [i = 88, j = 28]
	, [i = 51, j = 89], [i = 9, j = 78], [i = 98, j = 61]
	, [i = 20, j = 58], [i = 16, j = 40], [i = 11, j = 22]
	]
	after sorting:
	[[i = 9, j = 78], [i = 11, j = 22], [i = 16, j = 40]
	, [i = 20, j = 58], [i = 22, j = 7], [i = 51, j = 89]
	, [i = 58, j = 55], [i = 61, j = 29], [i = 68, j = 0]
	, [i = 88, j = 28], [i = 93, j = 61], [i = 98, j = 61]
	]
	*///:~

// 如果没有实现Comparable接口，会抛出ClassCastException运行时异常，因为sort方法会把参数类型转换为Comparable。

/**
 * Collections的reverseOrder方法可以产生一个Comparator，可以反转自然的排序顺序：
 */
class Reverse {
	public static void main(String[] args) {
	    CompType[] a = Generated.array(
	    		new CompType[12], CompType.generator());
	    System.out.println("before sorting:");
	    System.out.println(Arrays.toString(a));
	    // 反转排序
	    Arrays.sort(a, Collections.reverseOrder());
	    System.out.println("after sorting:");
	    System.out.println(Arrays.toString(a));
	}
} /* Output:
	before sorting:
	[[i = 58, j = 55], [i = 93, j = 61], [i = 61, j = 29]
	, [i = 68, j = 0], [i = 22, j = 7], [i = 88, j = 28]
	, [i = 51, j = 89], [i = 9, j = 78], [i = 98, j = 61]
	, [i = 20, j = 58], [i = 16, j = 40], [i = 11, j = 22]
	]
	after sorting:
	[[i = 98, j = 61], [i = 93, j = 61], [i = 88, j = 28]
	, [i = 68, j = 0], [i = 61, j = 29], [i = 58, j = 55]
	, [i = 51, j = 89], [i = 22, j = 7], [i = 20, j = 58]
	, [i = 16, j = 40], [i = 11, j = 22], [i = 9, j = 78]
	]
	*///:~

/**
 * 假设有人给你一个并没有实现Comparable的类，或者给你的类实现了Comparable接口，但是你并不喜欢他的实现方式，
 * 你需要另外一种不同的比较方式，可以使用策略模式，创建一个实现了Comparator接口的单独的类：
 */
class CompTypeComparator implements Comparator<CompType> {
	@Override
	public int compare(CompType o1, CompType o2) {
		return (o1.j < o2.j ? -1 : (o1.j == o2.j ? 0 : 1));
	}
}

class ComparatorTest {
	public static void main(String[] args) {
	    CompType[] a = Generated.array(
	    		new CompType[12], CompType.generator());
	    System.out.println("before sorting:");
	    System.out.println(Arrays.toString(a));
	    Arrays.sort(a, new CompTypeComparator());
	    System.out.println("after sorting:");
	    System.out.println(Arrays.toString(a));
	}
} /* Output:
	before sorting:
	[[i = 58, j = 55], [i = 93, j = 61], [i = 61, j = 29]
	, [i = 68, j = 0], [i = 22, j = 7], [i = 88, j = 28]
	, [i = 51, j = 89], [i = 9, j = 78], [i = 98, j = 61]
	, [i = 20, j = 58], [i = 16, j = 40], [i = 11, j = 22]
	]
	after sorting:
	[[i = 68, j = 0], [i = 22, j = 7], [i = 11, j = 22]
	, [i = 88, j = 28], [i = 61, j = 29], [i = 16, j = 40]
	, [i = 58, j = 55], [i = 20, j = 58], [i = 93, j = 61]
	, [i = 98, j = 61], [i = 9, j = 78], [i = 51, j = 89]
	]
	*///:~

public class Chapter16_07_03 {
	public static void main(String[] args) {
	    Reverse.main(args);
    }
}
