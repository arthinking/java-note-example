package com.itzhai.javanote.chapter_17_Container;

import java.util.Iterator;
import java.util.LinkedHashMap;

import temp.net.mindview.util.CountingGenerator;
import temp.net.mindview.util.Generator;
import temp.net.mindview.util.RandomGenerator;

/**
 * 17 容器深入研究
 *   2 填充容器
 *     2 Map生成器
 * 
 * 填充map需要有一个Pair类因为组装Map，每次调用Generator的next()放都必须产生一个对象对：
 */
class Pair<K,V> {
    public final K key;
    public final V value;
    public Pair(K k, V v) {
      key = k;
      value = v;
    }
} ///:~


/**
 * Map适配器现在可以使用各种不同的Generator，Iterator和常量值的组合来填充Map初始化对象：
 */
class MapData<K,V> extends LinkedHashMap<K,V> {
    // 直接生成Pair的生成器：
    public MapData(Generator<Pair<K,V>> gen, int quantity) {
      for(int i = 0; i < quantity; i++) {
        Pair<K,V> p = gen.next();
        put(p.key, p.value);
      }
    }
    // Key和Value分开：
    public MapData(Generator<K> genK, Generator<V> genV,
        int quantity) {
      for(int i = 0; i < quantity; i++) {
        put(genK.next(), genV.next());
      }
    }
    // 一个Key的生成器，使用同一个value填充：
    public MapData(Generator<K> genK, V value, int quantity){
      for(int i = 0; i < quantity; i++) {
        put(genK.next(), value);
      }
    }
    // An Iterable and a value Generator:
    public MapData(Iterable<K> genK, Generator<V> genV) {
      for(K key : genK) {
        put(key, genV.next());
      }
    }
    // An Iterable and a single value:
    public MapData(Iterable<K> genK, V value) {
      for(K key : genK) {
        put(key, value);
      }
    }
    // Generic convenience methods:
    public static <K,V> MapData<K,V>
    map(Generator<Pair<K,V>> gen, int quantity) {
      return new MapData<K,V>(gen, quantity);
    }
    public static <K,V> MapData<K,V>
    map(Generator<K> genK, Generator<V> genV, int quantity) {
      return new MapData<K,V>(genK, genV, quantity);
    }
    public static <K,V> MapData<K,V>
    map(Generator<K> genK, V value, int quantity) {
      return new MapData<K,V>(genK, value, quantity);
    }
    public static <K,V> MapData<K,V>
    map(Iterable<K> genK, Generator<V> genV) {
      return new MapData<K,V>(genK, genV);
    }
    public static <K,V> MapData<K,V>
    map(Iterable<K> genK, V value) {
      return new MapData<K,V>(genK, value);
    }
} 

/**
 * 下面是使用MapData的示例，LettersGenerator通过产生一个Iterator还实现了Iterable，
 * 通过这种方式，它可以被用来测试MapData.map()方法，而这些方法都需要用到Iterable：
 */
class Letters implements Generator<Pair<Integer,String>>,Iterable<Integer> {
    private int size = 9;
    private int number = 1;
    private char letter = 'A';
    public Pair<Integer,String> next() {
        return new Pair<Integer,String>(
                number++, "" + letter++);
    }
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            public Integer next() { return number++; }
            public boolean hasNext() { return number < size; }
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}

class MapDataTest {
    public static void main(String[] args) {
        // Pair Generator:
        System.out.println(MapData.map(new Letters(), 11));
        // Two separate generators:
        System.out.println(MapData.map(new CountingGenerator.Character(),
                new RandomGenerator.String(3), 8));
        // A key Generator and a single value:
        System.out.println(MapData.map(new CountingGenerator.Character(),
                "Value", 6));
        // An Iterable and a value Generator:
        System.out.println(MapData.map(new Letters(),
                new RandomGenerator.String(3)));
        // An Iterable and a single value:
        System.out.println(MapData.map(new Letters(), "Pop"));
}
} /* Output:
{1=A, 2=B, 3=C, 4=D, 5=E, 6=F, 7=G, 8=H, 9=I, 10=J, 11=K}
{a=YNz, b=brn, c=yGc, d=FOW, e=ZnT, f=cQr, g=Gse, h=GZM}
{a=Value, b=Value, c=Value, d=Value, e=Value, f=Value}
{1=mJM, 2=RoE, 3=suE, 4=cUO, 5=neO, 6=EdL, 7=smw, 8=HLG}
{1=Pop, 2=Pop, 3=Pop, 4=Pop, 5=Pop, 6=Pop, 7=Pop, 8=Pop}
*///:~


public class Chapter17_02_02 {

}
