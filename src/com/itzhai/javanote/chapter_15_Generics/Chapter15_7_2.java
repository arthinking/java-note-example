package com.itzhai.javanote.chapter_15_Generics;

/**
 * 15 泛型  
 *   7 擦除的神秘之处
 *     2 迁移兼容性
 * 
 * 泛型是JDK1.5才出现的，所以为了兼容，采用了擦除的方式实现。
 * 泛型类型只有在静态类型检查期间才出现，在此之后，程序中所有泛型类型都被擦除，替换为他们的非泛型上界。
 * 例如List<T>将被擦除为List，而普通的类型变量在未指定边界的情况下将被擦除为Object。
 * 
 */
public class Chapter15_7_2 {
	
}