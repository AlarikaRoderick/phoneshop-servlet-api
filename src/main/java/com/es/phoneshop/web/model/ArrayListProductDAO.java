package com.es.phoneshop.web.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ArrayListProductDAO implements ProductDAO {
    private static List<Product> products;

    private static Long idCount = 1L;
    private static ArrayListProductDAO instance = null;

    public static synchronized Long createId(){
        return idCount++;
    }

    private ArrayListProductDAO() {
        products = new ArrayList<Product>();

        save(new Product(createId(), "first code", "first description", new BigDecimal(250), Currency.getInstance(Locale.US), 200));
        save(new Product(createId(), "second code", "second description", new BigDecimal(300), Currency.getInstance(Locale.US), 0));
        save(new Product(createId(), "third code", "third description", null, Currency.getInstance(Locale.US),300));
        save(new Product(createId(), "fourth code", "fourth description", new BigDecimal(130), Currency.getInstance(Locale.US),150));

    }

    public static ArrayListProductDAO getInstance(){
        if (instance == null){
            synchronized (ArrayListProductDAO.class){
                if (instance == null)
                    instance = new ArrayListProductDAO();
            }
        }
        return instance;
    }

    public Product getProduct(Long id) {
        for (Product product : products){
            if (product.getId().equals(id)) return product;
        }
        return null;
    }

    public List<Product> findProducts() {
        List<Product> productList = products.stream().filter(p->p.getPrice() != null && p.getStock() > 0).collect(Collectors.toList());
        return productList;
    }

    public void save(Product product) {
        products.add(product);
    }

    public void remove(Long id) {
        products.remove(getProduct(id));
    }
}
