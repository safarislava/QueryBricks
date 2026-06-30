package com.querybricks.expression;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

final class NowTest {
    private final Expression function = new Now();

    @Test
    void testSql() {
        MatcherAssert.assertThat(
            this.function.sql(),
            Matchers.equalTo("NOW()")
        );
    }
}
