package ru.progwards.java1.lessons.io1;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CharFilter {

//    адача 3. Класс CharFilter
//    Создать статический метод public static void filterFile(String inFileName, String outFileName, String filter),
//    в котором прочитать файл inFileName и удалить символы, содержащиеся в String filter, результат записать в выходной файл.
//    В случае возникновения ошибки, пробросить стандартное исключение выше, корректно закрыв все ресурсы
//
//    Например файл содержит:
//    Java — строго типизированный объектно-ориентированный язык программирования,
//    разработанный компанией Sun Microsystems (в последующем приобретённой компанией Oracle).
//    obscene = " -,.()"
//    Должен выдать результат:
//    Javaстроготипизированныйобъектноориентированныйязыкпрограммированияразработанныйкомпанией
//            SunMicrosystemsвпоследующемприобретённойкомпаниейOracle

    public static void filterFile(String inFileName, String outFileName, String filter) throws IOException {
        FileReader reader = new FileReader(inFileName);
        FileWriter writer = new FileWriter(outFileName);
        char[] charFilter = filter.toCharArray();
        char tmp = 0;

        try {
            for (int ch; (ch = reader.read()) >= 0; ) {
                for (char c : charFilter) {
                    if (ch == (int) c) {
                        writer.write(ch);
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
