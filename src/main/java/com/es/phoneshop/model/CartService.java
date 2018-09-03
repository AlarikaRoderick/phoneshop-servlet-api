package com.es.phoneshop.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CartService {

    private static CartService instance = new CartService();
    private static final String CART_NAME = "cart";

    private CartService(){}

    public static CartService getInstance(){
        return instance;
    }

    public Cart getCart(HttpServletRequest request){
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute(CART_NAME);
        if(cart == null){
            cart = new Cart();
            for (Product product : ArrayListProductDAO.getInstance().findProducts()){
                add(cart, product, 1);
            }
            session.setAttribute(CART_NAME, cart);
        }
        return cart;
    }

    public void add(Cart cart, Product product, int quantity){
        cart.getCartItems().add(new CartItem(product, quantity));
    }
}
