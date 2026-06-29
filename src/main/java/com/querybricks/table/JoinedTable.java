package com.querybricks.table;

public final class JoinedTable<L extends Table, R extends Table> implements BinaryTable<L, R>, FilterableTable {
    private final L left;
    private final R right;
    private final JoinRule joinRule;

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
}
