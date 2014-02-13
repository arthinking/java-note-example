package com.itzhai.javanote.chapter_15_Generics;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

import com.itzhai.javanote.entity.Circle;

/**
 * 15 泛型  
 *   11 问题 
 *     3 转型和警告
 * 
 * 使用带有泛型类型参数的转型或indtanceof不会有任何效果
 * 
 */
class FixedSizeStack<T> {
	private int index = 0;
	private Object[] storage;
	public FixedSizeStack(int size) {
		storage = new Object[size];
	}
    public void push(T item) { storage[index++] = item; }
	@SuppressWarnings("unchecked")
	public T pop() {
	    // 转型 unchecked cast警告，由于擦除的原因，编译器无法知道这个转型是否安全
	    // 实际上只是将Object转型为Object
	    return (T)storage[--index]; 
	}
}	

public class Chapter15_11_3 {
	public static final int SIZE = 10;
	public static void main(String[] args) {
		FixedSizeStack<String> strings =
		      new FixedSizeStack<String>(SIZE);
		for(String s : "A B C D E F G H I J".split(" "))
			strings.push(s);
		for(int i = 0; i < SIZE; i++) {
			String s = strings.pop();
		    System.out.print(s + " ");
		}
	}
	
	@SuppressWarnings("unchecked")
	public void f(String filepath) throws Exception{
	    // 下面演示由readObject()方法读取转型
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(filepath));
        // 如果没有压制的注解，则会阐释警告 Unchecked cast from Object to List<Circle>
        // List<Circle> circles = (List<Circle>)in.readObject();
        
        // 如果想继续使用泛型的情况下不产生警告，则可以使用Java EE5中的使用泛型类来转型
        List<Circle> circles = List.class.cast(in.readObject());
        
        // 但是你继续添加如下转型是仍会得到一个警告
        // Type safety: Unchecked cast from List to List<Circle>
        circles = (List<Circle>)List.class.cast(in.readObject());
        
	}
}
