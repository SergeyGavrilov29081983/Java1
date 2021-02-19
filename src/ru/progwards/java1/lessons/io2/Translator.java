package ru.progwards.java1.lessons.io2;

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
        this.inLang = new String[inLang.length];
        System.arraycopy(inLang, 0, this.inLang, 0, inLang.length);
        this.outLang = new String[outLang.length];
        System.arraycopy(inLang, 0, this.outLang, 0, outLang.length);
        this.inLang = inLang;
        this.outLang = outLang;
    }

    public static void main(String[] args) {

       /* ERROR: Тест "Конструктор Translator(String[] inLang, String[] outLang)"
        не пройден. Метод возвращает неверное значение.
        Экземпляр Translator был создан с параметрами: new String[]{"make", "love", "not", "war"}
        и new String[]{"твори", "любовь", "не", "войну"}. Метод был вызван с параметром: "make love not war".
                Возвращено: "", ожидалось: "твори любовь не войну".
*/
        Translator translator = new Translator(
                new String[]{"make", "love", "not", "war"},
                new String[]{"твори", "любовь", "не", "войну"});
        System.out.println(translator.translate("make love not war"));
    }

    public String translate(String sentence) {
        StringBuilder builder = new StringBuilder();
        String[] strings = sentence.split("\\s+|(?=[,.])");  //"[ \n\t\r.,;:!?(){]"
        for (int i = 0; i < strings.length; i++) {
            String tmp = strings[i];
            if (inLang[i].equalsIgnoreCase(tmp)) {
                tmp = outLang[i];
                    if(Character.isUpperCase(strings[i].charAt(0))){
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
