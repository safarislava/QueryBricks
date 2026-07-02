package com.querybricks.expression;

/**
 * Represents the current date and time function (NOW()).
 */
public class Now implements Function {
    @Override
    public String sql() {
        return "NOW()";
    }
}
