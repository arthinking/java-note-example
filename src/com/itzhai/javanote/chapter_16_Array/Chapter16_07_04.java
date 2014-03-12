package com.itzhai.javanote.chapter_16_Array;

import java.util.Arrays;
import java.util.Collections;

/**
 * 16 数组 
 *   7 Arrays实用功能
 *     4 数组排序
 * 
 * 使用内置的排序方法，可以对任意的基本类型数组排序，也可以对对象数组排序，只要该对象实现了Comparable接口，
 * 或者具有相关联的Comparator:
 * 
 */
class StringSorting {
	public static void main(String[] args) {
	    String[] sa = Generated.array(new String[20],
	    		new RandomGenerator.String(5));
	    System.out.println("Before sort: " + Arrays.toString(sa));
	    Arrays.sort(sa);
	    System.out.println("After sort: " + Arrays.toString(sa));
	    Arrays.sort(sa, Collections.reverseOrder());
	    System.out.println("Reverse sort: " + Arrays.toString(sa));
	    // 忽略大小写排序
	    Arrays.sort(sa, String.CASE_INSENSITIVE_ORDER);
	    System.out.println("Case-insensitive sort: " + Arrays.toString(sa));
	}
} /* Output:
	Before sort: [YNzbr, nyGcF, OWZnT, cQrGs, eGZMm, JMRoE, suEcU, OneOE, dLsmw, HLGEa, hKcxr, EqUCB, bkIna, Mesbt, WHkjU, rUkZP, gwsqP, zDyCy, RFJQA, HxxHv]
	After sort: [EqUCB, HLGEa, HxxHv, JMRoE, Mesbt, OWZnT, OneOE, RFJQA, WHkjU, YNzbr, bkIna, cQrGs, dLsmw, eGZMm, gwsqP, hKcxr, nyGcF, rUkZP, suEcU, zDyCy]
	Reverse sort: [zDyCy, suEcU, rUkZP, nyGcF, hKcxr, gwsqP, eGZMm, dLsmw, cQrGs, bkIna, YNzbr, WHkjU, RFJQA, OneOE, OWZnT, Mesbt, JMRoE, HxxHv, HLGEa, EqUCB]
	Case-insensitive sort: [bkIna, cQrGs, dLsmw, eGZMm, EqUCB, gwsqP, hKcxr, HLGEa, HxxHv, JMRoE, Mesbt, nyGcF, OneOE, OWZnT, RFJQA, rUkZP, suEcU, WHkjU, YNzbr, zDyCy]
	*///:~

/** 
 * Java标准库中的排序算法针对正排序的特殊类型进行了优化：
 *     针对基本类型的快速排序
 *     针对对象设计的“稳定归并排序”
 * 
 * 所以无需担心排序的性能问，除非确实确定了排序不分是程序效率的瓶颈。
 */

public class Chapter16_07_04 {

	
}