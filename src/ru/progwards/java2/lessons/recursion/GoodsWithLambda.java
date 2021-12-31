package ru.progwards.java2.lessons.recursion;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GoodsWithLambda {

    List<Goods> list = new ArrayList<>();

    public void setGoods(List<Goods> list) {
        this.list = list;
    }

    public List<Goods> sortByName() {
        return list.stream()
                .sorted(Comparator.comparing(Goods::getName, String::compareTo))
                .collect(Collectors.toList());
    }

    public List<Goods> sortByNumber() {
        return list.stream()
                .sorted(Comparator.comparing(Goods::getNumber, String::compareTo))
                .collect(Collectors.toList());
    }

    public List<Goods> sortByPartNumber() {
        return list.stream()
                .sorted(Comparator.comparing(goods -> goods.getName().substring(0, 2).toLowerCase(), String::compareTo))
                .collect(Collectors.toList());
    }

    public List<Goods> sortByAvailabilityAndNumber() {
        return list.stream()
                .sorted(Comparator.comparing(Goods::getAvailable).thenComparing(goods -> goods.getNumber().toLowerCase(), String::compareTo))
                .collect(Collectors.toList());
    }

    public List<Goods> expiredAfter(Instant date) {
        return list.stream()
                .filter(good -> good.getExpired().isAfter(date))
                .sorted(Comparator.comparing(Goods::getExpired))
                .collect(Collectors.toList());
    }

    public List<Goods> countLess(int count) {
        return list.stream()
                .filter(goods -> goods.getAvailable() < count)
                .sorted(Comparator.comparing(Goods::getAvailable))
                .collect(Collectors.toList());
    }

    public List<Goods> countBetween(int count1, int count2) {
        return list.stream()
                .filter(goods -> goods.getAvailable() < count2 && goods.getAvailable() > count1)
                .sorted(Comparator.comparing(Goods::getAvailable))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Goods> lg = new ArrayList<>();
        lg.add(new Goods("caramel", "vv67ty", 3, 0.1, Instant.now()));
        lg.add(new Goods("apple", "av17ty", 2, 0.1, Instant.now()));
        lg.add(new Goods("banana", "aV37ty", 2, 0.1, Instant.parse("2021-01-29T14:50:17.986308500Z")));
        GoodsWithLambda gwl = new GoodsWithLambda();
        gwl.setGoods(lg);
        gwl.sortByName().forEach(x -> System.out.println(x.getName()));
        gwl.sortByNumber().forEach(x -> System.out.println(x.getNumber()));
        gwl.sortByPartNumber().forEach(x -> System.out.println(x.getNumber()));
        gwl.sortByAvailabilityAndNumber().forEach(x -> System.out.println(x.getNumber() + " " + x.getAvailable()));
        gwl.expiredAfter(Instant.now()).forEach(x -> System.out.println(x.getExpired()));
        gwl.countLess(3).forEach(x -> System.out.println(x.getAvailable()));
        gwl.countBetween(2, 3).forEach(x -> System.out.println(x.getAvailable()));
    }
}
