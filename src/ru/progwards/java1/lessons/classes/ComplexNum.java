package ru.progwards.java1.lessons.classes;

public class ComplexNum {

    private int a;
    private int b;

    public ComplexNum(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return a + "+" + b + "i";
    }

    public ComplexNum add(ComplexNum num) {
        a = this.a + num.a;
        b = this.b + num.b;
        return new ComplexNum(a, b);

       // (a + bi) + (c + di) = (a + c) + (b + d)i;
    }


    public ComplexNum sub(ComplexNum num) {

        //(a + bi) - (c + di) = (a - c) + (b - d) i
        return null;
    }

    public ComplexNum mul(ComplexNum num) {

       // (a + bi) * (c + di) = (a * c - b * d) + (b * c + a * d) i
        return null;
    }

    public ComplexNum div(ComplexNum num) {

       // (a + bi) / (c + di) = (a * c + b * d) / (c * c + d * d) + ((b * c - a * d) / (c * c + d * d)) i
        return null;
    }

    public static void main(String[] args) {
        System.out.println(new ComplexNum(1,56).toString());
    }
}
