package com.querybricks.query;

import com.querybricks.column.ColumnsProcessor;

public interface ResultedQuery extends Query {
    void processColumns(ColumnsProcessor consumer);
}
