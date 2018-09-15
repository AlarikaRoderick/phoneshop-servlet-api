package com.es.phoneshop.web;

import com.es.phoneshop.exception.NotEnoughProductsException;
import com.es.phoneshop.exception.QuantityUnderZeroException;
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
import java.lang.reflect.Parameter;
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
        for(int i=0; i<productIds.length; i++){
            Product product = productDAO.getProduct(Long.valueOf(productIds[i]));
            Locale locale = request.getLocale();
            try{
                int quantity = DecimalFormat.getInstance(locale).parse(quantities[i]).intValue();
                if (quantity < 0)
                    throw new QuantityUnderZeroException(QuantityUnderZeroException.QUANTITY_UNDER_ZERO_MESSAGE);
                if (quantity > product.getStock())
                    throw new NotEnoughProductsException(NotEnoughProductsException.NOT_ENOUGH_PRODUCTS_MESSAGE);
                cartService.update(cartService.getCart(request), product, quantity);
                response.sendRedirect("cart");
                return;
            }catch (ParseException e){
                errors[i] = "not a number";
            }catch (QuantityUnderZeroException e){
                errors[i] = QuantityUnderZeroException.QUANTITY_UNDER_ZERO_MESSAGE;
            }catch (NotEnoughProductsException e){
                errors[i] = NotEnoughProductsException.NOT_ENOUGH_PRODUCTS_MESSAGE;
            }
        }
        request.setAttribute("errors", errors);
        doGet(request, response);
    }
}
