package ru.progwards.java1.lessons.bigints;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BigAlgebra {

    public static void main(String[] args) {
        BigAlgebra algebra = new BigAlgebra();
        System.out.println(algebra.fastPow(new BigDecimal(2), 2));
        System.out.println(algebra.fibonacci(10));

    }

    public BigDecimal fastPow(BigDecimal num, int pow) {
        BigDecimal res = new BigDecimal("1");
        while (pow > 0) {
            if ((pow & 1) == 1) {
                res = res.multiply(num);
            }
            num = num.multiply(num);
            pow >>= 1;
        }
        return res;
    }

    public BigInteger fibonacci(int n) {
        BigInteger first;
        BigInteger next = new BigInteger("1");
        BigInteger current = new BigInteger("0");
        for (int i = 0; i < n; i++) {
            first = next;
            next = current;
            current = first.add(next);
        }
        return current;
    }
}
