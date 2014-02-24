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
// 实现了Iterable接口，所以可以再循环语句中使用
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
    
    // 迭代方法
    @Override
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

// 下面的类是Generator<T>接口的另一个实现，负责生成Fibonacci数列：
class Fibonacci implements Generator<Integer> {
    private int count = 0;
    public Integer next() { return fib(count++); }
    private int fib(int n) {
        if(n < 2) return 1;
        return fib(n-2) + fib(n-1);
    }
    public static void test(String[] args) {
        Fibonacci gen = new Fibonacci();
        for(int i = 0; i < 18; i++)
            System.out.print(gen.next() + " ");
    }
} 
/* Output:
  1 1 2 3 5 8 13 21 34 55 89 144 233 377 610 987 1597 2584
*///:~

// 下面编写一个实现了Iterable的Fibonacci生成器，通过继承来创建适配器类：
class IterableFibonacci extends Fibonacci implements Iterable<Integer> {
  private int n;
  public IterableFibonacci(int count) { n = count; }
  
  @Override
  public Iterator<Integer> iterator() {
    return new Iterator<Integer>() {
      public boolean hasNext() { return n > 0; }
      public Integer next() {
        n--;
        return IterableFibonacci.this.next();
      }
      public void remove() { // Not implemented
        throw new UnsupportedOperationException();
      }
    };
  } 
  public static void test(String[] args) {
    for(int i : new IterableFibonacci(18))
      System.out.print(i + " ");
  }
} /* Output:
1 1 2 3 5 8 13 21 34 55 89 144 233 377 610 987 1597 2584
*///:~

public class Chapter15_3 {
	
}