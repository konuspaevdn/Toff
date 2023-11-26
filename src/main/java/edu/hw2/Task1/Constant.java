package edu.hw2.Task1;

public record Constant(double c) implements Expr {
    public Constant(Expr e) {
        this(e.evaluate());
    }

    @Override
    public double evaluate() {
        return c;
    }
}
