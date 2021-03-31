package ru.progwards.java1.lessons.sets;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductAnalytics {

    private List<Shop> shops;
    private List<Product> products;

    public ProductAnalytics(List<Product> products, List<Shop> shops) {
        this.products = products;
        this.shops = shops;
    }

    public Set<Product> existInAll() {
        Set<Product> result = new HashSet<>();
        result.addAll(shops.get(0).getProducts());
        for (int i = 1; i < shops.size(); i++) {
            result.retainAll(shops.get(i).getProducts());
        }
        return result;
    }

    public Set<Product> existAtListInOne() {
        Set<Product> result = new HashSet<>();
        for (Shop shop : shops) {
            List<Product> products = shop.getProducts();
            result.addAll(products);
        }
        return result;
    }

    public Set<Product> notExistInShops() {
        Set<Product> result = new HashSet<>();
        for (Shop shop : shops) {
            List<Product> products = shop.getProducts();
            result.addAll(products);
        }
        Set<Product> ProductsNotExistsInShops = new HashSet<>();
        for (Product product : products) {
            if (!result.contains(product)) {
                ProductsNotExistsInShops.add(product);
            }
        }
        return ProductsNotExistsInShops;
    }

    public Set<Product> existOnlyInOne() {
        Set<Product> unique = new HashSet<>();
        for (Product product : products) {
            int counter = 0;
            for (Shop shop : shops) {
                for (Product product1 : shop.getProducts()) {
                    if (product1.equals(product)) {
                        counter++;
                    }
                }
            }
            if (counter == 1) {
                unique.add(product);
            }
        }
        return unique;
    }
}

class Product {
    private String code;

    public Product(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Product{" +
                "code='" + code + '\'' +
                '}';
    }
}

class Shop {
    private List<Product> products;

    public Shop(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }
}
