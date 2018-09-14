package com.es.phoneshop.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayListProductDAO implements ProductDAO {
    private static List<Product> products;

    private static Long idCount = 1L;
    private static volatile ArrayListProductDAO instance = null;

    public static synchronized Long createId(){
        return idCount++;
    }

    private ArrayListProductDAO() {
        products = new ArrayList<Product>();

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
        return products.stream()
                .filter((p) -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NumberFormatException("No products with such id."));
    }

    public List<Product> findProducts() {
        List<Product> productList = products.stream()
                .filter(p->p.getPrice() != null && p.getStock() > 0)
                .collect(Collectors.toList());
        return productList;
    }

    public void save(Product product) {
        products.add(product);
    }

    public void remove(Long id) {
        products.remove(getProduct(id));
    }
}
