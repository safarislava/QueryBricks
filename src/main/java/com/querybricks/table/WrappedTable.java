package com.querybricks.table;

import com.querybricks.column.BoundColumn;
import java.util.List;

/**
 * A decorator/wrapper interface for a {@link Table}.
 *
 * @param <T> the type of the wrapped table
 */
public interface WrappedTable<T extends Table> extends Table {
    /**
     * Returns the original wrapped table.
     *
     * @return the wrapped table
     */
    T origin();

    @Override
    default List<BoundColumn<?>> columns() {
        return this.origin().columns();
    }
}
