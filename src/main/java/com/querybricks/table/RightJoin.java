package com.querybricks.table;

import com.querybricks.Bindings;
import com.querybricks.condition.Condition;

/**
 * Represents a right outer join rule based on a specified condition.
 */
public final class RightJoin implements JoinRule {
    private final Condition condition;

    /**
     * Constructs a {@code RightJoin} with the specified join condition.
     *
     * @param condition the condition that links the joined tables
     */
    public RightJoin(Condition condition) {
        this.condition = condition;
    }

    @Override
    public String sql(Table right) {
        return String.format("RIGHT JOIN %s ON %s", right.sql(), this.condition.sql());
    }

    @Override
    public Bindings bind(Bindings bindings, Table right) {
        return this.condition.bind(right.bind(bindings));
    }
}
