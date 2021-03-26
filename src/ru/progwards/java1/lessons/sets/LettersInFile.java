package ru.progwards.java1.lessons.sets;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class LettersInFile {

    public static String process(String fileName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(fileName));
        StringBuilder builder = new StringBuilder();
        for (String str : lines) {
            builder.append(str.replaceAll("[^a-zA-Zа-яА-Я]", ""));
        }
        return Stream.of(builder.toString().split("")).sorted().collect(Collectors.joining());
    }
}
