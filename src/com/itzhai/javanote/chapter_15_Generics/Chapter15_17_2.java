package com.itzhai.javanote.chapter_15_Generics;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import temp.generics.SimpleQueue;

/**
 * 15 泛型
 *   17 对缺乏潜在类型机制的补偿
 *     2 将一个方法应用于序列
 * 
 * 上一节通过反射类型实现的潜在类型机制把所有类型检查都转移到了运行时，因此许多情况下并不是我们所希望的。
 * 
 * 下面创建一个apply()方法，它能够将任何方法f应用于某个序列seq中的所有对象，通过反射和可变参数args传递方法的参数来实现。
 */
class Apply {
    // 必须放置边界和通配符，银边使得Apply和FilledList在所有需要的情况下都可以使用，否则，下面的某些Apply和FilledList应用将无法工作。
    public static <T, S extends Iterable<? extends T>>
        void apply(S seq, Method f, Object... args) {
        try {
            for(T t: seq)
                f.invoke(t, args);
        } catch(Exception e) {
            // Failures are programmer errors
            throw new RuntimeException(e);
        }
    }
}   

class Shape {
    public void rotate() { System.out.println(this + " rotate"); }
    public void resize(int newSize) {
        System.out.println(this + " resize " + newSize);
    }
}

class Square extends Shape {}

class FilledList<T> extends ArrayList<T> {
    // 类型标记技术是Java文献推荐的技术。但是，有些人强烈地首先工厂方式
    public FilledList(Class<? extends T> type, int size) {
        try {
            for(int i = 0; i < size; i++)
                // Assumes default constructor:
                add(type.newInstance());
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}   

public class Chapter15_17_2 {
    public static void main(String[] args) throws Exception {
        List<Shape> shapes = new ArrayList<Shape>();
        for(int i = 0; i < 10; i++)
            shapes.add(new Shape());
        Apply.apply(shapes, Shape.class.getMethod("rotate"));
        Apply.apply(shapes,
                Shape.class.getMethod("resize", int.class), 5);
        List<Square> squares = new ArrayList<Square>();
        for(int i = 0; i < 10; i++)
            squares.add(new Square());
        Apply.apply(squares, Shape.class.getMethod("rotate"));
        Apply.apply(squares,
                Shape.class.getMethod("resize", int.class), 5);
      
        Apply.apply(new FilledList<Shape>(Shape.class, 10),
        Shape.class.getMethod("rotate"));
        Apply.apply(new FilledList<Shape>(Square.class, 10),
        Shape.class.getMethod("rotate"));

        SimpleQueue<Shape> shapeQ = new SimpleQueue<Shape>();
        for(int i = 0; i < 5; i++) {
            shapeQ.add(new Shape());
            shapeQ.add(new Square());
        }
        Apply.apply(shapeQ, Shape.class.getMethod("rotate"));
    }
}