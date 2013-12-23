package com.itzhai.javanote.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.itzhai.javanote.entity.Circle;
import com.itzhai.javanote.entity.Shape;
import com.itzhai.javanote.entity.Square;
import com.itzhai.javanote.entity.Triangle;

/**
 * 字面量的生成器实现
 * @author arthinking
 *
 */
public class LiteralCreator extends ShapeCreator{

	public static final List<Class<? extends Shape>> allType = 
			Collections.unmodifiableList(Arrays.asList(Circle.class, Triangle.class, Square.class));
	
	public List<Class<? extends Shape>> types(){
		return allType;
	}
	
	public static void main(String[] args){
		System.out.println(allType);
	}
	
}
