package org.example.pojo;

import lombok.Data;

@Data
public class Order {
    private Long orderNo;
    private Integer uid;
    private Integer addrId;
    private Float amount;
    private Integer type;
    private Integer freight;
    private Integer status;
    private String paymentTime;
    private String deliveryTime;
    private String finishTime;
    private String closeTime;
    private String created;
    private String updated;


}
