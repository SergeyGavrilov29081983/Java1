package ru.progwards.java1.lessons.compare_if_cycles;

public class CyclesGoldenFibo {

    public static boolean containsDigit(int number, int digit) {
        String str = String.valueOf(number);
        for (int i = 0; i < str.length(); i++) {
            if (digit == Integer.parseInt(str.substring(i, (i + 1)))) {
                return true;
            }
        }
        return false;
    }

    public static int fiboNumber(int n) {
        int first;
        int next = 1;
        int current = 0;
        for (int i = 0; i < n; i++) {
            first = next;
            next = current;
            current = first + next;
        }
        return current;
    }

    public static boolean isGoldenTriangle(int a, int b, int c) {
        double ratio;
        if (a == b || a == c || b == c) {
            if (a < b && b == c) {
                ratio = (double) b / a;
                if (ratio >= 1.61703 && ratio <= 1.61903) {
                    return true;
                }
            }
            if (b < a && a == c) {
                ratio = (double) b / a;
                if (ratio >= 1.61703 && ratio <= 1.61903) {
                    return true;
                }
            }
            if (c < a && a == b) {
                ratio = (double) a / c;
                if (ratio >= 1.61703 && ratio <= 1.61903) {
                    return true;
                }
            }
        }


               /* &&  (double) a / (double) b >= 1.61703 & (double) a / (double) b <= 1.61903
                | (double) a / (double) c >= 1.61703 & (double) a / (double) c <= 1.61903
                | (double) b / (double) c >= 1.61703 & (double) b / (double) c <= 1.61903;*/
        return false;
    }

    public static void main(String[] args) {
        /*for (int i = 1; i < 16; i++) {
            System.out.println(fiboNumber(i));
        }*/
        /*for (int i = 1; i < 16; i++) {
            if (isGoldenTriangle(fiboNumber(i), fiboNumber(i), fiboNumber(i + 1))) {
                System.out.println(fiboNumber(i));
                System.out.println(fiboNumber(i));
                System.out.println(fiboNumber(i + 1));
            }
        }*/
        System.out.println(isGoldenTriangle(34, 34, 55));
    }
}
