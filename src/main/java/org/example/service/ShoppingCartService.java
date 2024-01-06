package org.example.service;

import org.example.pojo.GoodsLists;
import org.example.pojo.ShoppingCart;

import java.util.List;


public interface ShoppingCartService {
    Integer findCartGoodsQuantityByUserId(Integer userId);

    GoodsLists updateCarts(String productId, String count, String checked,Integer userId);

    void clearCarts(Integer userId);

    GoodsLists delCarts(Integer productId,Integer userId);

    ShoppingCart getShoppingCarts(String productId, Integer userId);

    GoodsLists findAllCarts(Integer userId);

    void saveCart(Integer userId, String productId, String count);

    List<ShoppingCart> findCartByUserId(Integer userId);



}
