package edu.hw2.Task2;

public class Rectangle {

    public Rectangle() {
        width = 0;
        height = 0;
    }

    public Rectangle(double x) {
        width = x;
        height = x;
    }

    public Rectangle(double x, double y) {
        width = x;
        height = y;
    }

    private final double width;
    private final double height;

    public Rectangle setWidth(double width) {
        return new Rectangle(width, this.height);
    }

    public Rectangle setHeight(double height) {
        return new Rectangle(this.width, height);
    }

    public Rectangle setWidthHeight(double width, double height) {
        return new Rectangle(this.width, this.height);
    }

    public final double area() {
        return width * height;
    }
}


