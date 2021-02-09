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

        byte[] tmp = new byte[this.digits.length];
        byte[] result = new byte[this.digits.length];
        for (int i = num.getDigits().length - 1; i >= 0; i--) {
            tmp[i] = num.getDigits()[i];
        }

        for (int i = 0; i < this.digits.length; i++) {
            if (this.digits[i] + tmp[i] < 10) {
                result[i] = (byte) (this.digits[i] + tmp[i]);
            } else {
                int digit = this.digits[i] + tmp[i];
                digit = (byte) (digit % 10);
                result[i] = (byte) digit;
                this.digits[i + 1] = (byte) (this.digits[i + 1] + 1);
            }
        }
        this.digits = result;
        return true;
    }
}





