package com.kafka.demo.test;

import java.util.*;

/**
 * @author Mr.LB
 * @description: TODO
 * @date 2019/5/30 15:41
 */
public class TestChild extends TestParent {

    public static void main(String[] args) {
        List<String> list = null;

        Map<Integer,List<String>> map = new HashMap<>();

        for (int i = 0;i < 10; i++){
            list = map.get(i);
            if(null == list){
                list = new ArrayList<>();
                map.put(i,list);
            }
            list.add(""+i);
        }

        Set<Integer> keySet = map.keySet();
        for (int i = 0;i < keySet.size(); i++){

        }
    }
}
