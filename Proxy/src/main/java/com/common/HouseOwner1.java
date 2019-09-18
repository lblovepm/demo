package com.common;

/**
 * 房东角色的实现类
 * @author Mr.LB
 * @date 2019-09-18
 */
public class HouseOwner1 implements IHouseOwner {

    @Override
    public Boolean rentHouse() {
        System.out.println("我是房东本人: 我的房子出租");

        return true;
    }
}
