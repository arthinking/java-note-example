package com.itzhai.javanote.chapter_15_Generics;

/**
 * 15 泛型  
 *   7 擦除的神秘之处
 *     4 边界处的动作
 * 
 */
class GenericHolder<T> {
    private T obj;
    public void set(T obj) { this.obj = obj; }
    public T get() { return obj; }
    public static void main(String[] args) {
        GenericHolder<String> holder =
                new GenericHolder<String>();
        holder.set("Item");
        String s = holder.get();
    }
}

/** 
 * 上面的代码的set()方法会在编译期接受检查，而get()的时候直接取出了String类型，其实此处还是会进行转型的，
 * 只不过是由编译器自动插入的相当于插入了这样的代码：(String)holder.get()，详细的转型处理可以编译成字节码查看。
 *
 */

public class Chapter15_7_4 {
	
}