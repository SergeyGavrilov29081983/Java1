package ru.progwards.java1.lessons.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FindDuplicates {

    public static final List<List<String>> files = new ArrayList<>();

    public static List<List<String>> findDuplicates(String startPath) {

        File dir = new File(startPath);
        File[] listFiles = dir.listFiles();

        for (File file : listFiles) {
            if (file.isDirectory()) {
                findDuplicates(file.getPath());
            } else {
                Item item = new Item(file.getName(), file.lastModified(), file.length());
                List<String> paths = findDublicate(item, startPath);
                files.add(paths);
            }
        }
        return files;
    }

    private static List<String> findDublicate(Item item, String path) {
        List<String> result = new ArrayList<>();
        File dir = new File(path);
        File[] listFiles = dir.listFiles();
        for (File file : listFiles) {
            if (file.isDirectory()) {
                findDublicate(item, file.toPath().toString());
            } else {
                Item itemToEqual = new Item(file.getName(), file.lastModified(), file.length());
                if (item.equals(itemToEqual)) {
                    try {
                        if (sameContent(new File(path), new File(file.getName()))) {
                            result.add(file.getAbsolutePath());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return result;
    }

    public static boolean sameContent(File f1, File f2) throws IOException {
        if (f1.length() != f2.length()) return false;
        FileInputStream fis1 = new FileInputStream(f1);
        FileInputStream fis2 = new FileInputStream(f2);
        try {
            int byte1;
            while ((byte1 = fis1.read()) != -1) {
                int byte2 = fis2.read();
                if (byte1 != byte2) return false;
            }
        } finally {
            fis1.close();
            fis2.close();
        }
        return true;
    }

  /*  private static String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        else return "";
    }*/

   /* В заданном каталоге и его подкаталогах найти файлы, точно совпадающие по названию
            (и расширению), дате-времени последнего изменения, размеру и по содержимому.
    Сигнатура метода public List<List<String>> findDuplicates(String startPath), результат -
    список, содержащий списки строк с именами и полными путями совпадающих файлов.
}*/

    public static void main(String[] args) {
        findDuplicates("outFiles");
        System.out.println();
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
    }

}