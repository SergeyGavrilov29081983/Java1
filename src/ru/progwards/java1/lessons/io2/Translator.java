package ru.progwards.java1.lessons.io2;

import java.util.Arrays;

public class Translator {

 /*   Задача 2. Класс Translator

    Создать класс Translator - переводчик, который будет переводить предложения с одного языка на другой

2.1 Конструктор Translator(String[] inLang, String[] outLang), где inLang и outLang
    - массивы слов на разных языках, например русском и английском. Сохранить значения параметров в приватных свойствах класса

2.2  Метод public String translate(String sentence), в котором найти слова,
    содержащиеся в sentence и в inLang и заменить их на соответствующие в outLang.
    Пунктуация должна быть соблюдена (скопирована из оригинальной строки).
    При этом надо соблюсти заглавные буквы, если они были в оригинальном тексте.
    В inLang и outLang все слова хранятся в нижнем регистре.

    Например, фраза
"Hello World!" будет переведена как "Привет Мир!"
    при наличии слов "hello", "world" в inLang и "привет", "мир" в outLang*/

    private String[] inLang;
    private String[] outLang;

    public Translator(String[] inLang, String[] outLang) {
        this.inLang = inLang;
        this.outLang = outLang;
    }

    public String translate(String sentence) {
        StringBuilder builder = new StringBuilder();
        String[] strings = sentence.split("[ \n\t\r.,;:!?(){]");
     for (int i = 0; i < strings.length; i++) {
      String tmp = strings[i];
      if (inLang[i] == tmp) {
       tmp = outLang[i];
       builder.append(tmp);
      }
     }
        return builder.toString();
    }
}
