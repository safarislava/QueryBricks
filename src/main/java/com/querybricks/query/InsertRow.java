package com.querybricks.query;

import com.querybricks.expression.Expression;
import java.util.List;
import java.util.stream.Collectors;

public class InsertRow {
    private final List<Expression> values;

    public InsertRow(Expression... values) {
        this.values = List.of(values);
    }

    public String sql() {
        return this.values.stream()
            .map(Expression::sql)
            .collect(Collectors.joining(", ", "(", ")"));
    }
}
