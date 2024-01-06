package org.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.pojo.Order;

import java.util.List;

@Mapper
public interface OrderMapper {
    @Select("select * from `order` where order_no = #{orderNo}")
    Order findOrderByOrderNo(Long orderNo);

    @Update("update `order` set status = 4 ,finish_time = now(),updated = now() where order_no = #{orderNo}")
    void confirmReceipt(Long orderNo);

    @Insert("insert into `order`(uid,addr_id,type,freight,status,created,updated) values (#{userId},#{addrId},1,0,1,now(),now())")
    void createOrder(Integer userId, Integer addrId);

    @Select("SELECT order_no FROM `order` ORDER BY order_no DESC LIMIT 1")
    Integer findOrderOrderNo();

    @Update("update `order` set amount = #{totalPrice} where order_no = #{orderNo}")
    void updateOrder(Float totalPrice,Integer orderNo);

    @Select("select * from `order` where uid = #{userId}")
    List<Order> getList(Integer userId);
}
