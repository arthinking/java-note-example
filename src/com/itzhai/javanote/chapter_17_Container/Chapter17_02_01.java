package com.itzhai.javanote.chapter_17_Container;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import temp.net.mindview.util.Generator;

/**
 * 17 容器深入研究
 *   2 填充容器
 *     1 一种Generator解决方案
 * 
 * CollectionData是适配器设计模式的一个实例，将Generator适配到Collection的构造器上：
 */
class CollectionData<T> extends ArrayList<T> {
    public CollectionData(Generator<T> gen, int quantity) {
      for(int i = 0; i < quantity; i++)
        add(gen.next());
    }
    // A generic convenience method:
    public static <T> CollectionData<T>
    list(Generator<T> gen, int quantity) {
      return new CollectionData<T>(gen, quantity);
    }
} ///:~


class Government implements Generator<String> {
    String[] foundation = ("strange women lying in ponds " +
      "distributing swords is no basis for a system of " +
      "government").split(" ");
    private int index;
    public String next() { return foundation[index++]; }
}

/**
 * 下面分别使用构造函数传入ArrayList和直接调用addAll方法来填充数组
 */
class CollectionDataTest {
    public static void main(String[] args) {
      Set<String> set = new LinkedHashSet<String>(
        new CollectionData<String>(new Government(), 15));
      // Using the convenience method:
      set.addAll(CollectionData.list(new Government(), 15));
      System.out.println(set);
    }
} /* Output:
  [strange, women, lying, in, ponds, distributing, swords, is, no, basis, for, a, system, of, government]
  *///:~

/**
 * 无论是直接使用构造函数传入ArrayList或者是直接调用addAll方法来填充数组，这些生成的元素的顺序和它们插入的顺序相同，因为LiinedHashSet维护的是保持了插入顺序的链接猎豹。
 *
 */





public class Chapter17_02_01 {
    public static void main(String[] args){
        CollectionDataTest.main(args);
    }
}
