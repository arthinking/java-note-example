package com.itzhai.javanote.util;

import java.util.ArrayList;
import java.util.List;

import com.itzhai.javanote.entity.Shape;

/**
 * forName的生成器实现
 * @author arthinking
 *
 */
public class ForNameCreator extends ShapeCreator{

	private static List<Class<? extends Shape>> types = 
			new ArrayList<Class<? extends Shape>>();
	private static String[] typeNames = {
		"com.itzhai.javanote.entity.Circle",
		"com.itzhai.javanote.entity.Square",
		"com.itzhai.javanote.entity.Triangle"
	};
	
	@SuppressWarnings("unused")
	private static void loader(){
		for(String name : typeNames){
			try {
				types.add((Class<? extends Shape>)Class.forName(name));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	// 初始化加载所需的类型数组
	static {
		loader();
	}
	public List<Class<? extends Shape>> types() {
		return types;
	}
}
