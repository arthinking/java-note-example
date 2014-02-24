package com.itzhai.javanote.chapter_15_Generics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 15 泛型  
 *   4 泛型方法
 *     1 杠杆利用类型参数推断
 * 
 */
// 首先是一个静态方法：
class New1 {
    public static <K, V> Map<K, V> map(){
        return new HashMap<K, V>();
    }
    
    // 然后可以这样创建一个Map：
    public static void test(String[] args){
        Map<String, List<Cat>> catMap = New.map();
    }
}
/**
 * 可以发现，右边的不用再按照以前的这种写法了：
 * Map<String, List<Cat>> catMap = new HashMap<String, List<Cat>>();
 * 
 * 左边声明部分的类型为右边提供了一种推断，使得编译器可以直接创造出来具体的类了。
 * 不过，这种场景没有声明直接使用New.map()是编译不通过的，因为没有像这里左边的可以推断的依据了，
 * 如下面的，加入f()是一个方法，需要传入一个Map，如下的写法是编译不通过的：
 * f(New.map());
 * 
 * 如果确实还是想按照上面那样使用，则可以考虑使用显示类型说明了，在操作符与方法名直接插入尖括号显示的指明类型，代码如下：
 * F(New.<Person, List<Cat>>map());
 * 
 * 不过这种方式很少使用。
 * 
 * 也就是说，在编写非赋值语句时，才要这样的说明，而使用不了杠杆利用类型推断。
 * 
 * 我们为了方便，可以使用同样的方式创建其他的容器了，可惜JDK本身没有提供这样的类：
 */
class New {
    public static <K,V> Map<K,V> map() {
      return new HashMap<K,V>();
    }
    public static <T> List<T> list() {
      return new ArrayList<T>();
    }
    public static <T> LinkedList<T> lList() {
      return new LinkedList<T>();
    }
    public static <T> Set<T> set() {
      return new HashSet<T>();
    } 
    public static <T> Queue<T> queue() {
      return new LinkedList<T>();
    }
    // Examples:
    public static void test(String[] args) {
      Map<String, List<String>> sls = New.map();
      List<String> ls = New.list();
      LinkedList<String> lls = New.lList();
      Set<String> ss = New.set();
      Queue<String> qs = New.queue();
    }
}


public class Chapter15_4_1 {
	
}