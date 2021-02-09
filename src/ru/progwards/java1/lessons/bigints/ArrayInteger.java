package ru.progwards.java1.lessons.bigints;

import java.math.BigInteger;
import java.util.Arrays;

public class ArrayInteger {

    private byte[] digits;

    public ArrayInteger(int n) {
        this.digits = new byte[n];
    }

    public static void main(String[] args) {
        ArrayInteger ai1 = new ArrayInteger(8);
        ai1.fromInt(new BigInteger("15947106"));

        ArrayInteger ai2 = new ArrayInteger(5);
        ai2.fromInt(new BigInteger("18675"));

        ai1.add(ai2);
        BigInteger integ = ai1.toInt();
        //1 5 9 6 5 7 8 1
        System.out.println(integ.intValue());
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

          /*  int[] r = new int[Math.max(arr1.length, arr2.length)];
            for (int i = 0; i < r.length; i++) {
                r[i] = (i < arr1.length ? arr1[i] : 0) + (i < arr2.length ? arr2[i] : 0);

        }*/

       /* int[] tmp = new int[this.digits.length];
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = (i < this.digits.length ? this.digits[i] : 0)
                    + (i < num.getDigits().length ? num.getDigits()[i] : 0);
        }*/

        byte[] tmp = new byte[this.digits.length];
        for (int i = num.getDigits().length - 1; i >= 0; i--) {
            tmp[i] = num.getDigits()[i];
        }

        for (int i = 0; i < this.digits.length; i++) {

            if (this.digits[i] + tmp[i] >= 10) {
                this.digits[i] = 0;
                this.digits[i + 1] = (byte) (this.digits[i + 1] + 1);
            }
            this.digits[i] = (byte) (this.digits[i] + tmp[i]);
        }
        return true;
    }
}
