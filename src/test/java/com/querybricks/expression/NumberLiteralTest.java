package com.querybricks.expression;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

final class NumberLiteralTest {
    private final Literal literal = new NumberLiteral(42);

    @Test
    void testSql() {
        MatcherAssert.assertThat(
            this.literal.sql(),
            Matchers.equalTo("42")
        );
    }
}
