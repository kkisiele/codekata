package com.kkisiele.kata04.parser;

import java.util.LinkedList;
import java.util.List;

public interface ParserResult {
    TextFileHeader header();
    List<TextFileRow> dataRows();

    default List<String> headerNames() {
        List<String> result = new LinkedList<>();
        for(HeaderName header : header().values()) {
            result.add(header.name());
        }
        return result;
    }

    default int numberOfHeaders() {
        return header().count();
    }

    default int numberOfDataRows() {
        return dataRows().size();
    }
}