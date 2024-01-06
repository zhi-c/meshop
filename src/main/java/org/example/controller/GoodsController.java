package org.example.controller;


import org.example.pojo.Goods;
import org.example.pojo.PageBean;
import org.example.pojo.Result;
import org.example.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
@Validated
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    //首页楼层商品数据接口
    @PostMapping("/findfloors.do")
    public Result findFloors(){
        Integer goodsSum = goodsService.findGoodsSum();
        Map<String,List<Goods>> map = new HashMap<>();
        List<Goods> list = null;
        if(goodsSum==0){
            return Result.error("楼商品数据尚未准备完整");
        }
        Integer floorsSum = goodsSum / 8 + (goodsSum % 8 == 0 ? 0 : 1) ;
        System.out.println(floorsSum);
        if(floorsSum>4){
            floorsSum = 4;
        }
        for(int i=1;i<=floorsSum;i++){
            list = goodsService.findGoodsFloors((i-1)*8);
            switch (i){
                case 1 :
                    map.put("oneFloor",list);
                    break;
                case 2:
                    map.put("twoFloor",list);
                    break;
                case 3:
                    map.put("threeFloor",list);
                    break;
                case 4:
                    map.put("fourFloor",list);
                    break;
            }
        }

        return  Result.success(map);
    }
    //热销产品接口
    @PostMapping("findhotproducts.do")
    public Result finHotProducts(String num){
        List<Goods> hotGoods = goodsService.findHotProducts(num);
        if(!(hotGoods==null)){
            return Result.success(hotGoods);
        }
        return Result.error("尚未设置热销商品！");
    }

    //商品详情数据接口
    @PostMapping("/getdetail.do")
    public Result getDetail(String productId){
        Goods goods = goodsService.findGoodsByProductId(productId);
        if((goods==null)||goods.getStatus()==3){
            return Result.error("产品已经下架！");
        }
        return  Result.success(goods);

    }

    //商品分页列表接口
    @PostMapping("findproducts.do")
    public Result<PageBean<Goods>> finProducts(
            @RequestParam(required = false) String productTypeId, @RequestParam(required = false) String partsId,
            String pageNum, String pageSize, @RequestParam(required = false) String name){

        PageBean<Goods> goodsPageBean = goodsService.list(productTypeId, partsId, pageNum, pageSize, name);
        return Result.success(goodsPageBean);
    }
}
