package com.itzhai.javanote.chapter_15_Generics;

/**
 * 15 泛型  
 *   7 擦除的神秘之处
 *     3 擦除的问题
 * 
 * 擦除使得现有的非泛型客户端代码能够在不改变的情况下继续使用，直至客户端准备好用泛型重写这些代码。
 * 
 * 但是擦除的代价也是显著的，泛型不能用于显式的引用运行时类型的操作中，如转型，instanceof和new操作符，因为所有关于参数的类型信息都丢失了。无论何时当你在编写泛型代码时，必须时刻提醒自己，
 * 你只是看起来好像拥有有关参数的类型信息而已，实际上，它只是一个Object。
 * 
 * 当要使用@SuppressWarnings("unchecked") 关闭警告时，最好尽量地“聚焦”，这样就不会因为过于宽泛地关闭警告，而导致意外的屏蔽掉真正的问题。
 * 
 * 下面的Derived3的错误意味着编译器期望得到一个原生基类，当你希望将参数类型不要仅仅当做Object处理时，需要付出额外努力来管理边界。
 * 
 */
class GenericBase<T> {
    private T element;
    public void set(T arg) { arg = element; }
    public T get() { return element; }
}

class Derived1<T> extends GenericBase<T> {}

class Derived2 extends GenericBase {} // No warning

// class Derived3 extends GenericBase<?> {}
// Strange error:
//   unexpected type found : ?
//   required: class or interface without bounds    

class ErasureAndInheritance {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Derived2 d2 = new Derived2();
        Object obj = d2.get();
        d2.set(obj); // Warning here!
    }
} ///:~

public class Chapter15_7_3 {
	
}