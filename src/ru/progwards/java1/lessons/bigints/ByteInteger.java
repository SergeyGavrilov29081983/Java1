package ru.progwards.java1.lessons.bigints;

public class ByteInteger extends AbsInteger<Byte>{

    private byte integer;

    public ByteInteger(byte integer){
        this.integer = integer;
    }

    @Override
    public String toString() {
        return String.valueOf(integer);
    }
}
