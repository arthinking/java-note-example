package com.itzhai.javanote.chapter_15_Generics;
/**
 * 15 泛型  
 *   11 问题 
 *     2 实现参数化接口
 * 
 * 一个类不能实现同一个泛型接口的两种变体，由于擦除的原因，这两个变体会成为相同的接口。
 * @author arthinking
 *
 */
interface Payable<T>{}

class Employee implements Payable<Employee>{}

/**
 * 下面不能编译通过，因为擦除将会将 Payable<Employee> 和 Payable<Hourly> 简化为相同的类Payable。
 * 去掉泛型，却可以通过编译。
 *
 */
class Hourly extends Employee implements Payable<Hourly>{}

public class Chapter15_11_2 {
	
}
