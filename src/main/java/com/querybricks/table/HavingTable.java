package com.querybricks.table;

import com.querybricks.Bindings;
import com.querybricks.condition.Condition;

/**
 * Represents a table with an applied filter condition on grouped rows (HAVING clause).
 *
 * @param <T> the type of the havable table
 */
public final class HavingTable<T extends HavableTable> implements WrappedTable<T> {
    private final T table;
    private final Condition condition;

    /**
     * Constructs a new {@code HavingTable} with the given table and having condition.
     *
     * @param table the table whose grouped rows will be filtered
     * @param condition the condition to apply to grouped rows
     */
    public HavingTable(T table, Condition condition) {
        this.table = table;
        this.condition = condition;
    }

    @Override
    public String sql() {
        return String.format("%s HAVING %s", table.sql(), condition.sql());
    }

    @Override
    public Bindings bind(Bindings bindings) {
        return this.condition.bind(this.table.bind(bindings));
    }

    @Override
    public T origin() {
        return table;
    }
}
