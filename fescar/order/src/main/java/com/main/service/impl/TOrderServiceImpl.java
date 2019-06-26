package com.main.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.main.entity.TOrder;
import com.main.mapper.TOrderMapper;
import com.main.service.TOrderService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Mr.LB
 * @since 2019-06-11
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {

}
