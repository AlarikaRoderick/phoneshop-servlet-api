package com.es.phoneshop.web.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayListProductDAO implements ProductDAO {
    private static List<Product> products;

    public ArrayListProductDAO() {
        products = getInstance();

        Product product = new Product();
        product.setId();
        product.setCode("first code");
        product.setDescription("first description");
        product.setPrice(new BigDecimal(250));
        product.setStock(200);
        save(product);

        product = new Product();
        product.setId();
        product.setCode("second code");
        product.setDescription("second description");
        product.setPrice(new BigDecimal(300));
        product.setStock(0);
        save(product);

        product = new Product();
        product.setId();
        product.setCode("third code");
        product.setDescription("third description");
        product.setPrice(null);
        product.setStock(300);
        save(product);

        product = new Product();
        product.setId();
        product.setCode("fourth code");
        product.setDescription("fourth description");
        product.setPrice(new BigDecimal(130));
        product.setStock(150);
        save(product);
    }

    public static List<Product> getInstance(){
        if (products == null){
            synchronized (Product.class){
                if (products == null)
                    products = new ArrayList<Product>();
            }
        }
        return products;
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
        for (Product product : products){
            if (product.getId().equals(id)) products.remove(product);
        }
    }
}
