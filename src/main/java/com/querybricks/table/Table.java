package com.querybricks.table;

import com.querybricks.Bindings;
import com.querybricks.QueryPart;
import com.querybricks.column.BoundColumn;
import java.util.List;

/**
 * Represents a database table query part in a query builder.
 */
public interface Table extends QueryPart {
    /**
     * Returns the list of columns bound to this table.
     *
     * @return the list of bound columns
     */
    List<BoundColumn<?>> columns();

    @Override
    default Bindings bind(Bindings bindings) {
        return bindings;
    }
}
