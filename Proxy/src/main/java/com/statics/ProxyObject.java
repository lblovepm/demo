package com.statics;

import com.common.IHouseOwner;

/**
 * 静态代理，代理一些真实角色,并且在真是角色的基础上增加一些附加属性
 * @author Mr.LB
 * @date 2019-09-18
 *
 *    优点:
 * 　　　　使真实角色处理的业务更加的纯粹，不再关注一些公共的事；
 * 　　　　公共的业务由代理来完成，实现了业务的分工；
 * 　　　　公共业务的扩展变得更加集中和方便
 * 　　缺点:
 * 　　　　类变多了，多了代理类，工作量变大了，且不易扩展
 * 　　　　解决此问题的方案就是使用动态代理
 */
public class ProxyObject implements IHouseOwner {

    private IHouseOwner iHouseOwner;

    public ProxyObject(IHouseOwner iHouseOwner){
        this.iHouseOwner = iHouseOwner;
    }

    @Override
    public Boolean rentHouse() {
        Boolean flag = false;

        seeHouse();
        flag = iHouseOwner.rentHouse();
        signContract();

        return flag;
    }

    /**************代理角色附带（增强）的一些功能**************/
    //看房子
    private void seeHouse(){
        System.out.println("我是房产中介: 我正在带租客看房子");
    }

    //签合同
    private void signContract(){
        System.out.println("我是房产中介: 我正在和租客签合同");
    }
}
