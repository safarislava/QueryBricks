package com.querybricks.expression;

public class NullLiteral implements Literal {
    @Override
    public String sql() {
        return "NULL";
    }
}
