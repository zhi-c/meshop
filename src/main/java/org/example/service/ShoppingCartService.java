package org.example.service;

import org.example.pojo.GoodsLists;


public interface ShoppingCartService {
    Integer findCartGoodsQuantityByUserId(Integer userId);

    GoodsLists updateCarts(String productId, String count, String checked,Integer userId);
}
