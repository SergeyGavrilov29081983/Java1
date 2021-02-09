package ru.progwards.java1.lessons.bigints;

public class AbsInteger {

    public static int sum;
    public int integer;

    public static AbsInteger add(AbsInteger num1, AbsInteger num2) {
        sum = num1.toIntObj().getInteger() + num2.toIntObj().getInteger();
        if (sum >= -127 && sum <= 128) {
            return new ByteInteger((byte) sum);
        }
        if (sum >= -32768 && sum <= 32767) {
            return new ShortInteger((short) sum);
        }
        return new IntInteger(sum);
    }

    public IntInteger toIntObj() {
        return new IntInteger(this.integer);
    }
}
