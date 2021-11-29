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

    public List<Goods> сountLess(int count) {
        return list.stream()
                .filter(goods -> goods.getAvailable() < count)
                .sorted(Comparator.comparing(Goods::getAvailable))
                .collect(Collectors.toList());
    }

    public List<Goods> сountBetween(int count1, int count2) {
        return list.stream()
                .filter(goods -> goods.getAvailable() < count2 && goods.getAvailable() > count1)
                .sorted(Comparator.comparing(Goods::getAvailable))
                .collect(Collectors.toList());
    }
}
