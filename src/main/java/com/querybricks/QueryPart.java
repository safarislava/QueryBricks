package com.querybricks;

/**
 * Represents a part of an SQL query.
 */
public interface QueryPart {
    /**
     * Generates SQL query string representation.
     *
     * @return SQL query string snippet.
     */
    String sql();

    /**
     * Binds query parameters of this part to the provided Bindings collector.
     *
     * @param bindings The bindings' collector.
     * @return The updated Bindings instance containing this query part's parameters.
     */
    Bindings bind(Bindings bindings);
}
