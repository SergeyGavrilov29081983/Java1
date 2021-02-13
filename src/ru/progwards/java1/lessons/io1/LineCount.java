package ru.progwards.java1.lessons.io1;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LineCount {

/*
    Задача 1. Класс LineCount
    Создать статический метод public static int calcEmpty(String fileName),
    в котором посчитать количество пустых строк в файле.
    В случае возникновения ошибок, вернуть -1
*/

    public static int calcEmpty(String fileName) {

        try {
            FileReader reader = new FileReader(fileName);
            Scanner scanner = new Scanner(reader);
            int counter = 0;
            try {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line.isEmpty()) {
                        counter += 1;
                    }
                }
                return counter;
            } finally {
                scanner.close();
                reader.close();
            }
        } catch (Exception e) {
            return -1;
        }
    }

    public static void main(String[] args) {
        System.out.println(calcEmpty("test.txt"));
    }
}
