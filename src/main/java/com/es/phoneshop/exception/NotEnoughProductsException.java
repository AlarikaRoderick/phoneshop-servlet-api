package com.es.phoneshop.exception;

public class NotEnoughProductsException extends RuntimeException {
    public static final String NOT_ENOUGH_PRODUCTS_MESSAGE = "Not enough stock for this product";

    public NotEnoughProductsException(String string){
        super(string);
    }
}
