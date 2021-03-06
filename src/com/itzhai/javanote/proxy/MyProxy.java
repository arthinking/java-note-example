package com.itzhai.javanote.proxy;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class MyProxy {  
    
    public static Object newProxyInstance(Class interfaces,MyInvocationHandler handler) throws Exception{  
        String r = "\n";
        
        Method[] methods = interfaces.getMethods();  
          
        StringBuffer sb = new StringBuffer("");  
        // 生成需要代理的接口中的所有方法 的代码，通过调用MyInvocationHandler的invoke方法实现真实对象方法的调用
        for(int i =0;i<methods.length;i++){
            sb.append(" public void "+methods[i].getName()+"() {"+ r +  
            "       try{ "+ r +  
            "       Method md = "+interfaces.getName()+".class.getMethod(\""+methods[i].getName()+"\");"+ r +  
            "       handler.invoke( md,new Object[]{});"+ r +  
            "       }catch(Exception e){e.printStackTrace();}"+ r +     
            "   }"+r + r
            );  
        }
        
        // 生成完整的类代码
        String src = "package com.itzhai.javanote.proxy;"+ r + r + 
                     "import java.lang.reflect.*;"+ r + r + 
        "public class Proxy$1 implements "+interfaces.getName() +"{"+ r +  
      
        "   private com.itzhai.javanote.proxy.MyInvocationHandler handler;"+ r +  
      
        "   public Proxy$1("+handler.getClass().getName() +" handler){"+ r +  
        "       this.handler = handler;"+ r +  
        "   }"+ r + r +  
  
            sb.toString() +  
  
        "}" +r;  
        // 输出Java文件
        String dir = System.getProperty("user.dir")+"/src/com/itzhai/javanote/proxy/";  
        FileWriter writer = new FileWriter(new File(dir+"Proxy$1.java"));  
        writer.write(src);  
        writer.flush();  
        writer.close();  
        // 编译动态代理类
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null, null, null);  
        Iterable<? extends JavaFileObject> units = fileMgr.getJavaFileObjects(dir+"Proxy$1.java");
        Iterable<String> options = Arrays.asList("-d", System.getProperty("user.dir") + "/bin/");
        CompilationTask t = compiler.getTask(null, fileMgr, null, options, null, units);  
        t.call();  
        fileMgr.close();  
        
        // 加载动态代理类，并返回动态代理类的实例
        URL[] urls = new URL[]{new URL("file:/"+dir)};  
        URLClassLoader loader = new URLClassLoader(urls);  
        Class c = loader.loadClass("com.itzhai.javanote.proxy.Proxy$1");  
        Constructor ctr = c.getConstructor(handler.getClass());  
        return ctr.newInstance(handler);  
    }  
}