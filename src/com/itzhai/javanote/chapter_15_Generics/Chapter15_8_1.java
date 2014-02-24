package com.itzhai.javanote.chapter_15_Generics;

/**
 * 15 泛型  
 *   8 擦除的补偿
 *     1 创建类型实例
 * 
 * Jason：我们怎么在一个泛型类中创建泛型的对象呢，上面直接创建的方法也是编译不通过的？
 * Mike：我们可以使用泛型工厂的方式。可以保存一个类型标签，使用Class.newInstance()的方式，创建泛型的对象，
 * 但是这种情况，传入的类型标签对应的类必须要有构造函数，所以不推荐这样干，
 * 下面说说显示的工厂这种方法（限制其类型，使得只能接受实现了这个工厂的类）：
 * 
 * 首先来创建一个工厂接口：
 */
interface Factory<T> {
    T create();
}

// 接下来创建一个对象，里面包含了一个需要使用工厂创建的泛型对象：
class Foo<T> {
    private T x;
    public <F extends Factory<T>> Foo(F factory){
        x = factory.create();
    }
}

// 接下来创建显示的工厂：
class IntegerFactory implements Factory<Integer>{
    @Override
    public Integer create() {
        return new Integer(0);
    }
}
class Widget {
    public static class WFactory implements Factory<Widget>{
        @Override
        public Widget create() {
            return new Widget();
        }
    }
}

// 这样子我们就可以创建泛型类中的泛型对象了，通过传入上面的显示工厂：
public class Chapter15_8_1 {
	public static void main(String[] args){
	    new Foo<Integer>(new IntegerFactory());
	    new Foo<Widget>(new Widget.WFactory());
	    
	    // TODO 模板方法设计模式
	}
}