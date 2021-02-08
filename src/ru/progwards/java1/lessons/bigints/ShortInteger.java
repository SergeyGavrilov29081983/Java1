package ru.progwards.java1.lessons.bigints;

public class ShortInteger extends AbsInteger<Short> {

    private Short integer;

    public ShortInteger(Short integer){
        this.integer = integer;
    }

    @Override
    public String toString() {
        return integer.toString();
    }
}
