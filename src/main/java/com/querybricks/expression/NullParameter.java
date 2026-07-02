package com.querybricks.expression;

import com.querybricks.Bindings;
import com.querybricks.column.Column;

/**
 * Represents a parameter with a null value.
 */
public final class NullParameter implements Expression, Column<Object> {
    @Override
    public String sql() {
        return "NULL";
    }

    @Override
    public Bindings bind(Bindings bindings) {
        return bindings;
    }
}
