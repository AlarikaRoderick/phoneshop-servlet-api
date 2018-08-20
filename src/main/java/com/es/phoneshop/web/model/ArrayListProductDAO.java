package com.es.phoneshop.web.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayListProductDAO implements ProductDAO {
    private static List<Product> products;

    private static Long idCount = 1L;

    public static synchronized Long createId(){
        return idCount++;
    }

    public void createProduct(Product product, String code, String description, BigDecimal price, Integer stock){
        product = new Product();
        product.setId(createId());
        product.setCode(code);
        product.setDescription(description);
        product.setPrice(price);
        product.setStock(stock);
        save(product);

    }

    public ArrayListProductDAO() {
        products = getInstance();

        Product product = null;

        createProduct(product, "first code", "first description", new BigDecimal(250), 200);
        createProduct(product, "second code", "second description", new BigDecimal(300), 0);
        createProduct(product, "third code", "third description", null, 300);
        createProduct(product, "fourth code", "fourth description", new BigDecimal(130), 150);

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
