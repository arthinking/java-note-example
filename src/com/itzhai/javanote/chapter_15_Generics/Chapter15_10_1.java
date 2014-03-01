package com.itzhai.javanote.chapter_15_Generics;

import java.util.Arrays;
import java.util.List;

/**
 * 15 泛型  
 *   10 通配符
 *     1 编译器有多聪明
 *     
 * 使用了 ? extends Fruit 的泛型的方法参数，将不能传入任何具体的参数。
 */
class CompilerIntelligence {
    public static void main(String[] args) {
        // 声明了 List<? extends Fruit> ，编译器不能了解这里需要Fruit的哪个具体子类型，因此不会接受任何类型的Fruit，
        // add()方法的参数就变成了“? extends Fruit”，不能加入任何的元素
        List<? extends Fruit> flist =
                Arrays.asList(new Apple());
        // 但是却可以进行转型
        Apple a = (Apple)flist.get(0); // No warning
        // contains 和 indexOf方法参数类型是Object，因此不涉及任何通配符，编译器允许这个调用。
        // 这意味着将由泛型类的设计者来决定哪些调用时安全的，并使用Object类型作为其参数类型
        flist.contains(new Apple()); // Argument is 'Object'
        flist.indexOf(new Apple()); // Argument is 'Object'
    }
}

// 为了在类型中使用了通配符的情况系禁止contains的这类调用，我们需要在参数列表中使用类型参数
class Holder<T> {
    private T value;
    public Holder() {}
    public Holder(T val) { value = val; }
    public void set(T val) { value = val; }
    public T get() { return value; }
    public boolean equals(Object obj) {
        return value.equals(obj);
    } 
    public static void main(String[] args) {
        Holder<Apple> Apple = new Holder<Apple>(new Apple());
        Apple d = Apple.get();
        Apple.set(d);
        // 普通的泛型Holder<Apple>不能进行向上转型赋值为Holder<Fruit>
        // Holder<Fruit> Fruit = Apple; // Cannot upcast
        // 但是可以向上转型为Holder<? extends Fruit>
        Holder<? extends Fruit> fruit = Apple; // OK
        // 在使用get的时候，如果知道更具体的信息，就可以转换为具体的子类了，但也存在这转换异常的风险。
        Fruit p = fruit.get();
        d = (Apple)fruit.get(); // Returns 'Object'
        try {
            Orange c = (Orange)fruit.get(); // No warning
        } catch(Exception e) { System.out.println(e); }
        // 跟上例类型，set不能按照如下调用
        // fruit.set(new Apple()); // Cannot call set()
        // fruit.set(new Fruit()); // Cannot call set()
        // equals接受的是Object，所以也能正常运行
        System.out.println(fruit.equals(d)); // OK
    }
} 
/* Output: (Sample)
  java.lang.ClassCastException: Apple cannot be cast to Orange
  true
*///:~

public class Chapter15_10_1 {
	
}