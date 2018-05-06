package com.kkisiele.datamunging.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TextFileHeader {
    private List<Column> columns = new ArrayList<>();

    public TextFileHeader(String line) {
        parse(line);
    }

    private void parse(String line) {
        String[] columnNames = line.trim().split("\\s+");
        for(String columnName : columnNames) {
            int offset = line.indexOf(columnName);
            addColumn(columnName, offset);
        }
    }

    private void addColumn(String name, int offset) {
        columns.add(new Column(name, offset, this));
    }

    public Column getColumn(String name) {
        for(Column column : columns) {
            if(column.hasName(name)) {
                return column;
            }
        }
        throw new ParserException("Column [" + name + "] doesn't exist");
    }

    public int numberOfColumns() {
        return columns.size();
    }

    Column nextTo(Column column) {
        int index = columns.indexOf(column);
        if(index + 1 < columns.size()) {
            return columns.get(index + 1);
        }
        return null;
    }

    public List<Column> columns() {
        return Collections.unmodifiableList(columns);
    }
}