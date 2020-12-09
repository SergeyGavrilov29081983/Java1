package ru.progwards.java1.lessons.basics;

public class ReverseDigits {

    public static int reverseDigits(int number) {
        int result = 0;
        while (number > 0) {
            result = result * 10 + number % 10;
            number /= 10;
        }
        return result;
    }
}

