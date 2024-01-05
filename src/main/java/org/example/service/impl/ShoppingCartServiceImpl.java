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

import java.util.ArrayList;
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
        GoodsLists goodsLists = new GoodsLists();
        List<ShoppingCart> shoppingCartsList = shoppingCartMapper.findCartByUserId(userId);
        List<GoodsInCart> goodsInCartsList = new ArrayList<>();
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
        }
        goodsLists.setLists(goodsInCartsList);
        goodsLists.setTotalPrice(totalPrice);
        return goodsLists;
    }

    @Override
    public void clearCarts(Integer userId) {
        shoppingCartMapper.clearCarts(userId);
    }

    @Override
    public GoodsLists delCarts(Integer productId,Integer userId) {
        shoppingCartMapper.delCarts(productId,userId);
        GoodsLists goodsLists = new GoodsLists();
        List<ShoppingCart> shoppingCartsList = shoppingCartMapper.findCartByUserId(userId);
        List<GoodsInCart> goodsInCartsList = new ArrayList<>();
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
            goodsInCartsList.add(goodsInCart);
        }
        goodsLists.setLists(goodsInCartsList);
        goodsLists.setTotalPrice(totalPrice);
        return goodsLists;
    }

    @Override
    public ShoppingCart getShoppingCarts(String productId, Integer userId) {
        ShoppingCart shoppingCart= shoppingCartMapper.getShoppingCarts(productId,userId);
        return shoppingCart;
    }

    @Override
    public GoodsLists findAllCarts(Integer userId) {
        GoodsLists goodsLists = new GoodsLists();
        List<ShoppingCart> shoppingCartsList = shoppingCartMapper.findCartByUserId(userId);
        List<GoodsInCart> goodsInCartsList = new ArrayList<>();
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
            goodsInCartsList.add(goodsInCart);
        }
        goodsLists.setLists(goodsInCartsList);
        goodsLists.setTotalPrice(totalPrice);
        return goodsLists;
    }

    @Override
    public void saveCart(Integer userId, String productId, String count) {
        shoppingCartMapper.saveCart(userId,productId,count);
    }

    @Override
    public List<ShoppingCart> findCartByUserId(Integer userId) {
        return shoppingCartMapper.findCartByUserId(userId);
    }
}
