```mermaid
classDiagram
    class Scheme~C~ {
        <<interface>>
        + columns() C
    }

    class Table {
        <<interface>>
    }
    Table ..|> Scheme

    class DbTable
    DbTable ..|> Table

    class JoinRule {
        <<interface>>
    }

    class InnerJoin
    InnerJoin ..|> JoinRule
    class LeftJoin
    LeftJoin ..|> JoinRule

    class JoiningScheme~LC RC~ {
        -left Scheme~LC~
        -right Scheme~RC~
        -rule JoinRule
    }
    JoiningScheme --> JoinRule
    JoiningScheme ..|> Scheme

    class Condition {
        <<interface>>
    }

    class Equals
    Equals ..|> Condition
    class And
    And ..|> Condition

    class WhereFilter~C~ {
        -origin Scheme~C~
        -condition Condition
    }
    %% надо сделать так, чтобы WHERE нельзя было писать после GROUP BY
    WhereFilter ..|> Scheme
    WhereFilter --> Condition

    class Grouping~C~
    Grouping ..|> Scheme

    class Limiting~C~
    Limiting ..|> Scheme

    class Offset~C~
    Offset ..|> Scheme

    class Distinct~C~
    Distinct ..|> Scheme

    %% спроектировать HAVING

    class Column {
        <<interface>>
    }

    class DbColumn
    DbColumn ..|> Column

    class AggregatedColumn
    AggregatedColumn ..|> Column

    class Columns {
        <<interface>>
    }

    class AllColumns
    AllColumns ..|> Columns

    class ColumnsSelection {
        -columns List~Column~
    }
    ColumnsSelection ..|> Columns
    ColumnsSelection --> Column

    class Subquery~C~ {
        -query Query~C~
    }
    Subquery ..|> Scheme

    class Query~C~ {
        <<interface>>
    }

    class DbQuery~C~ {
        -columns Columns
        -mapping Scheme~C~
    }
    DbQuery ..|> Query
    DbQuery --> Columns

    %% алиасы для таблиц и столбцов
```

# Пример запроса кода
## Схема данных
```java
class UsersColumns {
    final Column id       = new DbColumn("id");
    final Column username = new DbColumn("username");
    final Column status   = new DbColumn("status");
}

class OrdersColumns {
    final Column userId = new DbColumn("user_id");
    final Column amount = new DbColumn("amount");
}
```

```java
Scheme<UsersColumns>  users  = new DbScheme<>(new DbTable("users"),  new UsersColumns());
Scheme<OrdersColumns> orders = new DbScheme<>(new DbTable("orders"), new OrdersColumns());

JoiningScheme<UsersColumns, OrdersColumns> joined = new JoiningScheme<>(
    users,
    orders,
    new InnerJoin(users.columns().id, orders.columns().userId)
);

Scheme<JoinedColumns<UsersColumns, OrdersColumns>> filtered = new WhereFilter<>(
    joined,
    new Equals(joined.columns().left().status, new Literal("active"))
);

Scheme<JoinedColumns<UsersColumns, OrdersColumns>> limited = new Limiting<>(filtered, 10);

Query<?> query = new DbQuery<>(
    new ColumnsSelection(
        joined.columns().left().id,
        joined.columns().left().username,
        joined.columns().right().amount
    ),
    limited
);
```

## Итоговый SQL
```sql
SELECT users.id, users.username, orders.amount
FROM users
JOIN orders ON users.id = orders.user_id
WHERE users.status = 'active'
LIMIT 10
```