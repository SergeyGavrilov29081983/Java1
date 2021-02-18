package ru.progwards.java1.lessons.io2;

public class PhoneNumber {

   /* Задача 1. Класс PhoneNumber

    Создать статический метод public static String format(String phone),
    который форматирует телефон под формат +7(999)111-2233, входящий формат может быть различным:
            - 79991112233
            - 8(999)111-22-33
            - 8 999 111 22 33

    формальное задание на форматирование:
            - выделить все цифры, отбросить все разделители
- проверить что цифр в номере 11 или 10, в противном случае выбросить исключение
- убрать спереди 8
            - добавить, если нужно +7
            - отформатировать под выходной шаблон*/

    public static String format(String phone) throws Exception {
        StringBuilder phoneNumber = new StringBuilder();
        for (char digit : phone.toCharArray()) {
            if (Character.isDigit(digit)) {
                phoneNumber.append(digit);
            }
        }
        if (phoneNumber.length() > 11) {
            throw new Exception();
        }
        if (phoneNumber.charAt(0) == '8' || phoneNumber.charAt(0) == '7') {
            phoneNumber.replace(0, 1, "+7");
        }
        phoneNumber.insert(2, "(").insert(6, ")").insert(10, "-");

        return phoneNumber.toString();
    }

    public static void main(String[] args) throws Exception {
        System.out.println(format("89110545454"));
    }
}
