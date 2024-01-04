package org.example.controller;

import org.example.pojo.Result;
import org.example.pojo.ShoppingCart;
import org.example.service.ShoppingCartService;
import org.example.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cart")
@Validated
public class ShoppingCartController {
    @Autowired
    ShoppingCartService shoppingCartService;

    @PostMapping("/getcartcount.do")
    public Result getCartCount(){
        Map<String,Object> map = ThreadLocalUtil.get();
        if(map==null){
            return Result.error("请登录后，在查看购物车！");
        }
        String userId = (String) map.get("id");

        Integer quantity = shoppingCartService.findCartByUserId(userId);
        return Result.success(quantity);


    }
}
