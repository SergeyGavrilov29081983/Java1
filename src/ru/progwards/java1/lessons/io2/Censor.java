package ru.progwards.java1.lessons.io2;



import java.io.File;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.sql.ClientInfoStatus;
import java.util.*;

public class Censor {
    static class CensorException extends Exception {
        String mExc;
        String fName;

        CensorException(String mExc, String fName) {
            super(fName + ":" + mExc);
            this.mExc = mExc;
            this.fName = fName;
        }

        @Override
        public String toString() {
            return fName + ":" + mExc;
        }
    }

    private static String censor(String word, String[] obscene) {
        String symbols = "";
        String prew = ""; // знаки препинания до слова
        String postw = ""; // знаки препинания после слова

        for (char c : word.toCharArray())
            if (Character.isAlphabetic(c)) {
                symbols = symbols + c;
            } else if ("".equals(symbols)) {
                prew = prew + c;
            } else {
                postw = postw + c;
            }

        for (int i = 0; i < obscene.length; i++) {
            if (symbols.equals(obscene[i])) {
                symbols = "";
                for (int j = 0; j < obscene[i].length(); j++) {
                    symbols = symbols + "*";
                }
                break;
            }
        }

        return prew + symbols + postw;
    }

    public static void censorFile(String inoutFileName, String[] obscene) throws CensorException {
        try {
            File fn = new File(inoutFileName);
            try (Scanner scan = new Scanner(fn)) { // воспринимал строку пути как просто строку %)
                RandomAccessFile raf = new RandomAccessFile(fn, "rw");
                while (scan.hasNext()) {
                    String word = scan.next();
                    String res;
                    if (word.contains("-")) {
                        String[] word2 = new String[2];
                        int ind = word.indexOf("-");
                        word2[0] = word.substring(0, ind);
                        word2[1] = word.substring(ind + 1);
                        res = censor(word2[0], obscene) + "-" + censor(word2[1], obscene);
                    } else if (word.contains("'")) {
                        String[] word2 = new String[2];
                        int ind = word.indexOf("'");
                        word2[0] = word.substring(0, ind);
                        word2[1] = word.substring(ind + 1);
                        res = censor(word2[0], obscene) + "'" + censor(word2[1], obscene);
                    } else {
                        res = censor(word, obscene);
                    }

                    if (scan.hasNext()) {
                        res = res + " ";
                    }
                    raf.write(res.getBytes());
                }
                raf.close();
            } catch (Exception e) {
                throw new CensorException(e.getMessage(), inoutFileName);
            }
        } catch (Exception e) {
            throw new CensorException(e.getMessage(), inoutFileName);
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        System.out.println(filter(list));
        try (FileWriter writer = new FileWriter("D:\\123.txt")) {
            writer.write("It's a nice day, isn't it? Java супер)");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String[] obscene = {"Java", "Oracle", "two", "day"};

        try {
            censorFile("D:\\123.txt", obscene);
        } catch (CensorException e) {
            System.out.println(e.toString());
        }

        /*Напишите метод с сигнатурой public List<Integer>
        listAction(List<Integer> list), который выполняет следующие действия:

        удаляет минимальный элемент коллекции
        по индексу 0 добавляет число равное количеству элементов
        по индексу 2 добавляет максимальный элемент из list
        возвращает list как результат метода


        */


    }

    public void iterator3(ListIterator<Integer> iterator)  {
        while(iterator.hasNext()) {
            Integer integer = iterator.next();
            if(integer % 3 == 0) {
                iterator.set(iterator.nextIndex());
            }
        }
    }

    public List<Integer> listAction(List<Integer> list) {
        list.remove(Collections.min(list));
        list.add(0, list.size());
        list.add(2, Collections.max(list));
        return list;
    }

   /* суммирует значения
    всех элементов
    списка
    удаляет из
    списка элементы, значение
    которых больше
    суммы,
    деленной на 100(
    целочисленное деление)
    возвращает результирующий
    список
*/
    public static List<Integer> filter(List<Integer> list) {
        int sum = 0;
        for (Integer el : list) {
            sum += el;
        }
List<Integer> list1 = new ArrayList<>(list);
        for (Integer el : list1) {
            if (el > sum / 100) {
                list.remove(el);
            }

        }


        return list1;

    }

}