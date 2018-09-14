package com.es.phoneshop.web;

import com.es.phoneshop.model.ArrayListProductDAO;
import com.es.phoneshop.model.CartService;
import com.es.phoneshop.model.Product;
import com.es.phoneshop.model.ProductDAO;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

        for(int i=0; i<productIds.length; i++){
            Product product = productDAO.getProduct(Long.valueOf(productIds[i]));
            cartService.update(cartService.getCart(request), product, Integer.valueOf(quantities[i]));
        }

        response.sendRedirect("cart");
    }
}
