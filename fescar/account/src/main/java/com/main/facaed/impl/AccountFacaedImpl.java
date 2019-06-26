package com.main.facaed.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.main.entity.TAccount;
import com.main.facaed.AccountFacaed;
import com.main.mapper.TAccountMapper;

import javax.annotation.Resource;

/**
 * @author Mr.LB
 * @description: TODO
 * @date 2019/6/4 18:48
 */
@Service
public class AccountFacaedImpl implements AccountFacaed {

    @Resource
    private TAccountMapper tAccountMapper;

    @Override
    public int reduceAccount(String userId, Double money) {

        TAccount tAccount = new TAccount();
        tAccount.setUserId(userId);
        TAccount resultTAccount = tAccountMapper.selectOne(new QueryWrapper<>(tAccount));
        resultTAccount.setAmount(resultTAccount.getAmount()-money);
        tAccountMapper.updateById(resultTAccount);

        return 1;
    }
}
