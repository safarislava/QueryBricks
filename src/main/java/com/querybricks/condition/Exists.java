package com.querybricks.condition;

import com.querybricks.Bindings;
import com.querybricks.query.Query;

/**
 * A condition representing the EXISTS clause with a subquery.
 *
 * @since 1.0.0
 */
public final class Exists implements Condition {
    private final Query query;

    /**
     * Constructs an EXISTS condition with the specified subquery.
     *
     * @param query the subquery to evaluate for existence
     */
    public Exists(Query query) {
        this.query = query;
    }

    @Override
    public String sql() {
        return String.format("EXISTS (%s)", this.query.sql());
    }

    @Override
    public Bindings bind(Bindings bindings) {
        return this.query.bind(bindings);
    }
}
