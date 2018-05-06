package com.kkisiele.datamunging.parser;

import java.util.LinkedList;
import java.util.List;

public interface ParserResult {
    TextFileHeader header();
    List<TextFileRow> dataRows();

    default List<String> columnNames() {
        List<String> result = new LinkedList<>();
        for(Column header : header().columns()) {
            result.add(header.name());
        }
        return result;
    }

    default int numberOfColumns() {
        return header().numberOfColumns();
    }

    default int numberOfDataRows() {
        return dataRows().size();
    }
}