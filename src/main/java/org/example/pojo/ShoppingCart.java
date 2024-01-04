package org.example.pojo;

import lombok.Data;

@Data
public class ShoppingCart {
    private Integer id;
    private Integer userId;
    private Integer productId;
    private Integer quantity;
    private String created;
    private String updated;
}
