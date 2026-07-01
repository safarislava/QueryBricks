package com.querybricks.table;

import com.querybricks.QueryPart;

import com.querybricks.column.Column;
import java.util.List;

public interface Table extends QueryPart {
    List<Column<?>> columns();
}
