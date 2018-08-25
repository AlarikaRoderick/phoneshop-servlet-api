package com.es.phoneshop.web.web;

import com.es.phoneshop.web.model.ArrayListProductDAO;
import com.es.phoneshop.web.model.ProductDAO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductDetailsPageServlet extends HttpServlet {

    private ProductDAO productDAO = ArrayListProductDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String uri = request.getRequestURI();
        int index = request.getRequestURI().lastIndexOf("/");
        String idString = uri.substring(index + 1);
        request.setAttribute("product", productDAO.getProduct(Long.valueOf(idString)));
        request.getRequestDispatcher("/WEB-INF/pages/product.jsp").forward(request, response);
    }
}
