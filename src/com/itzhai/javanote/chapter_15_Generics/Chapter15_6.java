package com.itzhai.javanote.chapter_15_Generics;

import java.util.ArrayList;
import java.util.Random;
import temp.generics.Generators;
import com.itzhai.javanote.entity.Circle;

/**
 * 15 泛型  
 *   6 构建复杂模型
 * 泛型的一个重要好处是能够简单而安全地创建复杂的模型，例如很容易的创建List元组：
 */
class TupleList<A,B,C,D>
    extends ArrayList<FourTuple<A,B,C,D>> {
    public static void main(String[] args) {
        TupleList<Circle, Square, String, Integer> tl =
                new TupleList<Circle, Square, String, Integer>();
        tl.add(TupleTest.h());
        tl.add(TupleTest.h());
        for(FourTuple<Circle,Square,String,Integer> i: tl)
            System.out.println(i);
    }
} /* Output: (75% match)
(Circle, com.itzhai.javanote.chapter_15_Generics.Square@1befab0, hi, 47)
(Circle, com.itzhai.javanote.chapter_15_Generics.Square@13c5982, hi, 47)
*///:~


// 下面一个实例展示使用泛型类型来构建复杂模型是多么简单的事情
class Product {
    private final int id;
    private String description;
    private double price;
    public Product(int IDnumber, String descr, double price){
      id = IDnumber;
      description = descr;
      this.price = price;
      System.out.println(toString());
    }
    public String toString() {
      return id + ": " + description + ", price: $" + price;
    }
    public void priceChange(double change) {
      price += change;
    }
    public static Generator<Product> generator =
      new Generator<Product>() {
        private Random rand = new Random(47);
        public Product next() {
          return new Product(rand.nextInt(1000), "Test",
                Math.round(rand.nextDouble() * 1000.0) + 0.99);
        }
    };
}
    
class Shelf extends ArrayList<Product> {
    public Shelf(int nProducts) {
        Generators.fill(this, Product.generator, nProducts);
    }
}   

class Aisle extends ArrayList<Shelf> {
    public Aisle(int nShelves, int nProducts) {
      for(int i = 0; i < nShelves; i++)
        add(new Shelf(nProducts));
    }
}

class CheckoutStand {}
class Office {}

public class Store extends ArrayList<Aisle> {
    private ArrayList<CheckoutStand> checkouts =
            new ArrayList<CheckoutStand>();
    private Office office = new Office();
    public Store(int nAisles, int nShelves, int nProducts) {
        for(int i = 0; i < nAisles; i++)
            add(new Aisle(nShelves, nProducts));
    }
    public String toString() {
        StringBuilder result = new StringBuilder();
        for(Aisle a : this)
            for(Shelf s : a)
                for(Product p : s) {
                    result.append(p);
                result.append("\n");
                }
        return result.toString();
    }
    public static void main(String[] args) {
        System.out.println(new Store(14, 5, 10));
    }
} /* Output:
  258: Test, price: $400.99
  861: Test, price: $160.99
  868: Test, price: $417.99
  207: Test, price: $268.99
  551: Test, price: $114.99
  278: Test, price: $804.99
  520: Test, price: $554.99
  140: Test, price: $530.99
  ...
  *///:~

public class Chapter15_6 {
	public static void main(String[] args){
	    TupleList.main(args);
	}
}