package com.querybricks.expression;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

final class StringLiteralTest {
    private final Literal literal = new StringLiteral("hello");

    @Test
    void testSql() {
        MatcherAssert.assertThat(
            this.literal.sql(),
            Matchers.equalTo("'hello'")
        );
    }
}
