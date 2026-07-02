package com.querybricks.expression;

import com.querybricks.Bindings;

/**
 * An expression representing the addition of two sub-expressions.
 */
public class Addition implements BinaryOperator {
    private final Expression left;
    private final Expression right;

    /**
     * Constructs an addition expression with two operands.
     *
     * @param left The left operand
     * @param right The right operand
     */
    public Addition(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String sql() {
        return String.format("%s + %s", this.left.sql(), this.right.sql());
    }

    @Override
    public Bindings bind(Bindings bindings) {
        return this.right.bind(this.left.bind(bindings));
    }
}
