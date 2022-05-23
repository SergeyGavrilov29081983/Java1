package ru.progwards.java2.lessons.annotation;

public class SimpleCalculator {

    public static int sum(int val1, int val2) throws ArithmeticException {
        long longRes = ((long) val1) + val2;
        if (longRes > Integer.MAX_VALUE || longRes < Integer.MIN_VALUE)
            throw new ArithmeticException("Переполнение диапазона int при сложении " + longRes);
        return val1 + val2;
    }

    public  static int diff(int val1, int val2) {
        long longRes = ((long) val1) - val2;
        if (longRes > Integer.MAX_VALUE || longRes < Integer.MIN_VALUE)
            throw new ArithmeticException("Переполнение диапазона int при вычитании " + longRes);
        return val1 - val2;
    }

    public static int mult(int val1, int val2) {

        long longRes = ((long) val1) * val2;
        if (longRes > Integer.MAX_VALUE || longRes < Integer.MIN_VALUE)
            throw new ArithmeticException("Переполнение диапазона int при умножении " + longRes);
        return val1 * val2;
    }

    public static int div(int val1, int val2) {
        long longRes = ((long) val1) / val2;
        if (longRes > Integer.MAX_VALUE)
            throw new ArithmeticException("Переполнение диапазона int при делении " + longRes);
        return val1 / val2;
    }
}
