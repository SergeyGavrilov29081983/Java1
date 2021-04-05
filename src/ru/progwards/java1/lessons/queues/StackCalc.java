package ru.progwards.java1.lessons.queues;

import java.util.Stack;

public class StackCalc {

    Stack<Double> stack = new Stack<>();

    public void push(double value) {
        stack.push(value);
    }

    public double pop() {
        return stack.pop();
    }

    public void add() {
        Double firstNum = stack.pop();
        Double secondNum = stack.pop();
        stack.push(firstNum + secondNum);
    }

    public void sub() {
        Double firstNum = stack.pop();
        Double secondNum = stack.pop();
        stack.push(secondNum - firstNum);
    }


    public void mul() {
        Double firstNum = stack.pop();
        Double secondNum = stack.pop();
        stack.push(firstNum * secondNum);
    }

    public void div() {
        Double firstNum = stack.pop();
        Double secondNum = stack.pop();
        stack.push(firstNum / secondNum);
    }
}
