package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class FindDuplicates {

    public static final List<List<String>> files = new ArrayList<>();

    public static Map<Object, List<Item>> findDuplicates(String startPath) throws IOException {
        Map<Object, List<Item>> collect = Files.walk(Paths.get(startPath), 999).filter(Files::isRegularFile)
                .map(p -> new Item(p.toFile().getName(), p.toFile().lastModified(), p.toFile().length()))
                .collect(Collectors.groupingBy(item -> item.name, Collectors.toList()));

        for (Map.Entry<Object, List<Item>> coll : collect.entrySet()) {
            List<String> result = new ArrayList<>();
            for (Item str : coll.getValue()) {
                result.add(str.name);
            }
            files.add(result);
        }
        return collect;
    }


    public static void main(String[] args) throws IOException {
        Map<Object, List<Item>> list = findDuplicates("outFiles");

        for (Map.Entry<Object, List<Item>> entry : list.entrySet()) {
            System.out.println(entry);
            List list2 = entry.getValue();
            for (Object object : list2) {
                System.out.println(object.toString());
            }
        }

    }

    static class Item {
        String name;
        long lastModifiedTime;
        long size;

        public Item(String name, long lastModifiedTime, long size) {
            this.name = name;
            this.lastModifiedTime = lastModifiedTime;
            this.size = size;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Item item = (Item) o;
            return lastModifiedTime == item.lastModifiedTime &&
                    size == item.size &&
                    Objects.equals(name, item.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, lastModifiedTime, size);
        }

        @Override
        public String toString() {
            return "Item{" +
                    "name='" + name + '\'' +
                    ", lastModifiedTime=" + lastModifiedTime +
                    ", size=" + size +
                    '}';
        }
    }


}