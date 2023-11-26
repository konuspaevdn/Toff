package edu.hw2.Task1;

public record Exponent(double a, double b) implements Expr {

    public Exponent(Expr e, Expr f) {
        this(e.evaluate(), f.evaluate());
    }

    @Override
    public double evaluate() {
        return Math.pow(a, b);
    }
}
