package org.example.controller;

import org.example.pojo.GoodsLists;
import org.example.pojo.Result;

import org.example.service.ShoppingCartService;
import org.example.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
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
    //清空购物车接口
    @GetMapping("/clearcarts.do")
    public Result clearCarts(){
        Map<String,Object> map = ThreadLocalUtil.get();
        if(map == null){
            return Result.error("请登录后，在查看购物车！");
        }
        Integer userId = (Integer) map.get("id");
        shoppingCartService.clearCarts(userId);
        return  Result.success("清空购物车成功");
    }
    //购物车删除商品接口
    @PostMapping("/delcarts.do")
    public Result delCarts(String productId){
        Map<String,Object> map = ThreadLocalUtil.get();
        if(map == null){
            return Result.error("请登录后，在查看购物车！");
        }
        Integer userId = (Integer) map.get("id");
        GoodsLists goodsLists = shoppingCartService.delCarts(Integer.valueOf(productId),userId);
        if(shoppingCartService.getShoppingCarts(productId,userId)==null){
            return Result.success(goodsLists);
        }
        return Result.error("商品删除失败！");
    }
    //购物车商品列表
    @PostMapping("/findallcarts.do")
    public Result findAllCarts(){
        Map<String,Object> map = ThreadLocalUtil.get();
        if(map == null){
            return Result.error("请登录后，在查看购物车！");
        }
        Integer userId = (Integer) map.get("id");
        GoodsLists goodsLists = shoppingCartService.findAllCarts(userId);
        return  Result.success(goodsLists);
    }
    //购物车新增商品接口
    @PostMapping("/savecart.do")
    public Result saveCart(String productId,String count){
        Map<String,Object> map = ThreadLocalUtil.get();
        if(map == null){
            return Result.error("请登录后，在查看购物车！");
        }
        Integer userId = (Integer) map.get("id");
        shoppingCartService.saveCart(userId,productId,count);
        return  Result.success("商品已成功加入购物车！");
    }
}
