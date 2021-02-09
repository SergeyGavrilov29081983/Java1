package ru.progwards.java1.lessons.bigints;

import java.math.BigInteger;
import java.util.Arrays;

public class ArrayInteger {

    private byte[] digits;

    public ArrayInteger(int n) {
        this.digits = new byte[n];
    }

    public byte[] getDigits() {
        return digits;
    }

    public void fromInt(BigInteger value) {
        int val = value.intValue();
        for (int i = 0; i < digits.length; i++) {
            digits[i] = (byte) (val % 10);
            val = val / 10;
        }
    }

    BigInteger toInt() {
        StringBuilder result = new StringBuilder();
        for (byte digit : digits) {
            result.append(digit);
        }
        result.reverse();
        return new BigInteger(result.toString());
    }

    public boolean add(ArrayInteger num) {
        if (num.getDigits().length > this.digits.length) {
            Arrays.fill(this.digits, (byte) 0);
        return false;
        }
        for (int i = 0; i < this.digits.length - 1; i++) {
            if (this.digits[i] + num.getDigits()[i] >= 10) {
                this.digits[i] = 0;
                this.digits[i + 1] = (byte) (this.digits[i + 1] + 1);
            }
            this.digits[i] = (byte) (this.digits[i] + num.getDigits()[i]);
        }
           return true;
    }

    public static void main(String[] args) {
        ArrayInteger arrayInteger = new ArrayInteger(5);
        ArrayInteger arrayInteger1 = new ArrayInteger(2);
        arrayInteger.fromInt(new BigInteger("12345"));
        arrayInteger1.fromInt(new BigInteger("23"));
        arrayInteger.add(arrayInteger1);
        System.out.println(Arrays.toString(arrayInteger.getDigits()));
    }
}
