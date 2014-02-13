package com.itzhai.javanote.chapter_15_Generics;

import java.util.ArrayList;
import java.util.List;

/**
 * 15 泛型
 *   14 异常
 * 泛型使用于异常是非常受限的，catch语句不能捕获泛型类型的异常，因为在编译器和运行时都必须知道异常的确切类型，
 * 泛型类也不能直接或间接的基础自Throwable（这将进一步组织你去定义不能捕获的泛型异常），
 * 但是，类型参数可能会在一个方法的throws子句中用到，这使得你可以编写随检查型异常的类而发生变化的泛型代码：
 */
interface Processor<T,E extends Exception> {
    void process(List<T> resultCollector) throws E; // 抛出泛型异常
}

// ProcessRunner 是一个可变数组，保存Processor
class ProcessRunner<T,E extends Exception> extends ArrayList<Processor<T,E>> {
    // processAll()方法依次执行所有的Processor，resultCollector是收集参数，存储process的结果
    List<T> processAll() throws E {  // 抛出泛型异常
        List<T> resultCollector = new ArrayList<T>();
        for(Processor<T,E> processor : this)
            processor.process(resultCollector);
            return resultCollector;
    }
}   

class Failure1 extends Exception {}

class Processor1 implements Processor<String,Failure1> {
    static int count = 3;
    public void
    process(List<String> resultCollector) throws Failure1 {
        if(count-- > 1)
            resultCollector.add("Hep!");
        else
            resultCollector.add("Ho!");
        if(count < 0)
            throw new Failure1();
        }
    }   

class Failure2 extends Exception {}

class Processor2 implements Processor<Integer,Failure2> {
    static int count = 2;
    public void
    process(List<Integer> resultCollector) throws Failure2 {
        if(count-- == 0)
            resultCollector.add(47);
        else {
            resultCollector.add(11);
        }
        if(count < 0)
            throw new Failure2();
    }
}   
  
public class Chapter15_14 {
    
    public static void main(String[] args) {
        ProcessRunner<String,Failure1> runner =
                new ProcessRunner<String,Failure1>();
        for(int i = 0; i < 3; i++)
            runner.add(new Processor1());
        try {
            System.out.println(runner.processAll());  // [Hep!, Hep!, Ho!]
        } catch(Failure1 e) {
            System.out.println(e);
        }

        ProcessRunner<Integer,Failure2> runner2 =
                new ProcessRunner<Integer,Failure2>();
        for(int i = 0; i < 3; i++)
            runner2.add(new Processor2());
        try {
         // 由于Processor2中的count为2，所以执行第三个Processor2的时候抛出异常了
            System.out.println(runner2.processAll());
        } catch(Failure2 e) {
            System.out.println(e);
        }
    }
}