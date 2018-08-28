package com.es.phoneshop.web.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
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
        Long id = ArrayListProductDAO.createId();
        assertNotNull(id);
    }

    @Test
    public void getInstance() {
        ArrayListProductDAO instance = ArrayListProductDAO.getInstance();
        assertNotNull(instance);
    }

    @Test
    public void getProduct() {
        Long id = 2L;
        productDAO.save(new Product(id, "second code", "second description",
                new BigDecimal(100), Currency.getInstance(Locale.US), 100));
        Product product = productDAO.getProduct(id);
        assertEquals(id, product.getId());
    }

    @Test
    public void findProducts() {
        Long id = 4L;
        productDAO.save(new Product(id, "fourth code", "fourth description",
                new BigDecimal(499), Currency.getInstance(Locale.US), 10));
        List<Product> productList = productDAO.findProducts();
        assertFalse(productList.isEmpty());
    }

    @Test
    public void save() {
        Long id = 3L;
        productDAO.save(new Product(id, "third code", "third description",
                new BigDecimal(100), Currency.getInstance(Locale.US), 300));
        Product product = productDAO.getProduct(id);
        assertNotNull(product);
    }

    @Test
    public void remove() {
        Long id = 1L;
        productDAO.remove(id);
        Product product = productDAO.getProduct(id);
        assertNull(product);
    }
}