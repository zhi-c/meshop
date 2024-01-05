package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.ShoppingCart;

import java.util.List;


@Mapper
public interface ShoppingCartMapper {

    @Select("select sum(quantity) from shopping_cart where user_id = #{userId}")
    Integer findCartGoodsQuantityByUserId(Integer userId);

    @Select("select * from shopping_cart where user_id = #{userId}")
    List<ShoppingCart> findCartByUserId(Integer userId);

    @Update("update shopping_cart set quantity = #{count},updated = now() where user_id = #{userId} and product_id = #{productId}")
    void updateCatts(Integer userId, String productId, String count);
    @Delete("delete from shopping_cart where user_id = #{userId}")
    void clearCarts(Integer userId);
    @Delete("delete from shopping_cart where user_id = #{userId} and product_id=#{productId}")
    void delCarts(Integer productId, Integer userId);
    @Select("select * from shopping_cart where product_id = #{productId} and user_id = #{userId}")
    ShoppingCart getShoppingCarts(String productId, Integer userId);

    @Insert("insert into shopping_cart(user_id,product_id,quantity,created,updated)" +
            "values (#{userId},#{productId},#{count},now(),now())")
    void saveCart(Integer userId, String productId, String count);
}
