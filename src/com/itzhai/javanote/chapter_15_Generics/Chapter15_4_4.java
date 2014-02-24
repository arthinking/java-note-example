package com.itzhai.javanote.chapter_15_Generics;

/**
 * 15 泛型  
 *   4 泛型方法
 *     4 一个通用的Generator
 *     
 * 通过使用泛型类，我们更创建一个更加通用的生成器Generator。
 */
class BasicGenerator<T> implements Generator<T> {

    private Class<T> type;
    public BasicGenerator(Class<T> type){
        this.type = type;
    }
    
    @Override
    public T next() {
        try {
            return type.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static <T> Generator<T> create(Class<T> type){
        return new BasicGenerator<T>(type);
    }
}
// 由于使用了newInstance()方法，所以这里生产的类必须要提供一个默认的无参构造函数。

// 下面试验一下，创建一个对象，为了标示是新创建的对象，在类里面保存一个static的计数器，每创建一个对象就加1：
class CountObject {

    private static long counter = 0;
    private final long id = counter++;
    public long id(){
        return id;
    }
    public String toString(){
        return "countObject" + id;
    }
    public static void test(String[] args){
        Generator<CountObject> gen = BasicGenerator.create(CountObject.class);
        for(int i=0; i<5; i++){
            System.out.println(gen.next());
        }
    }
}
/*
test 输入结果如下：
countObject0
countObject1
countObject2
countObject3
countObject4
*/
public class Chapter15_4_4 {
	
}