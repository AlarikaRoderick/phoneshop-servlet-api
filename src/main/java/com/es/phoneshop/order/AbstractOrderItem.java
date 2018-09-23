package com.es.phoneshop.order;

import com.es.phoneshop.model.Product;

import java.io.Serializable;

public class AbstractOrderItem implements Serializable {
    private Product product;
    private int quantity;

    public AbstractOrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public AbstractOrderItem(){
        this.product = new Product();
        this.quantity = 0;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
