package com.itzhai.javanote.chapter_16_Array;

import java.util.Arrays;

/**
 * 16 数组 
 *   7 Arrays实用功能
 *     5 在已排序的数组中查找
 * 
 * 如果数组排好序了，可以使用Arrays.binarySearch()进行查找，
 * 对未排序的数组使用binarySearch，会产生意想不到的结果。
 * 查找到了则返回大于等于0的整数
 * 否则返回负值，表示保持数组排序状态下此目标所应该插入的位置，此负值计算方法：
 *     -(插入点) - 1
 */
class ArraySearching {
	public static void main(String[] args) {
	    Generator<Integer> gen =
	    		new RandomGenerator.Integer(1000);
	    int[] a = ConvertTo.primitive(
	    		Generated.array(new Integer[25], gen));
	    Arrays.sort(a);
	    System.out.println("Sorted array: " + Arrays.toString(a));
	    while(true) {
	    	int r = gen.next();
	    	// 使用binarySearch在数组中查找元素r
	    	int location = Arrays.binarySearch(a, r);
	    	if(location >= 0) {
	    		System.out.println("Location of " + r + " is " + location +
	    				", a[" + location + "] = " + a[location]);
	    		break; // Out of while loop
	    	}
	    }
	}
} /* Output:
	Sorted array: [128, 140, 200, 207, 258, 258, 278, 288, 322, 429, 511, 520, 522, 551, 555, 589, 693, 704, 809, 861, 861, 868, 916, 961, 998]
	Location of 322 is 8, a[8] = 322
	*///:~

/**
 * ruguo
 * @author pc
 * 
 * 如果使用Comparator排序对象数组（基本类型数组无法使用Comparator进行排序）了
 * 使用binarySearch对对象数组进行排序的时候必须提供同样的Comparator
 */
class AlphabeticSearch {
	public static void main(String[] args) {
		String[] sa = Generated.array(new String[30],
				new RandomGenerator.String(5));
		// 使用Comparator排序对象数组（基本类型数组无法使用Comparator进行排序）
	    Arrays.sort(sa, String.CASE_INSENSITIVE_ORDER);
	    System.out.println(Arrays.toString(sa));
	    // 使用binarySearch必须提供同样的Comparator
	    int index = Arrays.binarySearch(sa, sa[10],
	    		String.CASE_INSENSITIVE_ORDER);
	    System.out.println("Index: "+ index + "\n"+ sa[index]);
	}
} /* Output:
	[bkIna, cQrGs, cXZJo, dLsmw, eGZMm, EqUCB, gwsqP, hKcxr, HLGEa, HqXum, HxxHv, JMRoE, JmzMs, Mesbt, MNvqe, nyGcF, ogoYW, OneOE, OWZnT, RFJQA, rUkZP, sgqia, slJrL, suEcU, uTpnX, vpfFv, WHkjU, xxEAJ, YNzbr, zDyCy]
	Index: 10
	HxxHv
	*///:~



/**
 * summary
 * 现在容器在除了性能外的各个方法都使得数组相形见绌。
 * 
 * 有了额外的自动包装机制和泛型，在容器中持有基本类型就变得易如反掌了，而这也进一步促使你使用容器来替换数组。
 * 以为泛型可以产生类型安全的容器，面对这一点，数组变得毫无优势。
 * 
 * 优先容器而不是数组，只有在证明性能成为问，并且切花到数组对性能有所帮助时，你才应该将程序重构为使用数组。
 * 
 * 如果容器能够像某些语言一样内置于语言的内核中，那么编译器就会得到更好的优化良机。
 * 
 * 我们肯定还会使用数组，并且你在读写代码的时候还会看到他，但是，容器几乎是更好的选择。
 * TODO: Python中的数组的例子：
 *
 */

public class Chapter16_07_05 {

}