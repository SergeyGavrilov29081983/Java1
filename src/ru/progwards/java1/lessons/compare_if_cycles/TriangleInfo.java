package ru.progwards.java1.lessons.compare_if_cycles;

public class TriangleInfo {

    public static boolean isTriangle(int a, int b, int c) {
        if (a >= c + b) return false;
        if (c >= a + b) return false;
        return b >= c + a;
    }

    public static boolean isRightTriangle(int a, int b, int c) {
        return b * b + c * c == a * a;

    }

    public static boolean isIsoscelesTriangle(int a, int b, int c) {
        return a == b || a == c || b == c;
    }

    public static void main(String[] args) {
        System.out.println(isTriangle(4, 1, 5));
        System.out.println(isRightTriangle(5, 4, 3));
    }
}
