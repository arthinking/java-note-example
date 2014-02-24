package com.itzhai.javanote.chapter_15_Generics;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 15 泛型  
 *   4 泛型方法
 *     3 用于Generator的泛型方法
 *     
 * 通过使用泛型方法，封装更加抽象的方法，比如下面的fill()，然后在使用的时候才传入需要使用的的具体对象：
 */
class GenericGenerator{

    public static <T> Collection<T> fill(
            Collection<T> coll, Generator<T> gen, int n){
        for(int i=0; i<n; i++){
            coll.add(gen.next());
        }
        return coll;
    }
}

public class Chapter15_4_3 {
	public static void main(String[] args){
	    Collection<Shape> shapes = GenericGenerator.fill(new ArrayList<Shape>(), new ShapeGenerator(), 2);
        for(Shape a : shapes){
            System.out.println(a);
        }
	}
}