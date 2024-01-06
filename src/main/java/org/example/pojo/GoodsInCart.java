package org.example.pojo;

import lombok.Data;

@Data
public class GoodsInCart {
    private Integer id;
    private Integer userId;
    private Integer productId;
    private String name;
    private Integer quantity;
    private Float price;
    private Integer status;
    private Float totalPrice;
    private Integer stock;
    private String iconUrl;
    private Integer checked;
}
