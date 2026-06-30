package com.querybricks.expression;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

final class AdditionTest {
    private final Expression expression = new Addition(
        new NumberLiteral(5),
        new NumberLiteral(10)
    );

    @Test
    void testSql() {
        MatcherAssert.assertThat(
            this.expression.sql(),
            Matchers.equalTo("5 + 10")
        );
    }
}
