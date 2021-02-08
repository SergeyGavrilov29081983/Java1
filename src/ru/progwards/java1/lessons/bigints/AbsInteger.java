package ru.progwards.java1.lessons.bigints;

public class AbsInteger {

    public static int sum;
    public int integer;

    public static AbsInteger add(AbsInteger num1, AbsInteger num2) {
        AbsInteger abs = new AbsInteger();
        if (num1 instanceof ByteInteger || num2 instanceof ByteInteger) {
            sum = num1.toIntObj().getInteger() + num2.toIntObj().getInteger();
        }
        if (num1 instanceof ShortInteger || num2 instanceof ShortInteger) {
            sum = num1.toIntObj().getInteger() + num2.toIntObj().getInteger();
        }
        sum = num1.toIntObj().getInteger() + num2.toIntObj().getInteger();

        return abs;
    }

    public static void main(String[] args) {

        add(new ShortInteger((short) 12345), new IntInteger(8));
        System.out.println(sum);
    }

    public IntInteger toIntObj() {
        return new IntInteger(this.integer);
    }
}
