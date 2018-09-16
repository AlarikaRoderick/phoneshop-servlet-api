package com.es.phoneshop.cart;

import com.es.phoneshop.model.Product;

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

    public synchronized Cart getCart(HttpServletRequest request){
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

    private synchronized void addOrUpdate(Cart cart, Product product, int quantity, boolean add){
        List<CartItem> cartItems = cart.getCartItems();
        Optional<CartItem> cartItemOptional = cartItems.stream()
                .filter(ci -> ci.getProduct().equals(product))
                .findAny();
        if(cartItemOptional.isPresent()) {
            CartItem cartItem = cartItemOptional.get();
            int newQuantity = add ? cartItem.getQuantity() + quantity : quantity;
            cartItem.setQuantity(newQuantity);
        }
        else{
            cart.getCartItems().add(new CartItem(product, quantity));
        }
    }

    public void add(Cart cart, Product product, int quantity){
        addOrUpdate(cart, product, quantity, true);
    }

    public void update(Cart cart, Product product, int quantity){
        addOrUpdate(cart, product, quantity, false);
    }
}
