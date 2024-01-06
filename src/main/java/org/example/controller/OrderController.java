package org.example.controller;

import org.example.pojo.Order;
import org.example.pojo.OrderDetail;
import org.example.pojo.Result;
import org.example.service.OrderService;
import org.example.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
@Validated
public class OrderController {
    @Autowired
    OrderService orderService;

    //确认收货
    @PostMapping("/confirmreceipt.do")
    public Result confirmReceipt(String orderNo){
        Order order = orderService.findOrderByOrderNo(Long.parseLong(orderNo));
        if(order==null||order.getStatus()==4){
            Result.error("失败");
        }
        orderService.confirmReceipt(Long.parseLong(orderNo));
        return Result.success("订单已确认收货！");
    }
    //订单详情接口
    @GetMapping("/getdetail.do")
    public Result getDetail(String orderNo){
        return Result.error("未完成");
    }
    //订单创建
    @PostMapping("/createorder.do")
    public Result createOrder(String addrId){
        Map<String,Object> map = ThreadLocalUtil.get();
        if(map == null){
            return Result.error("请登录后，在查看购物车！");
        }
        Integer userId = (Integer) map.get("id");
        OrderDetail orderDetail = orderService.createOrder(userId,Integer.valueOf(addrId));
        return Result.success(orderDetail);

    }

    //获取用户订单列表
    @GetMapping("/getlist.do")
    public Result getList(){
        Map<String,Object> map = ThreadLocalUtil.get();
        if(map == null){
            return Result.error("请登录后，在查看购物车！");
        }
        Integer userId = (Integer) map.get("id");
        List<Order> orderList = orderService.getList(userId);
        return Result.success(orderList);

    }

}
