package com.es.phoneshop.web;

import com.es.phoneshop.model.ArrayListProductDAO;
import com.es.phoneshop.model.ProductDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CompareServlet extends HttpServlet {
    private ProductDAO productDAO = ArrayListProductDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Long id = (Long) session.getAttribute("compare");
        request.setAttribute("compare", productDAO.getProduct(id));
        request.getRequestDispatcher("/WEB-INF/pages/comparePage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
