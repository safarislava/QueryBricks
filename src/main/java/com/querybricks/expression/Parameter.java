package com.querybricks.expression;

import com.querybricks.Bindings;
import com.querybricks.column.Column;

/**
 * Represents a typed query parameter that binds a value.
 *
 * @param <T> The type of the parameter value
 */
public final class Parameter<T> implements Expression, Column<T> {
    private final T value;

    /**
     * Constructs a parameter with the specified value.
     *
     * @param value The value to bind
     */
    public Parameter(T value) {
        this.value = value;
    }

    @Override
    public String sql() {
        return "?";
    }

    @Override
    public Bindings bind(Bindings bindings) {
        return bindings.with(this.value);
    }
}
