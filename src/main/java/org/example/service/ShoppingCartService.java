package org.example.service;

import org.example.pojo.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    Integer findCartByUserId(String userId);
}
