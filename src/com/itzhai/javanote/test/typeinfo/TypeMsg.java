package com.itzhai.javanote.test.typeinfo;

import java.util.Arrays;
import java.util.List;

abstract class Shape {
	// this 调用当前类的toString()方法，返回实际的内容
	void draw(){ System.out.println(this + "draw()"); }
	// 声明 toString()为abstract类型，强制集成在重写该方法
	abstract public String toString();
}

class Circle extends Shape {
	public String toString(){ return "Circle"; }
}

class Square extends Shape {
	public String toString(){ return "Square"; }
}

class Triangle extends Shape {
	public String toString(){ return "Triangle"; }
}

public class TypeMsg {
	public static void main(String[] args){
		// 把Shape对象放入List<Shape>的数组的时候会向上转型为Shape，从而丢失了具体的类型信息
		List<Shape> shapeList = Arrays.asList(new Circle(), new Square(), new Triangle());
		// 从数组中取出时，这种容器，实际上所有的元素都当成Object持有，会自动将结果转型为Shape，这就是RTTI的基本的使用。
		for(Shape shape : shapeList){
			shape.draw();
		}
	}
}

