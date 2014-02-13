package com.itzhai.javanote.chapter_15_Generics;

/**
 * 15 泛型
 *   12 自限定的类型
 *     1 古怪的循环泛型
 * 下面演示一个循环泛型的例子
 */
class GenericType<T>{}
// 解释:创建一个新类，继承自一个泛型类型，这个泛型类型接受新类的名字作为其参数。
class CuriouslyRecurringGeneric extends GenericType<CuriouslyRecurringGeneric> {}


/**
 * 下面演示一下循环泛型的作用
 * 首先创建一个泛型类
 */
class BasicHolder<T> {
    T element;
    void set(T arg) { element = arg; }
    T get() { return element; }
    void f() {
        System.out.println(element.getClass().getSimpleName());
    }
}
// 实现循环泛型类，作用：基类BasicHolder用导出类Subtype替代其方法间传递的参数。
class Subtype extends BasicHolder<Subtype> {}

// 使用
public class Chapter15_12_1 {
    public static void main(String[] args) {
        Subtype st1 = new Subtype(), st2 = new Subtype();
        // 传递给set()的参数和从get()返回的类型都是确切的Subtype
        st1.set(st2);
        Subtype st3 = st1.get();
        st1.f();
    }
}
