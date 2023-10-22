package edu.hw2.Task1;

public record Multiplication(double a, double b) implements Expr {
    public Multiplication(double e, Expr f) {
        this(e, f.evaluate());
    }

    public Multiplication(Expr e, double f) {
        this(e.evaluate(), f);
    }

    public Multiplication(Expr e, Expr f) {
        this(e.evaluate(), f.evaluate());
    }

    @Override
    public double evaluate() {
        return a * b;
    }
}
