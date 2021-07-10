package ru.progwards.java1.lessons.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FindDuplicates {

    public static final List<List<String>> files = new ArrayList<>();
    public static boolean isDuplicate = false;

    public static List<List<String>> findDuplicates(String startPath) {
        try {
            List<Path> listPaths = Files.walk(Paths.get(startPath), 999)
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());

            for (Path path : listPaths) {
                PathMatcher pm = FileSystems.getDefault().getPathMatcher("glob:**/" + path);
                List<String> result = new ArrayList<>();
                List<String> finalResult = result;
                Files.walkFileTree(Paths.get(startPath), new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path p, BasicFileAttributes attrs) {
                        if (pm.matches(p)) {
                            if (checkEquality(p.toFile(), path.toFile())) {
                                finalResult.add(p.toString());
                                isDuplicate = true;
                            }
                        }
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFileFailed(Path p, IOException e) {
                        return FileVisitResult.CONTINUE;
                    }
                });
                if (!isDuplicate) {
                    result = null;
                } else {
                    result.add(path.toString());
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return files;
    }

    public static boolean checkEquality(File file1, File file2) {
        if (file1.length() == file2.length()) {
            if (file1.getName().equals(file2.getName())) {
                if (file1.lastModified() == file2.lastModified()) {
                    try {
                        byte[] bytes = Files.readAllBytes(Paths.get(file1.getPath()));
                        byte[] bytes1 = Files.readAllBytes(Paths.get(file2.getPath()));
                        return Arrays.equals(bytes, bytes1);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
        return false;
    }
}