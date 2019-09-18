package com.statics;

import com.common.HouseOwner1;
import com.common.IHouseOwner;

public class Test {

    public static void main(String[] args) {
        IHouseOwner iHouseOwner = new HouseOwner1();
        ProxyObject proxyStatic = new ProxyObject(iHouseOwner);

        proxyStatic.rentHouse();
    }
}
