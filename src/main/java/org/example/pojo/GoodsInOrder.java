package org.example.pojo;

import lombok.Data;

@Data
public class GoodsInOrder {
    private Long orderNo;
    private Integer goodsId;
    private String goodsName;
    private String iconUrl;
    private Float curPrice;
    private  Integer quantity;
    private Float totalPrice;
    private String created;
}
