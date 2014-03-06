package com.itzhai.javanote.chapter_16_Array;

import java.util.Arrays;


/**
 * 16 数组 
 *   7 Arrays实用功能
 *     2 数组的比较
 * 
 * Arrays.equals(): 相等的条件：元素个数是否相等，对应位置的元素是否也相等。
 * 
 * 对于基本类型是通过使用其包装器类的equals()判断是否相等的
 */
class ComparingArrays {
	public static void main(String[] args) {
	    int[] a1 = new int[10];
	    int[] a2 = new int[10];
	    Arrays.fill(a1, 47);
	    Arrays.fill(a2, 47);
	    // 使用包装器类判断是否相等
	    System.out.println(Arrays.equals(a1, a2));
	    a2[3] = 11;
	    System.out.println(Arrays.equals(a1, a2));
	    String[] s1 = new String[4];
	    Arrays.fill(s1, "Hi");
	    String[] s2 = { new String("Hi"), new String("Hi"),
	      new String("Hi"), new String("Hi") };
	    // 不同的对象，比较结果却相等，是因为String数组相等是基于内容的（String的equals方法重载过）。
	    System.out.println(Arrays.equals(s1, s2));
	}
} /* Output:
	true
	false
	true
	*///:~

class User{
	private String username;
	private String nickname;
	public User(String username, String nickname){
		this.username = username;
		this.nickname = nickname;
	}
	public String getUsername() {
    	return username;
    }
	public void setUsername(String username) {
    	this.username = username;
    }
	public String getNickname() {
    	return nickname;
    }
	public void setNickname(String nickname) {
    	this.nickname = nickname;
    }
}

public class Chapter16_07_02 {
	public static void main(String[] args) {
	    User[] users1 = new User[3];
	    User[] users2 = new User[3];
	    User user1 = new User("Jason", "arthinking");
	    User user2 = new User("Jason", "arthinking");
	    Arrays.fill(users1, user1);
	    Arrays.fill(users2, user1);
	    // 输出为true
	    System.out.println(Arrays.equals(users1, users2));
	    Arrays.fill(users2, user2);
	    //  输出为 false
	    System.out.println(Arrays.equals(users1, users2));
	    // 可见对象直接调用了Object.equals()方法，比较内存地址是否相等
	    // 为了比较内容，需要重载User的equals方法
    }
}