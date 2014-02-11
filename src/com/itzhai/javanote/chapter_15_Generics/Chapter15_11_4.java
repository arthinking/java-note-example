package com.itzhai.javanote.chapter_15_Generics;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

import com.itzhai.javanote.entity.Circle;

/**
 * 重载
 * 
 * 由于擦除的原因，重载方法将产生相同的类型签名
 * 
 */
class UseList<W,T> {
    // 错误：Method f(List<T>) has the same erasure f(List<E>) as another method in type UseList<W,T>
    void f(List<T> v) {}
    void f(List<W> v) {}
}

public class Chapter15_11_4 {
}
