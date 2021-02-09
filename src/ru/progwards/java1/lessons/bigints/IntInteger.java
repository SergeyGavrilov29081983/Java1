package ru.progwards.java1.lessons.bigints;

public class IntInteger extends AbsInteger {

    private Integer integer;

    public IntInteger(Integer integer){
        this.integer = integer;
    }

    @Override
    public String toString() {
        return integer.toString();
    }

    public Integer getInteger() {
        return integer;
    }

    @Override
    public IntInteger toIntObj() {
        return new IntInteger(this.getInteger());
    }
}
