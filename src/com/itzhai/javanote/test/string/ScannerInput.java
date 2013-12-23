package com.itzhai.javanote.test.string;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ScannerInput {

	public static void main(String[] args){
		/*
		// 使用StringReader将String转换为可读的流对象，然后使用BufferedReader对象封装，使得可以一次读入一行文本
		BufferedReader reader = new BufferedReader(new StringReader("Jason\n23 7"));
		try {
			System.out.println("Input your name:");
			String name = reader.readLine();
			System.out.println("name: " + name);
			System.out.println("How old are you and what's you favorite number?");
			String line = reader.readLine();
			String[] numArr = line.split(" ");
			// 逐个字符串转换解析
			int age = Integer.parseInt(numArr[0]);
			int num = Integer.parseInt(numArr[1]);
			System.out.println("age: " + age);
			System.out.println("favorite number: " + num);
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		/*
		// 使用StringReader将String转换为可读的流对象，然后使用BufferedReader对象封装，使得可以一次读入一行文本
		BufferedReader reader = new BufferedReader(new StringReader("Jason\n23 7"));
		Scanner stdin = new Scanner(reader);
		System.out.println("Input your name: ");
		String name = stdin.nextLine();
		System.out.println("name: " + name);
		System.out.println("How old are you and what's your favorite number?");
		int age = stdin.nextInt();
		int number = stdin.nextInt();
		System.out.println("age: " + age);
		System.out.println("favorite numver: " + number);
		*/
		
		/*
		Scanner scanner = new Scanner("1, 2, 3, 4, 5, 6, 7, 8");
		// 使用delimiter()获取当前正在作为定界符的Pattern对象
		System.out.println(scanner.delimiter());  // 输出: \p{javaWhitespace}+
		while(scanner.hasNext()){
			System.out.print("[" + scanner.next() + "]");
		}
		// 输出: [1,][2,][3,][4,][5,][6,][7,][8]
		scanner = new Scanner("1, 2, 3, 4, 5, 6, 7, 8");
		// 使用useDelimiter()方法来设定自己所需的定界符
		scanner.useDelimiter("\\s*,\\s*");
		while(scanner.hasNextInt()){
			System.out.print("[" + scanner.nextInt() + "]");
		}
		// 输出： [1][2][3][4][5][6][7][8]
		 */
		
		/*
		String content = "192.168.0.1\n" +
				"192.168.0.2\n" +
				"192.168.12.34";
		Scanner scanner = new Scanner(content);
		String pattern = "(\\d+[.]\\d+[.]\\d+[.]\\d+)";
		// 下面用正则表达式进行扫描
		while(scanner.hasNext(pattern)){
			scanner.next(pattern);
			MatchResult match = scanner.match();
			String ip = match.group(1);
			// 格式化输出IP
			System.out.format("ip address: %s\n", ip);
		}
		*/
		
		// 使用StringTokenizer的方式
		String content = "It is raining outside. 外面下着雨...";
		StringTokenizer stoke = new StringTokenizer(content);
		while(stoke.hasMoreElements()){
			System.out.print(stoke.nextToken() + " ");
		}
		
		// 使用split的方式
		System.out.println(Arrays.toString(content.split(" ")));
		
		// 使用Scanner的方式
		Scanner scanner = new Scanner(content);
		while(scanner.hasNext()){
			System.out.print(scanner.next() + " ");
		}
		
		
		
	}
}
