package com.itzhai.javanote.util;

import java.util.LinkedHashMap;

import com.itzhai.javanote.entity.Shape;

/**
 * 通过使用Class.instanceof动态的测试对象，移除掉原来的ShapeCount中单调的instanceof语句
 * @author arthinking
 *
 */
public class ShapeCount2 {

	static class ShapeCounter extends LinkedHashMap<Class<? extends Shape>, Integer>{
		public ShapeCounter(){
			super(MapData.map(LiteralCreator.allType, 0));
		}
		
	}
}
