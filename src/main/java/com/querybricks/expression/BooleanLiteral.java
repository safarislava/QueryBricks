package com.querybricks.expression;

public class BooleanLiteral implements Literal {
    private final boolean value;

    public BooleanLiteral(boolean value) {
        this.value = value;
    }

    @Override
    public String sql() {
        return String.valueOf(this.value);
    }
}
