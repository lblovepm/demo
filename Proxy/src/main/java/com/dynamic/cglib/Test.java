package com.dynamic.cglib;

import com.common.HouseOwner2;
import org.springframework.cglib.proxy.Enhancer;

public class Test {

    public static void main(String[] args) {
        //设置增强需要用到的类
        Enhancer enhancer = new Enhancer();
        //设置需要代理的类(这里是HouseOwner2类)
        enhancer.setSuperclass(HouseOwner2.class);
        //设置需要回调的拦截器
        enhancer.setCallback(new CglibProxyMethodInterceptor());
        //生成对应的代理类
        HouseOwner2 houseOwner2 = (HouseOwner2) enhancer.create();
        houseOwner2.rentHouse();
    }
}
