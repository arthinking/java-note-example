package com.itzhai.javanote.chapter_15_Generics;

import java.util.Arrays;
import java.util.List;

/**
 * 15 泛型  
 *   10 通配符
 *     1 编译器有多聪明
 *     
 * 
 */
class CompilerIntelligence {
    public static void main(String[] args) {
        List<? extends Fruit> flist =
                Arrays.asList(new Apple());
        Apple a = (Apple)flist.get(0); // No warning
        flist.contains(new Apple()); // Argument is 'Object'
        flist.indexOf(new Apple()); // Argument is 'Object'
    }
}

public class Chapter15_10_1 {
	
}