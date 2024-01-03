package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.Goods;

import java.util.List;

@Mapper
public interface GoodsMapper {
    @Select("select * from goods limit #{num}, 8")
    List<Goods> findGoodsFloors(Integer num);
    @Select("select count(*) from goods")
    Integer findGoodsSum();
    @Select("select * from goods where is_hot = 1 limit #{num}")
    List<Goods> findHotProducts(Integer num);
    @Select("select * from goods where product_id = #{productid}")
    Goods findGoodsByProductId(Integer productid);

    List<Goods> list(String productTypeId, String partsId, String name);
}
