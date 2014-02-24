package com.itzhai.javanote.chapter_15_Generics;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import temp.generics.Generators;

/**
 * 15 泛型  
 *   5 匿名内部类
 *   
 * 泛型方法还可以应用于内部类和匿名内部类，下面是使用匿名内部类实现Generator接口的例子：
 */
class Customer {
    private static long counter = 1;
    private final long id = counter++;
    // private 构造器，强制你使用Generator类的generator方法生成对象
    private Customer() {}
    public String toString() { return "Customer " + id; }
    // A method to produce Generator objects:
    public static Generator<Customer> generator() {
        return new Generator<Customer>() {
            public Customer next() { return new Customer(); }
        };
    }
}   

class Teller {
    private static long counter = 1;
    private final long id = counter++;
    private Teller() {}
    public String toString() { return "Teller " + id; }
    // A single Generator object:
    public static Generator<Teller> generator =
            new Generator<Teller>() {
        public Teller next() { return new Teller(); }
    };
}   

class BankTeller {
    public static void serve(Teller t, Customer c) {
        System.out.println(t + " serves " + c);
    }
    public static void main(String[] args) {
      Random rand = new Random(47);
      Queue<Customer> line = new LinkedList<Customer>();
      Generators.fill(line, Customer.generator(), 15);
      List<Teller> tellers = new ArrayList<Teller>();
      Generators.fill(tellers, Teller.generator, 4);
      for(Customer c : line)
        serve(tellers.get(rand.nextInt(tellers.size())), c);
    } 
} /* Output:
  Teller 3 serves Customer 1
  Teller 2 serves Customer 2
  Teller 3 serves Customer 3
  Teller 1 serves Customer 4
  Teller 1 serves Customer 5
  Teller 3 serves Customer 6
  Teller 1 serves Customer 7
  Teller 2 serves Customer 8
  Teller 3 serves Customer 9
  Teller 3 serves Customer 10
  Teller 2 serves Customer 11
  Teller 4 serves Customer 12
  Teller 2 serves Customer 13
  Teller 1 serves Customer 14
  Teller 1 serves Customer 15
  *///:~
  
public class Chapter15_5 {
	
}