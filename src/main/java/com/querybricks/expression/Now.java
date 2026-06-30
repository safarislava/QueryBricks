package com.querybricks.expression;

public class Now implements Function {
    @Override
    public String sql() {
        return "NOW()";
    }
}
