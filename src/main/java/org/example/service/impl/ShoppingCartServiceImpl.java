package org.example.service.impl;

import org.example.mapper.ShoppingCartMapper;
import org.example.pojo.GoodsLists;
import org.example.pojo.ShoppingCart;
import org.example.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    @Override
    public Integer findCartByUserId(Integer userId) {
        Integer shoppingCartsList = shoppingCartMapper.findCartByUserId(userId);
        return shoppingCartsList;
    }

    @Override
    public GoodsLists updateCarts(String productId, String count, String checked) {


        return null;
    }
}
