package ru.progwards.java1.lessons.compare_if_cycles;

public class TriangleInfo {

    public static boolean isTriangle(int a, int b, int c) {
        if (a < c + b) return true;
        if (b < a + c) return true;
        return c < a + b;
    }

    public static boolean isRightTriangle(int a, int b, int c) {
        if (a * a + b * b == c * c) return true;
        if (b * b + c * c == a * a) return true;
        return a * a + c * c == b * b;

    }

    public static boolean isIsoscelesTriangle(int a, int b, int c) {
        return a == b || a == c || b == c;
    }

    public static void main(String[] args) {
        System.out.println(isTriangle( 3, 4, 5));
        System.out.println(isRightTriangle(3, 4, 5));
    }
}
