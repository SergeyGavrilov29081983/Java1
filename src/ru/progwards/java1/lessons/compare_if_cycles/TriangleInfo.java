package ru.progwards.java1.lessons.compare_if_cycles;

public class TriangleInfo {

    public static boolean isTriangle(int a, int b, int c) {
        if (a < c + b) return true;
        if (c < a + b) return true;
        return b < c + a;
    }

    public static boolean isRightTriangle(int a, int b, int c) {
        return a * a + b * b == c * c;
    }

    public static boolean isIsoscelesTriangle(int a, int b, int c) {
        return a == b || a == c || b == c;
    }
}
