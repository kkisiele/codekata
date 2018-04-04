package com.kkisiele.kata04.parser;

import java.util.ArrayList;
import java.util.List;

public class TextFileContent {
    private TextFileParser parser;

    public TextFileContent(TextFileParser parser) {
        this.parser = parser;
    }

    public List<TextFileRow> lines() {
        return parser.lines();
    }

    public int numberOfRows() {
        return lines().size();
    }

    public List<String> headerNames() {
        List<String> result = new ArrayList<>();
        for(TextFileHeader header : parser.headers()) {
            result.add(header.name());
        }
        return result;
    }
}