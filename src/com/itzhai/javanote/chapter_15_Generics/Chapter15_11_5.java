package com.itzhai.javanote.chapter_15_Generics;

/**
 * 15 泛型
 *   11 问题
 *     5 基类劫持了接口
 * @author arthinking
 *
 */
class ComparablePet implements Comparable<ComparablePet> {
    public int compareTo(ComparablePet arg) { return 0; }
}
// 报错：The interface Comparable cannot be implemented more than once with different arguments: 
// Comparable<ComparablePet> and Comparable<TomCat>
// 基类ComparablePet劫持了Comparable接口，只能进行ComparablePet的比较，而不能进行TomCat的比较
class TomCat extends ComparablePet implements Comparable<TomCat>{
    // Error: Comparable cannot be inherited with
    // different arguments: <Cat> and <Pet>
    public int compareTo(TomCat arg) { return 0; }
}

/**
 * 下面演示实现ComparablePet中的相同接口的可行性：
 * 这只是与覆盖基类中的方法相同
 */
class Hamster extends ComparablePet implements Comparable<ComparablePet> {
    public int compareTo(ComparablePet arg) { return 0; }
}
// Or just:
class Gecko extends ComparablePet {
    public int compareTo(ComparablePet arg) { return 0; }
}

public class Chapter15_11_5 {
}
