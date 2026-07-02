package com.querybricks.table;

import com.querybricks.Bindings;
import com.querybricks.query.Query;

/**
 * A wrapper representing a subquery treated as a table.
 *
 * @param <T> the type of the underlying filterable table
 */
public final class SubqueryTable<T extends FilterableTable> implements WrappedTable<T>, FilterableTable {
    private final T table;
    private final Query query;

    /**
     * Constructs a new {@code SubqueryTable} wrapping the given table and query.
     *
     * @param table the underlying table
     * @param query the query that forms the subquery
     */
    public SubqueryTable(T table, Query query) {
        this.table = table;
        this.query = query;
    }

    @Override
    public T origin() {
        return this.table;
    }

    @Override
    public String sql() {
        return String.format("(%s)", this.query.sql());
    }

    @Override
    public Bindings bind(Bindings bindings) {
        return this.query.bind(bindings);
    }
}
