package ru.progwards.java1.lessons.sets;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class LettersInFile {

    public static String process(String fileName) throws IOException {

       /* List<String> lines = Files.readAllLines(Paths.get(fileName));
        StringBuilder builder = new StringBuilder();
        for (String str : lines) {
            builder.append(str.replaceAll("[^a-zA-Zа-яА-Я]", ""));
        }*/
       return Files.lines(Paths.get(fileName))
               .map(str -> Arrays.toString(str.replaceAll("[^a-zA-Zа-яА-Я]", "").split("")))
               .distinct()
               .sorted()
               .collect(Collectors.joining());
        //return Stream.of(builder.toString().split("")).sorted().distinct().collect(Collectors.joining());
    }
}
