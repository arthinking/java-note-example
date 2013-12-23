package com.itzhai.javanote.test.string;

import java.util.Formatter;

public class TableData {

	// 格式化说明符的使用例子
	private Formatter f = new Formatter(System.out);
	public void printHead(){
		f.format("%-10s %5s %5s\n", "username", "level", "score");
		f.format("%-10s %5s %5s\n", "---", "---", "---");
	}
	public void printData(){
		f.format("%-10s %5d %5.2f\n", "Jason", 1, 9.87654321);
		f.format("%-10s %5d %5.2f\n", "arthinking", 2, 9.6512);
	}
	
	public static void main(String[] args){
		TableData tb = new TableData();
		tb.printHead();
		tb.printData();
	}
}
