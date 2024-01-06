package org.example.service.impl;

import org.example.mapper.GoodsOrderMapper;
import org.example.pojo.GoodsOrder;
import org.example.service.GoodsOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsOrderServiceImpl implements GoodsOrderService {

    @Autowired
    private GoodsOrderMapper goodsOrderMapper;
    @Override
    public void createGoodsOrder(GoodsOrder goodsOrder) {
        goodsOrderMapper.createGoodsOrder(goodsOrder);
    }
}
