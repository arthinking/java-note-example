package com.itzhai.javanote.chapter_15_Generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15 泛型  
 *   10 通配符
 *     2 逆变
 *     
 * 超类型通配符:
 * 可以声明通配符是由某个特定类的rene积累来界定的 <? super MyClass>
 * 也可以使用类型惨 <? super T>
 * 
 * 这使得你可以安全的传递一个类型对象到泛型类型中，因此，有了超类型通配符，就可以向Collection写入了：
 */
class SuperTypeWildcards {
    // Apple是下界
    static void writeTo(List<? super Apple> apples) {
        apples.add(new Apple());
        apples.add(new Jonathan());
      // apples.add(new Fruit()); // Error
    }
}

/**
 * 根据如何能够向一个泛型类型“写入”（传递给一个方法），以及如何能够从一个泛型类型中“读取”（从一个方法中返回），
 * 来着手思考子类型和超类型边界？
 * 
 * 超类型边界放松了在可以向方法传递的参数上所作的限制
 */
class GenericWriting {
    static <T> void writeExact(List<T> list, T item) {
        list.add(item);
    }
    static List<Apple> apples = new ArrayList<Apple>();
    static List<Fruit> fruit = new ArrayList<Fruit>();
    static void f1() {
        writeExact(apples, new Apple());
        // writeExact(fruit, new Apple()); // Error:
        // Incompatible types: found Fruit, required Apple
    }
    static <T> void writeWithWildcard(List<? super T> list, T item) {
        list.add(item);
    }
    static void f2() {
        writeWithWildcard(apples, new Apple());
        // 使用超类型边界之后，可以把Apple添加到类型为Fruit的list中了
        writeWithWildcard(fruit, new Apple());
    }
    public static void main(String[] args) { f1(); f2(); }
}

/**
 * 下面继续看一个关于协变和通配符的例子
 */
class GenericReading {
    // readExact使用了精确类型
    static <T> T readExact(List<T> list) {
        return list.get(0);
    }
    static List<Apple> apples = Arrays.asList(new Apple());
    static List<Fruit> fruit = Arrays.asList(new Fruit());
    // A static method adapts to each call:
    static void f1() {
        Apple a = readExact(apples);
        Fruit f = readExact(fruit);
        f = readExact(apples);
    }
    // 如果有一个泛型类，当你创建这个类的实例时，要为这个类确定参数，就像在f2()中看到的，确定了类型后，就不能传递其他类型 的参数了。
    static class Reader<T> {
        T readExact(List<T> list) { return list.get(0); }
    } 
    static void f2() {
        Reader<Fruit> fruitReader = new Reader<Fruit>();
        Fruit f = fruitReader.readExact(fruit);
        // Fruit a = fruitReader.readExact(apples); // Error:
        // readExact(List<Fruit>) cannot be
        // applied to (List<Apple>).
    }
    // 为了解决这个问题，可以考虑使用子类型边界(向上转换为T，超类边界是为了让具体的子类可用)
    static class CovariantReader<T> {
        T readCovariant(List<? extends T> list) {
            return list.get(0);
        }
    }
    static void f3() {
        CovariantReader<Fruit> fruitReader =
                new CovariantReader<Fruit>();
        Fruit f = fruitReader.readCovariant(fruit);
        Fruit a = fruitReader.readCovariant(apples);
    } 
    public static void main(String[] args) {
        f1(); f2(); f3();
    }
} ///:~

public class Chapter15_10_2 {
	public static void main(String[] args){
	    GenericReading.f3();
	}
}