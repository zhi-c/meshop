package org.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.pojo.ShoppingCart;

import java.util.List;


@Mapper
public interface ShoppingCartMapper {

    @Select("select sum(quantity) from shopping_cart where user_id = #{userId}")
    Integer findCartGoodsQuantityByUserId(Integer userId);

    @Select("select * from shopping_cart where user_id = #{userId}")
    List<ShoppingCart> findCartByUserId(Integer userId);

    @Update("update shopping_cart set quantity = #{count} where user_id = #{userId} and product_id = #{productId}")
    void updateCatts(Integer userId, String productId, String count);
}
