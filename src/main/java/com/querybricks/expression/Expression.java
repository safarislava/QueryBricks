package com.querybricks.expression;

import com.querybricks.Bindings;
import com.querybricks.QueryPart;

/**
 * Represents a SQL expression component.
 */
public interface Expression extends QueryPart {
    @Override
    default Bindings bind(Bindings bindings) {
        return bindings;
    }
}
