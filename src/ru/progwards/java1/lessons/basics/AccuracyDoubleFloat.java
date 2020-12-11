package ru.progwards.java1.lessons.basics;

public class AccuracyDoubleFloat {

    public static double volumeBallDouble(double radius) {
        return 4.0/ 3 * 3.14 * radius * radius * radius;
    }

    public static float volumeBallFloat(float radius) {
        return (float) (4.0f/ 3 * 3.14 * radius * radius * radius);
    }

    public static double calculateAccuracy(double radius) {
        return volumeBallDouble(radius) - volumeBallFloat((float) radius);
    }

}
