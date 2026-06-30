package com.querybricks.expression;

public class Addition implements BinaryOperator {
    private final Expression left;
    private final Expression right;

    public Addition(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String sql() {
        return String.format("%s + %s", this.left.sql(), this.right.sql());
    }
}
