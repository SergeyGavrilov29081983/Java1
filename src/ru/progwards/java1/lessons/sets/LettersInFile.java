package ru.progwards.java1.lessons.sets;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class LettersInFile {

public static String process(String fileName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(fileName));
        StringBuilder builder = new StringBuilder();
        for (String str: lines) {
            builder.append(str.replaceAll("[^\\da-zA-Zа-яёА-ЯЁ ]", ""));
        }
        return builder.toString();
    }
}
