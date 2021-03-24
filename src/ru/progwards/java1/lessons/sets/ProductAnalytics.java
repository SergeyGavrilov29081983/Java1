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
        Set<Product> retainProducts = new HashSet<>(shops.get(0).getProducts());
        for (int i = 1; i < shops.size(); i++) {
            retainProducts.retainAll(shops.get(i).getProducts());
        }

        Set<Product> productsExistOnlyOneShop = new HashSet<>();

        for (Shop shop: shops) {
            List<Product> shopProducts = shop.getProducts();
            for (Product product: shopProducts) {
                if (!retainProducts.contains(product)) {
                    productsExistOnlyOneShop.add(product);
                }
            }
        }
        return productsExistOnlyOneShop;
        //товары из products, которые есть только в одном магазине
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
