package com.es.phoneshop.web;

import com.es.phoneshop.cart.Cart;
import com.es.phoneshop.exception.NotEnoughProductsException;
import com.es.phoneshop.exception.QuantityUnderZeroException;
import com.es.phoneshop.model.ArrayListProductDAO;
import com.es.phoneshop.cart.CartService;
import com.es.phoneshop.model.Product;
import com.es.phoneshop.model.ProductDAO;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Locale;

public class CartPageServlet extends HttpServlet {
    private CartService cartService;
    private ProductDAO productDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        cartService = CartService.getInstance();
        productDAO = ArrayListProductDAO.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("cart", cartService.getCart(request));
        request.getRequestDispatcher("/WEB-INF/pages/cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] productIds = request.getParameterValues("productId");
        String[] quantities = request.getParameterValues("quantity");
        String[] errors = new String[productIds.length];
        String delValue = request.getParameter("delete");
        boolean hasErrors = false;
        if (delValue != null) {
            deleteProcess(request, delValue);
            request.setAttribute("successDelete", true);
        }else {
            hasErrors = isHasErrorsUpdateProcess(request, productIds, quantities, errors, hasErrors);
        }
        if(hasErrors) {
            request.setAttribute("errors", errors);
            request.setAttribute("quantities", quantities);
            doGet(request, response);
        }
        else {
            response.sendRedirect( "cart?successUpdate=true");
        }
    }

    private boolean isHasErrorsUpdateProcess(HttpServletRequest request, String[] productIds, String[] quantities, String[] errors, boolean hasErrors) {
        Product product;
        for (int i = 0; i < productIds.length; i++) {
            product = productDAO.getProduct(Long.valueOf(productIds[i]));
            Locale locale = request.getLocale();
            try {
                int quantity = DecimalFormat.getInstance(locale).parse(quantities[i]).intValue();
                if (quantity < 0) {
                    errors[i] = "Quantity under zero";
                    hasErrors = true;
                }
                if (quantity > product.getStock()){
                    errors[i] = "Not enough stock for this product";
                    hasErrors = true;
                }
                cartService.update(cartService.getCart(request), product, quantity);
                request.setAttribute("successUpdate", true);
            } catch (ParseException e) {
                errors[i] = "not a number";
                hasErrors = true;
            }
        }
        return hasErrors;
    }

    private void deleteProcess(HttpServletRequest request, String delValue) {
        Product product;
        int deletedProductId = Integer.valueOf(delValue);
        product = cartService.getCart(request).getCartItems().get(deletedProductId).getProduct();
        cartService.delete(cartService.getCart(request), product, deletedProductId);
    }
}
