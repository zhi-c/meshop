package org.example.pojo;

import lombok.Data;

import java.util.List;

@Data
public class GoodsLists {
    private List<GoodsInCart> lists;
    private Float totalPrice;
}
