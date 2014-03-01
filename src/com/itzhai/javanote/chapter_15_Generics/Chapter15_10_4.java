package com.itzhai.javanote.chapter_15_Generics;

import temp.generics.Holder;

/**
 * 15 泛型  
 *   10 通配符
 *     4 捕获转换
 * 
 * 下面演示一下捕获转换
 */
class CaptureConversion {
    // f1()的类型参数都是确切的，没有通配符或者边界
    static <T> void f1(Holder<T> holder) {
        T t = holder.get();
        System.out.println(t.getClass().getSimpleName());
    }
    // 是一个无界通配符，看起来是未知的，但是f2里面调用的f1的参数类型是要已知的，
    // 这里发生的是：参数类型在调用f2()的过程中被捕获，因此它可以再对f1的调用中被使用
    static void f2(Holder<?> holder) {
        f1(holder); // Call with captured type
    } 
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Holder raw = new Holder<Integer>(1);
        // f1(raw); // Produces warnings
        f2(raw); // No warnings
        Holder rawBasic = new Holder();
        rawBasic.set(new Object()); // Warning
        f2(rawBasic); // No warnings
        // Upcast to Holder<?>, still figures it out:
        Holder<?> wildcarded = new Holder<Double>(1.0);
        f2(wildcarded);
    }
} /* Output:
  Integer
  Object
  Double
  *///:~

// 捕获转换非常有趣，但是非常受限：
// 捕获转换在这个情况下才会工作，即在方法内部，需要使用确切的类型的时候，注意，不能从f2()中返回T，因为T对于f2()来说是未知的。

public class Chapter15_10_4 {
	
}