package com.es.phoneshop.model;

import com.sun.source.tree.AssertTree;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

import static org.junit.Assert.*;

public class CartServiceTest {
    private CartService cartService = CartService.getInstance();

    @Test
    public void getInstance() {
        CartService instance = CartService.getInstance();
        assertNotNull(instance);
    }

    @Test
    public void add() {
        Long id = 1L;
        Cart cart = new Cart();
        ArrayListProductDAO productDAO = ArrayListProductDAO.getInstance();
        productDAO.save(new Product(id, "sixth code", "sixth description", new BigDecimal(200),
                Currency.getInstance(Locale.US),150));
        cartService.add(cart, productDAO.getProduct(id), 2);
        assertFalse(cart.getCartItems().isEmpty());
    }
}