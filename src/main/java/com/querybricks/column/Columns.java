package com.querybricks.column;

import com.querybricks.QueryPart;

public interface Columns extends QueryPart {
    void processAll(ColumnsProcessor consumer);
}
