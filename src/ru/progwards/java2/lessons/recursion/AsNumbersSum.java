package ru.progwards.java2.lessons.recursion;

public class AsNumbersSum {
    private static final String eq = " = ";
    private static final String pl = "+";

    public static String asNumbersSum(int number) {
        return number + asNumbersSumR(number - 1, 1, "");
    }

    public static String asNumbersSumR(int n, int i, String p) {
        return (n != 0 ? (i > n ? asNumbersSumR(n, i - n, p + n + pl) : eq + p + n + pl + i + asNumbersSumR(i - 1, 1, p + n + pl)) + asNumbersSumR(n - 1, i + 1, p) : "");
    }

    public static void main(String[] args) {
        System.out.println(asNumbersSum(5));
    }
}


