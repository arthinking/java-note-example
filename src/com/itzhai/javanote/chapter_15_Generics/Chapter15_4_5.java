package com.itzhai.javanote.chapter_15_Generics;

/**
 * 15 泛型  
 *   4 泛型方法
 *     5 简化元组的使用
 *     
 * 我们可以发现之前创建的元组，在使用的时候都传入了一长串具体的类型，
 * 通过杠杆利用类型推断参数，我们其实可以直接省略掉那一长串具体的类型了，
 * 添加一个static方法，可以使该方法成为更通用的类库的方法了：
 */
class TupleTest2 {

    public static<A,B,C> ThreeTuple<A,B,C> tuple(A a, B b, C c){
        return new ThreeTuple<A,B,C>(a, b ,c);
    }
    
}
public class Chapter15_4_5 {
    public static void main(String[] args){
        // 根据左边的类型自动判断右边的类型，无需手动创建时指明类型了
        ThreeTuple<Cat, Integer, String> tt = TupleTest2.tuple(new Cat(), 1, "Jason");
        System.out.println(tt);
    }
}