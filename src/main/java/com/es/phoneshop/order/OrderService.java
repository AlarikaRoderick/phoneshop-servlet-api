package com.es.phoneshop.order;

import com.es.phoneshop.cart.Cart;

public interface OrderService {
    Order placeOrder(Cart cart, String name, String address, String phone, Integer orderSum);
    Order getOrder(String orderId);
}
