package com.itzhai.javanote.test.string;

import java.util.Formatter;

public class Test {

	public static void main(String[] agrs){
		Formatter f = new Formatter(System.out);
		f.format("boolean %b: ", "false");
		f.format("boolean %b: \n", 0);
		
		String formatStr = String.format("%#4X", 12);
		System.out.println(formatStr);
	}
}
