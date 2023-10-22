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

    private double width;
    private double height;

    public Rectangle setWidth(double width) {
        this.width = width;
        return this;
    }

    public Rectangle setHeight(double height) {
        this.height = height;
        return this;
    }

    public Rectangle setWidthHeight(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public final double area() {
        return width * height;
    }
}


