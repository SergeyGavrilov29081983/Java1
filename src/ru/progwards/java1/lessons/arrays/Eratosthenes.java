package ru.progwards.java1.lessons.arrays;

import java.util.Arrays;

public class Eratosthenes {
    private boolean[] sieve;
    private int N;

    public Eratosthenes(int N) {
        sieve = new boolean[N];
        this.N = N;
        Arrays.fill(sieve, true);
        sift();
    }

    private void sift() {
        for (int i = 2; i < N; i++) {
            for (int j = 2; j < i; j++) {
                sieve[i] = i % j == 0;
            }
        }
    }

    public boolean isSimple(int n) {
        return sieve[n];
    }

}
