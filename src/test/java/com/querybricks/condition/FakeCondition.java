package com.querybricks.condition;

public final class FakeCondition implements Condition {
    private final String expression;

    public FakeCondition(String expression) {
        this.expression = expression;
    }

    @Override
    public String sql() {
        return this.expression;
    }
}
