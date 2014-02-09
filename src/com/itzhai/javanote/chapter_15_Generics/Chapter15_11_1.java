package com.itzhai.javanote.chapter_15_Generics;
/**
 * 任何基本类型都不能作为类型参数
 * 
 * 可以使用基本类型的包装类，使用容器的时候，自动包装机制会把基本类型转换为包装类，
 * 但是记住：自动包装无法用于数组，所以泛型数组不能传入基本类型的数组。
 * 
 * @author arthinking
 *
 */
public class Chapter15_11_1 {
	
	// 使用 t 填充数组
	public static <T> T[] fill(T[] a, T t){
		for(int i=0; i<a.length; i++){
			a[i] = t;
		}
		return a;
	}

	public static void main(String[] args){
		fill(new Integer[10], 3);
		// fill(new int[10], 3);  //编译失败，因为自动包装机制不能应用于数组，因此这无法工作。
	}
}
