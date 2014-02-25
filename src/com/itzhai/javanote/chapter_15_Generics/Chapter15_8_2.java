package com.itzhai.javanote.chapter_15_Generics;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * 15 泛型  
 *   8 擦除的补偿
 *     2 泛型数组
 * 
 * 从上面Erased的例子中可以看出，不能直接创建泛型数组，一般使用ArrayList替代。
 */
class ListOfGenerics<T> {
    private List<T> array = new ArrayList<T>();
    public void add(T item) { array.add(item); }
    public T get(int index) { return array.get(index); }
}

class Generic<T> {}

// 但是可以按照编译器喜欢的方式来定义一个引用，却永远都不能创建这个确切类型的数组。
class ArrayOfGenericReference {
    static Generic<Integer>[] gia;
} 
// 不能创建这个确切类型的数组
class ArrayOfGeneric {
    static final int SIZE = 100;
    static Generic<Integer>[] gia;
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
      gia = (Generic<Integer>[])new Object[SIZE];  // 编译通过，运行报ClassCastException错误，因为数组将跟踪它们的实际类型，而这个类型是在数组被创建时确定的。
      // Runtime type is the raw (erased) type:
      gia = new Generic<Integer>[SIZE];  // 不能这样创建，Cannot create a generic array of Generic<Integer>
      gia = (Generic<Integer>[])new Generic[SIZE];  // 成功创建泛型数组的唯一方法就是创建一个被擦除类型的新数组，然后对其转型。
      System.out.println(gia.getClass().getSimpleName());  // Generic[]
      gia[0] = new Generic<Integer>();
      gia[1] = new Object();  // 错误：cannot convert from Object to Generic<Integer>
      gia[2] = new Generic<Double>();  // 错误：cannot convert from Generic<Double> to Generic<Integer>
    }
}

// 下面是一个泛型数组包装器
class GenericArray<T> {
    private T[] array;
    @SuppressWarnings("unchecked")
    public GenericArray(int sz) {
        array = (T[])new Object[sz];
    }
    public void put(int index, T item) {
        array[index] = item;
    }
    public T get(int index) { return array[index]; }
    // Method that exposes the underlying representation:
    public T[] rep() { return array; }    
    public static void main(String[] args) {
        GenericArray<Integer> gai =
                new GenericArray<Integer>(10);
        // This causes a ClassCastException:
        Integer[] ia = gai.rep(); // 返回T[]，运行报ClassCastException，还是因为实际的运行时类型是Object[]
        // This is OK:
        Object[] oa = gai.rep();
    }
}
// 因为有了擦除，数组的运行时类型就只能是Object[]，如果我们立即将其转型为T[]，在编译期该数组的实际类型就会丢失，
// 而编译器可能会错过某些潜在的错误检查。正因为这样，最好是在集合内部使用Object[]，当使用数组元素时，添加一个对T的类型转换
class GenericArray2<T> {
    private Object[] array;
    public GenericArray2(int sz) {
        array = new Object[sz];
    }
    public void put(int index, T item) {
        array[index] = item;
    }
    @SuppressWarnings("unchecked")
    public T get(int index) { return (T)array[index]; }
    @SuppressWarnings("unchecked")
    public T[] rep() {
        return (T[])array; // Warning: unchecked cast
    } 
    public static void main(String[] args) {
        GenericArray2<Integer> gai =
                new GenericArray2<Integer>(10);
        for(int i = 0; i < 10; i ++)
            gai.put(i, i);
        for(int i = 0; i < 10; i ++)
            System.out.print(gai.get(i) + " ");
        System.out.println();
        try {
            Integer[] ia = gai.rep(); // 这里仍将报转型错误，因此，没有任何方式可以推翻底层的数组类型，它只能是Object[]
        } catch(Exception e) { System.out.println(e); }
    }
} /* Output: (Sample)
0 1 2 3 4 5 6 7 8 9
java.lang.ClassCastException: [Ljava.lang.Object; cannot be cast to [Ljava.lang.Integer;
*///:~

// 可以传递一个类型标记，使得rep()方法可以工作：
class GenericArrayWithTypeToken<T> {
    private T[] array;
    @SuppressWarnings("unchecked")
    public GenericArrayWithTypeToken(Class<T> type, int sz) {
        array = (T[])Array.newInstance(type, sz);
    }
    public void put(int index, T item) {
        array[index] = item;
    }
    public T get(int index) { return array[index]; }
    // Expose the underlying representation:
    public T[] rep() { return array; }    
    public static void main(String[] args) {
        GenericArrayWithTypeToken<Integer> gai =
                new GenericArrayWithTypeToken<Integer>(
                        Integer.class, 10);
        // This now works:
        Integer[] ia = gai.rep();
    }
} ///:~

public class Chapter15_8_2 {
	public static void main(String[] args){
	    GenericArray.main(args);
	}
}