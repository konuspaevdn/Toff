package edu.hw2.Task2;

public class Square extends Rectangle {

    public Square() {
        side = 0;
    }

    public Square(double x) {
        super(x);
        side = x;
    }

    private final double side;

    public Square setSide(double side) {
        return new Square(side);
    }
}
