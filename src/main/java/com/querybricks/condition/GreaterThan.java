package com.querybricks.condition;

import com.querybricks.Bindings;
import com.querybricks.QueryPart;

/**
 * A condition representing the greater than (&gt;) comparison between two query parts.
 *
 * @since 1.0.0
 */
public class GreaterThan implements Condition {
    private final QueryPart left;
    private final QueryPart right;

    /**
     * Constructs a greater than condition comparing two query parts.
     *
     * @param left the left-hand side operand
     * @param right the right-hand side operand
     */
    public GreaterThan(QueryPart left, QueryPart right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String sql() {
        return String.format("%s > %s", this.left.sql(), this.right.sql());
    }

    @Override
    public Bindings bind(Bindings bindings) {
        return this.right.bind(this.left.bind(bindings));
    }
}
