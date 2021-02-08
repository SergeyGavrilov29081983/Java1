package ru.progwards.java1.lessons.bigints;

public class IntInteger extends AbsInteger<Integer> {

    private Integer integer;

    public IntInteger(Integer integer){
        this.integer = integer;
    }

    @Override
    public String toString() {
        return integer.toString();
    }
}
