package com.itzhai.javanote.chapter_16_Array;

import java.util.Arrays;
import java.util.Random;


/**
 * 16 数组  
 *   3 返回一个数组
 *  
 *  C或者C++中不能返回一个数组，而只能返回一个指向数组的指针，真会早恒一些问题，使得控制数组的生命周期变得很困难，并且容易造成内存泄露。
 *  
 *  Java中，只需要直接返回一个数组即可：
 */
class IceCream {
    private static Random rand = new Random(47);
    static final String[] FLAVORS = {
      "Chocolate", "Strawberry", "Vanilla Fudge Swirl",
      "Mint Chip", "Mocha Almond Fudge", "Rum Raisin",
      "Praline Cream", "Mud Pie"
    };
    // 创建了一个名为results的String数组
    public static String[] flavorSet(int n) {
      if(n > FLAVORS.length)
        throw new IllegalArgumentException("Set too big");
      String[] results = new String[n];
      // picked数组用来确保生成的元素不会重复，用boolean标示是否已存在
      boolean[] picked = new boolean[FLAVORS.length];
      for(int i = 0; i < n; i++) {
        int t;
        do
          t = rand.nextInt(FLAVORS.length);
        while(picked[t]);
        results[i] = FLAVORS[t];
        picked[t] = true;
      }
      return results;
    }
    public static void main(String[] args) {
      for(int i = 0; i < 7; i++)
        System.out.println(Arrays.toString(flavorSet(3)));
    }
} /* Output:
  [Rum Raisin, Mint Chip, Mocha Almond Fudge]
  [Chocolate, Strawberry, Mocha Almond Fudge]
  [Strawberry, Mint Chip, Mocha Almond Fudge]
  [Rum Raisin, Vanilla Fudge Swirl, Mud Pie]
  [Vanilla Fudge Swirl, Chocolate, Mocha Almond Fudge]
  [Praline Cream, Strawberry, Mocha Almond Fudge]
  [Mocha Almond Fudge, Strawberry, Mint Chip]
  *///:~
public class Chapter16_03 {

}
