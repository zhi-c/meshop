package org.example.pojo;

import lombok.Data;

import java.util.List;

@Data
public class OrderDetail {
    private  Integer orderNo;
    private  Float   amount;
    private  Integer type;
    private String typeDesc;
    private Integer freight;
    private Integer status;
    private String statusDesc;
    private String paymentTime;
    private String deliveryTime;
    private String finishTime;
    private String closeTime;
    private String createdTime;
    private List<GoodsInOrder> orderItems;
    private Integer addrId;
    private String deliveryName;
    private AddressInOrder address;
}
