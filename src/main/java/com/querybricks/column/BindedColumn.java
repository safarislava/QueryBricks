package com.querybricks.column;

public interface BindedColumn<T> extends Column<T> {
    UnbindedColumn<T> unbinded();
}
