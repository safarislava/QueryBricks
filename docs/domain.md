```mermaid
classDiagram
    %% cхему можно кодогенерировать
    class Table~C~ {
        <<interface>>
        + schema() C
    }

    class FilterableTable~C~ {
        <<interface>>
    }
    FilterableTable ..|> Table
    
    class DbTable~C~ {
        -name String
        -schema C
    }
    DbTable ..|> FilterableTable
```

```mermaid
classDiagram
    class FilterableTable~C~ {
        <<interface>>
    }

    class JoinRule {
        <<interface>>
    }

    class InnerJoin
    InnerJoin ..|> JoinRule
    class LeftJoin
    LeftJoin ..|> JoinRule

    class JoinedSchema~LC, RC~ {
        + left() LC
        + right() RC
    }

    class JoinedTable~LC, RC~ {
        -left Table~LC~
        -right Table~RC~
        -rule JoinRule
    }
    JoinedTable ..|> FilterableTable
    JoinedTable --> JoinRule
    JoinedTable --> JoinedSchema
```

```mermaid
classDiagram
    class FilterableTable~C~ {
        <<interface>>
    }

    class Condition {
        <<interface>>
    }

    class Equals
    Equals ..|> Condition
    class And
    And ..|> Condition

    class FilteredTable~C~ {
        -origin FilterableTable~C~
        -condition Condition
    }
    FilteredTable ..|> FilterableTable
    FilteredTable --> Condition

    class SubqueryTable~C~ {
        -query Query~C~
    }
    SubqueryTable ..|> FilterableTable
```

```mermaid
classDiagram
    class Table~C~ {
        <<interface>>
    }

    class Condition {
        <<interface>>
    }

    class HavableTable~C~ {
        <<interface>>
    }
    HavableTable ..|> Table

    class GroupedTable~C~
    GroupedTable ..|> HavableTable

    class HavingGroupTable~C~ {
        -origin HavableTable~C~
        -condition Condition
    }
    HavingGroupTable --> HavableTable
    HavingGroupTable ..|> Table
    HavingGroupTable --> Condition

    class LimitedTable~C~
    LimitedTable ..|> Table

    class OffsetTable~C~
    OffsetTable ..|> Table

    class DistinctTable~C~
    DistinctTable ..|> Table
```

```mermaid
classDiagram
    class Column {
        <<interface>>
    }

    class DbColumn
    DbColumn ..|> Column

    class AggregatedColumn
    AggregatedColumn ..|> Column

    class AliasedColumn {
        -origin Column
        -alias String
    }
    AliasedColumn ..|> Column
    AliasedColumn --> Column

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
```

```mermaid
classDiagram
    class Table~C~ {
        <<interface>>
    }

    class Columns {
        <<interface>>
    }

    class Query~C~ {
        <<interface>>
    }

    class SelectDbQuery~C~ {
        -columns Columns
        -table Table~C~
    }
    SelectDbQuery ..|> Query
    SelectDbQuery --> Columns
    SelectDbQuery --> Table

    %% алиасы для таблиц
```

```mermaid
classDiagram
    class Value {
        <<interface>>
    }
    
    class Literal {
        <<interface>>
    }
    Literal ..|> Value

    class StringLiteral {
        -value String
    }
    StringLiteral ..|> Literal

    class NumberLiteral {
        -value Number
    }
    NumberLiteral ..|> Literal

    class BooleanLiteral {
        -value boolean
    }
    BooleanLiteral ..|> Literal

    class NullLiteral
    NullLiteral ..|> Literal

    class Function {
        <<interface>>
    }
    Function ..|> Value

    class Now
    Now ..|> Function

    class Addition {
        -left Value
        -right Value
    }
    Addition ..|> Function
    Addition --> Value

    class Subtraction {
        -left Value
        -right Value
    }
    Subtraction ..|> Function
    Subtraction --> Value
```

```mermaid
classDiagram
    class Table~C~ {
        <<interface>>
    }
    
    class Query~C~ {
        <<interface>>
    }

    class Value {
        <<interface>>
    }

    class Column {
        <<interface>>
    }

    class ColumnValue {
        -column Column
        -value Value
    }
    ColumnValue --> Column
    ColumnValue --> Value

    class InsertRow {
        -values List~ColumnValue~
    }
    InsertRow --> ColumnValue

    class InsertDbQuery~C~ {
        -table Table~C~
        -rows List~InsertRow~
    }
    InsertDbQuery ..|> Query
    InsertDbQuery --> Table
    InsertDbQuery --> InsertRow
```

```mermaid
classDiagram
    class Table~C~ {
        <<interface>>
    }

    class Query~C~ {
        <<interface>>
    }

    class Condition {
        <<interface>>
    }

    class ColumnValue {
        -column Column
        -value Value
    }

    class UpdateDbQuery~C~ {
        -table Table~C~
        -assignments List~ColumnValue~
        -condition Condition
    }
    UpdateDbQuery ..|> Query
    UpdateDbQuery --> Table
    UpdateDbQuery --> ColumnValue
    UpdateDbQuery --> Condition
```
    
```mermaid
classDiagram
    class Table~C~ {
        <<interface>>
    }

    class Query~C~ {
        <<interface>>
    }

    class Condition {
        <<interface>>
    }

    class DeleteDbQuery~C~ {
        -table Table~C~
        -condition Condition
    }
    DeleteDbQuery ..|> Query
    DeleteDbQuery --> Table
    DeleteDbQuery --> Condition
```

# Пример запроса кода
## Схема данных

<!-- Это компросисс на который прошлось пойти ради статической типизации. -->
```java
class UsersSchema {
    final Column id        = new DbColumn("id");
    final Column username  = new DbColumn("username");
    final Column status    = new DbColumn("status");
    final Column createdAt = new DbColumn("created_at");
}

class OrdersSchema {
    final Column userId = new DbColumn("user_id");
    final Column amount = new DbColumn("amount");
}
```

## Select query
```java
Table<UsersSchema>  users  = new DbTable<>("users",  new UsersSchema());
Table<OrdersSchema> orders = new DbTable<>("orders", new OrdersSchema());

Table<JoinedScema<UsersSchema, OrdersSchema>> joined = new JoinedTable<>(
    users,
    orders,
    new InnerJoin(users.schema().id, orders.schema().userId)
);

Table<JoinedScema<UsersSchema, OrdersSchema>> filtered = new ConditionFiltedTable<>(
    joined,
    new Equals(joined.schema().left().status, new StringLiteral("active"))
);

Table<JoinedScema<UsersSchema, OrdersSchema>> limited = new LimitedTable<>(filtered, 10);

Query<?> query = new SelectDbQuery<>(
    new ColumnsSelection(
        joined.schema().left().id,
        joined.schema().left().username,
        joined.schema().right().amount
    ),
    limited
);
```

### Итоговый SQL
```sql
SELECT users.id, users.username, orders.amount AS total
FROM users
JOIN orders ON users.id = orders.user_id
WHERE users.status = 'active'
LIMIT 10
```

## Aggregate query

```java
Table<UsersSchema>  users  = new DbTable<>("users",  new UsersSchema());
Table<OrdersSchema> orders = new DbTable<>("orders", new OrdersSchema());

Table<JoinedScema<UsersSchema, OrdersSchema>> joined = new JoinedTable<>(
        users,
        orders,
        new InnerJoin(users.schema().id, orders.schema().userId)
);

Table<JoinedSchema<UsersSchema, OrdersSchema>> grouped = new GroupedTable<>(
    joined,
    joined.schema().left().status
);

Query<?> query = new SelectDbQuery<>(
    new ColumnsSelection(
        joined.schema().left().status,
        new AliasedColumn(
                new AggregatedColumn("SUM", joined.schema().right().amount), 
                "total_amount"
        )
    ),
    grouped
);
```

### Итоговый SQL
```sql
SELECT users.status, SUM(orders.amount) AS total_amount
FROM users
JOIN orders ON users.id = orders.user_id
GROUP BY users.status
```

## Insert query

```java
DbTable<UsersSchema> users = new DbTable<>("users", new UsersSchema());

Query<?> insert = new InsertDbQuery<>(
    users,
    List.of(
        new InsertRow(
            new ColumnValue(users.schema().id,        new NumberLiteral(1)),
            new ColumnValue(users.schema().username,  new StringLiteral("john")),
            new ColumnValue(users.schema().status,    new StringLiteral("active")),
            new ColumnValue(users.schema().createdAt, new Now())
        )
    )
);
```

### Итоговый SQL
```sql
INSERT INTO users (id, username, status, created_at) VALUES (1, 'john', 'active', NOW())
```

## Update query

```java
DbTable<UsersSchema> users = new DbTable<>("users", new UsersSchema());

Query<?> update = new UpdateDbQuery<>(
    users,
    List.of(
        new ColumnValue(users.schema().status, new StringLiteral("inactive"))
    ),
    new Equals(users.schema().id, new NumberLiteral(1))
);
```

### Итоговый SQL
```sql
UPDATE users SET status = 'inactive' WHERE id = 1
```

## Delete query

```java
DbTable<UsersSchema> users = new DbTable<>("users", new UsersSchema());

Query<?> delete = new DeleteDbQuery<>(
    users,
    new Equals(users.schema().id, new NumberLiteral(1))
);
```

### Итоговый SQL
```sql
DELETE FROM users WHERE id = 1
```
