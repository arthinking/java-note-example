package com.itzhai.javanote.test.string;

import java.util.Formatter;

public class Message{
	private String username;
	private Formatter f;
	public Message(String username, Formatter f){
		this.username = username;
		this.f = f;
	}
	public void publishMsg(int hour, int minute){
		f.format("%s says it is %d:%d\n", username, hour, minute);
	}
	public static void main(String[] args){
		Message msg1 = new Message("Jason",
				new Formatter(System.out)); // 传入System.out，信息输出到此
		msg1.publishMsg(10, 12);
		
		Message msg2 = new Message("Jason",
				new Formatter(System.err)); // 当然，也可以传入System.err
		msg2.publishMsg(10, 13);
		
	}
}
