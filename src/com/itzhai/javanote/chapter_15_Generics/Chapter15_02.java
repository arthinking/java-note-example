package com.itzhai.javanote.chapter_15_Generics;

import com.itzhai.javanote.entity.Circle;

/**
 * 15 泛型  
 *   2 简单泛型
 *
 *  促成泛型出现最引人注目的一个原因就是为了创造容器类。
 *  
 *  首先看一个只能持有单个对象的类，这个类可以明确指定其持有的对象的类型
 */
class Holder1 {
    private Circle a;
    public Holder1(Circle a) { this.a = a; }
    Circle get() { return a; }
}

/**
 * 上面的类的可重用性不怎么样，无法持有其他类型的任何对象，下面通过持有Object类型的对象实现
 */
class Holder2 {
    private Object a;
    public Holder2(Object a) { this.a = a; }
    public void set(Object a) { this.a = a; }
    public Object get() { return a; }
    public static void main(String[] args) {
        // 下面演示存储不同类型的对象
      Holder2 h2 = new Holder2(new Circle());
      Circle a = (Circle)h2.get();
      h2.set("Not an Automobile");
      String s = (String)h2.get();
      h2.set(1); // Autoboxes to Integer
      Integer x = (Integer)h2.get();
    }
}

/**
 * 通常而言，我们只会用容器来存储一种类型的队形，泛型的主要目的之一就是用来指定容器要持有什么类型的对象，
 * 由编译器来保证类型的正确性：
 */
class Holder3<T> {
    private T a;
    public Holder3(T a) { this.a = a; }
    public void set(T a) { this.a = a; }
    public T get() { return a; }
    public static void main(String[] args) {
        // 当你创建Holder3对象时，必须指明想持有什么类型的对象，然后只能存入该类型（或其子类型，因为多台与泛型不冲突）的对象了
        // 取出对象的时候，会自动转型为正确的类型
        Holder3<Circle> h3 =
                new Holder3<Circle>(new Circle());
        Circle a = h3.get(); // No cast needed
        // h3.set("Not an Automobile"); // Error
        // h3.set(1); // Error
    }
}
// 也就是告诉编译器想使用什么类型，然后编译器帮你处理一切细节。

public class Chapter15_02 {
	
}