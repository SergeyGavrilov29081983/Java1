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
        return data.size();
  }

    public Map<String, Double> getGoods() {
        Map<String, Double> goods = new TreeMap<>();
        for (String[] record : data) {
            String key = record[1].trim();
            if (goods.containsKey(key)) {
                goods.merge(key, goods.get(key), (o, n) -> o + Double.parseDouble(record[3]));
            } else {
                goods.put(record[1].trim(), Double.valueOf(record[3]));
            }
        }
        return goods;
    }

    public Map<String, AbstractMap.SimpleEntry<Double, Integer>> getCustomers() {
        Map<String, AbstractMap.SimpleEntry<Double, Integer>> result = new TreeMap<>();
        for (String[] record: data) {
            String key = record[0];
            if (result.containsKey(key)) {
               AbstractMap.SimpleEntry<Double, Integer> entry = result.get(key);
               Double sum = entry.getKey() + Double.parseDouble(record[3].trim());
               Integer count = entry.getValue() + Integer.parseInt(record[2].trim());
               AbstractMap.SimpleEntry<Double, Integer> updatedEntry = new AbstractMap.SimpleEntry<>(sum, count);
               result.put(key, updatedEntry);
            } else {
                AbstractMap.SimpleEntry<Double, Integer> entry =
                        new AbstractMap.SimpleEntry<>(Double.parseDouble(record[3].trim()), Integer.parseInt(record[2].trim()));
                result.put(key, entry);
            }
        }
        return result;
    }
}