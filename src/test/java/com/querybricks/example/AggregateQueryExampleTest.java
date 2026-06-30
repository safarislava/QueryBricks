package com.querybricks.example;

import com.querybricks.column.ColumnsSelection;
import com.querybricks.column.Sum;
import com.querybricks.condition.Equals;
import com.querybricks.query.Query;
import com.querybricks.query.SelectQuery;
import com.querybricks.table.GroupedTable;
import com.querybricks.table.InnerJoin;
import com.querybricks.table.JoinedTable;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

final class AggregateQueryExampleTest {
    private final UsersTable users = new DbUsersTable("users");
    private final OrdersTable orders = new DbOrdersTable("orders");

    private final JoinedTable<UsersTable, OrdersTable> joined = new JoinedTable<>(
        this.users,
        this.orders,
        new InnerJoin(new Equals(this.users.id(), this.orders.userId()))
    );

    private final GroupedTable<JoinedTable<UsersTable, OrdersTable>> grouped = new GroupedTable<>(
        this.joined,
        new ColumnsSelection(this.joined.left().status())
    );

    private final Query query = new SelectQuery(
        new ColumnsSelection(
            this.grouped.origin().left().status(),
            new Sum<>(this.grouped.origin().right().amount())
        ),
        this.grouped
    );

    @Test
    void testSql() {
        MatcherAssert.assertThat(
            this.query.sql(),
            Matchers.equalTo(
                "SELECT users.status, SUM(orders.amount) FROM users "
                + "INNER JOIN orders ON users.id = orders.user_id "
                + "GROUP BY users.status"
            )
        );
    }
}
