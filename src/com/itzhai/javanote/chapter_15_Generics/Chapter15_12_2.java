package com.itzhai.javanote.chapter_15_Generics;

/**
 * 15 泛型
 *   12 自限定的类型
 *     2 自限定
 * 首先看一个没有自限定的例子，BasicHolder可以使用任何类型作为其泛型参数：
 */
class Other {}

class BasicOther extends BasicHolder<Other> {}

class Unconstrained {
  public static void main(String[] args) {
    BasicOther b = new BasicOther(), b2 = new BasicOther();
    b.set(new Other());
    Other other = b.get();
    b.f();
  }
}

/**
 * 我们使用自限定类型其实就是为了要求在继承关系中，像下面这样使用这个类
 * class A extends SelfBounded<A>{}
 * 这会强制要求将正在定义的子类当做参数传递给基类
 * 
 * 下面看一个自限定类型的例子
 */
class SelfBounded<T extends SelfBounded<T>> {
    T element;
    SelfBounded<T> set(T arg) {
        element = arg;
        return this;
    }
    T get() { return element; }
}

class A extends SelfBounded<A> {}
class B extends SelfBounded<A> {} // Also OK

class C extends SelfBounded<C> {
    C setAndGet(C arg) { set(arg); return get(); }
}   

class D {}
// Can't do this:
// class E extends SelfBounded<D> {}
// Compile error: Type parameter D is not within its bound

// Alas, you can do this, so you can't force the idiom:
class F extends SelfBounded {}

public class Chapter15_12_2 {
    public static void main(String[] args) {
        A a = new A();
        
        // 直接使用SelfBounded，传入类似A这样的子类
        SelfBounded<A> bounded = new SelfBounded<A>();
        
        a.set(new A());
        a = a.set(new A()).get();
        a = a.get();
        C c = new C();
        c = c.setAndGet(new C());
    }
}

/**
 * 还可以将自限定用于泛型方法
 */
class SelfBoundingMethods {
    static <T extends SelfBounded<T>> T f(T arg) {
        return arg.set(arg).get();
    }
    public static void main(String[] args) {
        A a = f(new A());
    }
}