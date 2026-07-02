package com.querybricks.table;

import com.querybricks.Bindings;
import com.querybricks.condition.Condition;

/**
 * Represents an inner join rule based on a specified condition.
 */
public final class InnerJoin implements JoinRule {
    private final Condition condition;

    /**
     * Constructs an {@code InnerJoin} with the specified join condition.
     *
     * @param condition the condition that links the joined tables
     */
    public InnerJoin(Condition condition) {
        this.condition = condition;
    }

    @Override
    public String sql(Table right) {
        return String.format("INNER JOIN %s ON %s", right.sql(), this.condition.sql());
    }

    @Override
    public Bindings bind(Bindings bindings, Table right) {
        return this.condition.bind(right.bind(bindings));
    }
}
