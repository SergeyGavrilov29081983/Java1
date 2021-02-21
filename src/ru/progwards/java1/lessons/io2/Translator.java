package ru.progwards.java1.lessons.io2;

public class Translator {

    private String[] inLang;
    private String[] outLang;

    public Translator(String[] inLang, String[] outLang) {
        this.inLang = inLang;
        this.outLang = outLang;
    }

    public String translate(String sentence) {
        StringBuilder builder = new StringBuilder();
        String[] strings = sentence.split(" ");
        for (int i = 0; i < strings.length; i++) {
            String tmp = strings[i];
            char ch = tmp.charAt(tmp.length() - 1);
            if (!Character.isLetter(ch)) {
                String mark = tmp.substring(tmp.length() - 1);
                tmp = tmp.substring(0, tmp.length() - 1);
                if (inLang[i].equalsIgnoreCase(tmp)) {
                    tmp = outLang[i];
                    if (Character.isUpperCase(strings[i].charAt(0))) {
                        builder.append(tmp.substring(0, 1).toUpperCase()).append(tmp.substring(1)).append(mark);
                    } else {
                        builder.append(tmp).append(mark);
                    }
                }
            }
            if (inLang[i].equalsIgnoreCase(tmp)) {
                tmp = outLang[i];
                if (Character.isUpperCase(strings[i].charAt(0))) {
                    builder.append(tmp.substring(0, 1).toUpperCase()).append(tmp.substring(1));
                } else {
                    builder.append(tmp);
                }
            }
            builder.append(" ");
        }
        return builder.toString();
    }
}
