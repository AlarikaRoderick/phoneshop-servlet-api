package com.es.phoneshop.web;

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer quantity = null;
        Long productId = Long.valueOf(req.getPathInfo().substring(1));
        Product product = productDAO.getProduct(productId);

        try{
             quantity = Integer.valueOf(req.getParameter("quantity"));
        }catch (NumberFormatException e){
            req.setAttribute("error", "not a number");
            show(product, req, resp);
            return;
        }


        Cart cart = cartService.getCart(req);
        cartService.add(cart, product, quantity);

        show(product, req, resp);

    }

    private void show(Product product, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setAttribute("product", product);
        request.getRequestDispatcher("/WEB-INF/pages/product.jsp").forward(request, response);
    }
}
