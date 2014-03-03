package com.itzhai.javanote.chapter_16_Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 16 数组  
 *   1 数组为什么特殊
 * 
 * 数组与其他种类容器之间的区别：效率，类型和保存基本类型的能力。
 * 
 * 数组是一种效率最高的存储和随机访问对象引用序列的方式。
 * 
 * 优点：元素访问非常快
 * 缺点：为这种速度所付出的的代价是数组对象的大小固定，并且在其生命周期中不可改变。
 * 
 * 与ArrayList对比：ArrayList的弹性分配空间需要开销，效率低很多。
 * 
 * 与泛型前的容器相比，数组可以持有某种具体类型，这样就可以使用编译器检查防止插入错误类型和抽取不恰当类型。
 * 也就是数组可以持有基本类型，而泛型之前的容器则不能。
 * 
 * 有了泛型：容器则可以检查它们所持有对象的类型，并且有了自动包装机制，容器还能够持有基本类型。
 * 
 * 下面的例子将数组与泛型容器进行比较：
 */
class BerylliumSphere {
	private static long counter;
	private final long id = counter++;
	public String toString() { return "Sphere " + id; }
}

class ContainerComparison {
	public static void main(String[] args) {
		BerylliumSphere[] spheres = new BerylliumSphere[10];
	    for(int i = 0; i < 5; i++)
	    	spheres[i] = new BerylliumSphere();
	    System.out.println(Arrays.toString(spheres));
	    // 数组使用[]来访问元素
	    System.out.println(spheres[4]);

	    List<BerylliumSphere> sphereList =
	    		new ArrayList<BerylliumSphere>();
	    for(int i = 0; i < 5; i++)
	    	// List使用add()和get()来访问元素
	    	sphereList.add(new BerylliumSphere());
	    System.out.println(sphereList);
	    System.out.println(sphereList.get(4));

	    int[] integers = { 0, 1, 2, 3, 4, 5 };
	    System.out.println(Arrays.toString(integers));
	    System.out.println(integers[4]);

	    List<Integer> intList = new ArrayList<Integer>(
	    		Arrays.asList(0, 1, 2, 3, 4, 5));
	    intList.add(97);
	    System.out.println(intList);
	    System.out.println(intList.get(4));
	}
}
/* Output:
	[Sphere 0, Sphere 1, Sphere 2, Sphere 3, Sphere 4, null, null, null, null, null]
	Sphere 4
	[Sphere 5, Sphere 6, Sphere 7, Sphere 8, Sphere 9]
	Sphere 9
	[0, 1, 2, 3, 4, 5]
	4
	[0, 1, 2, 3, 4, 5, 97]
	4
	*///:~

/**
 * 随着容器的出现，数组说过仅存的有点就是效率，如果要解决更一般化的问题，数组可能会受到过多的限制，
 * 因此在这些情况下你还是会使用容器。
 */

public class Chapter16_01 {

}
