package com.itzhai.javanote.chapter_15_Generics;

/**
 * 15 泛型  
 *   9 边界
 * 
 * 使用无界泛型调用的方法只能是Object可以调用的方法，如果能够将参数类型限定为某个类型子集，就可以用这些类型子集来调用方法了。
 * 
 * 使用extends关键字给泛型声明添加边界：
 */
interface Animal{
    public void speek();
}
interface Fish{
    public void bubble();
}
class GoldenFish implements Animal, Fish{
    @Override
    public void bubble() {
        System.out.println("O。.");
    }
    @Override
    public void speek() {
        System.out.println("wow~");
    }
}
class HoldItem<T>{
    T item;
    HoldItem(T item){ this.item = item; }
    T getItem() { return item; }
}
class Item1<T extends Animal & Fish> extends HoldItem<T>{
    Item1(T item){ super(item); }
    public void doSomething(){
        item.speek();
        item.bubble();
    }
}

public class Chapter15_9 {
	public static void main(String[] args){
	    GoldenFish fish = new GoldenFish();
	    // 创建泛型类，super关键字对应的类继承结构
	    Item1<GoldenFish> item1 = new Item1<GoldenFish>(fish);
	    item1.doSomething();
	}
}