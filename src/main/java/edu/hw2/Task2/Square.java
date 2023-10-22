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

    @Override
    public Rectangle setWidth(double width) {
        return new Rectangle(width, side);
    }

    @Override
    public Rectangle setHeight(double height) {
        return new Rectangle(side, height);
    }

    @Override
    public Rectangle setWidthHeight(double width, double height) {
        return new Rectangle(width, height);
    }

}
