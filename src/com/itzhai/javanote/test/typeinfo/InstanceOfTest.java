package com.itzhai.javanote.test.typeinfo;
import java.awt.Shape;

import com.itzhai.javanote.entity.Attribute;
import com.itzhai.javanote.entity.Circle;

public class InstanceOfTest {

    public static void main(String[] args){
        // instanceOf
        Circle c = new Circle();
        // 判断是否超类的实例
        System.out.format("Using instanceof: %s is a shape? %b\n", 
                c.toString(), c instanceof Shape);
        // 判断是否Circle的实例
        System.out.format("Using instanceof: %s is a circle? %b\n", 
                c.toString(), c instanceof Circle);
        // 判断是否超类的实例
        System.out.format("Using Class.isInstance: %s is a shape? %b\n", 
                c.toString(), Shape.class.isInstance(c));
        // 判断是否接口的实例
        System.out.format("Using Class.isInstance: %s is a Attribute? %b\n", 
                c.toString(), Attribute.class.isInstance(c));
        
        if(c instanceof Shape){
            
        }
    }
}

// 创建一个动物生成器
abstract class AnimalCreater{
    
}
