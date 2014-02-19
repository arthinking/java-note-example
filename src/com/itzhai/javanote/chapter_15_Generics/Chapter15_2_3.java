package com.itzhai.javanote.chapter_15_Generics;

import java.util.ArrayList;
import java.util.Random;

import temp.generics.RandomList;

/**
 * 15 泛型  
 *   2 简单泛型
 *     3 RandomList
 * 
 * 持有特定类型对象的列表，每次调用其上的select()方法，可以随机地取一个元素：
 */
class RandomList<T> {
    private ArrayList<T> storage = new ArrayList<T>();
    private Random rand = new Random(47);
    public void add(T item) { storage.add(item); }
    public T select() {
        return storage.get(rand.nextInt(storage.size()));
    }
    public static void main(String[] args) {
        RandomList<String> rs = new RandomList<String>();
        for(String s: ("The quick brown fox jumped over " +
                "the lazy brown dog").split(" "))
            rs.add(s);
        for(int i = 0; i < 11; i++)
            System.out.print(rs.select() + " ");
    }
} 
/* Output:
brown over fox quick quick dog brown The brown lazy brown
*///:~

public class Chapter15_2_3 {
	
}