package com.itzhai.javanote.chapter_15_Generics;

import java.util.Iterator;
import java.util.Random;

import com.itzhai.javanote.entity.Circle;
import com.itzhai.javanote.entity.Triangle;

/**
 * 15 泛型  
 *   3 泛型接口
 * 
 * 泛型接口也可以应用于接口，例如生成器
 * 
 * 是工厂模式的一种应用，不过使用生成器创建新的对象的时候，不需要任何的参数，而工厂方法一般需要参数。
 * 
 * 下面我就来创建一个生成器来展示泛型在接口中的使用场景
 */
interface Generator<T> { T next(); } ///:~

// 现在我们编写一个类，实现Generator<Shape>接口，能够随机生成不同类型的Coffee对象
class ShapeGenerator implements Generator<Shape>, Iterable<Shape> {
    private Class[] types = { Circle.class, Square.class,
            Triangle.class};
    private static Random rand = new Random(47);
    public ShapeGenerator() {}
    // For iteration:
    private int size = 0;
    public ShapeGenerator(int sz) { size = sz; } 
    public Shape next() {
        try {
            return (Shape)
                    types[rand.nextInt(types.length)].newInstance();
        // Report programmer errors at run time:
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
    class ShapeIterator implements Iterator<Shape> {
        int count = size;
        public boolean hasNext() { return count > 0; }
        public Shape next() {
            count--;
            return ShapeGenerator.this.next();
        }
        public void remove() { // Not implemented
            throw new UnsupportedOperationException();
        }
    };    
    public Iterator<Shape> iterator() {
        return new ShapeIterator();
    }
    public static void test() {
        ShapeGenerator gen = new ShapeGenerator();
        for(int i = 0; i < 5; i++)
            System.out.println(gen.next());
        for(Shape c : new ShapeGenerator(5))
            System.out.println(c);
    }
}


public class Chapter15_3 {
	
}