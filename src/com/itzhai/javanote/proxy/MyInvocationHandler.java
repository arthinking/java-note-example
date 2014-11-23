package com.itzhai.javanote.proxy;

import java.lang.reflect.Method;

public interface MyInvocationHandler {  
    public void invoke(Method m,Object... args) throws Exception;  
}