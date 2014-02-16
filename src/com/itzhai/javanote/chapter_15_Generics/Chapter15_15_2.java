package com.itzhai.javanote.chapter_15_Generics;

import java.util.Date;

/**
 * 15 泛型
 *   15 混型
 *     2 与接口混合
 * 使用接口来产生混型的例子，Mixin类基本上是在使用代理，因此，每个混入类型都要求在Mixin中有一个相应的域，
 * 而你必须在Mixin中编写所有必须的方法，将方法调用转发给恰当的对象。
 * 
 */
interface TimeStamped { long getStamp(); }

class TimeStampedImp implements TimeStamped {
    private final long timeStamp;
    public TimeStampedImp() {
        timeStamp = new Date().getTime();
    }
    public long getStamp() { return timeStamp; }
}

interface SerialNumbered { long getSerialNumber(); }

class SerialNumberedImp implements SerialNumbered {
    private static long counter = 1;
    private final long serialNumber = counter++;
    public long getSerialNumber() { return serialNumber; }
}

interface Basic {
    public void set(String val);
    public String get();
}

class BasicImp implements Basic {
    private String value;
    public void set(String val) { value = val; }
    public String get() { return value; }
}

/**
 * Mixin类基本上是在使用代理，因此，每个混入类型都要求在Mixin中有一个相应的域。
 */
class Mixin extends BasicImp
implements TimeStamped, SerialNumbered {
    // 混入类型对应的域
    private TimeStamped timeStamp = new TimeStampedImp();
    // 混入类型对应的域
    private SerialNumbered serialNumber =
            new SerialNumberedImp();
    // 在Mixin中编写所有必须的方法，将方法调用转发给恰当的对象:
    public long getStamp() { return timeStamp.getStamp(); }
    public long getSerialNumber() {
        return serialNumber.getSerialNumber();
    }
}

public class Chapter15_15_2 {
    
    public static void main(String[] args) {
        Mixin mixin1 = new Mixin(), mixin2 = new Mixin();
        mixin1.set("test string 1");
        mixin2.set("test string 2");
        System.out.println(mixin1.get() + " " +
          mixin1.getStamp() +  " " + mixin1.getSerialNumber());
        System.out.println(mixin2.get() + " " +
          mixin2.getStamp() +  " " + mixin2.getSerialNumber());
    }
}

// 缺点：当使用更复杂的混型时，代码数量会急速增加

///:~