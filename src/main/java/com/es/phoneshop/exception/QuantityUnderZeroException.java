package com.es.phoneshop.exception;

public class QuantityUnderZeroException extends RuntimeException {
    public static final String QUANTITY_UNDER_ZERO_MESSAGE = "Quantity under zero";

    public QuantityUnderZeroException(String string){
        super(string);
    }
}
