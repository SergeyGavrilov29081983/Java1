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
        for (Shop shop : shops) {
            List<Product> products = shop.getProducts();
            result.addAll(products);
        }
        return result;
        //товары из products, которые имеются во всех магазинах
    }

    public Set<Product> existAtListInOne() {
        Set<Product> result = new HashSet<>();
        for (Shop shop : shops) {
            List<Product> products = shop.getProducts();
            result.addAll(products);
        }
        return result;
        //товары из products, которые имеются хотя бы в одном магазине
    }


    public Set<Product> notExistInShops() {
        return null;
        //товары из products, которых нет ни в одном магазине
    }


    public Set<Product> existOnlyInOne() {
        return null;
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
