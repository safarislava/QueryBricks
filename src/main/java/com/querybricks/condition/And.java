package com.querybricks.condition;

import com.querybricks.Bindings;

/**
 * A condition representing the logical conjunction (AND) of two other conditions.
 *
 * @since 1.0.0
 */
public final class And implements Condition {
    private final Condition left;
    private final Condition right;

    /**
     * Constructs an AND condition combining two other conditions.
     *
     * @param left the left-hand side condition
     * @param right the right-hand side condition
     */
    public And(Condition left, Condition right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String sql() {
        return String.format("%s AND %s", this.left.sql(), this.right.sql());
    }

    @Override
    public Bindings bind(Bindings bindings) {
        return this.right.bind(this.left.bind(bindings));
    }
}
