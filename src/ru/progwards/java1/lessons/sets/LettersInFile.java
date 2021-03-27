package ru.progwards.java1.lessons.sets;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.TreeSet;


public class LettersInFile {

    public static String process(String fileName) throws IOException {
        TreeSet<Character> charSet = new TreeSet<>();
        Reader reader = new FileReader(fileName);
        StringBuilder result = new StringBuilder();
        int symbol = reader.read();
        while (symbol != -1) {
            char character = (char) symbol;
            if (Character.isLetter(character)) {
                charSet.add(character);
            }
            symbol = reader.read();
            Iterator<Character> iterator = charSet.iterator();
            while (iterator.hasNext()) {
                result.append(iterator.next());
            }
        }
        return result.toString();
    }
}
