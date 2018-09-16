package com.es.phoneshop.web;

import com.es.phoneshop.cart.Cart;
import com.es.phoneshop.cart.CartService;
import com.es.phoneshop.exception.NotEnoughProductsException;
import com.es.phoneshop.exception.QuantityUnderZeroException;
import com.es.phoneshop.model.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductDetailsPageServlet extends HttpServlet {

    private ProductDAO productDAO = ArrayListProductDAO.getInstance();
    private CartService cartService = CartService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idString = request.getPathInfo();
        try {
            request.setAttribute("product", productDAO.getProduct(Long.valueOf(idString.substring(1))));
            request.getRequestDispatcher("/WEB-INF/pages/product.jsp").forward(request, response);
        }catch(NumberFormatException e){
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer quantity;
        Long productId = Long.valueOf(request.getPathInfo().substring(1));
        Product product = productDAO.getProduct(productId);
        try{
             quantity = Integer.valueOf(request.getParameter("quantity"));
             if (quantity < 0)
                 throw new QuantityUnderZeroException(QuantityUnderZeroException.QUANTITY_UNDER_ZERO_MESSAGE);
             if (quantity > product.getStock())
                 throw new NotEnoughProductsException(NotEnoughProductsException.NOT_ENOUGH_PRODUCTS_MESSAGE);
        }catch (NumberFormatException e){
            setError("not a number", product, request, response);
            return;
        }catch (QuantityUnderZeroException e){
            setError(QuantityUnderZeroException.QUANTITY_UNDER_ZERO_MESSAGE, product, request, response);
            return;
        }catch (NotEnoughProductsException e){
            setError(NotEnoughProductsException.NOT_ENOUGH_PRODUCTS_MESSAGE, product, request, response);
            return;
        }
        Cart cart = cartService.getCart(request);
        cartService.add(cart, product, quantity);
        request.setAttribute("addedQuantity", quantity);
        showProductListPage(product, request, response);

    }

    private void showProductListPage(Product product, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setAttribute("product", product);
        request.getRequestDispatcher("/WEB-INF/pages/product.jsp").forward(request, response);
    }

    private void setError(String message, Product product, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setAttribute("error", Boolean.TRUE);
        request.setAttribute("errorMessage", message);
        showProductListPage(product, request, response);
    }
}
