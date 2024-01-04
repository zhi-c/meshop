package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;



@Mapper
public interface ShoppingCartMapper {

    @Select("select sum(quantity) from shopping_cart where user_id = #{userId}")
    Integer findCartByUserId(Integer userId);
}
