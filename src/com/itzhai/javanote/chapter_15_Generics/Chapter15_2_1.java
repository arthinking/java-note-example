package com.itzhai.javanote.chapter_15_Generics;

import temp.net.mindview.util.TwoTuple;

import com.itzhai.javanote.entity.Circle;

/**
 * 15 泛型  
 *   2 简单泛型
 *     1 一个一元组类库
 * 
 * 为了在一次方法调用返回多个对象，可以使用元组的概念：将一组对象直接打包存储于其中的一个单一对象，
 * 这个类容器允许读取其中元素，但是不允许向其中存放新的对象（也称为数据传送对象，或信使）。
 * 
 * 元组可以具有任意长度，元组中的对象可以使任意不同类型的，下面的程序是一个二维元组，能够持有两个对象：
 */
class TwoTuple<A,B> {
    public final A first;  // 声明为final，同样确保了public的安全性，不可改写，如果想要使用具有不同元素的元组，就强制要求另外创建一个新的TwoTuple对象
    public final B second;
    // 元组隐含的保持了其中元素的次序
    public TwoTuple(A a, B b) { first = a; second = b; }
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}

/**
 * 使用继承机制实现长度更长的元组
 */
class ThreeTuple<A,B,C> extends TwoTuple<A,B> {
    public final C third;
    public ThreeTuple(A a, B b, C c) {
        super(a, b);
        third = c;
    }
    public String toString() {
        return "(" + first + ", " + second + ", " + third +")";
    }
}

/**
 * 为了使用元组，只需定义长度合适的元组，作为方法的返回值就可以了
 */
class TupleTest {
    static TwoTuple<String,Integer> f() {
        // Autoboxing converts the int to Integer:
        return new TwoTuple<String,Integer>("hi", 47);  // 这里的new语句有点啰嗦，后面有方法简化
    }
    static ThreeTuple<Circle,String,Integer> g() {
        return new ThreeTuple<Circle, String, Integer>(
                new Circle(), "hi", 47);
    }
    public static void test() {
        TwoTuple<String,Integer> ttsi = f();
        System.out.println(ttsi);
        // ttsi.first = "there"; // Compile error: final
    }
}

public class Chapter15_2_1 {
	
}