package com.querybricks.expression;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

final class SubtractionTest {
    private final Expression expression = new Subtraction(
        new NumberLiteral(100),
        new NumberLiteral(50)
    );

    @Test
    void testSql() {
        MatcherAssert.assertThat(
            this.expression.sql(),
            Matchers.equalTo("100 - 50")
        );
    }
}
