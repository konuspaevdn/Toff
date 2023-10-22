package edu.hw2.Task1;

public record Exponent(double a, double b) implements Expr {
    public Exponent(double e, Expr f) {
        this(e, f.evaluate());
    }

    public Exponent(Expr e, double f) {
        this(e.evaluate(), f);
    }

    public Exponent(Expr e, Expr f) {
        this(e.evaluate(), f.evaluate());
    }

    @Override
    public double evaluate() {
        return Math.pow(a, b);
    }
}
