package ru.progwards.java1.lessons.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Collectors;

public class FindDuplicates {

    public static final List<List<String>> files = new ArrayList<>();
    public List<String> result;

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

    public List<List<String>> findDuplicates(String startPath) {
        
        try {
            List<Path> listPaths = Files.walk(Paths.get(startPath), 999)
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());

            for (Path path : listPaths) {
                result = new ArrayList<>();
                Files.walkFileTree(Paths.get(startPath), new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path p, BasicFileAttributes attrs) {
                            if (checkEquality(p.toFile(), path.toFile())) {
                                result.add(p.toString());
                            }
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFileFailed(Path p, IOException e) {
                        return FileVisitResult.CONTINUE;
                    }
                });
                files.add(result);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        files.removeIf(file -> file.size() == 1);
        Set<List<String>> set = new HashSet<>(files);
        files.clear();
        files.addAll(set);
        return files;
    }

    
}