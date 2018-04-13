package com.kkisiele.kata04.parser;

import java.util.LinkedList;
import java.util.List;

public interface TextFileParserResult {
    List<TextFileHeader> headers();
    List<TextFileRow> dataRows();

    default List<String> headerNames() {
        List<String> result = new LinkedList<>();
        for(TextFileHeader header : headers()) {
            result.add(header.name());
        }
        return result;
    }

    default int numberOfHeaders() {
        return headers().size();
    }

    default int numberOfDataRows() {
        return dataRows().size();
    }
}