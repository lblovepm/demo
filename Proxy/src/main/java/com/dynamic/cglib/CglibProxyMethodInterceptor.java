package com.dynamic.cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLIB动态代理(代理的类【HouseOwner2】可以不实现接口)
 * @author Mr.LB
 * @date 2019-09-18
 */
public class CglibProxyMethodInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object object, Method method, Object[] objectArr, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib动态代理执行前的操作-----------");

        Object result = methodProxy.invokeSuper(object,objectArr);

        return result;
    }
}
