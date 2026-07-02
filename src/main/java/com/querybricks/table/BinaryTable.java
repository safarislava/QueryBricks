package com.querybricks.table;

/**
 * Represents a table resulting from a binary operation (such as a join) between two tables.
 *
 * @param <L> the type of the left table
 * @param <R> the type of the right table
 */
public interface BinaryTable<L extends Table, R extends Table> extends Table {
    /**
     * Returns the left-side table of the binary operation.
     *
     * @return the left table
     */
    L left();

    /**
     * Returns the right-side table of the binary operation.
     *
     * @return the right table
     */
    R right();
}
