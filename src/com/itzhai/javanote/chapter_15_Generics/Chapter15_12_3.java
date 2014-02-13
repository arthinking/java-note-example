package com.itzhai.javanote.chapter_15_Generics;

/**
 * 15 泛型
 *   12 自限定的类型
 *     3 参数协变
 * 前一节演示的自限定类型的价值在于可以产生协变参数类型：方法参数类型会随子类而变化。
 * 而自限定类型还可以产生于子类类型相同的返回值，如前一节的B类，但这并不是重要的，因为协变返回类型是在JavaSE5中引入的，之前的JDK版本并不能编译自限定
 */
class Base {}
class Derived extends Base {}

interface OrdinaryGetter {
  Base get();
}

// 子接口继承OrdinaryGetter，其中的get()方法返回值为到处的雷系Derived，这在早先的Java版本是不合法的
interface DerivedGetter extends OrdinaryGetter {
  // Return type of overridden method is allowed to vary:
  Derived get();
}

class CovariantReturnTypes {
  void test(DerivedGetter d) {
    Derived d2 = d.get();
  }
}

/**
 * 下面演示一下自限定类型中导出类的方法接受导出类型而不是及类型为参数的
 * 首先看一下非泛型代码中，参数不能随子类型发生变化的例子
 *
 */
class OrdinarySetter {
    void set(Base base) {
      System.out.println("OrdinarySetter.set(Base)");
    }
}

class DerivedSetter extends OrdinarySetter {
    void set(Derived derived) {
      System.out.println("DerivedSetter.set(Derived)");
    }
}   

class OrdinaryArguments {
    public static void main(String[] args) {
      Base base = new Base();
      Derived derived = new Derived();
      DerivedSetter ds = new DerivedSetter();
      ds.set(derived);
      ds.set(base); // 这里ds实际上有两个方法，在继承的时候，set方法被重载了，而不是覆盖了。
    }
}

// 而在自限定中，是这样的：编译器不能识别将基类型当做参数传递给set()的尝试，因为没有任何方法具有这样的签名。
// 实际上，这个参数已经被覆盖了：
interface SelfBoundSetter<T extends SelfBoundSetter<T>> {
    void set(T arg);
}

interface Setter extends SelfBoundSetter<Setter> {}

class SelfBoundingAndCovariantArguments {
    void testA(Setter s1, Setter s2, SelfBoundSetter sbs) {
        s1.set(s2);
        // s1.set(sbs); // 错误，不存在这样的方法，这个方法已被Setter子类覆盖
        // set(Setter) in SelfBoundSetter<Setter>
        // cannot be applied to (SelfBoundSetter)
    }
}

// 而没有使用自限定类型的情况下，普通的基础机制就会介入，这个时候方法就会被重载，就像在非泛型的情况下一样：
class GenericSetter<T> { // 非自限定类型
    void set(T arg){
      System.out.println("GenericSetter.set(Base)");
    }
}

class DerivedGS extends GenericSetter<Base> {
    void set(Derived derived){
        System.out.println("DerivedGS.set(Derived)");
    }
}   

public class Chapter15_12_3 {
    public static void main(String[] args) {
        Base base = new Base();
        Derived derived = new Derived();
        DerivedGS dgs = new DerivedGS();
        dgs.set(derived);
        dgs.set(base); // 编译通过，DerivedGS中的set()方法被重载，非覆盖
    }
}