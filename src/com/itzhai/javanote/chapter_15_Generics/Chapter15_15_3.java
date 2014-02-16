package com.itzhai.javanote.chapter_15_Generics;

import java.util.Date;

/**
 * 15 泛型
 *   15 混型
 *     3 使用装饰器模式
 *     
 * 当你观察混型的使用方式时，就会发现混型概念好像与装饰器设计模式关系很近。
 * 
 * 装饰器模糊使用分层对象来动态透明地向单个对象中添加责任。装饰器指定包装在最初的对象周围的所有对象都具有相同的基本接口。
 * 某些事物是可装饰的，可以通过将其他类包装在这个可装饰对象的四周，来将功能分层。
 * 
 * 装饰器是通过使用组合和形式化结构来实现的，而混型是基于继承的。
 * 因此可以将基于参数化类型的混型当做是一种泛型装饰器机制，这种机制不需要装饰器设计模式的继承结构。
 * 
 * 上一节的例子可以改写为使用装饰器：
 */
class Basic {
    private String value;
    public void set(String val) { value = val; }
    public String get() { return value; }
}

class Decorator extends Basic {
    protected Basic basic;
    public Decorator(Basic basic) { this.basic = basic; }
    public void set(String val) { basic.set(val); }
    public String get() { return basic.get(); }
}   

class TimeStamped extends Decorator {
    private final long timeStamp;
    public TimeStamped(Basic basic) {
        super(basic);
        timeStamp = new Date().getTime();
    }
    public long getStamp() { return timeStamp; }
}

class SerialNumbered extends Decorator {
    private static long counter = 1;
    private final long serialNumber = counter++;
    public SerialNumbered(Basic basic) { super(basic); }
    public long getSerialNumber() { return serialNumber; }
}   

class Decoration {
    public static void main(String[] args) {
        TimeStamped t = new TimeStamped(new Basic());
        TimeStamped t2 = new TimeStamped(
                new SerialNumbered(new Basic()));
        //! t2.getSerialNumber(); // Not available
        SerialNumbered s = new SerialNumbered(new Basic());
        SerialNumbered s2 = new SerialNumbered(
                new TimeStamped(new Basic()));
        //! s2.getStamp(); // Not available
    }
}

public class Chapter15_15_3 {
    
    public static void main(String[] args) {
        
    }
}

// 缺点：从main方法注释掉的两行代码可以发现，使用装饰器所产生的对象类型是最后被装饰的类型，
// 尽管可以添加多个层，但是最后一层才是实际的类型，因此只有最后一层方法是可视的。
// 因此，对于装饰器来说，其明显的缺陷是它只能有效地工作于装饰中的最后一层，而混型方法显然会更自然一些。
// 因此，装饰器只是对由混型提出的问题的一种局限的解决方案。

///:~