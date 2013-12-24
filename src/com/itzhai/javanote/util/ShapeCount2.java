package com.itzhai.javanote.util;

import java.util.HashMap;
import java.util.List;

import com.itzhai.javanote.entity.Circle;
import com.itzhai.javanote.entity.Shape;
import com.itzhai.javanote.entity.Square;
import com.itzhai.javanote.entity.Triangle;

/**
 * 通过使用Class.instanceof动态的测试对象，移除掉原来的ShapeCount中单调的instanceof语句
 * @author arthinking
 *
 */
public class ShapeCount2 {
	
	private static final List<Class<? extends Shape>> shapeTypes = LiteralCreator.allType;
	
	
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
	
	// 演示通过Class.isInstance()统计对象类型
	public static void countShapes(ShapeCreator creator){
		ShapeCounter counter = new ShapeCounter();
		for(Shape shape : creator.createArray(20)){
			for(Class<? extends Shape> cls : shapeTypes){
				if(cls.isInstance(shape)){
					counter.count(cls.getSimpleName());
				}
			}
		}
		System.out.println(counter);
	}
	
	public static void main(String[] args){
		countShapes(new ForNameCreator());
	}
}
