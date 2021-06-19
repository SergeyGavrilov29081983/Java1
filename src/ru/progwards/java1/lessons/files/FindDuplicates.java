package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FindDuplicates {

    private final List<List<String>> files = new ArrayList<>();

   /* public List<List<String>> findDuplicates(String startPath) {

        Path path = Paths.get(startPath);
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:**");

        try {
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                    if (matcher.matches(path))
                       *//* File[] files = Files.list(path);*//*
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException e) {
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return files;
    }
*/

   /* В заданном каталоге и его подкаталогах найти файлы, точно совпадающие по названию
            (и расширению), дате-времени последнего изменения, размеру и по содержимому.
    Сигнатура метода public List<List<String>> findDuplicates(String startPath), результат -
    список, содержащий списки строк с именами и полными путями совпадающих файлов.
}*/

    public List<List<String>> findDuplicates(String startPath) {
        Path path = Paths.get(startPath);
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:**");

        try {
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                    if (matcher.matches(path)) {


                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException e) {
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
return null;
    }

    class FileItem {

    }
}