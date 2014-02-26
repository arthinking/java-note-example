package com.itzhai.javanote.chapter_15_Generics;

import java.util.ArrayList;
import java.util.List;

import temp.generics.Apple;
import temp.generics.Fruit;


/**
 * 15 泛型  
 *   10 通配符
 *   
 * 泛型参数表达式中的问号。
 * 
 * 首先来看一个例子，可以向导出类型Apple的数组，赋予基类型的数组引用：
 */
class Fruit {}
class Apple extends Fruit {}
class Jonathan extends Apple {}
class Orange extends Fruit {}

class CovariantArrays {
  public static void main(String[] args) {
    Fruit[] fruit = new Apple[10];
    fruit[0] = new Apple(); // OK
    fruit[1] = new Jonathan(); // OK
    // Runtime type is Apple[], not Fruit[] or Orange[]:
    try {
      // Compiler allows you to add Fruit:
      // 运行时抛出异常，此时的数组机制知道它处理的是Apple[]
      fruit[0] = new Fruit(); // ArrayStoreException
    } catch(Exception e) { System.out.println(e); }
    try {
      // Compiler allows you to add Oranges:
      fruit[0] = new Orange(); // ArrayStoreException
    } catch(Exception e) { System.out.println(e); }
  }
} /* Output:
java.lang.ArrayStoreException: Fruit
java.lang.ArrayStoreException: Orange
*///:~

/**
 * 我们使用泛型来替代数组，使得错误可以再编译期可以检测到：
 */
class NonCovariantGenerics {
    // Compile Error: incompatible types:
    List<Fruit> flist = new ArrayList<Apple>();
}

/**
 * 泛型是不会自动向上转型的，不能把一个涉及Apple的泛型赋给一个涉及Fruit的泛型。
 * 
 * 有时候你想要在两个类型之间建立某种类型的向上转型关系，这正是通配符所允许的：
 */
class GenericsAndCovariance {
    public static void main(String[] args) {
        // Wildcards allow covariance:
        // List<? extends Fruit>：具有任何从Fruit继承的类型的列表，但是为了向上转型为flist，这个类型是什么并没有人关心
        // 怎样才能安全地向其中添加对象呢？
        List<? extends Fruit> flist = new ArrayList<Apple>();
        // Compile Error: can't add any type of object:
        // flist.add(new Apple());
        // flist.add(new Fruit());  即使 创建  flist的时候使用 new ArrayList<Fruit>(); 也不可以成功添加
        // flist.add(new Object());
        flist.add(null); // Legal but uninteresting
        // We know that it returns at least Fruit: 可以向上转型为父类
        Fruit f = flist.get(0);
    }
}

public class Chapter15_10 {
	
}