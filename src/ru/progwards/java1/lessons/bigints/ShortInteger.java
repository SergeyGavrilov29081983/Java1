package ru.progwards.java1.lessons.bigints;

public class ShortInteger extends AbsInteger {

    private Short integer;

    public ShortInteger(Short integer){
        super();
        this.integer = integer;
    }

    @Override
    public String toString() {
        return integer.toString();
    }

    @Override
    public IntInteger toIntObj() {
        return new IntInteger((int)this.getInteger());
    }

    public Short getInteger() {
        return integer;
    }
}
