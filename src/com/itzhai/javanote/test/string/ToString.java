package com.itzhai.javanote.test.string;

import java.util.ArrayList;

public class ToString {

	public static void main(String[] args){
		User user1 = new User("Jason", "123");
		User user2 = new User("arthinking", "123");
		ArrayList<User> users = new ArrayList<User>();
		users.add(user1);
		users.add(user2);
		System.out.println(users);
	}
}

class User {
	private String name;
	private String id;
	public User(String name, String id){
		this.name = name;
		this.id = id;
	}
	
//	@Override
//	public String toString() {
//		return this.name + ":" + this.id;
//	}
	
	@Override
	public String toString() {
		return "address: " + super.toString();
	}
}