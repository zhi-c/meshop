package org.example.service.impl;

import org.example.mapper.GoodsMapper;
import org.example.mapper.ShoppingCartMapper;
import org.example.pojo.Goods;
import org.example.pojo.GoodsInCart;
import org.example.pojo.GoodsLists;
import org.example.pojo.ShoppingCart;
import org.example.service.ShoppingCartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private GoodsMapper goodsMapper;
    @Override
    public Integer findCartGoodsQuantityByUserId(Integer userId) {
        Integer shoppingCartsList = shoppingCartMapper.findCartGoodsQuantityByUserId(userId);
        return shoppingCartsList;
    }

    @Override
    public GoodsLists updateCarts(String productId, String count, String checked, Integer userId) {
        GoodsLists goodsLists=null;
        List<ShoppingCart> shoppingCartsList = shoppingCartMapper.findCartByUserId(userId);
        List<GoodsInCart> goodsInCartsList = null;
        Float totalPrice = 0.0F;
        for(int i=0;i<shoppingCartsList.size();i++){
            Goods goods=goodsMapper.findGoodsByProductId(shoppingCartsList.get(i).getProductId());
            GoodsInCart goodsInCart = new GoodsInCart();
            goodsInCart.setId(shoppingCartsList.get(i).getId());
            goodsInCart.setUserId(shoppingCartsList.get(i).getUserId());
            goodsInCart.setProductId(shoppingCartsList.get(i).getProductId());
            goodsInCart.setName(goods.getName());
            goodsInCart.setQuantity(shoppingCartsList.get(i).getQuantity());
            goodsInCart.setPrice(goods.getPrice());
            goodsInCart.setStatus(goods.getStatus());
            goodsInCart.setTotalPrice(goods.getPrice()*shoppingCartsList.get(i).getQuantity());
            totalPrice+=goods.getPrice()*shoppingCartsList.get(i).getQuantity();
            goodsInCart.setStock(goods.getStock());
            goodsInCart.setIconUrl(goods.getIconUrl());
            if(Integer.parseInt(productId) == shoppingCartsList.get(i).getProductId()){
                goodsInCart.setQuantity(Integer.valueOf(count));
                goodsInCart.setChecked(Integer.valueOf(checked));
                shoppingCartMapper.updateCatts(userId,productId,count);
            }
            goodsInCartsList.add(goodsInCart);
            goodsLists.setLists(goodsInCartsList);
            goodsLists.setTotalPrice(totalPrice);
        }

        return goodsLists;
    }
}
