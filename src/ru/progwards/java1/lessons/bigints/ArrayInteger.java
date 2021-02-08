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
        byte[] tmp = this.digits;
        for (int i = 0; i < tmp.length - 1; i++) {
            if (tmp[i] + num.getDigits()[i] >= 10) {
                tmp[i] = 0;
                tmp[i + 1] = (byte) (tmp[i + 1] + 1);
            }
            tmp[i] = (byte) (tmp[i] + num.getDigits()[i]);
        }
        if (this.digits.length < tmp.length) {
            Arrays.fill(this.digits, (byte) 0);
            return false;
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        ArrayInteger arrayInteger = new ArrayInteger(2);
        ArrayInteger arrayInteger1 = new ArrayInteger(2);
        arrayInteger.fromInt(new BigInteger("12"));
        arrayInteger1.fromInt(new BigInteger("23"));
        arrayInteger.add(arrayInteger1);
        System.out.println(Arrays.toString(arrayInteger.getDigits()));
    }
}
