package com.querybricks.condition;

import com.querybricks.QueryPart;

public class Equals implements Condition {
    private final QueryPart left;
    private final QueryPart right;

    public Equals(QueryPart left, QueryPart right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String sql() {
        return String.format("%s = %s", this.left.sql(), this.right.sql());
    }
}
