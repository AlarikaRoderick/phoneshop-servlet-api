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
                session.setAttribute(CART_NAME, cart);
            }
        }
        return cart;
    }

    private void addOrUpdate(Cart cart, Product product, int quantity, boolean add){
        List<CartItem> cartItems = cart.getCartItems();

        Optional<CartItem> cartItemOptional = cartItems.stream()
                .filter(ci -> ci.getProduct().equals(product))
                .findAny();

        if(cartItemOptional.isPresent()) {
            CartItem cartItem = cartItemOptional.get();
            int newQuantity = add ? cartItem.getQuantity() + quantity : quantity;
            cartItem.setQuantity(newQuantity);
            if(add) {
                product.setStock(product.getStock() - quantity);
            }
            else{
                int firstStock = product.getFirstStock();
                product.setStock(firstStock - quantity);
            }
        }
        else{
            cart.getCartItems().add(new CartItem(product, quantity));
            product.setStock(product.getStock() - quantity);
        }
    }

    public void add(Cart cart, Product product, int quantity){
        addOrUpdate(cart, product, quantity, true);
    }

    public void update(Cart cart, Product product, int quantity){
        addOrUpdate(cart, product, quantity, false);
    }
}
