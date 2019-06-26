package com.main.facaed.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.main.entity.TStorage;
import com.main.facaed.StorageFacaed;
import com.main.mapper.TStorageMapper;

import javax.annotation.Resource;

/**
 * @author Mr.LB
 * @description: TODO
 * @date 2019/6/4 19:02
 */
@Service
public class StorageFacaedImpl implements StorageFacaed {

    @Resource
    private TStorageMapper tStorageMapper;

    @Override
    public int reduceStorage(String commodityCode, int count) {

        TStorage tStorage = new TStorage();
        tStorage.setCommodityCode(commodityCode);
        TStorage resultTStorage = tStorageMapper.selectOne(new QueryWrapper<>(tStorage));
        resultTStorage.setCount(resultTStorage.getCount()-count);
        tStorageMapper.updateById(resultTStorage);

        return 1;
    }
}
