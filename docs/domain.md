```mermaid
classDiagram
    class Table {
        <<interface>>
    }

    class FilterableTable {
        <<interface>>
    }
    FilterableTable ..|> Table
```

```mermaid
classDiagram
    class FilterableTable {
        <<interface>>
    }

    class JoinRule {
        <<interface>>
    }

    class InnerJoin {
        -on Condition
    }
    InnerJoin ..|> JoinRule

    class LeftJoin {
        -on Condition
    }
    LeftJoin ..|> JoinRule

    class JoinedTable~L, R~ {
        -left L
        -right R
        -rule JoinRule
        + left() L
        + right() R
    }
    JoinedTable ..|> FilterableTable
    JoinedTable --> JoinRule
```

```mermaid
classDiagram
    class Table {
        <<interface>>
    }

    class FilterableTable {
        <<interface>>
    }
    FilterableTable ..|> Table

    class Condition {
        <<interface>>
    }

    class FilteredTable~T extends FilterableTable~ {
        -origin T
        -condition Condition
        + origin() T
    }
    FilteredTable ..|> Table
    FilteredTable --> Condition

    class SubqueryTable~T extends FilterableTable~ {
        -query Query
        + query() Query
    }
    SubqueryTable ..|> FilterableTable
```

```mermaid
classDiagram
    class Table {
        <<interface>>
    }

    class HavableTable {
        <<interface>>
    }
    HavableTable ..|> Table

    class GroupedTable~T extends Table~ {
        -origin T
        -by List~Column~
        + origin() T
    }
    GroupedTable ..|> HavableTable

    class HavingTable~T extends HavableTable~ {
        -origin T
        -condition Condition
        + origin() T
    }
    HavingTable ..|> Table

    class LimitedTable~T extends Table~ {
        -origin T
        -limit int
        + origin() T
    }
    LimitedTable ..|> Table

    class OffsetTable~T extends Table~ {
        -origin T
        -offset int
        + origin() T
    }
    OffsetTable ..|> Table

    class DistinctTable~T extends Table~ {
        -origin T
        + origin() T
    }
    DistinctTable ..|> Table
```

```mermaid
classDiagram
    class Expression {
        <<interface>>
    }

    class Column {
        <<interface>>
    }
    Column ..|> Expression

    class DbColumn {
        -tableAlias String
        -name String
    }
    DbColumn ..|> Column

    class AggregatedColumn {
        <<interface>>
    }
    AggregatedColumn ..|> Column

    class Sum {
        -column Column
    }
    Sum ..|> AggregatedColumn

    class Count {
        -column Column
    }
    Count ..|> AggregatedColumn

    class Avg {
        -column Column
    }
    Avg ..|> AggregatedColumn

    class AliasedColumn {
        -origin Column
        -alias String
    }
    AliasedColumn ..|> Column

    class Columns {
        <<interface>>
    }

    class AllColumns
    AllColumns ..|> Columns

    class ColumnsSelection {
        -columns List~Column~
    }
    ColumnsSelection ..|> Columns
```

```mermaid
classDiagram
    %% подумать над механизмом кодирования в SQL
    %% сейчас я думаю что нужен интерфейс QueryPart : content() String, которые будет реализовываться всем попало
    %% ну или просто оверайдить toString()
    class Query {
        <<interface>>
    }

    class SelectQuery {
        -columns Columns
        -table Table
    }
    SelectQuery ..|> Query
    
    %% алиасы для таблиц
```

```mermaid
classDiagram
    class Expression {
        <<interface>>
    }

    class Literal {
        <<interface>>
    }
    Literal ..|> Expression

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

    class BinaryOperator {
        <<interface>>
    }
    BinaryOperator ..|> Value

    class Addition {
        -left Value
        -right Value
    }
    Addition ..|> BinaryOperator
    Addition --> Value

    class Subtraction {
        -left Value
        -right Value
    }
    Subtraction ..|> BinaryOperator
    Subtraction --> Value
```

```mermaid
classDiagram
    class Table {
        <<interface>>
    }
    
    class Query {
        <<interface>>
    }

    class Expression {
        <<interface>>
    }

    class Column {
        <<interface>>
    }

    class ColumnValue {
        -column Column
        -value Expression
    }
    ColumnValue --> Column
    ColumnValue --> Expression

    class InsertRow {
        -values List~ColumnValue~
    }
    InsertRow --> ColumnValue

    class InsertQuery {
        -table Table
        -rows List~InsertRow~
    }
    InsertQuery ..|> Query
    InsertQuery --> Table
    InsertQuery --> InsertRow
```

```mermaid
classDiagram
    class Table {
        <<interface>>
    }

    class Query {
        <<interface>>
    }

    class Condition {
        <<interface>>
    }

    class ColumnValue {
        -column Column
        -value Expression
    }

    class UpdateQuery {
        -table Table
        -assignments List~ColumnValue~
        -condition Condition
    }
    UpdateQuery ..|> Query
    UpdateQuery --> Table
    UpdateQuery --> ColumnValue
    UpdateQuery --> Condition
```
    
```mermaid
classDiagram
    class Table {
        <<interface>>
    }

    class Query {
        <<interface>>
    }

    class Condition {
        <<interface>>
    }

    class DeleteQuery {
        -table Table
        -condition Condition
    }
    DeleteQuery ..|> Query
    DeleteQuery --> Table
    DeleteQuery --> Condition
```

# Пример запроса кода
## Схема данных

```java
interface UsersTable extends FilterableTable {
    Column id();
    Column username();
    Column status();
    Column createdAt();
}

interface OrdersTable extends FilterableTable {
    Column userId();
    Column amount();
}

class DbUsersTable implements UsersTable {
    private final String name;
    DbUsersTable(String name) { this.name = name; }
    public Column id()        { return new DbColumn(name, "id"); }
    public Column username()  { return new DbColumn(name, "username"); }
    public Column status()    { return new DbColumn(name, "status"); }
    public Column createdAt() { return new DbColumn(name, "created_at"); }
}

class DbOrdersTable implements OrdersTable {
    private final String name;
    DbOrdersTable(String name) { this.name = name; }
    public Column userId() { return new DbColumn(name, "user_id"); }
    public Column amount() { return new DbColumn(name, "amount"); }
}
```

## Select query
```java
UsersTable  users  = new DbUsersTable("users");
OrdersTable orders = new DbOrdersTable("orders");

var joined = new JoinedTable<>(
    users,
    orders,
    new InnerJoin(new Equals(users.id(), orders.userId()))
);

var filtered = new FilteredTable<>(
    joined,
    new Equals(joined.left().status(), new StringLiteral("active"))
);

var limited = new LimitedTable<>(filtered, 10);

Query query = new SelectQuery(
    new ColumnsSelection(
        limited.origin().origin().left().id(),
        limited.origin().origin().left().username(),
        limited.origin().origin().right().amount()
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
UsersTable  users  = new DbUsersTable("users");
OrdersTable orders = new DbOrdersTable("orders");

var joined = new JoinedTable<>(
    users,
    orders,
    new InnerJoin(new Equals(users.id(), orders.userId()))
);

var grouped = new GroupedTable<>(joined, joined.left().status());

Query query = new SelectQuery(
    new ColumnsSelection(
        grouped.origin().left().status(),
        new AliasedColumn(new Sum(grouped.origin().right().amount()), "total_amount")
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
UsersTable users = new DbUsersTable("users");

Query insert = new InsertQuery(
    users,
    List.of(
        new InsertRow(
            new ColumnValue(users.id(),        new NumberLiteral(1)),
            new ColumnValue(users.username(),  new StringLiteral("john")),
            new ColumnValue(users.status(),    new StringLiteral("active")),
            new ColumnValue(users.createdAt(), new Now())
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
UsersTable users = new DbUsersTable("users");

Query update = new UpdateQuery(
    users,
    List.of(
        new ColumnValue(users.status(), new StringLiteral("inactive"))
    ),
    new Equals(users.id(), new NumberLiteral(1))
);
```

### Итоговый SQL
```sql
UPDATE users SET status = 'inactive' WHERE id = 1
```

## Delete query

```java
UsersTable users = new DbUsersTable("users");

Query delete = new DeleteQuery(
    users,
    new Equals(users.id(), new NumberLiteral(1))
);
```

### Итоговый SQL
```sql
DELETE FROM users WHERE id = 1
```
