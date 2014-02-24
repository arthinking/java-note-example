package com.itzhai.javanote.chapter_15_Generics;

/**
 * 15 泛型  
 *   8 擦除的补偿
 * 
 * 正因为类型信息被擦除了，所以和类型相关的代码都无法工作了，如下的：
 */

class Erased<T> {
    private final int SIZE = 100;
    public static void f(Object arg) {
        if(arg instanceof T) {}          // Error
        T var = new T();                 // Error
        T[] array = new T[SIZE];         // Error
        T[] array = (T)new Object[SIZE]; // Unchecked warning
    }
}

// 上面的instanceof方法也没法使用了额，为了在泛型类中能够判断类型，可以引入类型标签：
class ClassTypeCapture<T> {

    Class<T> kind;
    public ClassTypeCapture(Class<T> kind){
        this.kind = kind;
    }
    public boolean f(Object arg){
        return kind.isInstance(arg);
    }
    public static void main(String[] args){
        ClassTypeCapture<String> ctc = new ClassTypeCapture<String>(String.class);
        System.out.println(ctc.f("art")); // true
        System.out.println(ctc.f(1));  // false
    }
}

public class Chapter15_8 {
	
}