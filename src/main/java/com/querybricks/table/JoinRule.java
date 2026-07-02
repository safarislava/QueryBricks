package com.querybricks.table;

import com.querybricks.Bindings;

/**
 * Defines a rule for joining a table with another table (e.g., INNER JOIN, LEFT JOIN).
 */
public interface JoinRule {
    /**
     * Generates the SQL segment representing this join rule for the specified right-side table.
     *
     * @param right the right-side table of the join
     * @return the SQL string segment
     */
    String sql(Table right);

    /**
     * Binds query parameters for the join operation.
     *
     * @param bindings the existing query bindings
     * @param right the right-side table of the join
     * @return the updated bindings containing join parameters
     */
    Bindings bind(Bindings bindings, Table right);
}
