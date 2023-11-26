package edu.hw2.Task1;

public record Addition(double a, double b) implements Expr {

    public Addition(Expr e, Expr f) {
        this(e.evaluate(), f.evaluate());
    }

    @Override
    public double evaluate() {
        return a + b;
    }
}
