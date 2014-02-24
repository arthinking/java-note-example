package com.itzhai.javanote.chapter_15_Generics;

/**
 * 15 泛型  
 *   8 擦除的补偿
 *     2 泛型数组
 * 
 * 从上面Erased的例子中可以看出，不能直接创建泛型数组，一般使用ArrayList替代。
 * 
 * 成功创建泛型数组的唯一方法就是创建一个被擦除类型的新数组，然后对其转型：
 */
class GenericArray {
    static final int SIZE = 100;
    static Generic<Integer>[] gia;
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
      // Compiles; produces ClassCastException:
      //! gia = (Generic<Integer>[])new Object[SIZE];
      // Runtime type is the raw (erased) type:
      gia = new Generic<Integer>[SIZE];  // 不能这样创建，
      gia = (Generic<Integer>[])new Generic[SIZE];  // 成功创建泛型数组的唯一方法就是创建一个被擦除类型的新数组，然后对其转型。
      System.out.println(gia.getClass().getSimpleName());  // Generic[]
      gia[0] = new Generic<Integer>();
      gia[1] = new Object();  // 错误：cannot convert from Object to Generic<Integer>
      gia[2] = new Generic<Double>();  // 错误：cannot convert from Generic<Double> to Generic<Integer>
    }
  }

public class Chapter15_8_2 {
	
}