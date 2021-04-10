package ru.progwards.java1.lessons.maps;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class UsageFrequency {

    private List<String> content;

    public void processFile(String fileName) throws IOException {
        this.content = Files.readAllLines(Paths.get(fileName));
    }

    public Map<Character, Integer> getLetters() {
        Map<Character, Integer> result = new HashMap<>();
        StringBuilder builder = new StringBuilder();
        for (String str : content) {
            builder.append(str);
        }
        char[] chars = builder.toString().toCharArray();
        for (Character character : chars) {
            if (Character.isLetter(character) || Character.isDigit(character)) {
                char key = character;
                if (result.containsKey(key)) {
                    result.merge(character, result.get(key), (o, n) -> o + 1);
                } else {
                    result.put(key, 1);
                }
            }
        }
        return result;
    }

    public Map<String, Integer> getWords() {
        Map<String, Integer> result = new HashMap<>();
        for (String word : content) {
            if (result.containsKey(word)) {
                result.merge(word, result.get(word), (o, n) -> o + 1);
            } else {
                result.put(word, 1);
            }
        }
        return result;
    }
}
