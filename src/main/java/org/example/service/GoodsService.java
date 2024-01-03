package org.example.service;

import org.example.pojo.Goods;
import org.example.pojo.PageBean;

import java.util.List;

public interface GoodsService {


    List<Goods> findGoodsFloors(Integer num);
    Integer findGoodsSum();

    List<Goods> findHotProducts(String num);

    Goods findGoodsByProductId(String productid);

    PageBean<Goods> list(String productTypeId, String partsId, String pageNum, String pageSize, String name);
}
