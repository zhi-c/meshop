package org.example.controller;

import org.example.pojo.GoodsLists;
import org.example.pojo.Result;

import org.example.service.ShoppingCartService;
import org.example.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Map;

@RestController
@RequestMapping("/cart")
@Validated
public class ShoppingCartController {
    @Autowired
    ShoppingCartService shoppingCartService;


    //获取购物车中商品数量接口
    @GetMapping("/getcartcount.do")
    public Result getCartCount(){
        Map<String,Object> map = ThreadLocalUtil.get();
        if(map == null){
            return Result.error("请登录后，在查看购物车！");
        }
        Integer userId = (Integer) map.get("id");
        Integer quantity = shoppingCartService.findCartGoodsQuantityByUserId(userId);

        return Result.success(quantity);
    }
    //更新购物车中商品数量接口
    @GetMapping("/updatecarts.do")
    public Result updateCarts(String productId,String count,String checked){
        Map<String,Object> map = ThreadLocalUtil.get();
        if(map == null){
            return Result.error("请登录后，在查看购物车！");
        }
        Integer userId = (Integer) map.get("id");
        GoodsLists goodsLists = shoppingCartService.updateCarts(productId,count,checked,userId);
        return Result.success(goodsLists);
    }
}
