package com.itzhai.javanote.chapter_15_Generics;

import java.util.ArrayList;
import java.util.List;

/**
 * 15 泛型  
 *   4 泛型方法
 *     2 可变参数与泛型方法
 *     
 * 可变参数也是可以使用泛型声明类型的：
 */
class GenericVarargs {

    public static <T> List<T> makeList(T... args){
        List<T> result = new ArrayList<T>();
        for(T item : args){
            result.add(item);
        }
        return result;
    }
    public static void test(String[] args){
        List<String> ls = makeList("Jay", "Mike");
    }
}
public class Chapter15_4_2 {
	
}