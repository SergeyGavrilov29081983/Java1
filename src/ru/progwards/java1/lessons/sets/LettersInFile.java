package ru.progwards.java1.lessons.sets;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.TreeSet;

public class LettersInFile {

    public static String process(String fileName) throws IOException {
        StringBuilder result = new StringBuilder();
        TreeSet<Character> res = new TreeSet<>();
        Reader reader = new FileReader(fileName);
        int b = reader.read();
        while (b != -1) {
            char s = (char) b;
            if (Character.isLetter(s)) {
                res.add(s);
            }
            b = reader.read();
        }
        Iterator<Character> it = res.iterator();
        while (it.hasNext()) {
            result.append(it.next());
        }
        return result.toString();
    }
}