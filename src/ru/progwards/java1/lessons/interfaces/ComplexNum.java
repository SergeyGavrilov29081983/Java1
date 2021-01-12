package ru.progwards.java1.lessons.interfaces;

public class ComplexNum {

    private int a;
    private int b;

    public ComplexNum(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public static void main(String[] args) {
        System.out.println(new ComplexNum(1, 56).toString());
    }

    @Override
    public String toString() {
        return a + "+" + b + "i";
    }

    public ComplexNum add(ComplexNum num) {
        return new ComplexNum(this.a + num.a, this.b + num.b);
    }

    public ComplexNum sub(ComplexNum num) {
        return new ComplexNum(this.a - num.a, this.b - num.b);
    }

    public ComplexNum mul(ComplexNum num) {
        return new ComplexNum(this.a * num.a - this.b * num.b, this.b * num.a + this.a * num.b);
    }

    public ComplexNum div(ComplexNum num) {
        return new ComplexNum(
                (this.a * num.a + this.b * num.b) / (num.a * num.a + num.b * num.b),
                (this.b * num.a - this.a * num.b) / (num.a * num.a + num.b * num.b)
        );
    }
}
