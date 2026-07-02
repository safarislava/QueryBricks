package com.querybricks.table;

import com.querybricks.Bindings;
import com.querybricks.column.BoundColumn;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a table resulting from joining two tables according to a specific join rule.
 *
 * @param <L> the type of the left table
 * @param <R> the type of the right table
 */
public final class JoinedTable<L extends Table, R extends Table> implements BinaryTable<L, R>, FilterableTable {
    private final L left;
    private final R right;
    private final JoinRule joinRule;

    /**
     * Constructs a new {@code JoinedTable} with the given left-side table, right-side table, and join rule.
     *
     * @param left the left-side table of the join
     * @param right the right-side table of the join
     * @param joinRule the join rule to apply
     */
    public JoinedTable(L left, R right, JoinRule joinRule) {
        this.left = left;
        this.right = right;
        this.joinRule = joinRule;
    }

    @Override
    public L left() {
        return left;
    }

    @Override
    public R right() {
        return right;
    }

    @Override
    public String sql() {
        return String.format("%s %s", left.sql(), joinRule.sql(right));
    }

    @Override
    public Bindings bind(Bindings bindings) {
        return this.joinRule.bind(this.left.bind(bindings), this.right);
    }

    @Override
    public List<BoundColumn<?>> columns() {
        List<BoundColumn<?>> columns = new ArrayList<>();
        columns.addAll(left.columns());
        columns.addAll(right.columns());
        return columns;
    }
}
