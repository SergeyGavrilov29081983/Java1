package ru.progwards.java1.lessons.bitsworld;

public class SumBits {
    public static int sumBits(byte value) {
        int sum = 0;
            for (byte a = 1; a != 0; a <<= 1)
                if ((value & a) !=  0) sum++;
        return sum;
    }


    public static void main(String[] args) {
        System.out.println(sumBits((byte) 32833));

    }

}
