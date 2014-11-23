package com.itzhai.javanote.proxy;

interface Animal{
    void sound();
}

class Dog implements Animal{
    public void sound(){
        System.out.println("wong~");
    }
}

public class Test {

    public static void main(String[] args){
        Animal dog = new Dog();  
        MyInvocationHandler handler = new AnimalHandler(dog);  
        Animal animal;
        try {
            animal = (Animal) MyProxy.newProxyInstance(Animal.class,handler);
            animal.sound();  
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }  
    }
}
/**
 * ToolProvider.getSystemJavaCompiler(); 通过这个获得编译器，注意，ToolProvider会查找JAVA_HOME对应目录的jrexx/lib/tools.jar，所以需要把这个文件从jdkxx/lib目录下拷贝到jrexx/lib目录下，才能够正确返回，否则报空指针异常。
 */