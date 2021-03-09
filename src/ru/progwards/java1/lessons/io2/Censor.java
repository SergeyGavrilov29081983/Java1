package ru.progwards.java1.lessons.io2;



import java.io.RandomAccessFile;

import java.util.Scanner;

public class Censor {

    private static String censor(String word, String[] obscene) {
        String symbols = "";
        String prew = "";
        String postw = "";

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

        return prew+symbols+postw;
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
