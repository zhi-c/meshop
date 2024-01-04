package org.example.service;

import org.example.pojo.GoodsLists;
import org.example.pojo.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    Integer findCartByUserId(Integer userId);

    GoodsLists updateCarts(String productId, String count, String checked);
}
