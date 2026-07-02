package com.querybricks.expression;

import com.querybricks.Bindings;

/**
 * An expression representing the subtraction of two sub-expressions.
 */
public final class Subtraction implements BinaryOperator {
    private final Expression left;
    private final Expression right;

    /**
     * Constructs a subtraction expression with two operands.
     *
     * @param left The left operand
     * @param right The right operand
     */
    public Subtraction(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String sql() {
        return String.format("%s - %s", this.left.sql(), this.right.sql());
    }

    @Override
    public Bindings bind(Bindings bindings) {
        return this.right.bind(this.left.bind(bindings));
    }
}
