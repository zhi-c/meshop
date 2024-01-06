package org.example.pojo;

import lombok.Data;

@Data
public class GoodsOrder {
    private Integer id;
    private Integer uid;
    private Integer orderId;
    private Integer goodsId;
    private String goodsName;
    private String iconUrl;
    private Float price;
    private Integer quantity;
    private Float totalPrice;
    private String created;
    private String updated;
}
