package com.itzhai.javanote.chapter_15_Generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import temp.generics.SimpleQueue;
import temp.generics.coffee.Coffee;
import temp.generics.coffee.Latte;
import temp.generics.coffee.Mocha;
import temp.net.mindview.util.Generator;

/**
 * 15 泛型
 *   17 对缺乏潜在类型机制的补偿
 *     3 用适配器仿真潜在类型机制
 *     
 * 实际上，潜在类型机制创建了一个包含所需方法的隐式接口。因此它遵循这样的规则：
 * 如果我们手工编写了必须的接口，那么它就应该能够解决问题。
 * 
 * 从我们拥有的接口中编写代码来产生我们需要的接口，这是适配器设计模式的一个典型示例。
 * 我们可以使用适配器来适配已有的接口，以产生想要的接口。
 * 
 * 首先创建一个Addable接口，具体的实现由适配器提供。
 */
interface Addable<T> { void add(T t); }

class Fill2 {
    // Classtoken version:
    // 用Addable取代前一节的Collection 
    public static <T> void fill(Addable<T> addable,
            Class<? extends T> classToken, int size) {
        for(int i = 0; i < size; i++)
            try {
                addable.add(classToken.newInstance());
            } catch(Exception e) {
                throw new RuntimeException(e);
            }
    }
    // Generator version:
    // 重载的fill，接受一个Generator而不是标记类型。
    // 编译器将确保传递的是正确的Generator，因此不会抛出任何异常。
    public static <T> void fill(Addable<T> addable,
            Generator<T> generator, int size) {
        for(int i = 0; i < size; i++)
            addable.add(generator.next());
    }
}

// To adapt a base type, you must use composition.
// Make any Collection Addable using composition:
// 创建一个Collection的Addable适配器
class AddableCollectionAdapter<T> implements Addable<T> {
    private Collection<T> c;
    public AddableCollectionAdapter(Collection<T> c) {
        this.c = c;
    }
    public void add(T item) { c.add(item); }
}
    
// A Helper to capture the type automatically:
class Adapter {
    public static <T>
    Addable<T> collectionAdapter(Collection<T> c) {
        return new AddableCollectionAdapter<T>(c);
    }
}

// To adapt a specific type, you can use inheritance.
// Make a SimpleQueue Addable using inheritance:
class AddableSimpleQueue<T>
    extends SimpleQueue<T> implements Addable<T> {
    public void add(T item) { super.add(item); }
}
    
class Fill2Test {
    public static void main(String[] args) {
        // Adapt a Collection:
        List<Coffee> carrier = new ArrayList<Coffee>();
        // 使用Addable的Collection适配器
        Fill2.fill(
                new AddableCollectionAdapter<Coffee>(carrier),
                Coffee.class, 3);
        // Helper method captures the type:
        Fill2.fill(Adapter.collectionAdapter(carrier),
                Latte.class, 2);
        for(Coffee c: carrier)
            System.out.println(c);
        System.out.println("----------------------");
        // Use an adapted class:
        AddableSimpleQueue<Coffee> coffeeQueue =
                new AddableSimpleQueue<Coffee>();
        Fill2.fill(coffeeQueue, Mocha.class, 4);
        Fill2.fill(coffeeQueue, Latte.class, 1);
        for(Coffee c: coffeeQueue)
            System.out.println(c);
    }
} 
/* Output:
Coffee 0
Coffee 1
Coffee 2
Latte 3
Latte 4
----------------------
Mocha 5
Mocha 6
Mocha 7
Mocha 8
Latte 9
*///:~


public class Chapter15_17_4 {
    
    public static void main(String[] args) throws Exception {
        
    }
}