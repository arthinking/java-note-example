package com.itzhai.javanote.proxy;

import java.lang.reflect.*;

public class ProxyPen implements com.itzhai.javanote.proxy.Animal{
   private com.itzhai.javanote.proxy.MyInvocationHandler handler;
   public ProxyPen(com.itzhai.javanote.proxy.AnimalHandler handler){
       this.handler = handler;
   }

 public void sound() {
       try{ 
       Method md = com.itzhai.javanote.proxy.Animal.class.getMethod("sound");
       handler.invoke( md,new Object[]{});
       }catch(Exception e){e.printStackTrace();}
   }

}
