package ru.progwards.java1.lessons.maps;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsageFrequency {

    private List<String> content;

    public void processFile(String fileName) {
        try {
            this.content = Files.readAllLines(Paths.get(fileName));
        } catch (IOException ex) {
            ex.getMessage();
        }
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
        for (String str : content) {
            String[] words = str.split(" ");
            for (String key : words) {
                if (!key.isEmpty()) {
                    if (Character.isLetter(key.charAt(0)) || Character.isDigit(key.charAt(0))) {
                        if (result.containsKey(key)) {
                            result.merge(key, result.get(key), (o, n) -> o + 1);
                        } else {
                            result.put(key, 1);
                        }
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        UsageFrequency usageFrequency = new UsageFrequency();
        System.out.println(usageFrequency.getWords());
    }
}
