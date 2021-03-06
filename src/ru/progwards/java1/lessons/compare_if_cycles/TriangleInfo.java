package ru.progwards.java1.lessons.compare_if_cycles;

public class TriangleInfo {

    public static boolean isTriangle(int a, int b, int c) {
        if (a >= b + c) return false;
        if (b >= a + c) return false;
        if (c >= a + b) return false;
        return true;
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
        System.out.println(isTriangle(4, 1, 5));
        System.out.println(isRightTriangle(3, 4, 5));
    }
}
