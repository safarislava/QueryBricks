package com.querybricks.expression;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

final class BooleanLiteralTest {
    private final Literal literal = new BooleanLiteral(true);

    @Test
    void testSql() {
        MatcherAssert.assertThat(
            this.literal.sql(),
            Matchers.equalTo("true")
        );
    }
}
