package ru.progwards.java1.lessons.io1;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;

public class Coder {
/*

    Задача 2. Класс Coder

    Создать статический метод
    public static void codeFile(String inFileName, String outFileName, char[] code, String logName),
    в котором прочитать файл inFileName и
    перекодировать его посимвольно
    в соответствии с заданным шифром,
    результат записать в outFileName.
    Шифр задается маcсивом char[] code,
    где каждому символу symbol оригинального файла соответствует
    символ code[(int)symbol] выходного файла. В случае ошибок,
    в файл с именем logName вывести название ошибки через метод класса Exception - getMessage()
*/

    public static void codeFile(String inFileName, String outFileName, char[] code, String logName) {
        String message = null;
        try {
            FileReader reader = new FileReader(inFileName);
            FileWriter writer = new FileWriter(outFileName);
            try {
                for (int ch; (ch = reader.read()) >= 0; ) {
                    writer.write(code[ch]);
                }
            }finally {
                reader.close();
                writer.close();
            }
        } catch (Exception ex) {
            message = ex.getMessage();
        }
        try {
            FileWriter writer = new FileWriter(logName);
            writer.write(message);
            writer.close();
        } catch (Exception ignored) {
        }

    }


    public static void main(String[] args) {
            char[] code = new char[256];
            Arrays.fill(code, '*');
            for (int i = 48; i < 58; i++)
                code[i] = (char) (i + 16);

            codeFile("in.txt", "out.txt", code, "log.txt");
        codeFile("inin.txt", "out.txt", code, "log.txt");

    }
}

