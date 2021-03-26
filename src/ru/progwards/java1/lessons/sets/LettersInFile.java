package ru.progwards.java1.lessons.sets;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class LettersInFile {

    public static String process(String fileName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(fileName));
        StringBuilder builder = new StringBuilder();
        for (String str : lines) {
            builder.append(str.replaceAll("[^\\da-zA-Zа-яёА-ЯЁ ]", ""));
        }
        Set<Character> sort = new HashSet<>();
        String result = builder.toString();
        for (Character character : result.toCharArray()) {
            sort.add(character);
        }
        for (Character character : sort) {
            builder.append(character);
        }
        return builder.toString();
    }
}
