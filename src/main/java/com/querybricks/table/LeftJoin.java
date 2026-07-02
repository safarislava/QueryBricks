package com.querybricks.table;

import com.querybricks.Bindings;
import com.querybricks.condition.Condition;

/**
 * Represents a left outer join rule based on a specified condition.
 */
public final class LeftJoin implements JoinRule {
    private final Condition condition;

    /**
     * Constructs a {@code LeftJoin} with the specified join condition.
     *
     * @param condition the condition that links the joined tables
     */
    public LeftJoin(Condition condition) {
        this.condition = condition;
    }

    @Override
    public String sql(Table right) {
        return String.format("LEFT JOIN %s ON %s", right.sql(), this.condition.sql());
    }

    @Override
    public Bindings bind(Bindings bindings, Table right) {
        return this.condition.bind(right.bind(bindings));
    }
}
