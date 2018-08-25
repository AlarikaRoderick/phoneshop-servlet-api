package com.es.phoneshop.web.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

import static com.es.phoneshop.web.model.ArrayListProductDAO.createId;
import static org.junit.Assert.*;

public class ArrayListProductDAOTest {

    private ProductDAO productDAO;

    @Before
    public void setUp() throws Exception {
        productDAO = ArrayListProductDAO.getInstance();

        productDAO.save(new Product(ArrayListProductDAO.createId(), "first code",
                "first description", new BigDecimal(250), Currency.getInstance(Locale.US), 200));

    }

    @Test
    public void createId() {
        assertNotNull(ArrayListProductDAO.createId());
    }

    @Test
    public void getInstance() {
        assertNotNull(ArrayListProductDAO.getInstance());
    }

    @Test
    public void getProduct() {
        assertEquals(1, productDAO.getProduct(1L).getId().longValue());
    }

    @Test
    public void findProducts() {
        assertTrue(!productDAO.findProducts().isEmpty());
    }

    @Test
    public void save() {
        productDAO.save(new Product(ArrayListProductDAO.createId(), "second code",
                "second description", new BigDecimal(100), Currency.getInstance(Locale.US), 300));
        assertNotNull(productDAO.getProduct(2L));
    }

    @Test
    public void remove() {
        productDAO.remove(1L);
        assertNull(productDAO.getProduct(1L));
    }
}