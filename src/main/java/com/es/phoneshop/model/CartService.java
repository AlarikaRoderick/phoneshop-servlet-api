package com.es.phoneshop.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class CartService {

    private static CartService instance = new CartService();
    private static final String CART_NAME = "cart";

    private CartService(){}

    public static CartService getInstance(){
        if(instance == null){
            synchronized (CartService.class){
                if (instance == null)
                    instance = new CartService();
            }
        }
        return instance;
    }

    public Cart getCart(HttpServletRequest request){
        HttpSession session = request.getSession();
        Cart cart = null;
        synchronized (session) {
            cart = (Cart) session.getAttribute(CART_NAME);
            if (cart == null) {
                cart = new Cart();
                for (Product product : ArrayListProductDAO.getInstance().findProducts()) {
                    add(cart, product, 1);
                }
                session.setAttribute(CART_NAME, cart);
            }
        }
        return cart;
    }

    public synchronized void add(Cart cart, Product product, int quantity){
        List<CartItem> cartItems = cart.getCartItems();

        Optional<CartItem> cartItemOptional = cartItems.stream()
                .filter(ci -> ci.getProduct().equals(product))
                .findAny();
        if(cartItemOptional.isPresent()) {
            CartItem cartItem = cartItemOptional.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            product.setStock(product.getStock() - quantity);
        }
        else{
            cart.getCartItems().add(new CartItem(product, quantity));
            product.setStock(product.getStock() - quantity);
        }
    }
}
