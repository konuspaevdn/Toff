package edu.hw2.Task1;

public record Addition(double a, double b) implements Expr {
    public Addition(double e, Expr f) {
        this(e, f.evaluate());
    }

    public Addition(Expr e, double f) {
        this(e.evaluate(), f);
    }

    public Addition(Expr e, Expr f) {
        this(e.evaluate(), f.evaluate());
    }

    @Override
    public double evaluate() {
        return a + b;
    }
}
