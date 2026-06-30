package com.querybricks.condition;

public class And implements Condition {
    private final Condition left;
    private final Condition right;

    public And(Condition left, Condition right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String sql() {
        return String.format("%s AND %s", this.left.sql(), this.right.sql());
    }
}
