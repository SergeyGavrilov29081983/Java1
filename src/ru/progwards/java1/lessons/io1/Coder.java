package ru.progwards.java1.lessons.io1;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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

    public static void codeFile(String inFileName, String outFileName, char[] code, String logName) throws IOException {
        FileReader reader = new FileReader(inFileName);
        FileWriter writer = new FileWriter(outFileName);

        try {
            for (int ch; (ch = reader.read()) >= 0; ) {
                for (char c : code) {
                    if (ch == (int) c) {
                        writer.write(ch);
                    }
                }
            }
        } catch (Exception ex) {
            writer.write(ex.getMessage());
        } finally {
            reader.close();
            writer.close();
        }
    }
}

