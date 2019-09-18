package com.dynamic.jdk;

import com.common.IHouseOwner;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * JDK动态代理(代理的类【HouseOwner1】必须实现接口)
 * @author Mr.LB
 * @date 2019-09-18
 */
public class JdkProxyInvocationHandler implements InvocationHandler {

    //被代理的对象
    private Object proxyObject;

    public JdkProxyInvocationHandler(Object proxyObject){
        this.proxyObject = proxyObject;
    }

    /**
     * @param proxy 代理类的实例，和proxyObject不是同一个，相当于com.statics包中的ProxyObject
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("jdk动态代理执行前的操作-----------");

        Object result = method.invoke(proxyObject,args);

        return result;
    }
}
