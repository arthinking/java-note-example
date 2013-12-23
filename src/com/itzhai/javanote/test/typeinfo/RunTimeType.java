package com.itzhai.javanote.test.typeinfo;

import java.util.Arrays;
import java.util.List;

import com.itzhai.javanote.entity.Circle;
import com.itzhai.javanote.entity.Shape;
import com.itzhai.javanote.entity.Square;
import com.itzhai.javanote.entity.Triangle;

public class RunTimeType {

	public static void main(String[] args) {
		// 使用泛型，把数组元素向上转型为Shape，从而丢失了具体类型。
		List<Shape> shapeList = Arrays.asList(new Circle(), new Square(), new Triangle());
		// 当从数组中取出元素时，这种容器会将所有元素都当做Object持有，会自动将Object转换为Shape，这是RTTI的基本使用形式。
		for(Shape shape : shapeList){
			shape.draw();
		}
	}
}

// 创建一个抽象类
/*
abstract class Shape{
	// this调用了当前类的toString方法获得信息
	void draw() { System.out.println(this + ".draw()"); }
	// 声明toString()方法为abstract，从而强制继承者需要重写该方法。
	abstract public String toString();
}

class Circle extends Shape{
	public String toString(){ return "Circle"; }
}

class Square extends Shape{
	public String toString(){ return "Square"; }
}

class Triangle extends Shape{
	public String toString(){ return "Triangle"; }
}
*/