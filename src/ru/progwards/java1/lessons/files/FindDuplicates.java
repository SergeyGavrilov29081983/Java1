package ru.progwards.java1.lessons.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FindDuplicates {

    public static final List<List<String>> files = new ArrayList<>();
    public static final String PATTERN = "glob:**";

    public static List<List<String>> findDuplicates(String startPath) {
        List<String> result = new ArrayList<>();
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher(PATTERN);

        try {
            List<Path> listPaths = Files.walk(Paths.get(startPath), 999)
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
            for (Path path : listPaths) {
                File tmp = path.toFile();
                for (Path pathToEqual : listPaths){
                    if (checkEquality(tmp, pathToEqual.toFile())) {
                        result.add(pathToEqual.toString());
                    }
                }
            }
            files.add(result);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return files;
    }

    public static boolean checkEquality(File file1, File file2) {
        if (file1.length() == file2.length() || file1.getName().equals(file2.getName()) || file1.lastModified() == file2.lastModified()) {
            try {
                byte[] bytes = Files.readAllBytes(Paths.get(file1.getPath()));
                byte[] bytes1 = Files.readAllBytes(Paths.get(file2.getPath()));
                return Arrays.equals(bytes, bytes1);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }


    public static void main(String[] args) throws IOException {
        List<List<String>> outFiles = findDuplicates("outFiles");

        for (List<String> outFile : outFiles) {
            for (String s : outFile) {
                System.out.println(s);
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

  /*  Map<Object, List<Item>> collect = null;
        try {
                collect = Files.walk(Paths.get(startPath), 999).filter(Files::isRegularFile)
                .map(p -> new Item(p.toFile().getName(), p.toFile().lastModified(), p.toFile().length()))
                .collect(Collectors.groupingBy(item -> item.name, Collectors.toList()));
                } catch (IOException e) {
                e.printStackTrace();
                }

                for (Map.Entry<Object, List<Item>> coll : collect.entrySet()) {

        List<String> result = new ArrayList<>();
        for (Item str : coll.getValue()) {
        result.add(str.name);
        }
        files.add(result);
        }
        return files;*/