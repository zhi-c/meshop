package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import org.example.pojo.GoodsCategory;
import org.example.pojo.Param;

import java.util.List;

@Mapper
public interface ParamMapper {


    @Select("select * from goods_category")
    List<Param> findAll();
    @Select("select * from goods_category where parent_id = #{parentId}")
    List<Param> findByid(String parentId);
}
