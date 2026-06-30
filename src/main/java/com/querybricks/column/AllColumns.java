package com.querybricks.column;

public class AllColumns implements Columns {
    @Override
    public String sql() {
        return "*";
    }
}
