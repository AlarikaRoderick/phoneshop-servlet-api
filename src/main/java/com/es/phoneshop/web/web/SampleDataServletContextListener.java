package com.es.phoneshop.web.web;

import com.es.phoneshop.web.model.ArrayListProductDAO;
import com.es.phoneshop.web.model.Product;
import com.es.phoneshop.web.model.ProductDAO;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

import static com.es.phoneshop.web.model.ArrayListProductDAO.createId;

public class SampleDataServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        String insertSampleData = servletContextEvent.getServletContext().getInitParameter("insertSampleData");
        if(!Boolean.valueOf(insertSampleData)) return;

        ProductDAO productDAO = ArrayListProductDAO.getInstance();

        productDAO.save(new Product(createId(), "first code", "first description", new BigDecimal(250), Currency.getInstance(Locale.US), 200));
        productDAO.save(new Product(createId(), "second code", "second description", new BigDecimal(300), Currency.getInstance(Locale.US), 0));
        productDAO.save(new Product(createId(), "third code", "third description", null, Currency.getInstance(Locale.US),300));
        productDAO.save(new Product(createId(), "fourth code", "fourth description", new BigDecimal(130), Currency.getInstance(Locale.US),150));
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
