package com.es.phoneshop.order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractOrder<T extends AbstractOrderItem> {
    private List<T> cartItems = new ArrayList<>();

    public List<T> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<T> cartItems) {
        this.cartItems = cartItems;
    }
}
