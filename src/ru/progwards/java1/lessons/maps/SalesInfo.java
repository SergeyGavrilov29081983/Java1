package ru.progwards.java1.lessons.maps;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class SalesInfo {

    private List<String[]> data = new ArrayList<>();

    private static boolean isInteger(String number) {
        try {
            Integer.parseInt(number.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isDouble(String number) {
        try {
            Double.parseDouble(number.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        SalesInfo salesInfo = new SalesInfo();
        salesInfo.loadOrders("in.txt");
        salesInfo.getGoods();
    }

    public int loadOrders(String fileName) {
        try {
            data = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8)
                    .stream()
                    .map(m -> m.split(","))
                    .filter(m -> m.length == 4)
                    .filter(m -> isInteger(m[2].trim()))
                    .filter(m -> isDouble(m[3].trim()))
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            ex.getMessage();
        }
        for(String[] record: data) {
            System.out.println(Arrays.toString(record));
        }
        return data.size();
  }

    public Map<String, Double> getGoods() {
        Map<String, Double> goods = new TreeMap<>();
        for (String[] record : data) {
            String key = record[1];
            if (goods.containsKey(key)) {
                goods.merge(key.trim(), goods.get(key.trim()), (o, n) -> o + Double.parseDouble(record[3]));
            } else {
                goods.put(record[1].trim(), Double.valueOf(record[3]));
            }
        }
        return goods;
    }

    public Map<String, AbstractMap.SimpleEntry<Double, Integer>> getCustomers() {
        return null;
    }
}

/*

        3.2 Реализовать метод public Map<String, Double> getGoods() -
        вернуть список товаров, отсортированный по наименованию товара.
        В String - наименование товара, в Double - общая сумма продаж по товару

        3.3 Реализовать метод
public Map<String, AbstractMap.SimpleEntry<Double, Integer>> getCustomers() -
        вернуть список покупателей, отсортированный по алфавиту. В String  - ФИ,
        в Double - сумма всех покупок покупателя, в Integer - количество покупок*/
