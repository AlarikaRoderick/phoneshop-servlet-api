package com.es.phoneshop.web;

import com.es.phoneshop.model.ArrayListProductDAO;
import com.es.phoneshop.model.ProductDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductDetailsPageServlet extends HttpServlet {

    private ProductDAO productDAO = ArrayListProductDAO.getInstance();

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
}
