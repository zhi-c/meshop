package org.example.service.impl;

import org.example.mapper.GoodsMapper;
import org.example.mapper.OrderMapper;
import org.example.pojo.*;
import org.example.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private GoodsOrderService goodsOrderService;

    @Override
    public Order findOrderByOrderNo(Long orderNo) {
        Order order = orderMapper.findOrderByOrderNo(orderNo);
        return order;
    }

    @Override
    public void confirmReceipt(Long orderNo) {
        orderMapper.confirmReceipt(orderNo);
    }

    @Override
    public OrderDetail createOrder(Integer userId,Integer addrId) {
        OrderDetail orderDetail = new OrderDetail();
        Address address = addressService.findAddressById(addrId);
        Float totalPrice = (float) 0;
        List<ShoppingCart> shoppingCartsList = shoppingCartService.findCartByUserId(userId);
        List<GoodsInOrder> goodsInOrderList = new ArrayList<>();
        orderMapper.createOrder(userId,addrId);
        Integer orderNo = orderMapper.findOrderOrderNo();
        for(int i=0;i<shoppingCartsList.size();i++){
            Goods goods = goodsService.findGoodsByProductId(String.valueOf(shoppingCartsList.get(i).getProductId()));
            ShoppingCart shoppingCart = shoppingCartsList.get(i);
            GoodsInOrder goodsInOrder = new GoodsInOrder();
            goodsInOrder.setOrderNo(Long.valueOf(orderNo));
            goodsInOrder.setGoodsId(goods.getId());
            goodsInOrder.setGoodsName(goods.getName());
            goodsInOrder.setIconUrl(goods.getIconUrl());
            goodsInOrder.setCurPrice(goods.getPrice());
            goodsInOrder.setQuantity(shoppingCart.getQuantity());
            goodsInOrder.setTotalPrice(shoppingCart.getQuantity()*goods.getPrice());
            goodsInOrder.setCreated(shoppingCart.getCreated());
            System.out.println(goodsInOrder);
            goodsInOrderList.add(goodsInOrder);
            totalPrice+=shoppingCart.getQuantity()*goods.getPrice();

            GoodsOrder goodsOrder = new GoodsOrder();
            goodsOrder.setUid(userId);
            goodsOrder.setOrderId(orderNo);
            goodsOrder.setGoodsId(goods.getId());
            goodsOrder.setGoodsName(goods.getName());
            goodsOrder.setIconUrl(goods.getIconUrl());
            goodsOrder.setPrice(goods.getPrice());
            goodsOrder.setQuantity(shoppingCart.getQuantity());
            goodsOrder.setTotalPrice(shoppingCart.getQuantity()*goods.getPrice());
            goodsOrderService.createGoodsOrder(goodsOrder);

        }
        AddressInOrder addressInOrder = new AddressInOrder();
        addressInOrder.setName(address.getName());
        addressInOrder.setPhone(address.getPhone());
        addressInOrder.setMobile(address.getMobile());
        addressInOrder.setProvince(address.getProvince());
        addressInOrder.setCity(address.getCity());
        addressInOrder.setDistrict(address.getDistrict());
        addressInOrder.setAddr(address.getAddr());
        addressInOrder.setZip(address.getZip());


        orderDetail.setOrderNo(orderNo);
        orderDetail.setAmount(totalPrice);
        orderDetail.setType(1);
        orderDetail.setTypeDesc("在线支付");
        orderDetail.setFreight(0);
        orderDetail.setStatus(1);
        orderDetail.setStatusDesc("未付款");
        orderDetail.setPaymentTime("");
        orderDetail.setDeliveryTime("");
        orderDetail.setFinishTime("");
        orderDetail.setCloseTime("");
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy--MMdd HH:mm:ss");
        orderDetail.setCreatedTime(formatter.format(calendar.getTime()));
        orderDetail.setOrderItems(goodsInOrderList);
        orderDetail.setAddrId(addrId);
        orderDetail.setAddress(addressInOrder);

        orderMapper.updateOrder(totalPrice,orderNo);


        return orderDetail;
    }

    @Override
    public List<Order> getList(Integer userId) {

        List<Order> orderList = orderMapper.getList(userId);
        return orderList;
    }
}
