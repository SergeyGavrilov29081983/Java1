package ru.progwards.java1.lessons.io1;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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
        Scanner scanner = new Scanner(reader);
        StringBuilder builder = new StringBuilder();

        if (filter.contains("-")) {
            filter = "[" + "\\-" + filter + "]";
        } else {
            filter = "[" + filter + "]";
        }
        StringBuilder regexFilter = new StringBuilder(filter);

        try {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                builder.append(line);
            }

            String result = builder.toString();
            result = result.replaceAll(regexFilter.toString(), "");
            writer.write(result);

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
