package com.itzhai.javanote.test.string;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Match {
	public static void main(String[] args){
		/*
		String str = "-123";
		boolean isNum = str.matches("(-|\\+)?\\d+"); // 可能包含负号或者正号的整数
		System.out.println(isNum);  // output: true
		
		// split方法演示
		String content = "Hello, this is an iPad.";
		String[] items = content.split("\\W+");  // 使用非单词字符分割字符串
		System.out.println(Arrays.toString(items));  // [Hello, this, is, an, iPad]  可以发现逗号也被当做分隔符给去掉了
		
		// split方法的重载版本，允许你限制字符串分割的次数
		items = content.split("\\W+", 3);
		System.out.println(Arrays.toString(items)); // [Hello, this, is an iPad.]
		
		// 字符串的替换  replaceFirst和replaceAll方法
		String str = "You make me cry, make me smile.";
		System.out.println(str.replaceFirst("m\\w+", "music"));  // You music me cry, make me smile.
		System.out.println(str.replaceAll("make|me", "music"));  // You music music cry, music music smile.
		
		
		String content = "one step tototoo far.";
		// 编译正则表达式生成一个Pattern对象
		Pattern p = Pattern.compile("(to){2,}");
		// 用Pattern对象的matcher()方法检索字符串，生成Matcher对象
		Matcher m = p.matcher(content);
		while(m.find()){
			System.out.println("Match \"" + m.group() + "\" ad position " + m.start() + "-" + (m.end() - 1));
			// output: Match "tototo" ad position 9-14
		}
		
		// boolean find(int start)
		String content = "wings you are the hero~";
		Pattern p = Pattern.compile("\\w+");
		Matcher m = p.matcher(content);
		int i=0;
		while(m.find(i)){
			System.out.print(m.group() + " ");
			i++;
		}
		
		
		String content = "It's my life,\n wings you are the hero~";
		Pattern p = Pattern.compile("(?m)(\\S+)\\s+(\\S+\\s+\\S+)$"); 
		// 上面的(?m)是模式标记（后面有介绍），相当于Pattern.MULTILINE，表示多行模式也可以这样写：
		// Pattern p = Pattern.compile("(\\S+)\\s+(\\S+\\s+\\S+)$", Pattern.MULTILINE);
		Matcher m = p.matcher(content);
		if(m.find()){
			for(int i=0; i<=m.groupCount(); i++){ // 注意这里的groupCount不包含匹配到的整个字符串
				System.out.print("[" + m.group(i) + "]"); 
				// output: [It's my life,][It's][my life,]
			}
		}
		
		// 下面是Matcher的matches()和lookingAt()方法的使用举例
		String content = "It's my life, hero you are, no hero, wings you are the hero~";
		Pattern p = Pattern.compile(".*life");
		Matcher m = p.matcher(content);
		while(m.find()){
			System.out.println("find(): " + m.group() + " start at " + m.start() + " end at " + (m.end() - 1));
		}
		if(m.lookingAt()){
			System.out.println("lookingAt(): " + m.group() + " start at " + m.start() + " end at " + (m.end() - 1));
		}
		if(m.matches()){
			System.out.println("matches(): " + m.group() + " start at " + m.start() + " end at " + (m.end() - 1));
		}
		
		
		Pattern p = Pattern.compile("java", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
		Matcher m = p.matcher("java is a programming language\n JAVA");
		while(m.find()){
			System.out.println(m.group());
		}
		
		String content = "Wings- you are -the -hero~";
		System.out.println(Arrays.toString(Pattern.compile("-").split(content)));
		System.out.println(Arrays.toString(Pattern.compile("-").split(content, 2)));
		
		
//		String content = "/*! long   long  ago, the is a man called Jack, \n he has one boat. !*/;
//		// Pattern.DOTALL: 这种模式下 . 可以匹配任何字符，包括换行符
//		Pattern p = Pattern.compile("/\\*!(.*)!\\*/", Pattern.DOTALL);
//		Matcher m = p.matcher(content);
//		if(m.find())
//			// 匹配到/*! !*/中的内容
//			content = m.group(1);
//		// 把两个以上空格的地方缩减为一个空格
//		content = content.replaceAll(" {2,}", " ");
//		// 开启多行模式，删除每一行开头部分的空格，+表示匹配一个或多个
//		content = content.replaceAll("(?m)^ +", "");
//		// 匹配到字符串中的第一个元音字母，并替换为VOWEL
//		content = content.replaceFirst("[aeiou]", "VOWEL");
//		// 下面一段程序演示把字符串中的所有元音字母替换为大写
//		Pattern p1 = Pattern.compile("[aeiou]");
//		Matcher m1 = p1.matcher(content);
//		StringBuffer sb = new StringBuffer();
//		while(m1.find()){
//			// 非终端添加和替换，
//			m1.appendReplacement(sb, m1.group().toUpperCase());
//		}
//		// 终端添加和替换
//		m1.appendTail(sb);
//		System.out.println(sb);
		
//		Matcher m = Pattern.compile("[hks][io][sbo]")
//				.matcher("This is the key.");
//		while(m.find())
//			System.out.println(m.group()); // output: his
//		// 把Matcher应用于一个新的字符序列
//		m.reset("New version"); 
//		while(m.find())
//			System.out.println(m.group()); // output: sio


		ArrayList<String> list = new ArrayList<String>();
		list.add("This is String...");
		list.add("Big blue ocean");
		list.add("Search something");
		Pattern p = Pattern.compile("\\b[Ssb]\\w+", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher("");
		for(String line : list){
			m.reset(line);
			while(m.find()){
				System.out.println(m.group() + ": start at " + m.start());
			}
		}
	}
}
