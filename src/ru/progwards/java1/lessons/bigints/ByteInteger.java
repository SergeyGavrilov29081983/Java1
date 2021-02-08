package ru.progwards.java1.lessons.bigints;

public class ByteInteger extends AbsInteger {

    private byte integer;

    public ByteInteger(byte integer){
        this.integer = integer;
    }

    @Override
    public String toString() {
        return String.valueOf(integer);
    }

    public byte getInteger() {
        return integer;
    }

    @Override
    public IntInteger toIntObj() {
        return new IntInteger((int)this.getInteger());
    }

    public void setInteger(byte integer) {
        this.integer = integer;
    }
}
