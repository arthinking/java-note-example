package com.itzhai.javanote.chapter_15_Generics;

/**
 * 15 泛型  
 *   4 泛型方法
 * 
 * 泛型方法使得该方法能够独立于类而产生变化。以下是一个基本的指导原则：
 * 无论如何，只要能够做到，就应该尽量使用泛型方法。
 * 
 * 对于static方法：无法访问泛型类的类型参数，所以如果static方法需要使用泛型能力，就必须使其成为泛型方法。
 * 
 * 下面定义一个泛型方法：将泛型参数列表置于返回值之前：
 */
class GenericMethods {

    public <T> void f(T x){
        System.out.println(x.getClass().getName());
    }
    
    public static void test(String[] args){
        GenericMethods gm = new GenericMethods();
        gm.f(1);
        gm.f("Jay");
        // output:
        // java.lang.Integer
        // java.lang.String
    }
}
// 使用泛型类时，在创建对象的时候必须制定类型参数的值，而使用泛型方法则不用，编译器会帮我们找到，这称为类型参数推断（type argument inference）。

public class Chapter15_4 {
	
}