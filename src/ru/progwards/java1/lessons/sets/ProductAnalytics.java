package ru.progwards.java1.lessons.sets;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProductAnalytics {

    private List<Shop> shops;
    private List<Product> products;

    public ProductAnalytics(List<Product> products, List<Shop> shops) {
        this.products = products;
        this.shops = shops;
    }

    public static void main(String[] args) {
        Shop shop1 = new Shop(Arrays.asList(new Product("art-2"), new Product("art-4"), new Product("art-7"), new Product("art-9")));
        Shop shop2 = new Shop(Arrays.asList(new Product("art-1"), new Product("art-3"), new Product("art-4"), new Product("art-10")));
        Shop shop3 = new Shop(Arrays.asList(new Product("art-1"),
                new Product("art-3"), new Product("art-4"), new Product("art-7"), new Product("art-10")));
        List<Product> products = new ArrayList<>(Arrays.asList(new Product("art-1"), new Product("art-2"), new Product("art-3"),
                new Product("art-4"), new Product("art-5"), new Product("art-6"), new Product("art-7"), new Product("art-8"),
                new Product("art-9"), new Product("art-10")));

        // Ожидалось множество, содержащее:art - 9, art - 2.
        ProductAnalytics productAnalytics = new ProductAnalytics(products, Arrays.asList(shop1, shop2, shop3));
        System.out.println(productAnalytics.existOnlyInOne());
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
return null;
    }
    //товары из products, которые есть только в одном магазине

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
