package com.es.phoneshop.model;

import com.es.phoneshop.cart.Cart;
import com.es.phoneshop.cart.CartService;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

import static org.junit.Assert.*;

public class CartServiceTest {
    private CartService cartService = CartService.getInstance();
    private Cart cart = new Cart();

    @Test
    public void getInstance() {
        CartService instance = CartService.getInstance();
        assertNotNull(instance);
    }

    @Test
    public void getCart(){
        HttpServletRequest mockRequest = Mockito.mock(HttpServletRequest.class);
        HttpSession mockSession = Mockito.mock(HttpSession.class);

        Mockito.when(mockRequest.getSession()).thenReturn(mockSession);
        Mockito.when(mockSession.getAttribute("cart")).thenReturn(cart);

        assertEquals(cart, cartService.getCart(mockRequest));
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