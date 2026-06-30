package com.querybricks.query;

import com.querybricks.expression.NumberLiteral;
import com.querybricks.expression.StringLiteral;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

final class InsertRowTest {
    private final InsertRow row = new InsertRow(
        new NumberLiteral(1),
        new StringLiteral("john")
    );

    @Test
    void testSql() {
        MatcherAssert.assertThat(
            this.row.sql(),
            Matchers.equalTo("(1, 'john')")
        );
    }
}
