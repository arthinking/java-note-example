package com.itzhai.javanote.chapter_17_Container;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 17 容器深入研究
 *   2 填充容器
 * 
 * 就像Arrays一样，相应的Collections类页游一些使用的static方法，其中包括fill()，
 * 与Arrays版本一样，此fill()方法只是赋值同一个队形引用来填充整个容器的，只对List对象持有，但是所产生的列表可以传递给构造器或者addAll方法：
 */
class StringAddress {
    private String s;
    public StringAddress(String s) { this.s = s; }
    public String toString() {
      return super.toString() + " " + s;
    }
}

class FillingLists {
    public static void main(String[] args) {
        // 使用Collections.nCopies()创建传递给构造器的List
        List<StringAddress> list= new ArrayList<StringAddress>(
                Collections.nCopies(4, new StringAddress("Hello")));
        System.out.println(list);
        // fill只能替换已经在List中存在的元素，而不能添加新的元素
        Collections.fill(list, new StringAddress("World!"));
        System.out.println(list);
    }
} /* Output: (Sample)
  [StringAddress@82ba41 Hello, StringAddress@82ba41 Hello, StringAddress@82ba41 Hello, StringAddress@82ba41 Hello]
  [StringAddress@923e30 World!, StringAddress@923e30 World!, StringAddress@923e30 World!, StringAddress@923e30 World!]
  *///:~

public class Chapter17_02 {

}
