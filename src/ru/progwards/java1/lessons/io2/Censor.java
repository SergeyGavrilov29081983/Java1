package ru.progwards.java1.lessons.io2;


import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.Scanner;

public class Censor {

    private static String censor(String word, String[] obscene) {
        StringBuilder symbols = new StringBuilder();
        StringBuilder prew = new StringBuilder();
        StringBuilder postw = new StringBuilder();

        for (char c : word.toCharArray())
            if (Character.isAlphabetic(c)) {
                symbols.append(c);
            } else if ("".equals(symbols.toString())) {
                prew.append(c);
            } else {
                postw.append(c);
            }

        for (String s : obscene) {
            if (symbols.toString().equals(s)) {
                symbols = new StringBuilder();
                for (int j = 0; j < s.length(); j++) {
                    symbols.append("*");
                }
                break;
            }
        }

        return prew.toString() + symbols + postw;
    }


    public static void censorFile(String inoutFileName, String[] obscene) throws CensorException {
        try {

            try (Scanner scan = new Scanner(inoutFileName)) {
                RandomAccessFile raf = new RandomAccessFile(inoutFileName, "rw");
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

    static class CensorException extends Exception {
        private String message;
        private String fileName;

        CensorException(String message, String fileName) {
            super(fileName + ":" + message);
            this.message = message;
            this.fileName = fileName;

        }

        @Override
        public String toString() {
            return fileName + ":" + message;
        }
    }
}
