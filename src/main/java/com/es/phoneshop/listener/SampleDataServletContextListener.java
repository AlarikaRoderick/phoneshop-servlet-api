package com.es.phoneshop.listener;

import com.es.phoneshop.model.ArrayListProductDAO;
import com.es.phoneshop.model.Product;
import com.es.phoneshop.model.ProductDAO;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

public class SampleDataServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        String insertSampleData = servletContextEvent.getServletContext().getInitParameter("insertSampleData");
        if(!Boolean.valueOf(insertSampleData)) return;

        ProductDAO productDAO = ArrayListProductDAO.getInstance();

        productDAO.save(new Product(ArrayListProductDAO.createId(), "first code", "first description", new BigDecimal(250), Currency.getInstance(Locale.US), 200));
        productDAO.save(new Product(ArrayListProductDAO.createId(), "second code", "second description", new BigDecimal(300), Currency.getInstance(Locale.US), 0));
        productDAO.save(new Product(ArrayListProductDAO.createId(), "third code", "third description", null, Currency.getInstance(Locale.US),300));
        productDAO.save(new Product(ArrayListProductDAO.createId(), "fourth code", "fourth description", new BigDecimal(130), Currency.getInstance(Locale.US),150));
        productDAO.save(new Product(ArrayListProductDAO.createId(), "fifth code", "fifth description", new BigDecimal(340), Currency.getInstance(Locale.US),95));
        productDAO.save(new Product(ArrayListProductDAO.createId(), "sixth code", "sixth description", new BigDecimal(200), Currency.getInstance(Locale.US),150));
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
