package com.itzhai.javanote.test.string;

public class StringTest {

	public static void main(String[] args) {
		/**
		 * String对象是不可变的，对一个String进行处理，无论是进行追加，转换大小写之类的，都会生成一个新的字符串。
		 * 同时不可变性会带来一定的效率问题。下面通过一个追加字符串的字节码来了解下
		 */
		String who = "arthinking";
		String str1 = "what" + "is" + who + "?";
		System.out.println(str1);
		// 编译成字节码，查看下具体的执行流程
		// TODO:
		// 进入编译生成的Class所在目录，执行  javap -c StringTest，得到如下的JVM字节码
		// 其中的dup和invokevirtural相当于Java虚拟机上的汇编语言，编译器自动引入了java.lang.StringBuilder类。
		// 从红色的框框标识可以看到，编译器创建了一个StringBuilder对象，用于构造最终的String，
		// 并为每个字符串调用一次StringBuilder的append()方法，最后调用了toString()生成结构，并使用astore_2命令存为s。
		
	}
}
