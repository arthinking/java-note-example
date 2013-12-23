package com.itzhai.javanote.util;

import java.util.ArrayList;

import com.itzhai.javanote.entity.Shape;

/**
 * 现在生成器有了两种实现，我们在这里添加一层外观，设置默认的实现方式
 * @author arthinking
 *
 */
public class Shapes {

	public static final ShapeCreator creator =
			new LiteralCreator();
	public static Shape randomShape(){
		return creator.randomShape();
	}
	public static Shape[] createArray(int size){
		return creator.createArray(size);
	}
	public static ArrayList<Shape> arrayList(int size){
		return creator.arrayList(size);
	}
}
