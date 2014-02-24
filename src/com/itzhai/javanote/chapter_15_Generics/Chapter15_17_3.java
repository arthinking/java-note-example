package com.itzhai.javanote.chapter_15_Generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import temp.generics.SimpleQueue;

/**
 * 15 泛型
 *   17 对缺乏潜在类型机制的补偿
 *     3 当你并未碰巧拥有正确的接口时
 *     
 * 上一节示例的Iterable接口是内建的，如果刚好不存在适合你的接口的时候呢？
 * 下面的例子中，没有预见到对“Addable”接口的需要，所以我们被限制在Collection继承层次结构之内，即便SimpleQueue有一个add()方法，
 * 它也不能工作。因为这会将代码限制为只能工作于Collection，因此这样的代码不是特别的繁华。有了潜在类型机制，情况就会不同了。
 */
class Fill {
    // 被限制在Collection继承层次结构之内
    public static <T> void fill(Collection<T> collection,
            Class<? extends T> classToken, int size) {
        for(int i = 0; i < size; i++)
            // Assumes default constructor:
            try {
                collection.add(classToken.newInstance());
            } catch(Exception e) {
                throw new RuntimeException(e);
            }
    }
}

class Contract {
    private static long counter = 0;
    private final long id = counter++;
    public String toString() {
        return getClass().getName() + " " + id;
    }
}

class TitleTransfer extends Contract {}

public class Chapter15_17_3 {
    public static void main(String[] args) throws Exception {
        List<Contract> contracts = new ArrayList<Contract>();
        Fill.fill(contracts, Contract.class, 3);
        Fill.fill(contracts, TitleTransfer.class, 2);
        for(Contract c: contracts)
            System.out.println(c);
        SimpleQueue<Contract> contractQueue =
                new SimpleQueue<Contract>();
        // Won't work. fill() is not generic enough:
        // Fill.fill(contractQueue, Contract.class, 3);
    }
}

/* Output:
Contract 0
Contract 1
Contract 2
TitleTransfer 3
TitleTransfer 4
*///:~