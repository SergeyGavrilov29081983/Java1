package ru.progwards.java1.lessons.io2;

public class PhoneNumber {



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
