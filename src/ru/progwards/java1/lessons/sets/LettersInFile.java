package ru.progwards.java1.lessons.sets;

import java.io.*;
import java.util.Iterator;
import java.util.TreeSet;

public class LettersInFile {

    public static String process(String fileName) throws IOException {
        String result = "";
        TreeSet<Character> res = new TreeSet<>();
        try (FileInputStream reader = new FileInputStream(fileName)) {
            InputStreamReader test = new InputStreamReader(reader);
            int b = test.read();
            while (b != -1) {
                char s = (char) b;
                if (Character.isLetter(s)) {
                    res.add(s);
                }
                b = test.read();
            }
            Iterator<Character> it = res.iterator();
            while (it.hasNext()) {
                result += it.next();
            }
        } catch (IOException e) {
            throw new IOException("что-то пошло не так.. " + e.getMessage());
        }
        return result;
    }
}