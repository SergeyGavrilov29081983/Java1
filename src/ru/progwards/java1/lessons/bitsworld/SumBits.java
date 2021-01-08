package ru.progwards.java1.lessons.bitsworld;

public class SumBits {
    public static int sumBits(byte value) {
        int sum =  0;
        for(byte b = 1; b !=  0; b <<= 1) {
            if ((value & b) !=  0) sum++;
        }
        return sum;
     }

    public static void main(String[] args) {
        System.out.println(sumBits((byte) 65));
        System.out.println((byte) 010010);
    }

   /* 1.1 Реализовать функцию public static int sumBits(byte value),
    которая суммирует все биты параметра value. Например,
    для двоичного числа 0100101 функция должна вернуть 3.

    Подсказки:

    используйте & с числом 1 для того, чтобы оставить только один правый значащий бит;
    используйте сдвиг вправо для того, чтобы проверить следующий бит.*/

}
