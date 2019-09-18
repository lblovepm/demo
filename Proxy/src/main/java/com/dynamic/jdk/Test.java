package com.dynamic.jdk;

import com.common.HouseOwner1;
import com.common.IHouseOwner;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Test {

    public static void main(String[] args) {
        IHouseOwner iHouseOwner = new HouseOwner1();

        InvocationHandler invocationHandler = new JdkProxyInvocationHandler(iHouseOwner);

        IHouseOwner iHouseOwner1 = (IHouseOwner) Proxy.newProxyInstance(iHouseOwner.getClass().getClassLoader(),iHouseOwner.getClass().getInterfaces(),invocationHandler);
        iHouseOwner1.rentHouse();
    }
}
