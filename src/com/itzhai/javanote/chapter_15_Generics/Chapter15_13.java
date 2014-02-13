package com.itzhai.javanote.chapter_15_Generics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * 15 泛型
 *   13 动态类型安全
 * 没有使用泛型之前的代码，是不能限定放入容器的元素类型的，所以旧式的代码有可能会破坏你的容器。
 * JavaSE5中有一组静态方法可以检查类型问题：checkedCollection(), checkedList(), checkedMap(), checkedSet(), checkedSortedMap(), checkedSortedSet()
 * 下面演示一下这种情况：
 */
interface Pet{}
class Dog implements Pet{}
class Cat implements Pet{}
public class Chapter15_13 {
    
    // 假设oldStyleMethod()是遗留的代码
    @SuppressWarnings("unchecked")
    static void oldStyleMethod(List probablyDogs) {
        probablyDogs.add(new Cat());
    } 
    
    public static void main(String[] args) {
        // 没有检验之前插入是没有问题的
        List<Dog> dogs1 = new ArrayList<Dog>();
        oldStyleMethod(dogs1); // Quietly accepts a Cat
        // 检验之后抛出 ClassCastException
        List<Dog> dogs2 = Collections.checkedList(
                new ArrayList<Dog>(), Dog.class);
        try {
            oldStyleMethod(dogs2); // Throws an exception
        } catch(Exception e) {
            System.out.println(e);
        }
        // Derived types work fine:
        List<Pet> pets = Collections.checkedList(
                new ArrayList<Pet>(), Pet.class);
        pets.add(new Dog());
        pets.add(new Cat());
    }
}