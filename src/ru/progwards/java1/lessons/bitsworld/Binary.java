package ru.progwards.java1.lessons.bitsworld;

public class Binary {

    private byte num;

    public Binary(byte num) {
        this.num = num;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for(int i = 8; i >= 0 ; i--) {
            int mask = 1 << i;
            result.append((num & mask) != 0 ? "1" : "0");
        }
        result.replace(result.length() - 1, result.length(), "");
        return result.toString();
    }
}
