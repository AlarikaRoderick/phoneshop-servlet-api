package com.es.phoneshop.order;

import com.es.phoneshop.cart.Cart;

import java.util.*;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService{
    private Map<String, Order> orders = Collections.synchronizedMap(new HashMap<>());

    private static class OrderServiceImplHelp {
        private static final OrderService INSTANCE = new OrderServiceImpl();
    }

    public static OrderService getInstance(){return OrderServiceImpl.OrderServiceImplHelp.INSTANCE;}

    @Override
    public Order placeOrder(Cart cart, String name, String address, String phone, Integer orderSum) {
        Order order = new Order();
        String id = generateId();
        order.setOrderId(id);
        order.setName(name);
        order.setAddress(address);
        order.setPhone(phone);
        order.setOrderSum(orderSum);
        order.setCartItems(cart.getCartItems().stream().map(cartItem -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            return orderItem;
        }).collect(Collectors.toList()));
        orders.put(id, order);
        return order;
    }

    private synchronized String generateId() {
        String id = java.util.UUID.randomUUID().toString();
        return (orders.get(id) == null ? id : generateId());
    }

    @Override
    public Order getOrder(String orderId) {
        return orders.get(orderId);
    }
}
