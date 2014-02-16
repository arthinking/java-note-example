package com.itzhai.javanote.chapter_15_Generics;

/**
 * 15 泛型
 *   16 潜在类型机制
 *   
 * Java泛型中，当要在泛型类型上执行操作时，就会产生问题，因为擦除要求指定可能会用到的泛型类型的边界，以安全地调用代码中的泛型对象上的具体方法。
 * 这是对“泛化”概念的一种明显的限制，因为必须限制你的泛型类型，使他们继承自特定的类，或者特定的接口。在某些情况下，你最终可能会使用普通类或者普通接口，
 * 因为限定边界的泛型和可能会和指定类或接口没有任何区别。
 * 
 * 某些编程语言提供的一种解决方法称为潜在雷系机制或结构化类型机制（鸭子类型机制：如果它走起来像鸭子，并且叫起来也像鸭子，那么你就可以将它当做鸭子对待。）
 * 
 * 潜在类型机制使得你可以横跨类继承结构，调用不属于某个公共接口的方法。因此，实际上一段代码可以声明：“我不关心你是什么类型，只要你可以speak()和sit()即可。”
 * 由于不要求具体类型，因此代码就可以更加泛化了。
 * 
 * 两种支持潜在类型机制的语言：Python和C++。
 * 
 * 下面一段Python代码演示下潜在类型机制的支持：
 */
/*
class Dog:
    def speak(self):
        print "Arf!"
    def sit(self):
        print "Sitting"
    def repoduce(self)
        pass

class Robot:
    def speak(self):
        print "Click!"
    def sit(self):
        print "Clank!"
    def repoduce(self)
        pass
        
def perform(anything):
    anything.spead()
    anything.sit()
*/

// perform的anything参数只是一个标示符，它必须能够执行perform()期望它执行的操作，因此这里隐含着一个接口，但是从来都不必显示地写出这个接口——它是潜在的。
// perform不关心其参数的类型，因此我们可以向它传递任何对象，只要该对象支持speak()和sit()方法，否则，得到运行时异常。

/**
 * Java的泛型是后来才添加的，因此没有任何机会可以去实现任何类型的潜在类型机制。
 * 
 * 如果试图用Java实现上面的示例，就会被强制要求使用一个类或者接口，并在边界表达式中指定它：
 */
interface Performs {
    void speak();
    void sit();
}

class PerformingDog extends Dog implements Performs {
    public void speak() { System.out.println("Woof!"); }
    public void sit() { System.out.println("Sitting"); }
    public void reproduce() {}
}

class Robot implements Performs {
    public void speak() { System.out.println("Click!"); }
    public void sit() { System.out.println("Clank!"); }
    public void oilChange() {}
}   

class Communicate {
    public static <T extends Performs>
    void perform(T performer) {
        performer.speak();
        performer.sit();
    }
}

public class Chapter15_16 {
    
    public static void main(String[] args) {
        PerformingDog d = new PerformingDog();
        Robot r = new Robot();
        Communicate.perform(d);
        Communicate.perform(r);
    }
}
/* Output:
Woof!
Sitting
Click!
Clank!
*/

// 注意：perform()不需要使用泛型来工作，它可以被简单的指定为接受一个Performs对象：
/*
class Communicate {
    public static void perform(Performs performer) {
        performer.speak();
        performer.sit();
    }
}
*/ 

///:~