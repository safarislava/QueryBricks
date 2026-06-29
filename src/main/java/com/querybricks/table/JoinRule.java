package com.querybricks.table;

public interface JoinRule {
    String sql(Table right);
}
