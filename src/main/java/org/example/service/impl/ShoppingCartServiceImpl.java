package org.example.service.impl;

import org.example.mapper.ShoppingCartMapper;
import org.example.pojo.ShoppingCart;
import org.example.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private ShoppingCartMapper shoppingCartMapper;
    @Override
    public Integer findCartByUserId(String userId) {
        Integer quantity = shoppingCartMapper.findCartByUserId(userId);
        return quantity;
    }
}
