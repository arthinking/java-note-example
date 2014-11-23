package com.itzhai.javanote.memorymonitor;

import java.util.ArrayList;

public class ArrayListTest {

	public static void main(String[] args){
		ArrayList<String> list = new ArrayList<String>();
		for(int i=0; i<1000000; i++){
			list.add("itzhai IT宅");
		}
		System.out.println(SizeOfAgent.fullSizeOf(list));
	}
}
