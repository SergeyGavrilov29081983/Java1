package ru.progwards.java1.lessons.io2;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Censor {

   /* Задача 3. Класс Censor
    Создать статический метод public static void censorFile(String inoutFileName, String[] obscene),
    в котором прочитать файл inoutFileName и заменить слова, содержащиеся в String[] obscene на '*',
    соответствующие количеству символов в слове, изменения записать в исходный файл. В случае возникновения ошибки,
    выбросить свое собственное исключение CensorException в котором сохранить - строку, полученную у оригинального exception
    через метод getMessage() и имя файла, в котором возникла ошибка. В классе перекрыть метод toString(), вернув <имя файла>:<строка
            ошибки>. Класс CensorException разместить в классе Censor

    Например файл содержит:
    Java — строго типизированный объектно-ориентированный
    язык программирования, разработанный компанией Sun Microsystems (в последующем приобретённой компанией Oracle).
    obscene = {"Java", "Oracle", "Sun", "Microsystems"}

    Должен выдать результат:
            **** — строго типизированный объектно-ориентированный
    язык программирования, разработанный компанией *** ************ (в последующем приобретённой компанией ******).*/

    public static void censorFile(String inoutFileName, String[] obscene) {

        try(RandomAccessFile randomAccessFile = new RandomAccessFile(inoutFileName, "rw")){
            while (randomAccessFile.length() != -1) {
                String line = randomAccessFile.readLine();
                line = new String(line.getBytes("ISO-8859-1"), "UTF-8");
                String[] words = line.split("[ \n\t\r.,;:!?(){]");
                System.out.println(Arrays.toString(words));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        censorFile("in.txt", new String[5]);
    }
    }

