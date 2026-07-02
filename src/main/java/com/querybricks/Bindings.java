package com.querybricks;

/**
 * Accumulates bound query parameters.
 */
public interface Bindings {
    /**
     * Binds a new value to the parameters list.
     *
     * @param value The value to bind.
     * @return New Bindings instance with the value added.
     */
    Bindings with(Object value);
}
