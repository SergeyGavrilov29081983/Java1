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
            if (a < b && a < c) {
                ratio = (double) b / a;
                if (ratio >= 1.61703 && ratio <= 1.61903) {
                    return true;
                }
            }
            if (b < a && b < c) {
                ratio = (double) a / b;
                if (ratio >= 1.61703 && ratio <= 1.61903) {
                    return true;
                }
            }
            if (c < a && c < b) {
                ratio = (double) a / c;
                if (ratio >= 1.61703 && ratio <= 1.61903) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        /*int side;
        int base;
        for (int i = 1; i < 16; i++) {
            System.out.println(fiboNumber(i));
            side = fiboNumber(i);
            base = fiboNumber(i + 1);
            if (isGoldenTriangle(side, side, base)) {
                System.out.println(side);
                System.out.println(side);
                System.out.println(base);
            }

        }*/
        System.out.println(fiboNumber(10));
    }

    static int addAsStrings(int n1, int n2) {
        return Integer.parseInt( String.valueOf(n1) + String.valueOf(n2));
    }

    static long factorial(long n) {
        if(n == 0) return 1;
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result = result * i;
        }
        return result;
    }



    static String textGrade(int grade) {
        if(grade == 0) return "не оценено";
        if(grade >=1 && grade <20) return "очень плохо";
        if(grade >=21 && grade <=40) return "плохо";
        if(grade >=41 && grade <60) return "удовлетворительно";
        if(grade >=61 && grade <80) return "хорошо";
        if(grade >=81 && grade <100) return "отлично";
        return "не определено";

    }




}
