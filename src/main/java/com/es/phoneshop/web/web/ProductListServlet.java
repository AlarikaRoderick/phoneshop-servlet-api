package com.es.phoneshop.web.web;

import com.es.phoneshop.web.model.ArrayListProductDAO;
import com.es.phoneshop.web.model.ProductDAO;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ProductListServlet extends HttpServlet {

    private ArrayListProductDAO arrayListProductDAO = ArrayListProductDAO.getInstance();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("products", arrayListProductDAO.findProducts());
        request.getRequestDispatcher("/WEB-INF/pages/productList.jsp").forward(request, response);
    }
}
