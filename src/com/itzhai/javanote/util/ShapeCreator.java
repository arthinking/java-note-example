package com.itzhai.javanote.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.itzhai.javanote.entity.Shape;

public abstract class ShapeCreator {
	private Random rand = new Random(10);
	// 返回一个对象类型数组，由实现类提供，后面会看到两种实现形式，基于forName的和基于类字面常量的.class
	public abstract List<Class<? extends Shape>> types();
	// 随机生成一个对象类型数组中的类型对象实例
	public Shape randomShape(){
		int n = rand.nextInt(types().size());
		try {
			return types().get(n).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
	// 生成一个随机数组
	public Shape[] createArray(int size){
		Shape[] result = new Shape[size];
		for(int i=0; i<size; i++){
			result[i] = randomShape();
		}
		return result;
	}
	// 生成一个随机数组，泛型的ArrayList
	public ArrayList<Shape> arrayList(int size){
		ArrayList<Shape> result = new ArrayList<Shape>();
		Collections.addAll(result, createArray(size));
		return result;
	}
}
