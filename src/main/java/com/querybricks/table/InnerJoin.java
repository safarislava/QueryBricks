package com.querybricks.table;

import com.querybricks.condition.Condition;

public final class InnerJoin implements JoinRule {
    private final Condition condition;

    public InnerJoin(Condition condition) {
        this.condition = condition;
    }

    @Override
    public String sql(Table right) {
        return String.format("INNER JOIN %s ON %s", right.sql(), this.condition.sql());
    }
}
