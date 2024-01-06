package org.example.service;

import org.example.pojo.Order;
import org.example.pojo.OrderDetail;

import java.util.List;

public interface OrderService {
    Order findOrderByOrderNo(Long orderNo);

    void confirmReceipt(Long orderNo);

    OrderDetail createOrder(Integer userId , Integer addr);

    List<Order> getList(Integer userId);
}
