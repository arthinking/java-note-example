package com.itzhai.javanote.chapter_15_Generics;

import java.lang.reflect.Method;

/**
 * 15 泛型
 *   17 对缺乏潜在类型机制的补偿
 *     1 反射
 *     
 * 对于潜在类型机制的一种补偿，可以使用的一种方式是反射，下面的perform()方法就是用了潜在类型机制：
 */
class Mime {
    public void walkAgainstTheWind() {}
    public void sit() { System.out.println("Pretending to sit"); }
    public void pushInvisibleWalls() {}
    public String toString() { return "Mime"; }
}

class SmartDog {
    public void speak() { System.out.println("Woof!"); }
    public void sit() { System.out.println("Sitting"); }
    public void reproduce() {}
}

class CommunicateReflectively {
    public static void perform(Object speaker) {
        Class<?> spkr = speaker.getClass();
        try {
            try {
                Method speak = spkr.getMethod("speak");
                speak.invoke(speaker);
            } catch(NoSuchMethodException e) {
                System.out.println(speaker + " cannot speak");
            }
            try {
                Method sit = spkr.getMethod("sit");
                sit.invoke(speaker);
            } catch(NoSuchMethodException e) {
                System.out.println(speaker + " cannot sit");
            }
        } catch(Exception e) {
            throw new RuntimeException(speaker.toString(), e);
        }
    }
}

public class Chapter15_17_1 {
    
    public static void main(String[] args) {
        CommunicateReflectively.perform(new SmartDog());
        CommunicateReflectively.perform(new Robot());
        CommunicateReflectively.perform(new Mime());
    }
}

/* Output:
Woof!
Sitting
Click!
Clank!
Mime cannot speak
Pretending to sit
*///:~