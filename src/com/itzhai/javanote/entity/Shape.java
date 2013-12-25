package com.itzhai.javanote.entity;

/**
 * 创建一个抽象类
 */
public abstract class Shape{
    // this调用了当前类的toString方法获得信息
    public void draw() { System.out.println(this + ".draw()"); }
    // 声明toString()方法为abstract，从而强制继承者需要重写该方法。
    abstract public String toString();
}