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
		// 进入编译生成的Class所在目录，执行  javap -c StringTest，得到如下的JVM字节码:
		
		// 其中的dup和invokevirtural相当于Java虚拟机上的汇编语言，编译器自动引入了java.lang.StringBuilder类。
		// 从红色的框框标识可以看到，编译器创建了一个StringBuilder对象，用于构造最终的String，
		// 并为每个字符串调用一次StringBuilder的append()方法，最后调用了toString()生成结构，并使用astore_2命令存为s。
		
	}
	
	/**
	 * 明确的使用StringBuilder对象能够避免产生过多的StringBuilder对象
	 */
	
	/**
	 * 在循环里使用+隐式的拼接字符串
	 */
	public String implicit(String[] fields){
		String result = "";
		for(int i=0; i<fields.length; i++){
			result += fields[i];
		}
		return result;
	}
	
	/**
	 * 在循环里显示的使用StringBuilder的append方法拼接字符串
	 */
	public String explicit(String[] fields){
		StringBuilder result = new StringBuilder();
		for(int i=0; i<fields.length; i++){
			result.append(fields[i]);
		}
		return result.toString();
	}
	/**
	 * 同样，进入编译生成的Class所在目录，执行  javap -c StringTest，得到JVM字节码，下面是implicit方法的：
	 * 
	 * 从第8行到第35行构成了一个循环体可以看出，StringBuilder是在循环体内构造的，这意味着每经过一次循环，就会创建一个新的StringBuilder对象
	 * 
	 * 下面是explicit方法的：
	 * 可以看到，这里只生成了一个StringBuilder对象，还可以为StringBuilder对象预先为其指定大小，如果已经知道最终的字符串大概有多长，这样可以避免多次给StringBuilder重新分配缓冲。
	 * 
	 * 总结：当为一个类编写toString()方法时，如果字符串操作比较简单，可以信赖编译器，它会合理的为你构造最终的字符串结果，如果要在toString()方法中使用循环，最好自己创建一个StringBuilder对象。
	 * 执行例如  append(a + ":" + b)，编译器会掉入陷阱，从而另外创建一个StringBuilder对象处理括号内的字符串操作。
	 * 
	 * 延伸：StringBuilder与StringBuffer的区别：
	 * StringBuilder提供了丰富而全面的方法，包括insert(), repleace(), substring(), reverse(), append(), toString(), delete()
	 */
	
}
