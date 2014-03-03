package com.itzhai.javanote.chapter_15_Generics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import temp.generics.Holder;

/**
 * 15 泛型  
 *   10 通配符
 *     3 无边界通配符
 *     
 * 无界通配符<?>看起来意味着“任何事物”，因此使用无界通配符好像等价于使用原生类型。
 * 编译器初看起来是支持这种判断的：
 */
class UnboundedWildcards1 {
    static List list1;
    static List<?> list2;
    static List<? extends Object> list3;
    static void assign1(List list) {
      list1 = list;
      list2 = list;
      // list3 = list; // Warning: unchecked conversion  可以看出<?>和<? extends Object>是不同的
      // Found: List, Required: List<? extends Object>
    }
    static void assign2(List<?> list) {
      list1 = list;
      list2 = list;
      list3 = list;
    } 
    static void assign3(List<? extends Object> list) {
      list1 = list;
      list2 = list;
      list3 = list;
    }
    public static void main(String[] args) {
      assign1(new ArrayList());
      assign2(new ArrayList());
      // assign3(new ArrayList()); // Warning:
      // Unchecked conversion. Found: ArrayList
      // Required: List<? extends Object>
      assign1(new ArrayList<String>());
      assign2(new ArrayList<String>());
      assign3(new ArrayList<String>());
      // Both forms are acceptable as List<?>:
      List<?> wildList = new ArrayList();
      wildList = new ArrayList<String>();
      assign1(wildList);
      assign2(wildList);
      assign3(wildList);
    }
} 

// 在这些情况中，<?>可以被认为是一种装饰，但它仍旧很有价值，声明了“我是想用Java的泛型来编写这段代码，我在这里并不是要用原生类型但在这种情况下，泛型参数可以持有任何类型。”

/**
 * 下面展示无界通配符的一个重要应用：当处理多个泛型参数时有时允许一个参数可以是任何类型，同时为其他参数确定某种特定类型的这种能力会显得很重要：
 */
class UnboundedWildcards2 {
    static Map map1;
    static Map<?,?> map2;
    static Map<String,?> map3;
    static void assign1(Map map) { map1 = map; }
    // 当全是通配符时，编译器就无法将其与原生Map区分开来了
    static void assign2(Map<?,?> map) { map2 = map; }
    static void assign3(Map<String,?> map) { map3 = map; }
    public static void main(String[] args) {
      assign1(new HashMap());
      assign2(new HashMap());
      // assign3(new HashMap()); // Warning:
      // Unchecked conversion. Found: HashMap
      // Required: Map<String,?>
      assign1(new HashMap<String,Integer>());
      assign2(new HashMap<String,Integer>());
      assign3(new HashMap<String,Integer>());
    }
}


/**
 * List<Object>与List<?>的区别：
 * List表示持有任何Object类型的原生List
 * List<?>表示具有某种特定类型的非原生List，只是我们不知道那种类型是什么
 * 
 * 编译器什么时候才会关注原生类型和涉及无界通配符的类型之间的差异呢？
 * 下面用例子演示下：
 */
class Wildcards {
    // Raw argument:
    // Holder是一个泛型类，这里表示称原生类型，但是编译器仍就知道向set传递一个Object是不安全的。
   static void rawArgs(Holder holder, Object arg) {
     // holder.set(arg); // Warning:
     //   Unchecked call to set(T) as a
     //   member of the raw type Holder
     // holder.set(new Wildcards()); // Same warning

     // Can't do this; don't have any 'T':
     // T t = holder.get();

     // OK, but type information has been lost:
     Object obj = holder.get();
   } 
   // Similar to rawArgs(), but errors instead of warnings:
   // 这里演示了<?>和原生类型是不同的：
   static void unboundedArg(Holder<?> holder, Object arg) {
     // 原生Holder将持有任何类型的组合，而Holder<?>将持有具有某种具体类型的同构集合，因此不能只是向其中传递Object
     holder.set(arg); // Error:
     //   set(capture of ?) in Holder<capture of ?>
     //   cannot be applied to (Object)
     // holder.set(new Wildcards()); // Same error

     // Can't do this; don't have any 'T':
     // T t = holder.get();

     // OK, but type information has been lost:
     Object obj = holder.get();
   } 
   static <T> T exact1(Holder<T> holder) {
     T t = holder.get();
     return t;
   }
   static <T> T exact2(Holder<T> holder, T arg) {
     holder.set(arg);
     T t = holder.get();
     return t;
   }
   // 在Holder类型上的限制被放松为包括持有任何扩展自T的对象的Holder，
   // 传入了Holder<Apple>之后，为了防止将Orange放置到Holder<Apple>,
   // 对set的调用都是不允许的，但是你仍旧知道任何来自Holder<? extends Fruit的对象至少是Fruit，因此get()是允许的
   static <T> T wildSubtype(Holder<? extends T> holder, T arg) {
     // holder.set(arg); // Error:
     //   set(capture of ? extends T) in
     //   Holder<capture of ? extends T>
     //   cannot be applied to (T)
     T t = holder.get();
     return t;
   } 
   // 展示超类型通配
   static <T> void wildSupertype(Holder<? super T> holder, T arg) {
     // holder可以是持有任何T的基类型的容器，因此，set()可以接受T，因为任何可以工作于基类的对象都可以多态地作用于导出类（这里就是T）
     holder.set(arg);
     // T t = holder.get();  // Error:  由holder持有的类型可以是任何超类型，因此唯一安全的类型就是Object
     //   Incompatible types: found Object, required T

     // OK, but type information has been lost:
     Object obj = holder.get();
   }
   public static void main(String[] args) {
     Holder raw = new Holder<Long>();
     // Or:
     raw = new Holder();
     Holder<Long> qualified = new Holder<Long>();
     Holder<?> unbounded = new Holder<Long>();
     Holder<? extends Long> bounded = new Holder<Long>();
     Long lng = 1L;

     rawArgs(raw, lng);
     rawArgs(qualified, lng);
     rawArgs(unbounded, lng);
     rawArgs(bounded, lng);
     
     unboundedArg(raw, lng);
     unboundedArg(qualified, lng);
     unboundedArg(unbounded, lng);
     unboundedArg(bounded, lng);

     // Object r1 = exact1(raw); // Warnings:
     //   Unchecked conversion from Holder to Holder<T>
     //   Unchecked method invocation: exact1(Holder<T>)
     //   is applied to (Holder)
     Long r2 = exact1(qualified);
     Object r3 = exact1(unbounded); // Must return Object
     Long r4 = exact1(bounded);
     
     // Long r5 = exact2(raw, lng); // Warnings:
     //   Unchecked conversion from Holder to Holder<Long>
     //   Unchecked method invocation: exact2(Holder<T>,T)
     //   is applied to (Holder,Long)
     Long r6 = exact2(qualified, lng);
     // Long r7 = exact2(unbounded, lng); // Error:
     //   exact2(Holder<T>,T) cannot be applied to
     //   (Holder<capture of ?>,Long)
     // Long r8 = exact2(bounded, lng); // Error:
     //   exact2(Holder<T>,T) cannot be applied
     //   to (Holder<capture of ? extends Long>,Long)
     
     // Long r9 = wildSubtype(raw, lng); // Warnings:
     //   Unchecked conversion from Holder
     //   to Holder<? extends Long>
     //   Unchecked method invocation:
     //   wildSubtype(Holder<? extends T>,T) is
     //   applied to (Holder,Long)
     Long r10 = wildSubtype(qualified, lng);
     // OK, but can only return Object:
     Object r11 = wildSubtype(unbounded, lng);
     Long r12 = wildSubtype(bounded, lng);
     
     // wildSupertype(raw, lng); // Warnings:
     //   Unchecked conversion from Holder
     //   to Holder<? super Long>
     //   Unchecked method invocation:
     //   wildSupertype(Holder<? super T>,T)
     //   is applied to (Holder,Long)
     wildSupertype(qualified, lng);
     // wildSupertype(unbounded, lng); // Error:
     //   wildSupertype(Holder<? super T>,T) cannot be
     //   applied to (Holder<capture of ?>,Long)
     // wildSupertype(bounded, lng); // Error:
     //   wildSupertype(Holder<? super T>,T) cannot be
     //  applied to (Holder<capture of ? extends Long>,Long)
   }
}

/**
 * exact2()具有最多的限制，因为它希望精确地得到一个Holder<T>，以及一个具有类型T的参数，
 * 正是由此，它将产生错误或警告，除非提供确切的参数。又是这样很好，但是如果它过于受限，那么就可以使用通配符，
 * 这取决于是否想要从泛型参数中返回类型确定的返回值（wildSubtype()）或者是想要向泛型参数传递类型确定的参数(wildSupertype())
 *
 * 使用确切类型来替代通配符的好处是可以用泛型参数来做更多的事，但是使用通配符使得你必须接受范围更宽的参数化类型作为参数。
 * 因此，必须逐个情况地权衡利弊，找到更适合你的需求的方法。
 */

public class Chapter15_10_3 {
	
}