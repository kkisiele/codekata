package com.kkisiele.kata04.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class TextFileHeader {
    private List<HeaderName> names = new ArrayList<>();

    public TextFileHeader(String line) {
        parse(line);
    }

    private void parse(String line) {
        String[] headerNames = line.trim().split("\\s+");
        for(String headerName : headerNames) {
            int offset = line.indexOf(headerName);
            add(headerName, offset);
        }
    }

    private void add(String name, int offset) {
        names.add(new HeaderName(name, offset, this));
    }

    public HeaderName get(String name) {
        for(HeaderName header : names) {
            if(header.hasName(name)) {
                return header;
            }
        }
        throw new ParserException("Header [" + name + "] doesn't exist");
    }

    public int count() {
        return names.size();
    }

    HeaderName nextTo(HeaderName header) {
        int index = names.indexOf(header);
        if(index + 1 < names.size()) {
            return names.get(index + 1);
        }
        return null;
    }

    public List<HeaderName> values() {
        return Collections.unmodifiableList(names);
    }
}