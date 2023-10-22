package edu.hw2.Task1;

public record Negate(double a) implements Expr {
    public Negate(Expr e) {
        this(e.evaluate());
    }

    @Override
    public double evaluate() {
        return -a;
    }
}
