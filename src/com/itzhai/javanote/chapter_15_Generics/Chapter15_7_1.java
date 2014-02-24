package com.itzhai.javanote.chapter_15_Generics;

/**
 * 15 泛型  
 *   7 擦除的神秘之处
 *     1 C++的方式
 *     
 * 7.1、C++的方式
 * 查看下面的一段C++的泛型代码：
 * 
 * #include <iostream>
 * using namespace std;

 * template<class T> class Manipulator {
 *   T obj;
 * public:
 *   Manipulator(T x) { obj = x; }
 *   void manipulate() { obj.f(); }
 * };

 * class HasF {
 * public:
 *   void f() { cout << "HasF::f()" << endl; }
 * };
 * 
 * int main() {
 *   HasF hf;
 *   Manipulator<HasF> manipulator(hf);
 *   manipulator.manipulate();
 * } /* Output:
 * HasF::f()
 * 
 * 
 * C++编写的泛型，当模板被实例化时，模板代码知道其模板参数的类型，
 * C++编译器将进行检查，如果泛型对象调用了一个当前实例化对象不存在的方法，
 * 则报一个编译期错误。例如上面的manipulate里面调用了obj.f()，因为实例化的HasF存在这个方法，所以不会报错。

 * 而Java是使用擦除实现泛型的，在没有指定边界的情况下，是不能在泛型类里面直接调用泛型对象的方法的，如下面的例子：
 * public class Manipulator<T> {
 * 
 *     private T obj;
 *     public Manipulator(T x){
 *         obj = x;
 *     }
 *     public void doSomething(){
 *         obj.f();  // 编译错误
 *     }
 * }
 * 
 * 通过没有边界的obj调用f()，编译出错了，下面指定边界，让其通过编译：
 * public class Manipulator<T extends HasF> {
 * 
 *     private T obj;
 *     public Manipulator(T x){
 *         obj = x;
 *     }
 *     public void doSomething(){
 *         obj.f();  // 编译错误
 *     }
 * }
 * class HasF{
 *     public void f(){
 *         System.out.println("HasF.f();");
 *     }
 * }
 * 
 * 上面的例子，把泛型类型参数擦除到了HasF，就好像在类的声明中用HasF替换了T一样。
 */
public class Chapter15_7_1 {
	
}