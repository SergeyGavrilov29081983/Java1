package ru.progwards.java1.lessons.io1;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CharFilter {

    public static void filterFile(String inFileName, String outFileName, String filter) throws IOException {
        FileReader reader = new FileReader(inFileName);
        Scanner scanner = new Scanner(reader);
        FileWriter writer = new FileWriter(outFileName);
        char[] charFilter = filter.toCharArray();




        try {
            while (scanner.hasNextLine()) {
                String content = scanner.nextLine();
                for (int i = 0; i < content.length(); i++) {
                    char tmp = content.charAt(i);
                        if (filter.indexOf(tmp) == -1) {
                            writer.write(tmp);
                    }
                }
            }
        } finally {
            try {
                reader.close();
            } catch (Throwable ignored) {
            }
            try {
                writer.close();
            } catch (Throwable ignored) {
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String filter = " -,.()";
        CharFilter.filterFile("testIn.txt", "testOut.txt", filter);
    }
}
