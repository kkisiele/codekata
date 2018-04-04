package com.kkisiele.kata04.parser;

import java.util.ArrayList;
import java.util.List;

class TextFileHeaders {
    private List<TextFileHeader> headers = new ArrayList<>();

    public void add(TextFileHeader header) {
        headers.add(header);
        header.setParent(this);
    }

    public TextFileHeader get(String name) {
        for(TextFileHeader header : headers) {
            if(header.hasName(name)) {
                return header;
            }
        }
        throw new ParserException("Header [" + name + "] doesn't exist");
    }

    public int count() {
        return headers.size();
    }

    public TextFileHeader nextTo(TextFileHeader header) {
        int index = headers.indexOf(header);
        if(index + 1 < headers.size()) {
            return headers.get(index + 1);
        }
        return null;
    }

    public List<TextFileHeader> values() {
        return headers;
    }
}