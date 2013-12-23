package com.itzhai.javanote.util;

import java.util.HashMap;

import com.itzhai.javanote.entity.Circle;
import com.itzhai.javanote.entity.Shape;
import com.itzhai.javanote.entity.Square;
import com.itzhai.javanote.entity.Triangle;

public class ShapeCount {

	static class ShapeCounter extends HashMap<String, Integer>{
		public void count(String type){
			Integer quantity = get(type);
			if(quantity == null){
				put(type, 1);
			} else {
				put(type, quantity + 1);
			}
		}
	}
	
	// 演示通过instanceof关键字统计对象类型
	public static void countShapes(ShapeCreator creator){
		ShapeCounter counter = new ShapeCounter();
		for(Shape shape : creator.createArray(20)){
			if(shape instanceof Circle)
				counter.count("Circle");
			if(shape instanceof Square)
				counter.count("Square");
			if(shape instanceof Triangle){
				counter.count("Triangle");
			}
		}
		System.out.println(counter);
	}
	
	public static void main(String[] args){
		countShapes(new ForNameCreator());
	}
}
