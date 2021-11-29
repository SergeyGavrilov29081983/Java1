package ru.progwards.java2.lessons.recursion;

public class AsNumbersSum {

    public static String asNumbersSum(int number) {
        if (number == 1) {
            return "";
        }
        int digit = 5;
        String res = "";

        return number + result(number, digit, res);
    }

    public static String result(int number, int digit, String res) {
        if (digit > number) {
            return result(number, digit - number, res + number + "+")
                    + result(number - 1, digit + 1, res);
        } else {
            return "=" + res + number + "+" + digit + result(digit - 1, 1,  res + number + "+")
                    + result(number -1, digit + 1, res);
        }

    }

    public static void main(String[] args) {

        System.out.println(asNumbersSum(5));


    }


}