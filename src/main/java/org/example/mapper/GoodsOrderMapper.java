package org.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.GoodsOrder;

@Mapper
public interface GoodsOrderMapper {

    @Insert("insert into goods_order(uid, order_id, goods_id, goods_name, icon_url, price, quantity, total_price, created, updated) " +
            "values (#{uid}, #{orderId}, #{goodsId}, #{goodsName}, #{iconUrl}, #{price}, #{quantity}, #{totalPrice}, now(), now())")
    void createGoodsOrder(GoodsOrder goodsOrder);



}
