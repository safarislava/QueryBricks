package com.querybricks.example;

import com.querybricks.column.BindedColumn;
import com.querybricks.column.RawColumn;
import com.querybricks.column.TableColumn;

public class DbUsersTable implements UsersTable {
    private final String name;

    public DbUsersTable(String name) {
        this.name = name;
    }

    @Override
    public String sql() {
        return this.name;
    }

    @Override
    public BindedColumn<Long> id() {
        return new TableColumn<>(this, new RawColumn<>("id"));
    }

    @Override
    public BindedColumn<String> username() {
        return new TableColumn<>(this, new RawColumn<>("username"));
    }

    @Override
    public BindedColumn<String> status() {
        return new TableColumn<>(this, new RawColumn<>("status"));
    }

    @Override
    public BindedColumn<java.time.Instant> createdAt() {
        return new TableColumn<>(this, new RawColumn<>("created_at"));
    }
}
